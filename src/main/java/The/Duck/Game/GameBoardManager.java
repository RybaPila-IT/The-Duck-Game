package The.Duck.Game;

import FXMLControlers.FirstBoardController;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class GameBoardManager {

    private static final String GameBoard1Path = "/TheShootingGameBoard1.fxml";

    private Stage CallingStage;
    private Stage GameBoardStage;
    private Scene GameBoardScene;

    /**
     * Associated directly with gameplay.
     */
    private boolean APressed, DPressed;
    private Player player;


    /**
     * Associated with animations.
     */
    private AnimationTimer timer;

    public GameBoardManager(Stage callingStage) {

        CallingStage = callingStage;
        GameBoardStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(GameBoard1Path));

        try {

            Parent root = loader.load();
            FirstBoardController controller = loader.getController();
            player = new Player(controller.getPlayer());
            GameBoardScene = new Scene(root);
            GameBoardStage.setScene(GameBoardScene);

            GameBoardStage.setOnCloseRequest(value -> CallingStage.show());

            GameBoardScene.setOnKeyPressed(value -> {
                if (value.getCode() == KeyCode.D)
                    DPressed = true;
                else if (value.getCode() == KeyCode.A)
                    APressed = true;

            });

            GameBoardScene.setOnKeyReleased(value -> {

                if (value.getCode() == KeyCode.D)
                    DPressed = false;
                else if (value.getCode() == KeyCode.A)
                    APressed = false;

            });


        } catch (IOException e) {
            System.err.println("GameBoardManager error. Unable to load FXML file with board layout");
            e.printStackTrace();
        }


    }

    public void StartGameBoard() {

        CallingStage.hide();
        CreateGameLoop();
        GameBoardStage.show();
    }

    private void CreateGameLoop() {

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
            }
        };

        timer.start();
    }

    private void movePlayer() {

        if (APressed)
            player.accelerate(false);
        else if (DPressed)
            player.accelerate(true);
        else
            player.slowDown();

        player.movePlayerModel();
    }


}
