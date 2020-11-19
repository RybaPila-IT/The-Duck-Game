package FXMLControlers;

import javafx.scene.Node;

public class PlayerController {

    private Node playerCharacter;

    public PlayerController(Node playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public void setLayoutX(double x) {
        playerCharacter.setLayoutX(x);
    }

    public void setLayoutY(double y) {
        playerCharacter.setLayoutY(y);
    }

}
