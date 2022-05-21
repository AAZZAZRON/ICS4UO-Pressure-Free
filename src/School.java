/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * School.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron)
 * - set up 800 by 600 collision grid for the character + helper methods
 *
 * Version 1 (Sion)
 * - set up collision detection for the rooms and border (not for doors)
 * - build character using collision grid
 */

/**
 * @author Aaron Zhu
 * May 17th, 2022
 * @version 1.0
 * Time: 5 minutes
 * Create School.java
 */

/**
 * @author Aaron Zhu
 * May 20th, 2022
 * @version 1.0
 * Time: 5 minutes
 * fillCollisions()
 * - fill the collision grid with a number
 * create buildDeficienciesRoom(), which just sets a background image
 */

/**
 * @author Sion Gang
 * May 20th, 2022
 * @version 1.0
 * Time: 40 minutes
 * buildDeficienciesRoom()
 * - add coordinates of the door
 * - test deficiency implementation into school
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 1 hour
 * Separated collisionGrid and promptGrid
 * fillCollisionGrid()
 * - fill the collision grid with true
 * fillPromptGrid()
 * - fill the prompt grid with integers
 *
 * moved the door collision into promptGrid
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 30 minutes
 * build all the doors for deficiencies room in buildDeficienciesRoom()
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 5 minutes
 * buildDeficienciesRoom()
 * - create textBoxes
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class School {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;

    /** collision grid for the character */
    private boolean[][] collisionGrid;

    /** prompt grid for the GUI layout */
    private int[][] promptGrid;

    /** what the textbox will display given the prompt */
    private TextBox[] textBoxes;

    /**
     * Constructor for School.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public School(Stage stage) {
        this.stage = stage;
        collisionGrid = new boolean[800][600];
        promptGrid = new int[800][600];
        textBoxes = new TextBox[10];
    }

    /**
     * fills the collision grid with true between ${x1} and ${x2} and ${y1} and ${y2}
     */
    private void fillCollisionGrid(int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                collisionGrid[i][j] = true;
            }
        }
    }

    /**
     * fills the prompt grid with ${val} between ${x1} and ${x2} and ${y1} and ${y2}
     */
    private void fillPromptGrid(int x1, int y1, int x2, int y2, int val) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                promptGrid[i][j] = val;
            }
        }
    }

    /**
     * builds the school for the deficiencies room
     */
    public void buildDeficienciesRoom() {
        ImageView image = Tools.createBackgroundImage("Rooms/schoolBg.png");

        // set scene
        Group root = new Group(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);

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
        fillPromptGrid(472, 216, 536, 232, 2); // washroom 1
        fillPromptGrid(704, 216, 768, 232, 3); // washroom 2
        fillPromptGrid(296, 320, 312, 384, 4); // room 2
        fillPromptGrid(296, 496, 312, 560, 5); // library
        fillPromptGrid(488, 504, 552, 520, 6); // room 3
        fillPromptGrid(688, 504, 752, 520, 7); // room 4
        fillPromptGrid(0, 88, 24, 184, 8); // exit

        // textbox messages
        textBoxes[1] = new TextBox(stage, root, scene, "Press e to enter room 101");
        textBoxes[2] = new TextBox(stage, root, scene, "Press e to enter the washroom");
        textBoxes[3] = new TextBox(stage, root, scene, "Press e to enter the washroom");
        textBoxes[4] = new TextBox(stage, root, scene, "Press e to enter room 102");
        textBoxes[5] = new TextBox(stage, root, scene, "Press e to enter the library");
        textBoxes[6] = new TextBox(stage, root, scene, "Press e to enter room 103");
        textBoxes[7] = new TextBox(stage, root, scene, "Press e to enter room 104");
        textBoxes[8] = new TextBox(stage, root, scene, "Press e to exit the school");

        // create character
        Character character = new Character(stage, root, scene, 100, 300, 300, collisionGrid, promptGrid, textBoxes);
        character.build();
    }
}
