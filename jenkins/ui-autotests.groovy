timeout(180){
	node('maven-slave') {
		wrap([$class: 'BuildUser']) {
			onwerInfo = """<b>Owner:</b> ${env.BUID_USER}"""
			currentBuild.description = summary
		}
		stage('Checkout') {
			checkout scm
		}
		stage('Running UI autotest') {
			sh "mvn clean test -D\"cucumber.filter.tags\"=${TAGS}"
		}
		stage('Publisher allure report'){
			allure([
					includeProperties: false,
					jdk: '',
					properties: [],
					reportBuildPolicy: 'ALWAYS',
					results: [[path: 'allure-results']]
			])
		}
		stage('Telegram notify') {
			shell{

				def bot_token = "5481726657:AAHTr-r4YRE05-emdesu1leB4VI2flzuY7o"
				def chat_id = "-1001503258272"

				def message = "+++++++++ UI tests report ++++++++++++++"
				message += "Build URL: ${env.JOB_URL}\n"
				message += "Report: \n"

				def report = readFile "${env.WORKSPACES}/allure-results/export/influxDb.txt"
				if(report =~ /.*Failed.*?\s+[1-9]+/){
					status =" *FAILED*"
				}

				for(def status: ["Passed", "Failed","Skipped"]){
					def matcher = report =~ /.*\$\{status}.*=[1-9]+/
					if(matcher.find()){
						message += "${status.toUpperCase()}: ${matcher[0][1]}"
					}
				}

				def url = "https://api.telegram.org/bot${bot_token}/sendMessage?chat_id=${chat_id}&text=${message}"

				if("${HTTP_PROXY}" != ""){
					def proxyUrl = new URL("${HTTP_PROXY}")
					def proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUrl.getHost(), proxyUrl.getPort()))
					urlConnection = new URL(url).openConnection(proxy) as HttpURLConnection
				} else {
					urlConnection = new URL(url).openConnection() as HttpURLConnection
				}

				urlConnection.setRequestMethod('GET')
				urlConnection.setDoOutput(true)

				def is = urlConnection.getInputStream() as InputStream
				echo is.getText('utf-8')
			}
		}
	}
}