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
 * @date May 17th, 2022
 * @version 1.0
 * @time 5 minutes
 * Create School.java
 */

/**
 * @author Aaron Zhu
 * @date May 20th, 2022
 * @version 1.0
 * @time 5 minutes
 * fillCollisions()
 * - fill the collision grid with a number
 * create buildDeficienciesRoom(), which just sets a background image
 */

/**
 * @author Sion Gang
 * @date May 20th, 2022
 * @version 1.0
 * @time 40 minutes
 * buildDeficienciesRoom()
 * - add coordinates of the door
 * - test deficiency implementation into school
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class School {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;

    /** collision grid for the character */
    private int[][] grid;

    /**
     * Constructor for School.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public School(Stage stage) {
        this.stage = stage;
        grid = new int[800][600];
    }

    /**
     * fills the collision grid with ${val} between ${x1} and ${x2} and ${y1} and ${y2}
     */
    private void fillCollisions(int x1, int y1, int x2, int y2, int val) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                grid[i][j] = val;
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
        fillCollisions(160, 0, 800, 216, 1);
        fillCollisions(0, 296, 296, 600, 1);
        fillCollisions(384, 296, 800, 504, 1);

        // borders collisions
        fillCollisions(0, 0, 800, 100, 2);
        fillCollisions(0, 0, 8, 600, 2);
        fillCollisions(0, 592, 800, 600, 2);
        fillCollisions(792, 0, 800, 600, 2);

        // door entrances
        fillCollisions(200, 217, 263, 218, 3);
        //fillCollisions(60, 28, 67, 28, 3);
       // fillCollisions(89, 28, 96, 28, 3);


      //  fillCollisions(38, 40, 38, 48, 3);
       // fillCollisions(62, 28, 38, 70, 3);


    //    fillCollisions(53, 36, 60, 36, 3);
     //   fillCollisions(79, 36, 86, 36, 3);

       // fillCollisions(26, 28, 33, 28, 3);
      //  fillCollisions(26, 28, 33, 28, 3);



        // create character
        Character character = new Character(stage, 100, 300, 300, grid);
        character.build(root, scene);
    }
}
