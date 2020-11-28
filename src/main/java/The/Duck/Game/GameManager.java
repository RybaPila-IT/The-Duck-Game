package The.Duck.Game;

import javafx.animation.AnimationTimer;

public class GameManager {


    private PlayerManager playerManager;
    private final AnimationTimer timer;

    public GameManager(PlayerManager playerManager) {

        this.playerManager = playerManager;

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerManager.movePlayer();
                moveBullets();
            }
        };

    }

    private void moveBullets() {
        BoardBullets.getInstance().moveBullets();
    }

    public void startGameLoop() {
        timer.start();
    }

    public void stopGameLoop() {
        timer.stop();
    }

}
