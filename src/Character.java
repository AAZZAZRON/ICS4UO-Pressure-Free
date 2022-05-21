/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Character.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron)
 * - create the character on the screen (size, position, image)
 * - set up character movement
 * - set up character collision detection
 */

/**
 * @author Aaron Zhu
 * May 17th, 2022
 * @version 1.0
 * Time: 1 hour
 * Create Character.java
 * - add a filler image as character
 *
 * build()
 * - add user input and move the character accordingly
 * - change direction character is facing based on user input
 *
 * changeCharacterDirection()
 * - helper method
 * - change the direction the character is facing given the direction
 */

/**
 * @author Sion Gang
 * May 17th, 2022
 * @version 1.0
 * Time: 10 minutes
 * Change user input keys from arrow keys to WASD
 */

/**
 * @author Aaron Zhu
 * May 20th, 2022
 * @version 1.0
 * Time: 2 hours
 * Set up collision detection using grid[][]
 *
 * maxInstruction()
 * - returns the largest number in the character's "feet" area [i.e. where the character is standing]
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 1 hour
 * Separated collision grid from prompt grid
 * collisionGrid --> boolean array
 * promptGrid --> int array
 *
 * added global stage, root, scene variables
 * isColliding() returns if the character collides with something on the GUI
 * getPrompt() returns the prompt (if any) of the character's current position
 * - contains temporary textbox code (will move to a separate class later)
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 30 minutes
 * - changed user input method to get all displayable keys
 * - moved TextBox to TextBox.java
 * - toggle textBoxes on and off in setUpCharacterMovement()
 */

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Character {
    /** Stores the character image */
    private ImageView character;

    /** Stores the character's width (sizeX) and height (sizeY) */
    private final int sizeX, sizeY;

    /** stores the stage that the character is on. Passed by reference */
    private final Stage stage;

    /** stores the root of the scene that the character is on. Passed by reference */
    private final Group root;

    /** stores the scene that the character is on. Passed by reference */
    private final Scene scene;

    /** stores how fast the character moves (in pixels) */
    private final int speed = 5;

    /** stores which keys are being pressed */
    private boolean[] keyPressed;

    /**
     * stores the current x and y coordinates of the character
     * coordinate is stored as the top left corner of the character
     */
    private int posX, posY;

    /**
     * stores the offset of the character's foot (Y) from the top of the character
     */
    private final int footOffsetY;

    /** stores the textbox that is being displayed, if any */
    private int textBoxOpen = 0;

    /**
     * stores a grid of the map that the character is on (for collision detection)
     * false - no collision
     * true - collision
     *
     * collision detection takes the highest value that the character is "standing" on
     */
    private boolean[][] collisionGrid;

    /**
     * stores a grid of the map that the character is on (for user prompting)
     * 0 - no prompt
     * positive value - prompt
     *
     * prompt creates a text box that will tell the user what to do
     */
    private int[][] promptGrid;

    /**
     * stores the textbox messages that will be displayed to the user
     */
    private TextBox[] textBoxes;

    /**
     * constructor for Character
     * @param stage the stage that the character is on. Passed by reference
     * @param root the root of the scene that the character is on. Passed by reference
     * @param scene the scene that the character is on. Passed by reference
     * @param sizeY the size of the character (height)
     * @param collisionGrid collision grid for character
     * @param promptGrid prompt grid for character
     * @param textBoxes textbox messages for character
     */
    public Character(Stage stage, Group root, Scene scene, int sizeY, boolean[][] collisionGrid, int[][] promptGrid, TextBox[] textBoxes) {
        this.stage = stage;
        this.sizeY = sizeY;
        this.root = root;
        this.scene = scene;
        posX = 0;
        posY = 0;
        footOffsetY = (int) (sizeY * 10 / 11.0);
        sizeX = (int) (sizeY * 3 / 7.0);
        this.collisionGrid = collisionGrid;
        this.promptGrid = promptGrid;
        this.textBoxes = textBoxes;
        keyPressed = new boolean[300];
    }

    /**
     * constructor for Character
     * @param stage the stage that the character is on. Passed by reference
     * @param root the root of the scene that the character is on. Passed by reference
     * @param scene the scene that the character is on. Passed by reference
     * @param sizeY the size of the character (height)
     * @param posX the x coordinate of the character
     * @param posY the y coordinate of the character
     *             sets character position to (posX, posY)
     * @param collisionGrid collision grid for character
     * @param promptGrid prompt grid for character
     * @param textBoxes textbox messages for character
     */
    public Character(Stage stage, Group root, Scene scene, int sizeY, int posX, int posY, boolean[][] collisionGrid, int[][] promptGrid, TextBox[] textBoxes) {
        this(stage, root, scene, sizeY, collisionGrid, promptGrid, textBoxes);
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * builds the character
     * - sets the image of the character
     * - sets the position of the character
     * - sets the size of the character
     */
    public void build() {
        character = new ImageView("Assets/Character/characterDown.png");
        character.setPreserveRatio(true);
        character.setFitHeight(sizeY);
        character.setX(posX);
        character.setY(posY);
        root.getChildren().add(character);

        setupCharacterMovement();

        // tester code to get coordinates
        scene.onMouseClickedProperty().set(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
             public void handle(javafx.scene.input.MouseEvent event) {
                 System.out.println("Mouse clicked at: " + event.getX() + ", " + event.getY());
            }
       });
    }

    /**
     * sets up the character's movement
     */
    private void setupCharacterMovement() {
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

        AnimationTimer timer = new AnimationTimer() { // timer for character movement
            @Override
            public void handle(long now) {
                // handle movement
                if (keyPressed['w']) {
                    posY -= speed;
                    // error trap so character doesn't walk on a nonzero grid space
                    if (isColliding()) posY += speed;
                    else changeCharacterDirection("Up");
                }
                if (keyPressed['s']) {
                    posY += speed;
                    if (isColliding()) posY -= speed;
                    else changeCharacterDirection("Down");
                }
                if (keyPressed['a']) {
                    posX -= speed;
                    if (isColliding()) posX += speed;
                    else changeCharacterDirection("Left");
                }
                if (keyPressed['d']) {
                    posX += speed;
                    if (isColliding()) posX -= speed;
                    else changeCharacterDirection("Right");
                }
                character.setX(posX);
                character.setY(posY);

                // handle prompt
                int prompt = getPrompt();

                // toggle textbox visibility
                if (prompt != 0) {
                    textBoxOpen = prompt; // toggle on if not already
                    textBoxes[textBoxOpen].toggleOn();

                    if (keyPressed['e']) { // if the user presses e
                        DeficiencyRoom deficiencyRoom = new DeficiencyRoom(stage); // display deficiencies room
                        System.out.println(3);
                        deficiencyRoom.deficiencyRoom();
                    }
                } else if (textBoxOpen != 0) {
                    textBoxes[textBoxOpen].toggleOff();
                    textBoxOpen = 0;
                }
            }
        };

        timer.start();

    }

    /**
     * returns if the character collides with the GUI
     * @return true if the character collides with the GUI
     */
    public boolean isColliding() {
        for (int i = posX; i < posX + sizeX; i++) {
            for (int j = posY + footOffsetY; j < posY + sizeY; j++) {
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
        for (int i = posX; i < posX + sizeX; i++) {
            for (int j = posY + footOffsetY; j < posY + sizeY; j++) {
                maxDetect = Math.max(maxDetect, promptGrid[i][j]);
            }
        }
        return maxDetect;
    }

    /**
     * changes the image of the character based on the direction that the character is facing
     * @param direction the direction that the character is facing
     */
    private void changeCharacterDirection(String direction) {
        character.setImage(new Image("Assets/Character/character" + direction + ".png"));
    }
}
