package otus.java.qa.professional.homework.data;

public enum MainCourses {
    FAMOUS_COURSES("Популярные курсы"),
    SPECIALIZATIONS("Специализации"),
    RECOMMENDATIONS("Рекомендации для вас"),
    ;

    private String name;

    MainCourses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
