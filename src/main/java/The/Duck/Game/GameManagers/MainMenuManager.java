package The.Duck.Game.GameManagers;

import FXMLControlers.BoardsControllers.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class MainMenuManager {

    private static final String MAIN_MENU_FXML = "/FXML/MainMenu.fxml";

    private boolean isCreditsSlided;
    private boolean isHowToPlaySlided;

    private MainMenuController controller;

    public MainMenuManager() {

        isCreditsSlided = false;
        isHowToPlaySlided = false;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_MENU_FXML));

        try {
            // Needed for proper initialization loader and controller object.
            Parent root = loader.load();
            controller = loader.getController();
            controller.setManager(this);

        } catch (IOException e) {
            System.err.println("Error has occured in MainMenuManager. Unable to load FXML file with main menu.");
            System.err.println("Please, check whether the file " + MAIN_MENU_FXML + " exists or is corrupted.");
        }

    }

    public void showMainMenu() {
        controller.showMainMenu();
    }

    public void startNewGame(boolean single) {
        controller.hideMainMenu();
        GameBoardManager boardManager = new GameBoardManager(this, single);
    }

    public void decideCreditsSliding() {

        if (isCreditsSlided)
            slideCreditsOut();
        else
            slideCreditsIn();

    }

    public void decideHowToPlaySliding() {

        if (isHowToPlaySlided)
            slideHowToPlayOut();
        else
            slideHowToPlayIn();

    }

    private void slideHowToPlayIn() {
        if (isCreditsSlided)
            slideCreditsOut();

        isHowToPlaySlided = true;
        controller.slideHowToPlayPaneIn();
    }

    private void slideHowToPlayOut() {
        isHowToPlaySlided = false;
        controller.slideHowToPlayPaneOut();
    }

    private void slideCreditsIn() {

        if (isHowToPlaySlided)
            slideHowToPlayOut();

        isCreditsSlided = true;
        controller.slideCreditsPaneIn();

    }

    private void slideCreditsOut() {
        isCreditsSlided = false;
        controller.slideCreditsPaneOut();
    }

}
