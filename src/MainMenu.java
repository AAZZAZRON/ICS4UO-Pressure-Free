import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Collection;

public class MainMenu {

    public Button createButton(Group root, String text, int x, int y, int sz) {
        // https://www.demo2s.com/java/javafx-button-setbackground-background-value.html
        // create button and hover
        ImageView buttonImg = new ImageView(new Image("assets/" + text + "Button.png"));
        ImageView buttonImgHover = new ImageView(new Image("assets/" + text + "ButtonHover.png"));
        buttonImg.setPreserveRatio(true);
        buttonImgHover.setPreserveRatio(true);
        buttonImg.setFitWidth(sz);
        buttonImgHover.setFitWidth(sz);

        Button btn = new Button();
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setGraphic(buttonImg);
        btn.setOnMouseEntered(e -> btn.setGraphic(buttonImgHover));
        btn.setOnMouseExited(e -> btn.setGraphic(buttonImg));

        root.getChildren().add(btn);

        return btn;
    }

    public void mainMenu(Stage stage) {
        // https://www.tutorialspoint.com/javafx/javafx_images.htm#:~:text=You%20can%20load%20an%20image%20in%20JavaFX%20by,object%20of%20the%20image%20to%20be%20loaded%20or%2C
        ImageView image = new ImageView("assets/mainMenu.png");

        // set scene
        Group root = new Group(image);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // add buttons
        Button playBtn = createButton(root, "mm", 150, 150, 484);
        Button insBtn = createButton(root, "mm", 150, 275, 484);
        Button exitBtn = createButton(root, "mm", 150, 400, 484);

        // add onclick
        playBtn.setOnAction(e -> {
            System.out.println("Play");
        });

        insBtn.setOnAction(e -> {
            System.out.println("Instructions");
        });

        exitBtn.setOnAction(e -> {
            // https://stackoverflow.com/questions/2909898/how-to-exit-a-java-application
            Platform.exit();
        });

        stage.show();
    }
}
