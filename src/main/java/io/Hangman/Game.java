package io.Hangman;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


import java.util.*;

/**
 * @author Quintin VanBooven
 * This deals with all aspects of the game.
 */
public class Game{

    private Pane pane;
    private final static HashMap<String,String> words = Main.getList();
    private final List<String> wrongLettersList = new ArrayList<>();
    private final List<String> rightLettersList = new ArrayList<>();
    private static int guessNum = 0;
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
        pane.getChildren().clear();
        System.out.println(words);
        guessNum = 0;
        Random random = new Random();
        wrongLettersList.clear();
        rightLettersList.clear();
        word = (String) words.keySet().toArray()[random.nextInt(words.size())];
        //word = words.get(random.nextInt(words.size()));
        System.out.println(word);
        ArrayList<String> correct = new ArrayList<>();
        Label wrongLetters = new Label("Wrong letters guess: ");
        Label rightLetters = new Label("Right letters guessed: ");
        wrongLetters.setLayoutY(30);
        pane.getChildren().addAll(wrongLetters, rightLetters);
        Label right = new Label("");
        pane.getChildren().add(right);
        correct.add(makeStringtoLength(word));
        pane.setVisible(true);
        Button hint = new Button("Hint");
        hint.resize(0,0);
        hint.setLayoutY(80);
        Label hintLbl = new Label(words.get(word));
        hintLbl.setLayoutY(110);
        hint.setOnMouseClicked( e ->{
            if(!pane.getChildren().contains(hintLbl))
                pane.getChildren().add(hintLbl);
        });
        pane.getChildren().add(hint);
        Label info = new Label("Press a key to guess");
        info.setLayoutY(60);
        pane.getChildren().add(info);
        pane.setOnKeyPressed(event -> {
            if(guessNum>=6)
                return;
            String text = event.getText();
            if(text.equalsIgnoreCase(" "))
                return;
            if(word.toLowerCase().contains(text.toLowerCase())) {
                if(rightLettersList.contains(text.toLowerCase()))
                    return;
                for(int i = 0; i < word.toCharArray().length; i++){
                    if(text.equalsIgnoreCase(String.valueOf(word.toCharArray()[i])))
                        rightLettersList.add(String.valueOf(word.toCharArray()[i]));
                }
                correct.set(0,findLetters(word,text, correct.get(0)));
                System.out.println(correct.get(0));
                rightLettersList.clear();
                rightLettersList.add(correct.get(0));
                System.out.println(rightLettersList.get(0));
                right.setText(" "+rightLettersList.get(0));
                right.setLayoutY(0);
                right.setLayoutX(115);
                if(rightLettersList.get(0).equalsIgnoreCase(word)){
                    Label label = new Label("YOU WON THE WORD WAS "+ word.toUpperCase()+"\n PRESS ENTER TO PLAY AGAIN");
                    label.setFont(Font.font("BOLD", FontWeight.BOLD,20));
                    label.setLayoutX(150);
                    label.setLayoutY(300);
                    pane.getChildren().add(label);
                    guessNum = -1;
                }
            }
            else{

                if(wrongLettersList.contains(text.toLowerCase()))
                    return;
                wrongLettersList.add(text.toLowerCase());
                Label wrong = new Label("");
                wrong.setText(wrongLettersList.toString().replace("[","").replace("]",""));
                wrong.setLayoutY(30);
                wrong.setLayoutX(115);
                pane.getChildren().add(wrong);
                switch (guessNum){
                    case -1:
                        if(event.getCode().isWhitespaceKey())
                            newGame();
                        return;
                    case 0:
                        pane.getChildren().add(7,Man.drawHead());
                        break;
                    case 1:
                        pane.getChildren().add(8,Man.drawBody());
                        break;
                    case 2:
                        pane.getChildren().add(9,Man.drawLeftArm());
                        break;
                    case 3:
                        pane.getChildren().add(10,Man.drawRightArm());
                        break;
                    case 4:
                        pane.getChildren().add(11,Man.drawLeftLeg());
                        break;
                    case 5:
                        pane.getChildren().add(12,Man.drawRightLeg());
                        Label label = new Label("YOU LOST THE\n WORD WAS "+word.toUpperCase());
                        label.setFont(Font.font("BOLD", FontWeight.BOLD,20));
                        label.setLayoutX(150);
                        label.setLayoutY(300);
                        pane.getChildren().add(label);
                        return;
                }
                guessNum++;
            }
        });
        pane.getChildren().addAll(Man.getThings());

        //pane.getChildren().add(0, textArea);
    }

    /*
    @Override
    public void handle(KeyEvent event){
        System.out.println("Preesedsd");
        String text = event.getText();
        if(text.equalsIgnoreCase(" "))
            return;
        if(word.toLowerCase().contains(text.toLowerCase())) {
            if(rightLettersList.contains(text.toLowerCase()))
                return;
                for(int i = 0; i < word.toCharArray().length; i++){
                    if(text.equalsIgnoreCase(String.valueOf(word.toCharArray()[i])))
                        rightLettersList.add(String.valueOf(word.toCharArray()[i]));
                }
            correct.set(0,findLetters(word,text, correct.get(0)));
            System.out.println(correct.get(0));
            rightLettersList.clear();
            rightLettersList.add(correct.get(0));
            System.out.println(rightLettersList.get(0));
            right.setText(" "+rightLettersList.get(0));
            right.setLayoutY(0);
            right.setLayoutX(115);
            if(rightLettersList.get(0).equalsIgnoreCase(word)){
                Label label = new Label("YOU WON THE WORD WAS "+ word.toUpperCase()+"\n PRESS ENTER TO PLAY AGAIN");
                label.setFont(Font.font("BOLD", FontWeight.BOLD,20));
                label.setLayoutX(150);
                label.setLayoutY(300);
                pane.getChildren().add(label);
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
                case 6:,
                    Label label = new Label("YOU LOST THE\n WORD WAS "+word.toUpperCase());
                    label.setFont(Font.font("BOLD", FontWeight.BOLD,20));
                    label.setLayoutX(150);
                    label.setLayoutY(300);
                    pane.getChildren().add(label);
                    return;
            }
            guessNum++;
        }
    }*/


    /**
     * This will start the game and is called by the driver
     */
    public void startGame(){
        newGame();
    }

    private void clearBoard(Pane pane){
        pane.getChildren().clear();
    }

    private String makeStringtoLength(String word){
        StringBuilder spaces = new StringBuilder();
        for(char c : word.toCharArray())
            spaces.append("-");
        return spaces.toString();
    }

    public static String findLetters(String word, String letter,String foundSoFar){
        char[] wordArray = word.toCharArray();
        StringBuilder found = new StringBuilder(foundSoFar);
        for(int i = 0; i < wordArray.length; i++){
            if(String.valueOf(wordArray[i]).equalsIgnoreCase(letter)){
                found.replace(i, i+1,String.valueOf(wordArray[i]));
            }
        }
        return found.toString();

    }

}
