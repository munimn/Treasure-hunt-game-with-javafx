package edu.lawrence.mazedrawing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Scene scene = new Scene(loader.load(), 640, 480);
        PrimaryController controller = (PrimaryController) loader.getController();
        controller.focusMaze();
        stage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'sans-serif'");
        stage.setTitle("Maze Drawing");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}