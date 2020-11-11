package FXMLControlers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Region;

public class FirstBoardController {

    @FXML
    private Region playerModel;
    @FXML
    private AnchorPane manePane;


    public void initialize() {

    }

    public AnchorPane gatManePane() {
        return manePane;
    }

    public Region getPlayer() {
        return playerModel;
    }

}
