/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * EscapeRoomSchool.java
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

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Arrays;

public class EscapeRoomSchool extends CollisionRoom {

    /** warning textbox if user tries to do a move that is not allowed */
    private TextBox warning;

    /** if the character can leave the school */
    public static boolean canLeave = false;

    private Backpack backpack;

    /**
     * Constructor for EscapeRoomSchool.
     * @param stage the primary stage for this application. Passed by reference.
     * @param backpack the backpack of the character. Passed by reference.
     */
    public EscapeRoomSchool(Stage stage, Backpack backpack) {
        super(stage);
        textBoxes = new TextBox[10];
        this.backpack = backpack;
    }

    /**
     * builds the school
     */
    @Override
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/SchoolBg.png");

        // set scene
        Group root = new Group(image);
        scene = new Scene(root);

        // room collisions
        fillCollisionGrid(160, 0, 800, 216);
        fillCollisionGrid(0, 296, 296, 600);
        fillCollisionGrid(384, 296, 800, 504);

        // borders collisions
        fillCollisionGrid(0, 0, 800, 100);
        fillCollisionGrid(0, 0, 8, 600);
        fillCollisionGrid(0, 592, 800, 600);
        fillCollisionGrid(792, 0, 800, 600);

        // door entrances
        fillPromptGrid(200, 216, 264, 232, 1); // room 1
        fillPromptGrid(296, 320, 312, 384, 2); // room 2
        fillPromptGrid(488, 504, 552, 520, 3); // room 3
        fillPromptGrid(688, 504, 752, 520, 4); // room 4
        fillPromptGrid(472, 216, 536, 232, 5); // washroom 1
        fillPromptGrid(704, 216, 768, 232, 6); // washroom 2
        fillPromptGrid(296, 496, 312, 560, 7); // library
        fillPromptGrid(0, 88, 24, 184, 8); // exit

        // textbox messages
        textBoxes[1] = new TextBox(stage, root, scene, "Press e to enter room 101", "Green");
        textBoxes[2] = new TextBox(stage, root, scene, "Press e to enter room 102", "Green");
        textBoxes[3] = new TextBox(stage, root, scene, "Press e to enter room 103", "Green");
        textBoxes[4] = new TextBox(stage, root, scene, "Press e to enter room 104", "Green");
        textBoxes[5] = new TextBox(stage, root, scene, "Press e to enter the washroom", "Green");
        textBoxes[6] = new TextBox(stage, root, scene, "Press e to enter the washroom", "Green");
        textBoxes[7] = new TextBox(stage, root, scene, "Press e to enter the library", "Green");
        textBoxes[8] = new TextBox(stage, root, scene, "Press e to exit the school", "Green");

        // warning textbox
        warning = new TextBox(stage, root, scene, "You cannot enter this room!", "Blue");

        backpack.buildBackpack(root);

        buildCharacter(root, 100, 60, 60);
    }

    /**
     * sets up the animation timer for the character
     */
    @Override
    public void setUpAnimationTimer() {
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // handle prompt
                int prompt = getPrompt();
                // toggle textbox visibility
                if (!warning.isVisible() && prompt != 0) { // warning > prompts
                    textBoxOpen = prompt; // toggle on if not already
                    textBoxes[textBoxOpen].toggleOn();

                    if (keyPressed['e']) { // if the user presses e to enter room
                        if (prompt != 8) { // if room is next lesson
                            stop(); // stop the timer
                            character.stopMovement(); // stop the character's movement
                            ChangeScene.changeToEscapeRoomRoom(prompt); // change to escape room room
                        } else if (canLeave) { // if the user can leave the school
                            stop(); // stop the timer
                            character.stopMovement(); // stop the character's movement
                            ChangeScene.changeToMainMenu(stage); // change to main menu
                        } else { // if the user cannot leave the school
                            warning.setMessage("You can't leave the school yet! Please find all the necessary materials for your assignment before trying again");
                            warning.setWarning(3);
                        }
                    }
                } else if (textBoxOpen != 0) {
                    textBoxes[textBoxOpen].toggleOff();
                    textBoxOpen = 0;
                }
            }
        };
    }

    /**
     * restarts a scene
     */
    @Override
    public void startScene() {
        // reset keypress
        Arrays.fill(keyPressed, false);

        backpack.changeRoom(this);
        character.startMovement();
        collisionTimer.start();
        stage.setScene(scene);
    }
}
