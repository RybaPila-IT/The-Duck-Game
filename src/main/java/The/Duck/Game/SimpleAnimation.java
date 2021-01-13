package The.Duck.Game;

import java.util.List;

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
