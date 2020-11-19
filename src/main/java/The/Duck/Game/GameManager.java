package The.Duck.Game;

import javafx.animation.AnimationTimer;

public class GameManager {


    private final PlayerManager playerManager;
    private AnimationTimer timer;

    public GameManager(PlayerManager playerManager) {

        this.playerManager = playerManager;

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerManager.movePlayer();
            }
        };

    }

    public void startGameLoop() {
        timer.start();
    }

    public void stopGameLoop() {
        timer.stop();
    }

}
