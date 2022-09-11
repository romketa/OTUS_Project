Созданы две сети, для selenoid_1, selenoid_2

docker run -d --name selenoid_1 -p 4445:4444 --net=selenoid_1 -v //var/run/docker.sock:/var/run/docker.sock -v D:\Repo\Selenoid_practice:/etc/selenoid:ro aerokube/selenoid -limit=12 -capture-driver-logs -max-timeout=0h30m0s -container-network=selenoid_1

docker run -d --name selenoid_2 -p 4446:4444 --net=selenoid_2 -v //var/run/docker.sock:/var/run/docker.sock -v D:\Repo\Selenoid_practice:/etc/selenoid:ro aerokube/selenoid -limit=12 -capture-driver-logs -max-timeout=0h30m0s -container-network=selenoid_2

----

пусть бинарники для ggr, ggr_ui, selenoid_ui находятся в папке \Selenoid_practice

Для запуска нужно выполнить следующие команды в консоле:

.\ggr_windows_386 -guests-allowed -guests-quota "test" -verbose -quotaDir D:\Repo\Selenoid_practice\ggr_config\quota

.\ggr-ui_windows_386.exe -quota-dir D:\Repo\Selenoid_practice\ggr_config\quota

.\selenoid-ui_windows_386 --selenoid-uri http://127.0.0.1:8888 -listen ":8090" -webdriver-uri "http://127.0.0.1:4444" -allowed-origin "*"

-----
Для прокси-сервера есть папочка \nginx-1.22.0, в папке configs лежит selenoid.conf, а в nginx.conf указан include selenoid.conf 

Запустить сервер nginx -start nginx

Проверить что процесс запущен - tasklist /fi "imagename eq nginx.exe"

Остановить сервер - .\nginx -s stop