package p2;

import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HomeShop {
    private HomePane homePane;
    private BorderPane root;

    public HomeShop(BorderPane root) {
        homePane = new HomePane();
        this.root = root;
        root.setCenter(homePane.getGridPane());
        setCallback();
    }

    private void setCallback() {
        homePane.getSendToServer().setOnAction(e -> {
            Student student = new Student(homePane.getNameField().getText(),
                    Integer.parseInt(homePane.getIdField().getText()),
                    Integer.parseInt(homePane.getScoreField().getText()));

            try {
                Socket socket = new Socket("localhost", 9000);
                ObjectOutputStream objectOutputStreamToServer =
                        new ObjectOutputStream(socket.getOutputStream());
                System.out.println("The edited object is being sent to the server");
                objectOutputStreamToServer.writeObject(student);

                objectOutputStreamToServer.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        homePane.getGetDataFromServer().setOnAction(e -> {
            int score = 0;
            int total = 0;
            DataPackage dataPackage = null;
            ObjectInputStream objectInputStreamFromServer = null;

            try {
                Socket socket = new Socket("localhost", 9000);
                objectInputStreamFromServer =
                        new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                dataPackage = (DataPackage) objectInputStreamFromServer.readObject();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data retrieved from server");
            alert.setHeaderText("Data retrieval");
            alert.setContentText("Score: " + dataPackage.getScore() + "\n" + "Total Students: " + dataPackage.getTotal());
            alert.showAndWait();
        });
    }
}
