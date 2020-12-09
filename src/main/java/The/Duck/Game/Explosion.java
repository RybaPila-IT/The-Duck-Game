package The.Duck.Game;

import FXMLControlers.ExplosionController;

public class Explosion extends BoardObject {

    private static final double HEIGHT = 64;
    private static final double WIDTH = 64;
    private static final double S_COUNTER = 90;
    private static final double W_COUNTER = 18;

    private int singleExplosionCounter;
    private int wholeExplosionCounter;

    private ExplosionController controller;

    public Explosion(double x, double y) {

        super(new Rectangle(x, y, WIDTH, HEIGHT));

        this.singleExplosionCounter = 0;
        this.wholeExplosionCounter = 0;
        this.controller = new ExplosionController(region);
    }

    @Override
    public void onTic() {

        if (singleExplosionCounter++ > S_COUNTER && wholeExplosionCounter <= W_COUNTER) {
            controller.nextAnimation();
            singleExplosionCounter = 0;
            wholeExplosionCounter++;
        }

        if (!isObjectValid()) {
            ExplosionManager.getInstance().explosionEnded();
            controller.removeExplosion();
        }

    }

    @Override
    public void onPlayerCollision(Player player) {
        System.out.println("Explosion and player collision");
    }

    @Override
    public boolean isObjectValid() {
        return wholeExplosionCounter <= W_COUNTER;
    }
}
