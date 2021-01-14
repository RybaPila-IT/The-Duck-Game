package FXMLControlers.BoardObjectsControllers;

import The.Duck.Game.GameManagers.BoardConstants;
import javafx.scene.Node;

/**
 * Class representing the most basic controller of the game object.
 *
 * <p>
 * Basic Controller enables changing positions of game objects
 * (moving them) and also removing them from the game scene.
 * </p>
 */
public class BasicController {

    protected Node region;

    public BasicController(Node region) {
        this.region = region;
    }

    public void setLayoutX(double x) {
        region.setLayoutX(x);
    }

    public void setLayoutY(double y) {
        region.setLayoutY(y);
    }

    public void remove() {
        BoardConstants.getInstance().getController().removeNodeFromScene(region);
    }


}
