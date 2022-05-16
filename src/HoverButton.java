import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HoverButton {
    public static Button createButton(Group root, String text, int x, int y, int sz) {
        // https://www.demo2s.com/java/javafx-button-setbackground-background-value.html
        // create button and hover
        ImageView buttonImg = new ImageView(new Image("Assets/MainMenu/" + text + "Button.png"));
        ImageView buttonImgHover = new ImageView(new Image("Assets/MainMenu/" + text + "ButtonHover.png"));
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
}
