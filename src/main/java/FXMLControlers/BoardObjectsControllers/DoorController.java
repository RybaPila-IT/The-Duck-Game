package FXMLControlers.BoardObjectsControllers;

import javafx.scene.Node;

/**
 * Class representing controller of the Door Class.
 */
public class DoorController extends BasicController {

    private static final String DOORS_CLOSED = "doors_locked";
    private static final String DOORS_OPENED = "doors_unlocked";

    public DoorController(Node region) {
        super(region);
    }

    /**
     * Sets Doors graphic to open.
     */
    public void open() {
        region.getStyleClass().clear();
        region.getStyleClass().add(DOORS_OPENED);
    }

    /**
     * Sets Doors graphic to locked.
     */
    public void close() {
        region.getStyleClass().clear();
        region.getStyleClass().add(DOORS_CLOSED);
    }

}
