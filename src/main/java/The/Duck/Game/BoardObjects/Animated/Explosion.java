package The.Duck.Game.BoardObjects.Animated;

import FXMLControlers.BoardObjectsControllers.AnimationController;
import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.GameManagers.ExplosionGenerator;
import The.Duck.Game.Player.Player;

import java.util.Arrays;
import java.util.List;

public class Explosion extends SimpleAnimation {

    private static final List<String> explosionStyles = Arrays.asList(
            "explosion_1", "explosion_2", "explosion_3", "explosion_4", "explosion_5",
            "explosion_6", "explosion_7", "explosion_8", "explosion_9", "explosion_10",
            "explosion_11", "explosion_12", "explosion_13", "explosion_14", "explosion_15"
    );


    private static final double HEIGHT = 64;
    private static final double WIDTH = 64;
    private static final double S_COUNTER = 2;

    private final AnimationController controller;

    public Explosion(double x, double y) {

        super(new Rectangle(x, y, WIDTH, HEIGHT), explosionStyles, S_COUNTER);

        this.controller = new AnimationController(region);
    }

    /**
     * Procedure managing explosion on game tic.
     *
     * <p>
     * On game tic explosion will change it`s animation after
     * pre-defined amount of board tics.
     * If it has ended it will remove itself from scene
     * and inform explosion manager about it`s end.
     * </p>
     */
    @Override
    public void onTic() {

        String nextAnimation = getNextAnimation();

        if (nextAnimation != null)
            controller.setNewAnimation(nextAnimation);
        else {
            controller.remove();
            ExplosionGenerator.getInstance().explosionEnded();
        }
    }

    /**
     * On collision, explosion will automatically kill player.
     *
     * @param player Player which has collided with the explosion.
     */
    @Override
    public void onPlayerCollision(Player player) {
        while (!player.isDead())
            player.decreaseHealth();
    }

}
