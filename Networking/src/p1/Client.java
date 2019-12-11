package p1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> nameList1 = new ArrayList<>();

        // socket = ip + program port
        // created server socket
        Socket socket = new Socket("localhost", 9000);
        ObjectOutputStream objectOutputStreamToServer =
                new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStreamFromServer =
                new ObjectInputStream(socket.getInputStream());

        nameList1 = (ArrayList<String>) objectInputStreamFromServer.readObject();
        nameList1.add("Carl");
        objectOutputStreamToServer.writeObject(nameList1);
        System.out.println("The edited object was sent to the server");
    }
}
