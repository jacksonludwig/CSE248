package billPugh;

public class Demo {
    public static void main(String[] args) {
        Instructor i1 = Instructor.makeTheInstructor();
        Instructor.setName("Adam");
        Instructor i2 = Instructor.makeTheInstructor();
        System.out.println(Instructor.getName());
        System.out.println(i1 == i2);
    }
}
