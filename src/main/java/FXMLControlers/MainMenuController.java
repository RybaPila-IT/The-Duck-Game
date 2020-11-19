package FXMLControlers;

import The.Duck.Game.MainMenuManager;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenuController {

    private static final String GAME_NAME = "The Shooting Game";
    private static final int CREDITS_BEG_Y = -800;
    private static final int CREDITS_DEST_Y = 884;

    @FXML
    private Button NewGameB;
    @FXML
    private Button QuitB;
    @FXML
    private Button CreditsB;

    @FXML
    private AnchorPane CreditsPane;
    private boolean slidedIn = false;

    @FXML
    private AnchorPane MainPane;

    private Scene mainMenuScene;
    private Stage mainMenuStage;

    private MainMenuManager manager;


    @FXML
    public void initialize() {

        setCreditsButton();
        setQuitButton();
        setNewGameButton();

        mainMenuScene = new Scene(MainPane);
        mainMenuStage = new Stage();

        mainMenuStage.setScene(mainMenuScene);
        mainMenuStage.setTitle(GAME_NAME);
        mainMenuStage.setResizable(false);
    }

    public void setManager(MainMenuManager manager) {
        this.manager = manager;
    }

    private void moveCreditsPaneIn() {

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.7));
        transition.setNode(CreditsPane);
        transition.setToY(slidedIn ? CREDITS_BEG_Y : CREDITS_DEST_Y);
        slidedIn = !slidedIn;
        transition.play();

    }

    private void setCreditsButton() {
        CreditsB.setOnMouseClicked(value -> moveCreditsPaneIn());
    }

    private void setQuitButton() {
        QuitB.setOnMouseClicked(value -> mainMenuStage.close());
    }

    private void setNewGameButton() {
        NewGameB.setOnMouseClicked(value -> manager.startNewGame());
    }

    public void showMainMenu() {
        mainMenuStage.show();
    }

    public void hideMainMenu() {
        mainMenuStage.hide();
    }

}
