import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class School {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;
    private int[][] canEnter;

    public School(Stage stage) {
        this.stage = stage;
        canEnter = new int[800][600];
    }

    private void fillCollisions(int x1, int y1, int x2, int y2, int val) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                canEnter[i][j] = val;
            }
        }
    }

    public void buildDeficienciesRoom() {
        ImageView image = Tools.createBackgroundImage("school.png");

        // set scene
        Group root = new Group(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // build rooms
        fillCollisions(160, 0, 800, 216, 1);
        fillCollisions(0, 296, 296, 600, 1);
        fillCollisions(384, 296, 800, 504, 1);

        // borders
        fillCollisions(0, 0, 800, 100, 1);
        fillCollisions(0, 0, 8, 600, 1);
        fillCollisions(0, 592, 800, 600, 1);
        fillCollisions(792, 0, 800, 600, 1);

        Character character = new Character(stage, 100, 300, 300, canEnter);
        character.build(root, scene);
    }
}
