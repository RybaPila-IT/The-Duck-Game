package The.Duck.Game.Bots;

/**
 * Bot interface which will simulate second player.
 */
public interface Bot {

    /**
     * Procedure ordering bot what to do.
     *
     * <p>
     * This procedure will order bots
     * all necessary tasks which he should perform in
     * current game tic.
     * </p>
     */
    void controlOnTic();
}
