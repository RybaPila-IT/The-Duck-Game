package FXMLControlers.BoardObjectsControllers;

import javafx.scene.Node;

public class WeaponController extends BasicController {

    private static final String WEAPON_FACE_LEFT = "weapon_left";
    private static final String WEAPON_FACE_RIGHT = "weapon_right";


    public WeaponController(Node weapon) {
        super(weapon);
    }

    public void setFacingRightGraphic(boolean isWeaponFacingRight) {
        String styleClass = isWeaponFacingRight ? WEAPON_FACE_RIGHT : WEAPON_FACE_LEFT;
        region.getStyleClass().clear();
        region.getStyleClass().add(styleClass);
    }


}
