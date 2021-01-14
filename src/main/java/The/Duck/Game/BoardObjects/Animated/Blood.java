package The.Duck.Game.BoardObjects.Animated;

import FXMLControlers.BoardObjectsControllers.AnimationController;
import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.Player.Player;

import java.util.Arrays;
import java.util.List;

public class Blood extends SimpleAnimation {

    private static final List<String> bloodStyles = Arrays.asList(
            "blood_1", "blood_2", "blood_3"
    );

    private static final double WIDTH = 40;
    private static final double HEIGHT = 40;
    private static final double S_COUNTER = 3;

    private final AnimationController controller;

    public Blood(double x, double y) {

        super(new Rectangle(x, y, WIDTH, HEIGHT), bloodStyles, S_COUNTER);

        this.controller = new AnimationController(region);
    }

    /**
     * Blood will simply change it`s picture.
     */
    @Override
    public void onTic() {

        String nextAnimation = getNextAnimation();

        if (nextAnimation != null)
            controller.setNewAnimation(nextAnimation);
        else
            controller.remove();

    }

    /**
     * Empty function.
     *
     * @param player Player.
     */
    @Override
    public void onPlayerCollision(Player player) {
    }

}
