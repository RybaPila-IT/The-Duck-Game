package FXMLControlers;

import javafx.scene.Node;

public class WeaponController {

    private static final String weaponFaceLeft = "weapon_left";
    private static final String weaponFaceRight = "weapon_right";

    private Node weaponCharacter;

    public WeaponController(Node weapon) {
        weaponCharacter = weapon;
    }

    public void setLayoutX(double x) {
        weaponCharacter.setLayoutX(x);
    }

    public void setLayoutY(double y) {
        weaponCharacter.setLayoutY(y);
    }

    public void setFacingRightGraphic(boolean isWeaponFacingRight) {
        String styleClass = isWeaponFacingRight ? weaponFaceRight : weaponFaceLeft;
        weaponCharacter.getStyleClass().clear();
        weaponCharacter.getStyleClass().add(styleClass);
    }


}
