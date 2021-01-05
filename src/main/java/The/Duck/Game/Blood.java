package The.Duck.Game;

import FXMLControlers.AnimationController;

public class Blood extends SimpleAnimation {

    private static final double WIDTH = 40;
    private static final double HEIGHT = 40;
    private static final double S_COUNTER = 3;

    private final AnimationController controller;

    public Blood(double x, double y) {

        super(new Rectangle(x, y, WIDTH, HEIGHT), BoardConstants.getBloodStyles(), S_COUNTER);

        this.controller = new AnimationController(region);
    }


    @Override
    public void onTic() {

        String nextAnimation = getNextAnimation();

        if (nextAnimation != null)
            controller.setNewAnimation(nextAnimation);
        else
            controller.remove();

    }

    @Override
    public void onPlayerCollision(Player player) {

    }

    @Override
    public void onBulletCollision(Bullet bullet) {

    }

}
