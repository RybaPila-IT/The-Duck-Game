package FXMLControlers.BoardObjectsControllers;

import javafx.scene.Node;

/**
 * Class representing the controller of the Jumper Class.
 */
public class JumperController {

    private static final String JUMPER_ACTIVE = "jumper_green";
    private static final String JUMPER_INACTIVE = "jumper_red";

    private final Node jumper;

    public JumperController(Node jumper) {
        this.jumper = jumper;
    }

    /**
     * Sets Jumper graphic to green arrow.
     */
    public void setJumperActive() {

        jumper.getStyleClass().clear();
        jumper.getStyleClass().add(JUMPER_ACTIVE);
    }

    /**
     * Sets Jumper graphic to red arrow.
     */
    public void setJumperInactive() {

        jumper.getStyleClass().clear();
        jumper.getStyleClass().add(JUMPER_INACTIVE);
    }

}
