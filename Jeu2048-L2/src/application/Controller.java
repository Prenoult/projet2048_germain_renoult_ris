package application;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class Controller {

    @FXML
    void keyPressed(KeyEvent event) {
        switch(event.getCode()) {
            case KP_LEFT:
                System.out.println("A gauche");
                break;
            case KP_RIGHT:
                break;
            case KP_DOWN:
                break;
            case KP_UP:
                break;
        }
    }
}
