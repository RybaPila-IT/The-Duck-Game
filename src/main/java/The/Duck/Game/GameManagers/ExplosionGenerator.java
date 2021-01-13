package The.Duck.Game.GameManagers;

import The.Duck.Game.BoardObjects.Animated.Explosion;

public class ExplosionGenerator {

    private static final int EXPLOSION_ALLOWED = 1;

    private static ExplosionGenerator instance;

    private int explosions;

    private ExplosionGenerator() {
        explosions = 0;
    }

    public static ExplosionGenerator getInstance() {

        if (instance == null)
            instance = new ExplosionGenerator();

        return instance;
    }

    public void generateExplosion() {

        if (explosions < EXPLOSION_ALLOWED) {
            BoardElements.getInstance().addBoardObject(
                    new Explosion(Math.random() * BoardConstants.getInstance().getBoardWidth(),
                            Math.random() * BoardConstants.getInstance().getBoardHeight()));
            explosions++;
        }

    }

    public void explosionEnded() {
        explosions--;
    }

    public void clear() {
        explosions = 0;
    }

}
