/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * HoverButton.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 */

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HoverButton {
    /**
     * Template to create a hoverable button.
     * @param root The root of the scene.
     * @param path The path to the normal and hover button images.
     * @param x The x coordinate to place the button
     * @param y The y coordinate to place the button
     * @param sz The width of the button (ratio is preserved)
     * @return The button object so events can be added to it.
     */
    public static Button createButton(Group root, String path, int x, int y, int sz) {
        // https://www.demo2s.com/java/javafx-button-setbackground-background-value.html
        // get images
        ImageView buttonImg = new ImageView(new Image("Assets/MainMenu/" + path + "Button.png"));
        ImageView buttonImgHover = new ImageView(new Image("Assets/MainMenu/" + path + "ButtonHover.png"));
        buttonImg.setPreserveRatio(true);
        buttonImgHover.setPreserveRatio(true);
        buttonImg.setFitWidth(sz);
        buttonImgHover.setFitWidth(sz);

        // create button
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
