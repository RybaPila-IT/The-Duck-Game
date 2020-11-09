package FXMLControlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button NewGameB;
    @FXML
    private Button QuitB;

    public MainMenuController() {
        System.out.println("Main Menu Controller created");
    }

    @FXML
    public void initialize() {
        NewGameB.hoverProperty().addListener(event -> {

            if (NewGameB.hoverProperty().getValue()) {
                System.out.println("Entering");
            } else {
                System.out.println("Escaping");
            }
            //NewGameB.setStyle( "-fx-background-color: red;" + "-fx-text-fill: white");

        });

        NewGameB.setOnMouseClicked(value -> {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DuckGameBoard1.fxml"));
            try {
                Parent root = loader.load();
                Stage stageSecond = new Stage();
                stageSecond.setScene(new Scene(root));
                stageSecond.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        QuitB.setOnMouseClicked(value -> {

            Node n = (Node) value.getSource();
            Stage primaryStage = (Stage) n.getScene().getWindow();
            primaryStage.close();

        });
    }

    @FXML
    public void onActionEvent() {
    }

    @FXML
    public void onMouseEntered() {
        //System.out.println("Entered button area");

    }
}
