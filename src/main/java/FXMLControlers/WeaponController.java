package FXMLControlers;

import javafx.scene.Node;

public class WeaponController {

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


}
