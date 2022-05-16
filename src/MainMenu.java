import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenu {
    public void mainMenu(Stage stage) {
        // https://www.tutorialspoint.com/javafx/javafx_images.htm#:~:text=You%20can%20load%20an%20image%20in%20JavaFX%20by,object%20of%20the%20image%20to%20be%20loaded%20or%2C
        ImageView image = new ImageView("Assets/MainMenu/mainMenu.png");
        image.setPreserveRatio(true);
        image.setFitWidth(786);

        // set scene
        Group root = new Group(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // add buttons
        Button playBtn = HoverButton.createButton(root, "mm", 150, 150, 484);
        Button insBtn = HoverButton.createButton(root, "mm", 150, 275, 484);
        Button exitBtn = HoverButton.createButton(root, "mm", 150, 400, 484);

        // add onclick
        playBtn.setOnAction(e -> {
            System.out.println("Play");
        });

        insBtn.setOnAction(e -> {
            Instructions instructions = new Instructions();
            instructions.instructions(stage);
        });

        exitBtn.setOnAction(e -> {
            // https://stackoverflow.com/questions/2909898/how-to-exit-a-java-application
            Platform.exit();
        });
    }
}
