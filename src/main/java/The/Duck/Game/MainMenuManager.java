package The.Duck.Game;

import FXMLControlers.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuManager {

    private static final String mainMenuFXMLPath = "/MainMenu.fxml";


    private Stage mainMenuStage;
    private Scene mainMenuScene;
    private MainMenuController controller;


    public MainMenuManager(Stage primaryStage) {

        mainMenuStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(mainMenuFXMLPath));

        try {

            Parent root = loader.load();
            controller = loader.getController();
            mainMenuScene = new Scene(root);
            mainMenuStage.setScene(mainMenuScene);
            mainMenuStage.show();

        } catch (IOException e) {

            System.err.println("Error has occured in MainMenuManager. Unable to load FXML file.");
            System.err.println(e.toString());

        }

    }


}
