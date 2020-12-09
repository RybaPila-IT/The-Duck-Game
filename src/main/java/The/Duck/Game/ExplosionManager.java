package The.Duck.Game;

public class ExplosionManager {

    private int explosions;

    private static ExplosionManager instance;

    private ExplosionManager() {
        explosions = 0;
    }

    public static ExplosionManager getInstance() {

        if (instance == null)
            instance = new ExplosionManager();

        return instance;
    }

    public void generateExplosion() {

        if (explosions == 0) {
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
