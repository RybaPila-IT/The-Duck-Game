package FXMLControlers;

import javafx.scene.Node;

public class JumperController {

    private static final String JUMPER_ACTIVE = "jumper_green";
    private static final String JUMPER_INACTIVE = "jumper_red";

    private final Node jumper;

    public JumperController(Node jumper) {
        this.jumper = jumper;
    }

    public void setJumperActive() {

        jumper.getStyleClass().clear();
        jumper.getStyleClass().add(JUMPER_ACTIVE);
    }

    public void setJumperInactive() {

        jumper.getStyleClass().clear();
        jumper.getStyleClass().add(JUMPER_INACTIVE);
    }

}
