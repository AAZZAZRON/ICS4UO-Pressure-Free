import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class Backpack {

    private static Stage stage;
    //private static Group root;
    public Backpack(Stage stage) {
        this.stage = stage;
    }

    public static void buildBackpack(Group root) {
        ImageView backpack = Tools.createButton(root, "Assets/School/Items/", "Backpack", 590, 220, 120);

        backpack.setOnMouseClicked(e -> {
            contents();

        });


    }

    public static void contents() {
        ImageView bg = Tools.createBackgroundImage("Assets/School/Rooms/SchoolBg.png");
        Group root = new Group(bg);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
