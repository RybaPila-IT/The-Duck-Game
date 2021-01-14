package The.Duck.Game.BoardObjects.Animated;

import The.Duck.Game.BoardObjects.RegularBoardObjects.BoardObject;
import The.Duck.Game.BoardObjects.Utility.Rectangle;

import java.util.List;

/**
 * Abstract class representing animated board objects.
 *
 * <p>
 * By animated board objects one should consider objects
 * which has specific styles which should be displayed
 * one after another after some time interval.
 * This class implements basic animated object utility
 * procedures and functions.
 * </p>
 */
public abstract class SimpleAnimation extends BoardObject {

    private final List<String> animations;
    private final double sCounter;

    private int sAnimationCounter;
    private int wAnimationCounter;

    protected SimpleAnimation(Rectangle r, List<String> animations, double sC) {

        super(r);

        this.animations = animations;
        this.sCounter = sC;
        this.sAnimationCounter = 0;
        this.wAnimationCounter = 0;
    }

    protected boolean isAnimationValid() {
        return wAnimationCounter < animations.size();
    }


    protected String getNextAnimation() {

        animationOnTic();

        return isAnimationValid() ?
                animations.get(wAnimationCounter) :
                null;
    }

    private void animationOnTic() {

        if (isAnimationValid()) {

            if (sAnimationCounter++ >= sCounter) {
                sAnimationCounter = 0;
                wAnimationCounter++;
            }

        }

    }

    @Override
    public boolean isValid() {
        return isAnimationValid();
    }

}
