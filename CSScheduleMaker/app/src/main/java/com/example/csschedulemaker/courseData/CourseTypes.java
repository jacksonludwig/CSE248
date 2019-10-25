package com.example.csschedulemaker.courseData;

public enum CourseTypes {

    COMPSCI("Computer Science"),
    ENGLISH("English"),
    GYM("Physical Education"),
    HISTORY("History"),
    HUMANITY("Humanities"),
    LANGUAGE("Second Language"),
    MATH("Mathematics"),
    SCIENCE("Sciences"),
    SOCIOLOGY("Sociology");

    private String displayName;

    CourseTypes(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
