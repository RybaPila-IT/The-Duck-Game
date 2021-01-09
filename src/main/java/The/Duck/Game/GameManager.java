package The.Duck.Game;

import javafx.animation.AnimationTimer;

public class GameManager {

    private final int DEAD_WAIT = 120;
    private final static double FRAME_DURATION = 1.0 / 90.0;
    private final static double GIGA = 1e9;

    private final AnimationTimer timer;

    private int deadWait;
    private double frameDuration;
    private long previousTic;

    //private final long[] frameTimes = new long[100];
    //private int frameTimeIndex = 0 ;
    //private boolean arrayFilled = false ;


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


                if (frameDuration <= 0) {

                    /*
                    long oldFrameTime = frameTimes[frameTimeIndex] ;
                    frameTimes[frameTimeIndex] = now ;
                    frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
                    if (frameTimeIndex == 0) {
                        arrayFilled = true ;
                    }
                    if (arrayFilled) {
                        long elapsedNanos = now - oldFrameTime ;
                        long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                        double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
                        System.out.println(String.format("Current frame rate: %.3f", frameRate));
                    } */

                    if (bot != null)
                        bot.controlOnTic();

                    firstPlayerManager.movePlayer();
                    secondPlayerManager.movePlayer();

                    if (BoardConstants.isExplosionOn())
                        ExplosionGenerator.getInstance().generateExplosion();

                    BoardConstants.getWeaponRespawn().spawnWeapon();
                    tickBoardElements();
                    frameDuration = FRAME_DURATION;
                }

                previousTic = now;

                if (firstPlayerManager.isPlayerDead() || secondPlayerManager.isPlayerDead())
                    if (deadWait++ >= DEAD_WAIT)
                        BoardConstants.getManager().loadNewMap();

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
