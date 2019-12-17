package sample;

import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Quintin VanBooven
 * This deals with all aspects of the game.
 */
public class Game {

    private Pane pane;
    private final static List<String> words = Main.getList();
    private final List<String> wrongLettersList = new ArrayList<>();
    private final List<String> rightLettersList = new ArrayList<>();
    private static int guessNum;
    private String word;

    /**
     * Simple constructor only needed to get the pane
     * @param pane The primary pane
     */
    public Game(Pane pane){
        this.pane = pane;

    }

    /**
     * This is the code for the game it controls all aspects of it
     * getting and checking guesses
     * Not my best work but does the job more time and I would clean up
     */
    private void newGame() {
        System.out.println(words);
        guessNum = 0;
        Random random = new Random();
        wrongLettersList.clear();
        rightLettersList.clear();
        word = words.get(random.nextInt(words.size()));
        Label wrongLetters = new Label("Wrong letters guess: ");
        Label rightLetters = new Label("Right letters guessed: ");
        wrongLetters.setLayoutY(30);
        TextArea textArea = new TextArea("Press a letter");
        textArea.setLayoutY(60);
        textArea.setMaxWidth(100);
        textArea.setMaxHeight(10);
        Button button = new Button("Guess");
        button.setLayoutY(100);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            int guessNum;
            @Override
            public void handle(MouseEvent event) {
                TextArea textArea = (TextArea)pane.getChildren().get(0);
                String text = textArea.getText();
                if(word.toLowerCase().contains(text.toLowerCase())) {
                    if(rightLettersList.contains(text.toLowerCase()))
                        return;
                    for(int i = 0; i < word.toCharArray().length; i++){
                        if(text.equalsIgnoreCase(String.valueOf(word.toCharArray()[i])))
                            rightLettersList.add(String.valueOf(word.toCharArray()[i]));
                    }
                    Label right = new Label("");
                    right.setText(rightLettersList.toString().replace("[","").replace("]",""));
                    right.setLayoutY(0);
                    right.setLayoutX(115);
                    pane.getChildren().add(right);
                    if(rightLettersList.size() == word.length()){
                        Label label = new Label("YOU WON THE WORD WAS "+ word.toUpperCase()+"\n PRESS ENTER TO PLAY AGAIN");
                        label.setFont(Font.font("BOLD", FontWeight.BOLD,20));
                        label.setLayoutX(150);
                        label.setLayoutY(300);
                        pane.getChildren().add(label);
                        pane.setOnKeyPressed(event1 -> {
                            System.out.println(textArea.getText());
                            if (event1.getCode().isWhitespaceKey()) {
                                pane.getChildren().clear();
                                newGame();
                            }
                        });
                    }
                }
                else{
                    if(guessNum>6)
                        return;
                    if(wrongLettersList.contains(text.toLowerCase()))
                        return;
                    wrongLettersList.add(text.toLowerCase());
                    Label wrong = new Label("");
                    wrong.setText(wrongLettersList.toString().replace("[","").replace("]",""));
                    wrong.setLayoutY(30);
                    wrong.setLayoutX(115);
                    pane.getChildren().add(wrong);
                    switch (guessNum){
                        case 0:
                            pane.getChildren().add(Man.drawHead());
                            break;
                        case 1:
                            pane.getChildren().add(Man.drawBody());
                            break;
                        case 2:
                            pane.getChildren().add(Man.drawLeftArm());
                            break;
                        case 3:
                            pane.getChildren().add(Man.drawRightArm());
                            break;
                        case 4:
                            pane.getChildren().add(Man.drawLeftLeg());
                            break;
                        case 5:
                            pane.getChildren().add(Man.drawRightLeg());
                            break;
                        case 6:
                            Label label = new Label("YOU LOST THE\n WORD WAS "+word.toUpperCase());
                            label.setFont(Font.font("BOLD", FontWeight.BOLD,20));
                            label.setLayoutX(150);
                            label.setLayoutY(300);
                            pane.getChildren().add(label);
                            return;
                    }
                    guessNum++;
                }
            }
        });
        pane.getChildren().addAll(wrongLetters, rightLetters,button);
        pane.getChildren().addAll(Man.getThings());
        pane.getChildren().add(0, textArea);
    }

    /**
     * This will start the game and is called by the driver
     */
    public void startGame(){
        newGame();
    }



}
