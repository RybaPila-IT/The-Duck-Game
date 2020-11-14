package The.Duck.Game;

import FXMLControlers.FirstBoardController;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;

public class GameBoardManager {

    private static final String GameBoard1Path = "/TheShootingGameBoard1.fxml";

    private Stage CallingStage;
    private Stage GameBoardStage;
    private Scene GameBoardScene;

    /**
     * Associated directly with gameplay.
     */
    private ButtonInfo buttonInfo;
    private Player player;

    /**
     * Associated with animations.
     */
    private AnimationTimer timer;


    private ArrayList<Obstacle> obstacles;


    private void setGameBoardStageListeners() {
        GameBoardStage.setOnCloseRequest(value -> {
            CallingStage.show();
            GameBoardStage.close();
        });
    }

    private void setGameBoardSceneListeners() {

        GameBoardScene.setOnKeyPressed(value -> {
            if (value.getCode() == KeyCode.D)
                buttonInfo.setDPressed(true);
            else if (value.getCode() == KeyCode.A)
                buttonInfo.setAPressed(true);
            else if (value.getCode() == KeyCode.SPACE)
                buttonInfo.setSpacePressed(true);

        });

        GameBoardScene.setOnKeyReleased(value -> {

            if (value.getCode() == KeyCode.D)
                buttonInfo.setDPressed(false);
            else if (value.getCode() == KeyCode.A)
                buttonInfo.setAPressed(false);
            else if (value.getCode() == KeyCode.SPACE)
                buttonInfo.setSpacePressed(false);

        });

    }


    public GameBoardManager(Stage callingStage) {

        CallingStage = callingStage;
        GameBoardStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(GameBoard1Path));

        try {

            /** Preprocessing of FXML board. */
            Parent root = loader.load();
            FirstBoardController controller = loader.getController();

            /** Setting private fields. */
            player = new Player(controller.getPlayer(), controller.getObstaclesList());
            obstacles = controller.getObstaclesList();
            buttonInfo = new ButtonInfo();
            GameBoardScene = new Scene(root);
            GameBoardStage.setScene(GameBoardScene);

            /** Setting Listeners. */
            setGameBoardStageListeners();
            setGameBoardSceneListeners();


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

        if (buttonInfo.isAPressed())
            player.accelerate(false);
        else if (buttonInfo.isDPressed())
            player.accelerate(true);
        else if (buttonInfo.isSpacePressed())
            player.jump();

        player.movePlayerModel();

    }


}
