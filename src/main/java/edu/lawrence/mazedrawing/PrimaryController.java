package edu.lawrence.mazedrawing;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {
    @FXML
    private VBox vBox;
    private MazePane maze;
    
    @FXML
    private Label score;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maze = new MazePane(score);
        maze.setPrefSize(640, 440);
        vBox.getChildren().add(maze);
    }  
    
    public void focusMaze() {
        maze.requestFocus();
    }
}
