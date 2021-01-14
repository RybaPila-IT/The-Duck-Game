package FXMLControlers.BoardObjectsControllers;

import javafx.scene.Node;

/**
 * Class representing the controller of the Weapon Class.
 */
public class WeaponController extends BasicController {

    private static final String WEAPON_FACE_LEFT = "weapon_left";
    private static final String WEAPON_FACE_RIGHT = "weapon_right";


    public WeaponController(Node weapon) {
        super(weapon);
    }

    /**
     * Function decides which graphic for weapon should be set.
     *
     * @param isWeaponFacingRight information whether the weapon is facing the right side (val true).
     */
    public void setFacingRightGraphic(boolean isWeaponFacingRight) {
        String styleClass = isWeaponFacingRight ? WEAPON_FACE_RIGHT : WEAPON_FACE_LEFT;
        region.getStyleClass().clear();
        region.getStyleClass().add(styleClass);
    }


}
