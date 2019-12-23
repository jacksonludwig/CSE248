package p2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    private BorderPane root;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        HomeShop homeShop = new HomeShop(root);

        Scene scene = new Scene(root, 800, 330);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}