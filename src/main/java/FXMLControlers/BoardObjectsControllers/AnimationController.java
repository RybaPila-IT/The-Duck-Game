package FXMLControlers.BoardObjectsControllers;

import The.Duck.Game.GameManagers.BoardConstants;
import The.Duck.Game.BoardObjects.Utility.Rectangle;

/**
 * Controller class for Animated objects.
 *
 * <p>
 * Animated object is object which has list of
 * style classes associated with it which should be
 * changed one after another.
 * Therefore Animation Controller enables setting new
 * (or rather next) animation for object on the Scene.
 * Animation Controller extends Basic Controller class.
 * </p>
 */
public class AnimationController extends BasicController {

    public AnimationController(Rectangle region) {
        super(BoardConstants.getInstance().getController().createNewRegion(region));
    }

    public void setNewAnimation(String styleClass) {
        region.getStyleClass().clear();
        region.getStyleClass().add(styleClass);
    }

}
