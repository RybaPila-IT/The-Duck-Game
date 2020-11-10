package FXMLControlers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.IOException;

public class MainMenuController {

    private static final String fontPath = "src/main/resources/graphics/kenvector_future_thin.ttf";

    @FXML
    private Button NewGameB;
    @FXML
    private Button QuitB;
    @FXML
    private Button CreditsB;

    private void setButtonFont(Button button) {

        try {

            button.setFont(Font.loadFont(new FileInputStream(fontPath), 23));

        } catch (IOException e) {

            System.err.println("Unable to load a custom font.");
            button.setFont(Font.font("Verdana", 23));

        }

    }

    @FXML
    public void initialize() {

        setButtonFont(QuitB);
        setButtonFont(NewGameB);
        setButtonFont(CreditsB);

    }

    public Button getNewGameButton() {
        return NewGameB;
    }

    public Button getQuitButton() {
        return QuitB;
    }

    public Button getCreditsButton() {
        return CreditsB;
    }

}
