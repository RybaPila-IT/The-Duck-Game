package The.Duck.Game;

import javafx.animation.AnimationTimer;

public class GameManager {

    private final AnimationTimer timer;

    public GameManager(PlayerManager playerManager) {

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerManager.movePlayer();
                ExplosionManager.getInstance().generateExplosion();
                tickBoardElements();
            }
        };

    }

    private void tickBoardElements() {
        BoardElements.getInstance().onTic();
    }

    public void startGameLoop() {
        timer.start();
    }

    public void stopGameLoop() {
        timer.stop();
    }

}
