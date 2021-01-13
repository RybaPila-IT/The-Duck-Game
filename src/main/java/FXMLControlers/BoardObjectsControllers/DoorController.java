package FXMLControlers.BoardObjectsControllers;

import javafx.scene.Node;

public class DoorController extends BasicController {

    private static final String DOORS_CLOSED = "doors_locked";
    private static final String DOORS_OPENED = "doors_unlocked";

    public DoorController(Node region) {
        super(region);
    }

    public void open() {
        region.getStyleClass().clear();
        region.getStyleClass().add(DOORS_OPENED);
    }

    public void close() {
        region.getStyleClass().clear();
        region.getStyleClass().add(DOORS_CLOSED);
    }

}
