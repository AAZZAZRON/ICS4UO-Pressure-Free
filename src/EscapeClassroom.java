/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * EscapeClassroom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 */

/**
 * @author Aaron Zhu
 * May 29th, 2022
 * @version 3.0
 * Time: 30 minutes
 * build the room with collision
 */

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EscapeClassroom extends ScenarioRoom {

    /**
     * Constructor for EscapeClassroom.
     * @param stage the primary stage for this application. Passed by reference.
     */
    public EscapeClassroom(Stage stage) {
        super(stage);
    }

    /**
     * builds the classroom
     */
    @Override
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/ClassBg.png");

        // set scene
        Group root = new Group(image);
        scene = new Scene(root);

        // room collisions
        fillCollisionGrid(360, 203, 490, 315);
        fillCollisionGrid(590, 203, 720, 315);
        fillCollisionGrid(105, 477, 234, 585);
        fillCollisionGrid(348, 477, 477, 585);
        fillCollisionGrid(53, 196, 230, 305); // teacher
        fillCollisionGrid(222, 203, 230, 308);
        fillCollisionGrid(53, 203, 60, 308);
        fillCollisionGrid(108, 160, 175, 200); // chair
        fillCollisionGrid(743, 343, 800, 483); // door

        // borders collisions
        fillCollisionGrid(0, 0, 800, 160);
        fillCollisionGrid(0, 0, 1, 600);
        fillCollisionGrid(0, 599, 800, 600);
        fillCollisionGrid(799, 0, 800, 600);

        // warning textbox
        warning = new TextBox(stage, root, scene, "You cannot enter this room!", "Blue");
        warningShown = false;

        buildCharacter(root, 100, 750, 395);
    }

    /**
     * sets up the animation timer for the character
     */
    @Override
    public void setUpAnimationTimer() {
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // handle movement
                if (keyPressed['w'] && !isColliding()) {
                    character.moveUp(true);
                    if (isColliding()) character.moveDown(false); // if character is colliding, revert movement
                }
                if (keyPressed['s'] && !isColliding()) {
                    character.moveDown(true);
                    if (isColliding()) character.moveUp(false);
                }
                if (keyPressed['a'] && !isColliding()) {
                    character.moveLeft(true);
                    if (isColliding()) character.moveRight(false);
                }
                if (keyPressed['d'] && !isColliding()) {
                    character.moveRight(true);
                    if (isColliding()) character.moveLeft(false);
                }

                // handle prompt
                int prompt = getPrompt();
                // toggle textbox visibility
                if (true || (!warningShown && prompt != 0)) { // warning > prompts
                    textBoxOpen = prompt; // toggle on if not already
                    //textBoxes[textBoxOpen].toggleOn();

                    if (keyPressed['e']) { // if the user presses e to enter room
                        keyPressed['e'] = false; // set the key to false
                        stop(); // stop the timer
                        ChangeScene.changeToEscapeRoomSchool(); // change to escape room school (exit room)
                    }
                } else if (textBoxOpen != 0) {
                    //textBoxes[textBoxOpen].toggleOff();
                    textBoxOpen = 0;
                }
            }
        };
    }
}
