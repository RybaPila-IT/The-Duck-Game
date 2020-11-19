package The.Duck.Game;

import FXMLControlers.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class MainMenuManager {

    private static final String MAIN_MENU_FXML = "/MainMenu.fxml";

    private MainMenuController controller;
    private GameBoardManager boardManager;


    public MainMenuManager() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_MENU_FXML));

        try {
            // Needed for proper initialization loader and controller object.
            Parent root = loader.load();
            controller = loader.getController();
            controller.setManager(this);

        } catch (IOException e) {

            System.err.println("Error has occured in MainMenuManager. Unable to load FXML file.");
            System.err.println(e.toString());

        }

    }

    public void showMainMenu() {
        controller.showMainMenu();
    }

    public void startNewGame() {
        controller.hideMainMenu();
        boardManager = new GameBoardManager(this);
        boardManager.StartGameBoard();
    }


}
