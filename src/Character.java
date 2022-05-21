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
    private int sizeX, sizeY;

    /** stores the stage that the character is on. Passed by reference */
    private final Stage stage;

    /** stores the root of the scene that the character is on. Passed by reference */
    private final Group root;

    /** stores the scene that the character is on. Passed by reference */
    private final Scene scene;

    /** stores how fast the character moves (in pixels) */
    private final int speed = 5;

    /**
     * stores if the user is clicking the keys corresponding to
     * north, south, east, and west
     */
    private boolean N, E, W, S;

    /**
     * stores the current x and y coordinates of the character
     * coordinate is stored as the top left corner of the character
     */
    private int posX, posY;

    /**
     * stores the offset of the character's foot (Y) from the top of the character
     */
    private int footOffsetY;

    /** stores if there is already a textBox on the screen */
    private boolean textBoxExists;

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
     * constructor for Character
     * @param stage the stage that the character is on. Passed by reference
     * @param root the root of the scene that the character is on. Passed by reference
     * @param scene the scene that the character is on. Passed by reference
     * @param sizeY the size of the character (height)
     * @param collisionGrid collision grid for character
     * @param promptGrid prompt grid for character
     */
    public Character(Stage stage, Group root, Scene scene, int sizeY, boolean[][] collisionGrid, int[][] promptGrid) {
        this.stage = stage;
        this.sizeY = sizeY;
        this.root = root;
        this.scene = scene;
        textBoxExists = false;
        posX = 0;
        posY = 0;
        footOffsetY = (int) (sizeY * 10 / 11.0);
        sizeX = (int) (sizeY * 3 / 7.0);
        this.collisionGrid = collisionGrid;
        this.promptGrid = promptGrid;
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
     */
    public Character(Stage stage, Group root, Scene scene, int sizeY, int posX, int posY, boolean[][] collisionGrid, int[][] promptGrid) {
        this(stage, root, scene, sizeY, collisionGrid, promptGrid);
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
            switch (event.getCode()) {
                case W:
                    N = true;
                    break;
                case S:
                    S = true;
                    break;
                case A:
                    W = true;
                    break;
                case D:
                    E = true;
                    break;
            }
        });

        scene.setOnKeyReleased(event -> { // when a key is released
            switch (event.getCode()) {
                case W:
                    N = false;
                    break;
                case S:
                    S = false;
                    break;
                case A:
                    W = false;
                    break;
                case D:
                    E = false;
                    break;
            }
        });

        AnimationTimer timer = new AnimationTimer() { // timer for character movement
            @Override
            public void handle(long now) {
                // handle movement
                if (N) {
                    posY -= speed;
                    // error trap so character doesn't walk on a nonzero grid space
                    if (isColliding()) posY += speed;
                    else changeCharacterDirection("Up");
                }
                if (S) {
                    posY += speed;
                    if (isColliding()) posY -= speed;
                    else changeCharacterDirection("Down");
                }
                if (W) {
                    posX -= speed;
                    if (isColliding()) posX += speed;
                    else changeCharacterDirection("Left");
                }
                if (E) {
                    posX += speed;
                    if (isColliding()) posX -= speed;
                    else changeCharacterDirection("Right");
                }
                character.setX(posX);
                character.setY(posY);

                // handle prompt
                int prompt = getPrompt();

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

        // temporary - will move
        if (maxDetect != 0 && !textBoxExists) {
            textBoxExists = true;
            ImageView textBox = new ImageView("Assets/textBox.png");
            textBox.setPreserveRatio(true);
            textBox.setFitWidth(750);
            textBox.setX(25);
            textBox.setY(30);
            textBox.setOpacity(0.6);
            root.getChildren().add(textBox);

//            DeficiencyRoom deficiencyRoom = new DeficiencyRoom(stage);
//            System.out.println(3);
//            deficiencyRoom.deficiencyRoom();
        } else if (maxDetect == 0 && textBoxExists) {
            root.getChildren().remove(root.getChildren().size() - 1);
            textBoxExists = false;
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
