import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Instructions {
    public void instructions(Stage stage) {
        // https://www.tutorialspoint.com/javafx/javafx_images.htm#:~:text=You%20can%20load%20an%20image%20in%20JavaFX%20by,object%20of%20the%20image%20to%20be%20loaded%20or%2C
        ImageView image = new ImageView("Assets/MainMenu/Instructions.png");
        image.setPreserveRatio(true);
        image.setFitWidth(786);

        stage.setScene(new Scene(new Group(image)));
    }
}
