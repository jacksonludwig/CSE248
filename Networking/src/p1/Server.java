package p1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> nameList1 = new ArrayList<>();
        nameList1.add("Adam");
        nameList1.add("Bill");
        nameList1.add("Cathy");
        System.out.println(nameList1);
        System.out.println("Waiting for client accept");
        ServerSocket listener = new ServerSocket(9000);
        Socket socket = listener.accept();
        // do the following if listener is awoke
        ObjectOutputStream objectOutputStreamToClient =
                new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStreamFromClient =
                new ObjectInputStream(socket.getInputStream());

        System.out.println("Sending out an object...");
        objectOutputStreamToClient.writeObject(nameList1);
        objectOutputStreamToClient.flush(); // send it

        ArrayList<String> nameList2 = (ArrayList<String>) objectInputStreamFromClient.readObject();
        System.out.println(nameList2);
    }
}
