import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Backpack {

    private Stage stage;
    private CollisionRoom room;

    public Backpack(Stage stage) {
        this.stage = stage;
    }

    public void changeRoom(CollisionRoom room) {
        this.room = room;
    }

    public void buildBackpack(Group root) {
        ImageView backpack = Tools.createButton(root, "Assets/School/Items/", "Backpack", 10, 500, 80);

        backpack.setOnMouseClicked(e -> {
            room.collisionTimer.stop();
            room.character.stopMovement();
            root.getChildren().add(root.getChildren().remove(0));
            contents(root);
        });
    }

    public void contents(Group root) {
        System.out.println("contents");

//        ImageView bg = Tools.createBackgroundImage("Assets/School/Rooms/SchoolBg.png");
//        Group root = new Group(bg);
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            root.getChildren().add(0, root.getChildren().remove(root.getChildren().size() - 1));
            room.startScene();
        });
        pause.play();
    }
}
