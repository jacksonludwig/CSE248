package eager_Intitialization;

public class Demo {

    public static void main(String[] args) {
        Instructor i1 = Instructor.makeTheInstructor("Bill");
        System.out.println(Instructor.getName());

        Instructor i2 = Instructor.makeTheInstructor("Adam");
        System.out.println(Instructor.getName());

        System.out.println(i1 == i2);

    }
}
