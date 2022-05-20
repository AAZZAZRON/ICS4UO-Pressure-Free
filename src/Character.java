/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Character.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1
 * - create the character on the screen (size, position, image)
 * - set up character movement
 * - set up character collision detection
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

    /**
     * stores a grid of the map that the character is on (for collision detection)
     * 0 - empty space
     * 1 - wall
     * 2 - border
     *
     * collision detection takes the highest value that the character is "standing" on
     * does a task based on the value
     */
    private int[][] grid;

    /**
     * constructor for Character
     * @param stage the stage that the character is on. Passed by reference
     * @param sizeY the size of the character (height)
     * @param grid the grid that the character is on (map of screen). Passed by reference
     * sets character position to (0, 0)
     */
    public Character(Stage stage, int sizeY, int[][] grid) {
        this.stage = stage;
        this.sizeY = sizeY;
        posX = 0;
        posY = 0;
        footOffsetY = (int) (sizeY * 10 / 11.0);
        sizeX = (int) (sizeY * 3 / 7.0);
        this.grid = grid;
    }

    /**
     * constructor for Character
     * @param stage the stage that the character is on. Passed by reference
     * @param sizeY the size of the character (height)
     * @param grid the grid that the character is on (map of screen). Passed by reference
     * @param posX the x coordinate of the character
     * @param posY the y coordinate of the character
     * sets character position to (posX, posY)
     */
    public Character(Stage stage, int sizeY, int posX, int posY, int[][] grid) {
        this(stage, sizeY, grid);
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * builds the character
     * - sets the image of the character
     * - sets the position of the character
     * - sets the size of the character
     * @param root the root of the scene
     * @param scene the scene that the character is on
     */
    public void build(Group root, Scene scene) {
        character = new ImageView("Assets/Character/characterDown.png");
        character.setPreserveRatio(true);
        character.setFitHeight(sizeY);
        character.setX(posX);
        character.setY(posY);
        root.getChildren().add(character);

        setupCharacterMovement(scene);

        // testing code
//        scene.onMouseClickedProperty().set(new EventHandler<javafx.scene.input.MouseEvent>() {
//            @Override
//            public void handle(javafx.scene.input.MouseEvent event) {
//                System.out.println("Mouse clicked at: " + event.getX() + ", " + event.getY());
//            }
//        });
    }

    /**
     * sets up the character's movement
     * @param scene the scene that the character is on
     */
    private void setupCharacterMovement(Scene scene) {
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
                if (N) {
                    posY -= speed;
                    // error trap so character doesn't walk on a nonzero grid space
                    if (maxInstruction() != 0) posY += speed;
                    else changeCharacterDirection("Up");
                }
                if (S) {
                    posY += speed;
                    if (maxInstruction() != 0) posY -= speed;
                    else changeCharacterDirection("Down");
                }
                if (W) {
                    posX -= speed;
                    if (maxInstruction() != 0) posX += speed;
                    else changeCharacterDirection("Left");
                }
                if (E) {
                    posX += speed;
                    if (maxInstruction() != 0) posX -= speed;
                    else changeCharacterDirection("Right");
                }
                // set character position
                character.setX(posX);
                character.setY(posY);
            }
        };
        timer.start();
    }

    /**
     * loops through the grid and returns the highest value that the character is "standing" on
     * @return the highest value that the character is "standing" on
     */
    public int maxInstruction() {
        int maxDetect = 0;
        for (int i = posX; i < posX + sizeX; i++) {
            for (int j = posY + footOffsetY; j < posY + sizeY; j++) {
                maxDetect = Math.max(maxDetect, grid[i][j]);
            }
        }
//        System.out.println(posX + ", " + posY + ": " + maxDetect);
//        System.out.println((posX) + " " + (posY + footOffsetY) + ", " + (posX + sizeX) + " " + (posY + sizeY) + " " + maxDetect);
        return maxDetect;
    }

    /**
     * changes the image of the character based on the direction that the character is facing
     * @param direction the direction that the character is facing
     */
    public void changeCharacterDirection(String direction) {
        character.setImage(new Image("Assets/Character/character" + direction + ".png"));
    }
}
