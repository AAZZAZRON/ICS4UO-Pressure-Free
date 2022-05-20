import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class School {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;
    private Room[] rooms;

    public School(Stage stage) {
        this.stage = stage;
        rooms = new Room[7];
    }

    public void build() {
        ImageView image = Tools.createBackgroundImage("school.png");

        // set scene
        Group root = new Group(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // build rooms

        Character character = new Character(stage, 100);
        character.build(root, scene);
    }
}
