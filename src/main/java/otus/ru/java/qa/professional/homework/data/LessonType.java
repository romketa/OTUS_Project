package otus.ru.java.qa.professional.homework.data;

import com.google.inject.Inject;

public enum LessonType {
    COURSES("Курсы"),
    SPECIALIZATIONS("Специализация")
    ;
    private final String name;

    public String getName() {
        return name;
    }

    @Inject
    LessonType(String name) {
        this.name = name;
    }
}