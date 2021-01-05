package FXMLControlers;

import The.Duck.Game.BoardConstants;
import The.Duck.Game.Rectangle;

public class AnimationController extends BasicController {

    public AnimationController(Rectangle region) {
        super(BoardConstants.getController().createNewRegion(region));
    }

    public void setNewAnimation(String styleClass) {
        region.getStyleClass().clear();
        region.getStyleClass().add(styleClass);
    }

}
