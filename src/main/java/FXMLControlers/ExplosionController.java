package FXMLControlers;

import The.Duck.Game.BoardConstants;
import The.Duck.Game.Rectangle;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class ExplosionController {

    private final static String PREFIX = "explosion_";

    private int counter;

    private Node explosion;

    public ExplosionController(Rectangle region) {

        counter = 1;

        Region newRegion = new Region();
        BoardConstants.getController().addToScene(newRegion);
        newRegion.setLayoutY(region.getLayoutY());
        newRegion.setLayoutX(region.getSecondX());
        newRegion.setPrefWidth(region.getWidth());
        newRegion.setPrefHeight(region.getHeight());
        newRegion.getStyleClass().add(getStyleClass());

        this.explosion = newRegion;
    }

    public void nextAnimation() {

        counter++;
        explosion.getStyleClass().clear();
        explosion.getStyleClass().add(getStyleClass());
    }

    public void removeExplosion() {
        BoardConstants.getController().removeNodeFromScene(explosion);
    }

    private String getStyleClass() {
        return PREFIX + counter;
    }

}
