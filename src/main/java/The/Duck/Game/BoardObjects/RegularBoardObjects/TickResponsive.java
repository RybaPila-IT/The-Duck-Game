package The.Duck.Game.BoardObjects.RegularBoardObjects;

/**
 * Objects which state can change during gameplay.
 *
 * <p>
 * This interface should be implemented by all
 * those objects which state can alter during gameplay.
 * It is also convenient for default implementation
 * which can simply be pass function.
 * </p>
 */
public interface TickResponsive {

    /**
     * Procedure performing task on game tic.
     */
    void onTic();

    /**
     * Information, if board object is still valid.
     *
     * @return True if object is valid; False otherwise.
     */
    boolean isValid();
}
