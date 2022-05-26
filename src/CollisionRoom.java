/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * CollisionRoom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 */

/**
 * @author Aaron Zhu
 * May 26th, 2022
 * @version 2.0
 * Time: 1 hour
 * Moved helper methods for collision detection and getting prompts to CollisionRoom.java
 * Moved helper methods for character movement + create template for character movement
 */

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class CollisionRoom {
    /** The primary stage for this application. Passed by reference. */
    public final Stage stage;

    /** The scene for this application. */
    public Scene scene;

    /**
     * stores a grid of the map that the character is on (for collision detection)
     * false - no collision
     * true - collision
     *
     * collision detection takes the highest value that the character is "standing" on
     */
    public boolean[][] collisionGrid;

    /**
     * stores a grid of the map that the character is on (for user prompting)
     * 0 - no prompt
     * positive value - prompt
     *
     * prompt creates a text box that will tell the user what to do
     */
    public int[][] promptGrid;

    /** what the textbox will display given the prompt */
    public TextBox[] textBoxes;

    /** stores the textbox that is being displayed, if any */
    public int textBoxOpen;

    /** stores which keys are being pressed */
    public boolean[] keyPressed;

    /** check collisions and movement of character using AnimationTimer */
    public AnimationTimer collisionTimer;

    /** stores the reference of the character in the scene */
    public Character character;

    public CollisionRoom(Stage stage) {
        this.stage = stage;
        collisionGrid = new boolean[800][600];
        promptGrid = new int[800][600];
        keyPressed = new boolean[300];
        textBoxOpen = 0;
    }

    /**
     * fills the collision grid with true between ${x1} and ${x2} and ${y1} and ${y2}
     */
    public void fillCollisionGrid(int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                collisionGrid[i][j] = true;
            }
        }
    }

    /**
     * fills the prompt grid with ${val} between ${x1} and ${x2} and ${y1} and ${y2}
     */
    public void fillPromptGrid(int x1, int y1, int x2, int y2, int val) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                promptGrid[i][j] = val;
            }
        }
    }

    /**
     * returns if the character collides with the GUI
     * @return true if the character collides with the GUI
     */
    public boolean isColliding() {
        int[] characterPos = character.getFootCoordinates();
        for (int i = characterPos[0]; i < characterPos[1]; i++) {
            for (int j = characterPos[2]; j < characterPos[3]; j++) {
                if (collisionGrid[i][j]) return true;
            }
        }
        return false;
    }

    /**
     * loops through the prompt grid and returns the highest value that the character is "standing" on
     * @return the prompt (if any) to display
     */
    public int getPrompt() {
        int maxDetect = 0;
        int[] characterPos = character.getFootCoordinates();
        for (int i = characterPos[0]; i < characterPos[1]; i++) {
            for (int j = characterPos[2]; j < characterPos[3]; j++) {
                maxDetect = Math.max(maxDetect, promptGrid[i][j]);
            }
        }
        return maxDetect;
    }

    /**
     * builds the room using ${promptGrid} and ${collisionGrid}
     */
    public abstract void buildRoom();

    /**
     * sets up getting user input for the scene
     */
    public void setUpUserInput() {
        scene.setOnKeyPressed(event -> { // when a key is pressed
            if (event.getText().length() == 1) { // if the key is displayable
                keyPressed[event.getText().charAt(0)] = true; // set the key to true
            }
            switch (event.getCode()) { // for non-displayable keys
            }
        });

        scene.setOnKeyReleased(event -> { // when a key is released
            if (event.getText().length() == 1) { // if the key is displayable
                keyPressed[event.getText().charAt(0)] = false; // set the key to false
            }
            switch (event.getCode()) { // for non-displayable keys
            }
        });
    }

    /**
     * abstract method to set up AnimationTimer for the character in the room
     * aka all the movement, collision detection, and scene changing logic
     */
    public abstract void setUpAnimationTimer();
}