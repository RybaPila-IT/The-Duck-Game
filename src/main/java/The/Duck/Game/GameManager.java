package The.Duck.Game;

import javafx.animation.AnimationTimer;

public class GameManager {

    private final static double FRAME_DURATION = 1.0 / 90.0;
    private final static double GIGA = 1e9;

    private final AnimationTimer timer;

    private double frameDuration;
    private long previousTic;

    //private final long[] frameTimes = new long[100];
    //private int frameTimeIndex = 0 ;
    //private boolean arrayFilled = false ;


    public GameManager(PlayerManager firstPlayerManager, PlayerManager secondPlayerManager) {

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


                    firstPlayerManager.movePlayer();
                    secondPlayerManager.movePlayer();
                    // ExplosionGenerator.getInstance().generateExplosion();
                    tickBoardElements();
                    frameDuration = FRAME_DURATION;
                }

                previousTic = now;

                if (firstPlayerManager.isPlayerDead() || secondPlayerManager.isPlayerDead())
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
