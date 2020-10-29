package io.Hangman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Quintin VanBooven
 * This is the driver of the program and it starts the game
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        primaryStage.setTitle("Hang Man");
        primaryStage.setScene(new Scene(pane, 500, 600));
        Game game = new Game(pane);
        game.startGame();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static List<String> getList(){
        List<String> words = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("Words.txt")))){
            reader.lines().forEach(words::add);
        }catch (IOException  e){
            e.printStackTrace();
        }
        return words;
    }
}
