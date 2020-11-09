package FXMLControlers;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class FirstBoardController {

    @FXML
    private Circle player1;

    public void initialize() {

    }

    public void onKeyTyped() {

        player1.setLayoutX(player1.getLayoutX() + 1.6);
        System.out.println("Typed key");
    }

}
