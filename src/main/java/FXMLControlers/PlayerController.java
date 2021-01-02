package FXMLControlers;

import javafx.scene.Node;

import java.util.List;

public class PlayerController {

    private static final int JUMPING_RIGHT = 0;
    private static final int JUMPING_LEFT = 1;
    private static final int WALK1_RIGHT = 2;
    private static final int WALK2_RIGHT = 3;
    private static final int WALK1_LEFT = 4;
    private static final int WALK2_LEFT = 5;
    private static final int STANDING = 6;

    private static final int COUNTER_VALUE = 10;

    private final List<String> styleClasses;

    private String currentWalkAnimation;
    private int walkAnimationCounter;
    private final Node playerCharacter;

    public PlayerController(Node playerCharacter, List<String> styleClasses) {
        this.styleClasses = styleClasses;
        this.playerCharacter = playerCharacter;
        currentWalkAnimation = styleClasses.get(WALK1_RIGHT);
        walkAnimationCounter = COUNTER_VALUE;
    }

    public void setLayoutX(double x) {
        playerCharacter.setLayoutX(x);
    }

    public void setLayoutY(double y) {
        playerCharacter.setLayoutY(y);
    }

    public void setPlayerGraphic(boolean jumping, boolean isFacedRight, double horSpeed) {

        String animation = styleClasses.get(STANDING);

        if (jumping)
            animation = isFacedRight ? styleClasses.get(JUMPING_RIGHT) : styleClasses.get(JUMPING_LEFT);
        else if (horSpeed > 0) {

            decideHorizontalGraphic(styleClasses.get(WALK1_RIGHT), styleClasses.get(WALK2_RIGHT));
            animation = currentWalkAnimation;

        } else if (horSpeed < 0) {

            decideHorizontalGraphic(styleClasses.get(WALK1_LEFT), styleClasses.get(WALK2_LEFT));
            animation = currentWalkAnimation;

        }

        playerCharacter.getStyleClass().clear();
        playerCharacter.getStyleClass().add(animation);
    }

    private void decideHorizontalGraphic(String graphic1, String graphic2) {

        if (!currentWalkAnimation.equals(graphic1) && !currentWalkAnimation.equals(graphic2))
            currentWalkAnimation = graphic1;

        if (walkAnimationCounter-- < 0) {
            currentWalkAnimation = (currentWalkAnimation.equals(graphic1) ? graphic2 : graphic1);
            walkAnimationCounter = COUNTER_VALUE;
        }

    }


}
