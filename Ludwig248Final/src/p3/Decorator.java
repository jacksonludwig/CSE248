package p3;

public abstract class Decorator implements StudentInterface {
    private StudentInterface student;

    public void setStudent(StudentInterface student) {
        this.student = student;
    }

    public void doHomework() {
        if (student != null) {
            student.doHomework();
        }
    }
}
