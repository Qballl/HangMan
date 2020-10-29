package sample;

import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
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
public class Game{

    private Pane pane;
    private final static List<String> words = Main.getList();
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
        System.out.println(words);
        guessNum = 0;
        Random random = new Random();
        wrongLettersList.clear();
        rightLettersList.clear();
        word = words.get(random.nextInt(words.size()));
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
        pane.setOnKeyPressed(event -> {
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
    }*/


    /**
     * This will start the game and is called by the driver
     */
    public void startGame(){
        newGame();
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
