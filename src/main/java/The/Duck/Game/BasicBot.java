package The.Duck.Game;

import java.util.Arrays;
import java.util.List;

public class BasicBot implements Bot {

    private final List<List<Boolean>> moves;
    private final List<Integer> timing;

    private int counter;
    private int combinationIdx;
    private int timesIdx;

    public BasicBot(List<List<Boolean>> m, List<Integer> t) {

        this.moves = m;
        this.timing = t;
        this.counter = 0;
        this.combinationIdx = 0;
        this.timesIdx = 0;
    }

    private void performTask(List<Boolean> settingKeys) {
        BoardConstants.getPlayer2Info().setPressedRight(settingKeys.get(0));
        BoardConstants.getPlayer2Info().setPressedLeft(settingKeys.get(1));
        BoardConstants.getPlayer2Info().setPressedUp(settingKeys.get(2));
        BoardConstants.getPlayer2Info().setPressedShoot(settingKeys.get(3));
        BoardConstants.getPlayer2Info().setPressedGrab(settingKeys.get(4));
        BoardConstants.getPlayer2Info().setInteract(settingKeys.get(5));
    }

    private void clearButtons() {
        performTask(Arrays.asList(false, false, false, false, false, false));
    }

    @Override
    public void controlOnTic() {

        clearButtons();
        counter++;

        if (counter == 150) {
            int a = 0;
        }

        if (counter >= timing.get(timesIdx + 1)) {
            combinationIdx = (combinationIdx + 1) % moves.size();
            timesIdx = (timesIdx + 2) % timing.size();
        }

        counter %= timing.get(timing.size() - 1);

        if (timing.get(timesIdx) <= counter && timing.get(timesIdx + 1) > counter)
            performTask(moves.get(combinationIdx));
    }
}
