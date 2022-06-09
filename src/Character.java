/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Character.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron)
 * - create the character on the screen (size, position, image)
 * - set up character movement
 * - set up character collision detection
 *
 * Version 2 (Aaron)
 * - moved character collision detection and prompt to CollisionRoom.java
 * - character only handles movement (up, down, left, right) and changing directions
 *
 * Version 3 (Aaron)
 * - animated the character's movement
 * - moved character's animation timer to one centralized one in Character.java
 * - ability to start/end character's animation timer using startMovement() and endMovement()
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

/**
 * @author Aaron Zhu
 * May 24th, 2022
 * @version 2.0
 * Time: 20 minutes
 * - fixed lag problem with changing scenes
 * - moved scene changing to ChangeScene.java
 */

/**
 * @author Aaron Zhu
 * May 26th, 2022
 * @version 2.0
 * Time: 1 hour
 * Moved all collision and prompt detection to CollisionRoom.java
 * added moveUp(), moveDown(), moveLeft(), moveRight() to move the character
 * Character.java class should not need to be updated anymore
 * all logic on how to move the character will be written in the room class that the character is in
 */

/**
 * @author Aaron Zhu
 * May 30th, 2022
 * @version 3.0
 * Time: 2 hours
 * moved character movement animation timer to Character.java
 * animate character left/right movement
 * startMovement() and endMovement() start and stop animation timer respectively
 * setPosition() sets the character's position (resets movement animation variables)
 */

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character {
    /** Stores the character image */
    private ImageView character;

    /** Stores the character's width (sizeX) and height (sizeY) */
    private final int sizeX, sizeY;

    /** stores the root of the scene that the character is on. Passed by reference */
    private final Group root;

    /** stores the scene that the character is on. Passed by reference */
    private final Scene scene;

    /** stores how fast the character moves (in pixels) */
    private final int SPEED = 4;

    /** moves the character in four directions */
    private AnimationTimer characterMovement;

    /** room the character is in, for collision */
    private CollisionRoom room;

    /**
     * stores the current x and y coordinates of the character
     * coordinate is stored as the top left corner of the character
     */
    private int posX, posY;

    /** how fast to move the character's arms/legs */
    private final int GRAPHIC_SPEED = 20;

    /** counter used to animate the character's arms/legs */
    private int counter = 0;

    /** stores the character's previous direction */
    private char previousDirection = 'q';

    /**
     * stores the offset of the character's foot (Y) from the top of the character
     */

    private final int footOffsetY;

    /**
     * constructor for Character with a specified position
     * @param root the root of the scene that the character is on. Passed by reference
     * @param scene the scene that the character is on. Passed by reference
     * @param room the room the character is in, for collision
     * @param sizeY the size of the character (height)
     * @param posX the x coordinate of the character
     * @param posY the y coordinate of the character
     */
    public Character(Group root, Scene scene, CollisionRoom room, int sizeY, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.sizeY = sizeY;
        this.root = root;
        this.scene = scene;
        this.room = room;
        footOffsetY = (int) (sizeY * 10 / 11.0);
        sizeX = (int) (sizeY * 3 / 7.0);
    }

    /**
     * builds the character
     * - sets the image of the character
     * - sets the position of the character
     * - sets the size of the character
     */
    public void build() {
        character = new ImageView("Assets/Character/Down1.png");
        character.setPreserveRatio(true);
        character.setFitHeight(sizeY);
        character.setX(posX);
        character.setY(posY);
        root.getChildren().add(character);


        characterMovement = new AnimationTimer() {
            @Override
            public void handle(long now) {
                char newDirection = 'q';
                if (room.isKeyPressed('w') && !room.isColliding()) {
                    moveUp();
                    if (room.isColliding()) moveDown(); // if character is colliding, revert movement
                    else newDirection = 'w';
                }
                if (room.isKeyPressed('s') && !room.isColliding()) {
                    moveDown();
                    if (room.isColliding()) moveUp();
                    else newDirection = 's';
                }
                if (room.isKeyPressed('a') && !room.isColliding()) {
                    moveLeft();
                    if (room.isColliding()) moveRight();
                    else newDirection = 'a';
                }
                if (room.isKeyPressed('d') && !room.isColliding()) {
                    moveRight();
                    if (room.isColliding()) moveLeft();
                    else newDirection = 'd';
                }
                if (newDirection == 'q') counter = 0;
                else {
                    if (newDirection != previousDirection) previousDirection = newDirection;
                    else counter++;
                }
                if (previousDirection == 'w') changeCharacterDirection("Up");
                else if (previousDirection == 's') changeCharacterDirection("Down");
                else if (previousDirection == 'a') changeCharacterDirection("Left");
                else if (previousDirection == 'd') changeCharacterDirection("Right");
            }
        };
    }

    /**
     * gets the border coordinates of the character's current foot position
     * @return an array of the character's current foot coordinates
     */
    public int[] getFootCoordinates() {
        int[] coordinates = new int[4];
        coordinates[0] = posX;
        coordinates[1] = posX + sizeX;
        coordinates[2] = posY + footOffsetY;
        coordinates[3] = posY + sizeY;
        return coordinates;
    }

    /**
     * moves the character up
     */
    private void moveUp() {
        posY -= SPEED;
        character.setX(posX);
        character.setY(posY);
    }

    /**
     * moves the character down
     */
    private void moveDown() {
        posY += SPEED;
        character.setX(posX);
        character.setY(posY);
    }

    /**
     * moves the character left
     */
    private void moveLeft() {
        posX -= SPEED;
        character.setX(posX);
        character.setY(posY);
    }

    /**
     * moves the character right
     */
    private void moveRight() {
        posX += SPEED;
        character.setX(posX);
        character.setY(posY);
    }

    /**
     * changes the image of the character based on the direction that the character is facing
     * @param direction the direction that the character is facing
     */
    private void changeCharacterDirection(String direction) {
        character.setImage(new Image("Assets/Character/" + direction + ((counter / GRAPHIC_SPEED) % 4 + 1) + ".png"));
    }

    /**
     * starts the character's movement
     */
    public void startMovement() {
        characterMovement.start();
    }

    /**
     * stops the character's movement
     */
    public void stopMovement() {
        characterMovement.stop();
    }

    /**
     * resets character's position to a certain spot
     * @param x the x coordinate of the character
     * @param y the y coordinate of the character
     */
    public void setPosition(int x, int y) {
        posX = x;
        posY = y;
        character.setX(posX);
        character.setY(posY);
        counter = 0;
        previousDirection = 'q';
        changeCharacterDirection("Down");
    }
}
