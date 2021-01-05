package FXMLControlers;

import The.Duck.Game.BoardConstants;
import javafx.scene.Node;

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
        BoardConstants.getController().removeNodeFromScene(region);
    }


}
