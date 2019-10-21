package billPugh;

public class Instructor {
    private static Instructor instructor;
    private static String name;

    private Instructor() {
        // prevent mult objects
    }

    private static class SingletonHelperClass {
        private static final Instructor _instructor = new Instructor();
    }

    public static Instructor makeTheInstructor() {
        return SingletonHelperClass._instructor;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Instructor.name = name;
    }
}
