package eager_Intitialization;

public class Instructor {
    private static String name;
    private static Instructor _instructor = new Instructor();

    private Instructor() {
        // prevent public constructor
    }

    public static Instructor makeTheInstructor(String name) {
        Instructor.setName(name);
        return _instructor;
    }

    public static void setName(String name) {
        Instructor.name = name;
    }

    public static String getName() {
        return Instructor.name;
    }
}
