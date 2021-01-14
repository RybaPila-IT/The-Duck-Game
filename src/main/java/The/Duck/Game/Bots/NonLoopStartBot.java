package The.Duck.Game.Bots;

import java.util.List;

/**
 * Class representing a bot with unique beginning actions.
 *
 * <p>
 * Bot of this type will perform some specified tasks at the very
 * beginning of it`s performance. Later, as it reaches last action
 * from the beginning tasks, it will continue performing
 * tasks in loop which can be specified as the other sequence.
 * </p>
 */
public class NonLoopStartBot implements Bot {

    private final int beginningTiming;
    private final Bot beginningActions;
    private final Bot loopedActions;

    private int beginningCounter;

    /**
     * Constructor of the NonLoopStartBot.
     *
     * @param bA List of beginning actions which will not be looped.
     * @param bT List of timings for beginning actions.
     * @param lA List of later actions which will be looped.
     * @param lT List of timings for looped actions.
     */
    public NonLoopStartBot(List<List<Boolean>> bA, List<Integer> bT, List<List<Boolean>> lA, List<Integer> lT) {

        this.beginningTiming = bT.get(bT.size() - 1);
        this.beginningCounter = 0;
        this.beginningActions = new BasicBot(bA, bT);
        this.loopedActions = new BasicBot(lA, lT);
    }

    /**
     * Generate next input for player.
     *
     * <p>
     * As the beginning actions have ended,
     * the behaviour of the bot is similar to BasicBot.
     * It loops actions which are specified as the lA
     * parameter in the constructor.
     * </p>
     */
    @Override
    public void controlOnTic() {

        if (beginningCounter >= beginningTiming) {
            loopedActions.controlOnTic();
        } else {
            beginningCounter++;
            beginningActions.controlOnTic();
        }
    }
}
