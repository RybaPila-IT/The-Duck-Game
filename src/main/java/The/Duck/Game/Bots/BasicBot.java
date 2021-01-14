package The.Duck.Game.Bots;

import The.Duck.Game.GameManagers.BoardConstants;

import java.util.Arrays;
import java.util.List;

/**
 * Basic Bot implementing the Bot interface.
 *
 * <p>
 * This type of bot performs actions in never ending loop.
 * </p>
 */
public class BasicBot implements Bot {

    private final List<List<Boolean>> moves;
    private final List<Integer> timing;

    private int counter;
    private int combinationIdx;
    private int timesIdx;

    /**
     * BasicBot constructor.
     *
     * @param m List of moves which should be performed by bot one after another.
     * @param t Time stamps for actions (on which tic should certain action be performed).
     */
    public BasicBot(List<List<Boolean>> m, List<Integer> t) {

        this.moves = m;
        this.timing = t;
        this.counter = 0;
        this.combinationIdx = 0;
        this.timesIdx = 0;
    }

    /**
     * Generate next input for player.
     *
     * <p>
     * This procedure will generate next movement input for player
     * if it reaches specified time stamp. After performing all tasks
     * it will simply re-do all tasks again, in possibly, never ending loop.
     * </p>
     */
    @Override
    public void controlOnTic() {

        clearButtons();
        counter++;

        if (counter >= timing.get(timesIdx + 1)) {
            combinationIdx = (combinationIdx + 1) % moves.size();
            timesIdx = (timesIdx + 2) % timing.size();
        }

        counter %= timing.get(timing.size() - 1);

        if (timing.get(timesIdx) <= counter && timing.get(timesIdx + 1) > counter)
            performTask(moves.get(combinationIdx));
    }

    private void performTask(List<Boolean> settingKeys) {
        BoardConstants.getInstance().getPlayer2Info().setPressedRight(settingKeys.get(0));
        BoardConstants.getInstance().getPlayer2Info().setPressedLeft(settingKeys.get(1));
        BoardConstants.getInstance().getPlayer2Info().setPressedUp(settingKeys.get(2));
        BoardConstants.getInstance().getPlayer2Info().setPressedShoot(settingKeys.get(3));
        BoardConstants.getInstance().getPlayer2Info().setPressedGrab(settingKeys.get(4));
        BoardConstants.getInstance().getPlayer2Info().setInteract(settingKeys.get(5));
    }

    private void clearButtons() {
        performTask(Arrays.asList(false, false, false, false, false, false));
    }
}
