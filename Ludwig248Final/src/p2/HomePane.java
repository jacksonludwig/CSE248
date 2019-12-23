package p2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomePane {
    private GridPane gridPane;

    private Button sendToServer;
    private Button getDataFromServer;

    private TextField nameField;
    private TextField idField;
    private TextField scoreField;

    private HBox buttonBox;
    private VBox vBox;

    public HomePane() {
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        sendToServer = new Button("Send New Student\nTo Server");
        sendToServer.setPrefSize(150, 50);

        getDataFromServer = new Button("Retrieve Data\nFrom Server");
        getDataFromServer.setPrefSize(150, 50);

        nameField = new TextField();
        nameField.setText("Name");
        idField = new TextField();
        idField.setText("ID");
        scoreField = new TextField();
        scoreField.setText("Score");

        buttonBox = new HBox(40);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(nameField, idField, scoreField, sendToServer, getDataFromServer);

        vBox = new VBox(40);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(buttonBox);

        gridPane.add(vBox, 0, 0);
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public Button getSendToServer() {
        return sendToServer;
    }

    public void setSendToServer(Button sendToServer) {
        this.sendToServer = sendToServer;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getIdField() {
        return idField;
    }

    public void setIdField(TextField idField) {
        this.idField = idField;
    }

    public TextField getScoreField() {
        return scoreField;
    }

    public void setScoreField(TextField scoreField) {
        this.scoreField = scoreField;
    }

    public HBox getButtonBox() {
        return buttonBox;
    }

    public void setButtonBox(HBox buttonBox) {
        this.buttonBox = buttonBox;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Button getGetDataFromServer() {
        return getDataFromServer;
    }

    public void setGetDataFromServer(Button getDataFromServer) {
        this.getDataFromServer = getDataFromServer;
    }
}
