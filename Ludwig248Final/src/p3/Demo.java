package p3;

public class Demo {
    public static void main(String[] args) {
        Student student = new Student();
        student.doHomework();

        System.out.println("---------------");

        StudentDecorator studentDecorator = new StudentDecorator();
        studentDecorator.setStudent(student);
        studentDecorator.doMoreWork();
    }

}
