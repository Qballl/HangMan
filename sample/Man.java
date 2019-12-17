package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Quintin VanBooven
 * This class draws all parts of the hangman including the stand
 */
public class Man {

    /**
     * Returns all parts needed for the stand
     * @return The stand
     */
    public static List<Node> getThings(){
        return new ArrayList<>(Arrays.asList(drawUpRight(),drawBeam(),drawBase(),drawRope()));
    }

    /**
     * Makes the upright part of the stand
     * @return A line representing the upright part of the stand
     */
    private static Line drawUpRight() {
        return new Line(100, 450, 100, 150);
    }

    /**
     * Makes the beam that the rope is tied to
     * @return A line representing the beam
     */
    private static Line drawBeam() {
        return new Line(100, 150, 250, 150);
    }

    /**
     * Makes the rope the person is tied to
     * @return A line representing the rope
     */
    private static Line drawRope(){
        return new Line(250, 150, 250, 195);
    }

    /**
     * Makes the person's body
     * @return A line representing the body of the person
     */
    public static Line drawBody(){
        return new Line(250, 275, 250, 375);
    }

    /**
     * Makes the left arm of the person
     * @return A line representing the left of the person
     */
    public static Line drawLeftArm(){
        return new Line(270, 270, 300, 315);
    }

    /**
     * Makes the right arm of the person
     * @return A line representing the right arm of the person
     */
    public static Line drawRightArm() {
       return new Line(230, 270, 210, 315);
    }

    /**
     * Makes the right leg of the person
     * @return A line representing the right leg of the person
     */
    public static Line drawRightLeg(){
        return new Line(250, 375, 210, 450);
    }

    /**
     * Makes the left leg of the person
     * @return A line representing the left leg of the person
     */
    public static Line drawLeftLeg() {
        return new Line(250, 375, 290, 450);
    }
    /**
     * A method to draw a base to help keep start clean.
     * @return the base
     */
    private static Arc drawBase() {
        Arc base = new Arc();
        base.setCenterX(100);
        base.setCenterY(520);
        base.setRadiusX(70);
        base.setRadiusY(70);
        base.setStartAngle(-5);
        base.setLength(190);
        base.setType(ArcType.OPEN);
        base.setFill(Color.WHITE);
        base.setStroke(Color.BLACK);
        return base;
    }

    /**
     * A method to draw the head to help keep start clean.
     * @return the head
     */
    public static Circle drawHead() {
        Circle head = new Circle();
        head.setCenterX(250);
        head.setCenterY(235);
        head.setRadius(40);
        head.setFill(Color.WHITE);
        head.setStroke(Color.BLACK);
        return head;
    }
}
