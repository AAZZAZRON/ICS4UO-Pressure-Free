/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * EscapeClassroom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 4.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 3 (Aaron):
 * - build room with collision
 * - add items for user to pick up
 *
 * Version 4 (Aaron):
 * - add items to room
 */

/**
 * @author Aaron Zhu
 * May 29th, 2022
 * @version 3.0
 * Time: 30 minutes
 * build the room with collision
 */

/**
 * @author Aaron Zhu
 * June 3rd, 2022
 * @version 3.0
 * Time: 15 minutes
 * add items for user to pick up
 */

/**
 * @author Aaron Zhu
 * June 4th, 2022
 * @version 4.0
 * Time: 2 hours
 * Move item placing to files
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EscapeClassroom extends ScenarioRoom {
    /**
     * Constructor for EscapeClassroom.
     * @param stage the primary stage for this application. Passed by reference.
     * @param backpack the backpack of the character. Passed by reference.
     * @param roomName the name of the room.
     * @param scenarioNum the scenario number.
     */
    public EscapeClassroom(Stage stage, Backpack backpack, String roomName, int scenarioNum) {
        super(stage, backpack, roomName, scenarioNum);
        this.bg = "ClassBg";
    }

    /**
     * builds the classroom
     */
    @Override
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/ClassBg.png");

        // set scene
        root = new Group(image);
        scene = new Scene(root);

        // room collisions
        fillCollisionGrid(356, 212, 484, 320);
        fillCollisionGrid(585, 212, 712, 320);
        fillCollisionGrid(105, 480, 232, 580);
        fillCollisionGrid(344, 480, 472, 580);
        fillCollisionGrid(53, 196, 230, 305); // teacher
        fillCollisionGrid(222, 203, 230, 308);
        fillCollisionGrid(53, 203, 60, 308);
        fillCollisionGrid(108, 160, 175, 200); // chair
        fillCollisionGrid(743, 343, 800, 483); // door

        textBoxes[1] = new TextBox(root, "Press e to leave the classroom.", "Green");
        fillPromptGrid(730, 330, 800, 500, 1);

        parseItemData();

        // borders collisions
        fillCollisionGrid(0, 0, 800, 168);
        fillCollisionGrid(0, 0, 8, 600);
        fillCollisionGrid(0, 592, 800, 600);
        fillCollisionGrid(792, 0, 800, 600);

        addBackpack();

        // warning textbox
        warning = new TextBox(root, "You cannot enter this room!", "Blue");

        buildCharacter(100, 750, 395);
    }
}
