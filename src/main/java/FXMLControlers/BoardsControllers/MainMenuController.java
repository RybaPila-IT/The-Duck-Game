package FXMLControlers.BoardsControllers;

import The.Duck.Game.GameManagers.MainMenuManager;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class representing the Controller of the Main Menu.
 *
 * <p>
 * Class manages TranslateTransition objects in order
 * to achieve smooth sliding in/out panes with
 * How To Play instruction and credits info.
 * It also creates and automatically starts new game
 * when pressing the single/multi player start game button.
 * </p>
 */
public class MainMenuController {

    private static final String GAME_NAME = "The Shooting Game";
    private static final String ICON_PATH = "/Graphics/weapon/weapon_2_face_left.png";

    private final static double DURATION = 1.0;
    private static final int CREDITS_BEG_Y = -800;
    private static final int CREDITS_DEST_Y = 884;
    private static final int HOW_TO_PLAY_BEG_Y = -1200;
    private static final int HOW_TO_PLAY_DEST_Y = 1284;

    @FXML
    private Button NewGameBMulti;
    @FXML
    private Button NewGameBSingle;
    @FXML
    private Button QuitB;
    @FXML
    private Button CreditsB;
    @FXML
    private Button HowToPlayB;

    @FXML
    private AnchorPane CreditsPane;
    @FXML
    private AnchorPane HowToPlayPane;
    @FXML
    private AnchorPane MainPane;

    private Stage mainMenuStage;

    private MainMenuManager manager;


    @FXML
    public void initialize() {

        setCreditsButton();
        setQuitButton();
        setNewGameButtonSingle();
        setNewGameButtonMulti();
        setHowToPlayButton();

        mainMenuStage = new Stage();

        mainMenuStage.setScene(new Scene(MainPane));
        mainMenuStage.setTitle(GAME_NAME);
        mainMenuStage.setResizable(false);

        setIcon();
    }

    public void setManager(MainMenuManager manager) {
        this.manager = manager;
    }


    private void setIcon() {
        mainMenuStage.getIcons().add(new Image(ICON_PATH));
    }

    private TranslateTransition getTransition(Node node, int dest) {

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(DURATION));
        transition.setNode(node);
        transition.setToY(dest);

        return transition;
    }

    public void slideCreditsPaneIn() {
        TranslateTransition transition = getTransition(CreditsPane, CREDITS_DEST_Y);
        transition.play();
    }

    public void slideCreditsPaneOut() {
        TranslateTransition transition = getTransition(CreditsPane, CREDITS_BEG_Y);
        transition.play();
    }

    public void slideHowToPlayPaneIn() {
        TranslateTransition transition = getTransition(HowToPlayPane, HOW_TO_PLAY_DEST_Y);
        transition.play();
    }

    public void slideHowToPlayPaneOut() {
        TranslateTransition transition = getTransition(HowToPlayPane, HOW_TO_PLAY_BEG_Y);
        transition.play();
    }


    private void setCreditsButton() {
        CreditsB.setOnMouseClicked(value -> manager.decideCreditsSliding());
    }

    private void setQuitButton() {
        QuitB.setOnMouseClicked(value -> mainMenuStage.close());
    }

    private void setNewGameButtonSingle() {
        NewGameBSingle.setOnMouseClicked(value -> manager.startNewGame(true));
    }

    private void setNewGameButtonMulti() {
        NewGameBMulti.setOnMouseClicked(value -> manager.startNewGame(false));
    }

    private void setHowToPlayButton() {
        HowToPlayB.setOnMouseClicked(value -> manager.decideHowToPlaySliding());
    }

    public void showMainMenu() {
        mainMenuStage.show();
    }

    public void hideMainMenu() {
        mainMenuStage.hide();
    }

}
