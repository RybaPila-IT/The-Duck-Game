package FXMLControlers;

import The.Duck.Game.BoardConstants;
import The.Duck.Game.Rectangle;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class BulletController {

    private Node bullet;


    public BulletController(Rectangle bulletArea) {

        Region newRegion = new Region();
        BoardConstants.getController().addToScene(newRegion);
        newRegion.setLayoutY(bulletArea.getLayoutY());
        newRegion.setLayoutX(bulletArea.getSecondX());
        newRegion.setPrefWidth(bulletArea.getWidth());
        newRegion.setPrefHeight(bulletArea.getHeight());
        newRegion.getStyleClass().add("bullet");

        this.bullet = newRegion;
    }

    public void setLayoutX(double x) {
        bullet.setLayoutX(x);
    }

    public void removeBullet() {
        BoardConstants.getController().removeNodeFromScene(bullet);
    }

}
