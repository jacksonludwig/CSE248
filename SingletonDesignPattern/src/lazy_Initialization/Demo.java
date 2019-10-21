package lazy_Initialization;

public class Demo {
    public static void main(String[] args) {
        Instructor i1 = Instructor.makeTheInstructor("Bill");
        Instructor i2 = Instructor.makeTheInstructor("Adam");
        // need setter to change name for single object
        System.out.println(i1 + "\n" + i2);
    }
}
