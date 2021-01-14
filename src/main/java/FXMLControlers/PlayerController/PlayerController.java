package FXMLControlers.PlayerController;

import FXMLControlers.BoardObjectsControllers.BasicController;
import The.Duck.Game.GameManagers.BoardConstants;
import javafx.scene.Node;

import java.util.List;

/**
 * Class controlling player display.
 *
 * <p>
 * This class is strictly connected with Player class.
 * It is crucial for proper MVC pattern accomplishment.
 * PlayerController extends BasicController class.
 * </p>
 */
public class PlayerController extends BasicController {

    private static final int JUMPING_RIGHT = 0;
    private static final int JUMPING_LEFT = 1;
    private static final int WALK1_RIGHT = 2;
    private static final int WALK2_RIGHT = 3;
    private static final int WALK1_LEFT = 4;
    private static final int WALK2_LEFT = 5;
    private static final int STANDING = 6;
    private static final int DEAD = 7;

    private static final int COUNTER_VALUE = 10;

    private final List<String> styleClasses;
    private final List<Node> health;

    private String currentWalkAnimation;
    private int walkAnimationCounter;
    private int healthLevel;

    /**
     * Constructor of the PlayerController class.
     *
     * @param playerCharacter Node which displays player character.
     * @param styleClasses    Classes used for styling Player character.
     * @param health          List of nodes containing health pictures.
     */
    public PlayerController(Node playerCharacter, List<String> styleClasses, List<Node> health) {
        super(playerCharacter);
        this.health = health;
        this.styleClasses = styleClasses;
        this.healthLevel = health.size() - 1;
        this.currentWalkAnimation = styleClasses.get(WALK1_RIGHT);
        this.walkAnimationCounter = COUNTER_VALUE;
    }

    /**
     * Function setting required style class for player.
     *
     * <p>
     * This function operates closely with Player class itself.
     * It generally decides in which position player is
     * currently placed (walking, standing etc.) and
     * sets proper graphic.
     * </p>
     *
     * @param jumping      information whether player is jumping.
     * @param isFacedRight information whether player is facing right side.
     * @param horSpeed     information about horizontal player speed.
     */
    public void setPlayerGraphic(boolean jumping, boolean isFacedRight, double horSpeed) {

        String animation = styleClasses.get(STANDING);

        if (healthLevel < 0)
            animation = styleClasses.get(DEAD);
        else if (jumping)
            animation = isFacedRight ?
                    styleClasses.get(JUMPING_RIGHT) :
                    styleClasses.get(JUMPING_LEFT);
        else if (horSpeed > 0) {

            decideHorizontalGraphic(styleClasses.get(WALK1_RIGHT),
                    styleClasses.get(WALK2_RIGHT));
            animation = currentWalkAnimation;

        } else if (horSpeed < 0) {

            decideHorizontalGraphic(styleClasses.get(WALK1_LEFT),
                    styleClasses.get(WALK2_LEFT));
            animation = currentWalkAnimation;

        }

        region.getStyleClass().clear();
        region.getStyleClass().add(animation);
    }

    private void decideHorizontalGraphic(String graphic1, String graphic2) {

        if (!currentWalkAnimation.equals(graphic1) && !currentWalkAnimation.equals(graphic2))
            currentWalkAnimation = graphic1;

        if (walkAnimationCounter-- < 0) {
            currentWalkAnimation = (currentWalkAnimation.equals(graphic1) ? graphic2 : graphic1);
            walkAnimationCounter = COUNTER_VALUE;
        }

    }

    /**
     * Function removing heart picture belonging to player from the Scene.
     */
    public void decreaseHealth() {
        BoardConstants.getInstance().getController().removeNodeFromScene(health.get(healthLevel--));
    }


}
