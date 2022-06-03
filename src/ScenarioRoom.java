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

/**
 * @author Aaron Zhu
 * May 31st, 2022
 * @version 3.0
 * Time: 30 minutes
 * Make character restart at room entrance on load
 */

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Arrays;

public abstract class ScenarioRoom extends CollisionRoom {

    /** warning textbox if user tries to do a move that is not allowed */
    public TextBox warning;

    /** backpack of the character */
    public Backpack backpack;

    /**
     * Constructor for ScenarioRoom.
     *
     * @param stage the primary stage for this application. Passed by reference.
     */
    public ScenarioRoom(Stage stage, Backpack backpack) {
        super(stage);
        this.backpack = backpack;
        this.textBoxes = new TextBox[10];
    }

    @Override
    public void setUpAnimationTimer() {
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // handle prompt
                int prompt = getPrompt();
                // toggle textbox visibility
                if ((!warning.isVisible() && prompt != 0)) { // warning > prompts
                    textBoxOpen = prompt; // toggle on if not already
                    textBoxes[textBoxOpen].toggleOn();
                    if (keyPressed['e']) { // if the user presses e to enter room
                        textBoxes[textBoxOpen].toggleOff();
                        stop(); // stop the timer
                        character.setPosition(750, 395);
                        character.stopMovement(); // stop the character's movement
                        ChangeScene.changeToEscapeRoomSchool(); // change to escape room school (exit room)
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

    public void addItem(Group root, String path, int x, int y, int val) {
        ImageView item = new ImageView(path);
        item.preserveRatioProperty().set(true);
        item.setX(x);
        item.setY(y);
        item.setFitWidth(50);
        fillCollisionGrid(x, y, x + 50, y + 50);
        fillPromptGrid(x - 20, y - 20, x + 70, y + 70, val);
        root.getChildren().add(item);
    }

    static class Coord {
        int x;
        int y;
        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
