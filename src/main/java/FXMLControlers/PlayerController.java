package FXMLControlers;

import javafx.scene.Node;

public class PlayerController {

    private static final String JUMPING_RIGHT = "soldier-jump-right";
    private static final String JUMPING_LEFT = "soldier-jump-left";
    private static final String WALK1_RIGHT = "soldier-walk-1-right";
    private static final String WALK2_RIGHT = "soldier-walk-2-right";
    private static final String WALK1_LEFT = "soldier-walk-1-left";
    private static final String WALK2_LEFT = "soldier-walk-2-left";
    private static final String STANDING = "soldier-stand";

    private static final int COUNTER_VALUE = 200;

    private String currentWalkAnimation;
    private int walkAnimationCounter;
    private final Node playerCharacter;

    public PlayerController(Node playerCharacter) {
        this.playerCharacter = playerCharacter;
        currentWalkAnimation = WALK1_RIGHT;
        walkAnimationCounter = COUNTER_VALUE;
    }

    public void setLayoutX(double x) {
        playerCharacter.setLayoutX(x);
    }

    public void setLayoutY(double y) {
        playerCharacter.setLayoutY(y);
    }

    public void setPlayerGraphic(boolean jumping, boolean isFacedRight, double horSpeed) {

        String animation = STANDING;

        if (jumping)
            animation = isFacedRight ? JUMPING_RIGHT : JUMPING_LEFT;
        else if (horSpeed > 0) {

            decideHorizontalGraphic(WALK1_RIGHT, WALK2_RIGHT);
            animation = currentWalkAnimation;

        } else if (horSpeed < 0) {

            decideHorizontalGraphic(WALK1_LEFT, WALK2_LEFT);
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
