package otus.ru.java.qa.professional.homework.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import otus.ru.java.qa.professional.homework.annotations.PagePath;
import otus.ru.java.qa.professional.homework.annotations.navigation.Url;
import otus.ru.java.qa.professional.homework.annotations.navigation.UrlTemplate;
import otus.ru.java.qa.professional.homework.exceptions.DataUrlNotValid;
import otus.ru.java.qa.professional.homework.exceptions.UrlTemplateNotValid;
import otus.ru.java.qa.professional.homework.support.GuiceScoped;

public abstract class BasePage<T> {

    /*public static void openSite(WebDriver driver) {
        driver.get(System.getProperty("webdriver.base.url"));
    }*/

    protected GuiceScoped guiceScoped;

    public BasePage(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
        PageFactory.initElements(guiceScoped.driver, this);
    }

    private String getPath() {
        Class<?> clazz = this.getClass();
        if(clazz.isAnnotationPresent(PagePath.class)) {
            PagePath pagePath = clazz.getAnnotation(PagePath.class);
            return pagePath.value().replaceAll("/+$", "");
        }

        return "";
    }

    private String getPageUrlTemplate(String name) throws UrlTemplateNotValid {
        Class<?> clazz = this.getClass();
        if(clazz.isAnnotationPresent(Url.class)) {
            Url urlTemplates = clazz.getAnnotation(Url.class);
            UrlTemplate[] templates = urlTemplates.value();
            for(UrlTemplate urlTemplate: templates) {
                if(urlTemplate.name().equals(name)) {
                    return urlTemplate.template();
                }
            }
            throw new UrlTemplateNotValid();
        }
        return "";
    }

    public T open(String name, String... values) throws Exception {
        if(values.length == 0) {
            throw new DataUrlNotValid();
        }
        String template = this.getPageUrlTemplate(name);
        String pathFromTemplate = "";
        for(int i =0; i < values.length; i++) {
            pathFromTemplate = template.replace(String.format("{%s}", i + 1), values[i]);
        }

        String hostname = System.getProperty("webdriver.base.url");
        hostname = hostname.replaceAll("/+$", "");

        if(!this.getPath().isEmpty()) {
            guiceScoped.driver.get(hostname + this.getPath() + "/" + pathFromTemplate);
        } else {
            guiceScoped.driver.get(hostname + "/" + pathFromTemplate);
        }

        return (T)this;
    }

    public T open() {
        guiceScoped.driver.get(System.getProperty("webdriver.base.url"));

        return (T) this;
    }

}
