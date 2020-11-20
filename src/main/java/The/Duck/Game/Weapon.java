package The.Duck.Game;

import FXMLControlers.WeaponController;
import javafx.scene.layout.Region;

public class Weapon {

    private int shots;
    private Rectangle area;
    private Rectangle owner;
    private WeaponController controller;


    public Weapon(Region weaponCharacter) {

        this.controller = new WeaponController(weaponCharacter);
        this.area = new Rectangle(weaponCharacter);
        this.shots = 4;
        this.owner = null;

    }

    public void shoot() {

        if (shots > 0) {
            System.out.println("Shooting!");
            shots--;
        }

    }

    public void setOwner(Rectangle owner) {
        this.owner = owner;
    }

    public void fallDown() {
        area.setY(area.getLayoutY() + 65);
        controller.setLayoutY(area.getLayoutY());
    }

    public Rectangle getArea() {
        return area;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public void followOwner() {

        if (hasOwner()) {

            area.setX(owner.getSecondX() - 30);
            area.setY(owner.getLayoutY());

        }

        controller.setLayoutX(area.getLayoutX());
        controller.setLayoutY(area.getSecondY());

    }


}
