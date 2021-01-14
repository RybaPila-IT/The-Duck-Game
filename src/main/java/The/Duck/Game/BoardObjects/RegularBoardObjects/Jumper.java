package The.Duck.Game.BoardObjects.RegularBoardObjects;

import FXMLControlers.BoardObjectsControllers.JumperController;
import The.Duck.Game.BoardObjects.Utility.Rectangle;
import The.Duck.Game.Player.Player;
import javafx.scene.layout.Region;

public class Jumper extends BoardObject {

    private final static int ACTIVATION_COUNT = 5;
    private final static double JUMP_STRENGTH = -33;

    private boolean activated;
    private int tillActivation;

    private final JumperController controller;

    /**
     * Jumper constructor.
     *
     * @param jumperRegion JavaFX region representing jumper.
     */
    public Jumper(Region jumperRegion) {

        super(new Rectangle(jumperRegion));

        activated = false;
        tillActivation = 0;

        controller = new JumperController(jumperRegion);
    }

    /**
     * Procedure will change jumper style after specified time
     * if jumper is active (red).
     */
    @Override
    public void onTic() {

        if (activated) {

            if (tillActivation++ > ACTIVATION_COUNT) {
                tillActivation = 0;
                activated = false;
                controller.setJumperActive();
            }

        }
    }

    /**
     * Procedure will set player vertical speed to jumper speed value.
     *
     * @param player Player which has collided with the jumper.
     */
    @Override
    public void onPlayerCollision(Player player) {

        player.jump(JUMP_STRENGTH);
        activated = true;
        controller.setJumperInactive();
    }

    @Override
    public boolean isValid() {
        return true;
    }

}
