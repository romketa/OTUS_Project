package otus.ru.java.qa.professional.homework.data;

public enum BrowserData {
    CHROME("chrome"),
    OPERA("opera"),
    FIREFOX("firefox");

    private final String name;

    public String getName() {
        return name;
    }

    BrowserData(String name) {
        this.name = name;
    }
}
