import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu {
    public static void mainMenu(Stage stage) {
        // set scene
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Text text = new Text("Pressure-Free");
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text.setX(50);
        text.setY(50);
        root.getChildren().add(text);

        stage.show();
    }
}
