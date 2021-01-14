package The.Duck.Game.GameManagers;

import The.Duck.Game.Bots.Bot;
import The.Duck.Game.Player.PlayerManager;
import javafx.animation.AnimationTimer;

/**
 * Class creating game loop.
 *
 * <p>
 * This class can be considered as the "heart" of the game.
 * It creates and manages game loop.
 * It tries to maintain stable FPS rate (which is about 60)
 * so it will slow down faster renders.
 * </p>
 */
public class GameManager {

    private final int DEAD_WAIT = 120;
    private final static double FRAME_DURATION = 1.0 / 90.0;
    private final static double GIGA = 1e9;

    private final AnimationTimer timer;

    private int deadWait;
    private double frameDuration;
    private long previousTic;

    /**
     * Constructor of the game manager.
     *
     * @param firstPlayerManager  Manager of the first player.
     * @param secondPlayerManager Manager of the second player.
     * @param bot                 Bot which will give input for Second Manager (null
     *                            if playing PvP).
     */
    public GameManager(PlayerManager firstPlayerManager, PlayerManager secondPlayerManager, Bot bot) {

        deadWait = 0;
        frameDuration = 0.0;
        previousTic = 0;

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (previousTic == 0.0)
                    previousTic = now;

                double duration = (now - previousTic) / GIGA;
                frameDuration -= duration;

                /*
                    Attempt to maintain moreover
                    stable rendering rate. If
                    frame has been presented for sufficient
                    time it will be eventually replaced
                    by the new one.
                 */
                if (frameDuration <= 0) {

                    if (bot != null)
                        bot.controlOnTic();

                    firstPlayerManager.movePlayer();
                    secondPlayerManager.movePlayer();

                    if (BoardConstants.getInstance().isExplosionOn())
                        ExplosionGenerator.getInstance().generateExplosion();

                    BoardConstants.getInstance().getWeaponRespawn().spawnWeapon();
                    tickBoardElements();
                    frameDuration = FRAME_DURATION;
                }

                previousTic = now;

                if (firstPlayerManager.isPlayerDead() || secondPlayerManager.isPlayerDead())
                    if (deadWait++ >= DEAD_WAIT)
                        BoardConstants.getInstance().getManager().loadNewMap();

            }
        };

    }

    public void startGameLoop() {
        timer.start();
    }

    public void stopGameLoop() {
        timer.stop();
    }

    private void tickBoardElements() {
        BoardElements.getInstance().onTic();
    }

}
