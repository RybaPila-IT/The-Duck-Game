package The.Duck.Game;

import FXMLControlers.AnimationController;

public class Explosion extends SimpleAnimation {

    private static final double HEIGHT = 64;
    private static final double WIDTH = 64;
    private static final double S_COUNTER = 2;

    private final AnimationController controller;

    public Explosion(double x, double y) {

        super(new Rectangle(x, y, WIDTH, HEIGHT), BoardConstants.getExplosionStyles(), S_COUNTER);

        this.controller = new AnimationController(region);
    }

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

    @Override
    public void onPlayerCollision(Player player) {
        while (!player.isDead())
            player.decreaseHealth();
    }

}
