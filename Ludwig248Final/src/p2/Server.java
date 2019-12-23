package p2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ArrayList<Student> studentList;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        studentList = new ArrayList<>();

        System.out.println("Waiting for client accept");
        ServerSocket listener = new ServerSocket(9000);
        Socket socket = listener.accept();
        // do the following if listener is awoke
        ObjectOutputStream objectOutputStreamToClient =
                new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStreamFromClient =
                new ObjectInputStream(socket.getInputStream());

        Student studentFromClient = (Student) objectInputStreamFromClient.readObject();
        System.out.println("The edited object was received");
        System.out.println(studentFromClient);

        DataPackage dataPackage = new DataPackage(getAverageScore(), getNumberOfStudents());
        System.out.println("Sending out an object...");
        objectOutputStreamToClient.writeObject(dataPackage);
        objectOutputStreamToClient.flush();
    }

    private static int getNumberOfStudents() {
        return studentList.size();
    }

    private static int getAverageScore() {
        int total = 0;
        for (Student student : studentList) {
            total += student.getScore();
        }
        if (total != 0) {
            return total / getNumberOfStudents();
        } else {
            return 0;
        }
    }
}
