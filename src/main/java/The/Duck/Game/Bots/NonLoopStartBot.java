package The.Duck.Game.Bots;

import java.util.List;

public class NonLoopStartBot implements Bot {

    private final int beginningTiming;
    private final Bot beginningActions;
    private final Bot loopedActions;

    private int beginningCounter;

    public NonLoopStartBot(List<List<Boolean>> bA, List<Integer> bT, List<List<Boolean>> lA, List<Integer> lT) {

        this.beginningTiming = bT.get(bT.size() - 1);
        this.beginningCounter = 0;
        this.beginningActions = new BasicBot(bA, bT);
        this.loopedActions = new BasicBot(lA, lT);
    }

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
