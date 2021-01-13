package FXMLControlers.BoardObjectsControllers;

import The.Duck.Game.GameManagers.BoardConstants;
import The.Duck.Game.BoardObjects.Utility.Rectangle;

public class AnimationController extends BasicController {

    public AnimationController(Rectangle region) {
        super(BoardConstants.getInstance().getController().createNewRegion(region));
    }

    public void setNewAnimation(String styleClass) {
        region.getStyleClass().clear();
        region.getStyleClass().add(styleClass);
    }

}
