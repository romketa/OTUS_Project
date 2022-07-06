package otus.ru.java.qa.professional.homework.data;

public enum LessonType {
    COURSES("Курсы"),
    SPECIALIZATIONS("Специализация")
    ;
    private String name;

    public String getName() {
        return name;
    }

    LessonType(String name) {
        this.name = name;
    }
}