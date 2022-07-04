package otus.java.qa.professional.homework.data;

public enum LessonType {
    COURSES("Курсы"),
    SPECIALIZATIONS("Специализации"),
    RECOMMENDATIONS("Рекомендации для вас"),
    ;

    private String name;

    LessonType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
