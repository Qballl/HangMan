package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressed implements EventHandler<KeyEvent> {


    @Override
    public void handle(KeyEvent event) {
        System.out.println("Fired");
    }
}
