package The.Duck.Game;

import FXMLControlers.WeaponController;
import javafx.scene.layout.Region;

public class Weapon {

    private static final int SHOTS_AMOUNT = 400;

    private boolean isWeaponFacingRight;
    private int shots;
    private final Rectangle area;
    private Rectangle owner;
    private final WeaponController controller;


    public Weapon(Region weaponCharacter) {

        this.controller = new WeaponController(weaponCharacter);
        this.area = new Rectangle(weaponCharacter);
        this.shots = SHOTS_AMOUNT;
        this.owner = null;
        this.isWeaponFacingRight = true;
    }

    public void shoot() {

        if (shots > 0) {
            BoardBullets.getInstance().addBullet(new Bullet(area, isWeaponFacingRight));
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

    public void followOwner(boolean isPlayerFacedRight, boolean jumping) {

        if (isPlayerFacedRight)
            area.setX(owner.getSecondX() - 30);
        else
            area.setX(owner.getLayoutX() - 35);

        if (jumping)
            area.setY(owner.getLayoutY() - 45);
        else
            area.setY(owner.getLayoutY());

        isWeaponFacingRight = isPlayerFacedRight;

        controller.setFacingRightGraphic(isPlayerFacedRight);
        controller.setLayoutX(area.getLayoutX());
        controller.setLayoutY(area.getSecondY());

    }


}
