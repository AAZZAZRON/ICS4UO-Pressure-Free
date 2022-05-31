/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * ScenarioRoom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 */

/**
 * @author Aaron Zhu
 * May 30th, 2022
 * @version 3.0
 * Time: 30 minutes
 * Create ScanarioRoom.java
 * - extends CollisionRoom
 * - used for the rooms in the escape room that all have the same functions
 * Functions to be added:
 * - peer pressure scenario
 * - ability to pick up items from the rooms
 */

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public abstract class ScenarioRoom extends CollisionRoom {

    /** warning textbox if user tries to do a move that is not allowed */
    public TextBox warning;

    /**
     * Constructor for ScenarioRoom.
     *
     * @param stage the primary stage for this application. Passed by reference.
     */
    public ScenarioRoom(Stage stage) {
        super(stage);
    }

    @Override
    public void setUpAnimationTimer() {
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // handle prompt
                int prompt = getPrompt();
                // toggle textbox visibility
                if (true || (!warning.isVisible() && prompt != 0)) { // warning > prompts
                    textBoxOpen = prompt; // toggle on if not already
                    //textBoxes[textBoxOpen].toggleOn();

                    if (keyPressed['e']) { // if the user presses e to enter room
                        keyPressed['e'] = false; // set the key to false
                        stop(); // stop the timer
                        character.stopMovement(); // stop the character's movement
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
