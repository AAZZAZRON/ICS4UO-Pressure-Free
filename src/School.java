/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * School.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1
 * - set up 800 by 600 collision grid for the character
 * - set up collision detection for the rooms and border (not for doors)
 * - builds character using collision grid
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

        // create character
        Character character = new Character(stage, 100, 300, 300, grid);
        character.build(root, scene);
    }
}
