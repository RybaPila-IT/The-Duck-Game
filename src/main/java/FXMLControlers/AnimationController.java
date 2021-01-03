package FXMLControlers;

import The.Duck.Game.BoardConstants;
import The.Duck.Game.Rectangle;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class AnimationController {

    private final Node animation;

    private Region createNewAnimationRegion(Rectangle region) {

        Region newRegion = new Region();
        BoardConstants.getController().addToScene(newRegion);
        newRegion.setLayoutY(region.getLayoutY());
        newRegion.setLayoutX(region.getSecondX());
        newRegion.setPrefWidth(region.getWidth());
        newRegion.setPrefHeight(region.getHeight());

        return newRegion;
    }

    public AnimationController(Rectangle region) {
        this.animation = createNewAnimationRegion(region);
    }

    public void setNewAnimation(String styleClass) {
        animation.getStyleClass().clear();
        animation.getStyleClass().add(styleClass);
    }

    public void removeAnimation() {
        BoardConstants.getController().removeNodeFromScene(animation);
    }

}
