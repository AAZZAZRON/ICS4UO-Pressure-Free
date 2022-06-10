/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * EscapeLibrary.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 4.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 3 (Sion):
 * - build room with collision detection
 *
 * Version 3 (Aaron):
 * - add items for user to pick up
 *
 * Version 4 (Aaron):
 * - add items to room
 */

/**
 * @author Sion Gang
 * May 20th, 2022
 * @version 3.0
 * Time: 15 minutes
 * implement Collision detection
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

public class EscapeLibrary extends ScenarioRoom {

    /**
     * Constructor for EscapeLibrary.
     * @param stage the primary stage for this application. Passed by reference.
     * @param backpack the backpack of the character. Passed by reference.
     * @param roomName the name of the room.
     * @param scenarioNum the scenario number.
     */
    public EscapeLibrary(Stage stage, Backpack backpack, String roomName, int scenarioNum) {
        super(stage, backpack, roomName, scenarioNum);
        this.bg = "LibraryBg";
    }

    /**
     * builds the escape room library
     */
    @Override
    public void buildRoom() {
        ImageView image = Tools.createBackgroundImage("Assets/School/Rooms/LibraryBg.png");

        // set scene
        root = new Group(image);
        scene = new Scene(root);


        // borders collisions
        fillCollisionGrid(0, 0, 800, 210);
        fillCollisionGrid(0, 0, 8, 600);
        fillCollisionGrid(0, 592, 800, 600);
        fillCollisionGrid(792, 0, 800, 600);

        fillCollisionGrid(0, 0, 39, 373);
        fillCollisionGrid(39, 373, 230, 486);

        fillCollisionGrid(369, 301, 604, 368);
        fillCollisionGrid(369, 445, 604, 515);

        fillCollisionGrid(734, 347, 800, 481);

        textBoxes[1] = new TextBox(root, "Press e to leave the library.", "Green");
        fillPromptGrid(730, 330, 800, 500, 1);

        parseItemData();


        addBackpack();

        // warning textbox
        warning = new TextBox(root, "You cannot enter this room!", "Blue");

        buildCharacter(100, 750, 395);
    }
}
