package lazy_Initialization;

public class Instructor {
    private static String name;
    private static Instructor instructor;

    private Instructor(String name) {
        this.name = name;
    }

    public static Instructor makeTheInstructor(String name) {
        if(instructor == null) { // prevent mult objects
            instructor = new Instructor(name);
        }
        return instructor;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        instructor.name = name;
    }

    public String toString() {
        return "Instructor: " + name;
    }
}
