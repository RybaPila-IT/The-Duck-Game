package The.Duck.Game;

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
                    new Explosion(Math.random() * BoardConstants.getBoardWidth(),
                            Math.random() * BoardConstants.getBoardHeight()));
            explosions++;
        }

    }

    public void explosionEnded() {
        explosions--;
    }

}
