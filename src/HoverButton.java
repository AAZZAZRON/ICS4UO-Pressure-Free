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
import javafx.scene.Node;
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
    public static ImageView createButton(Group root, String path, int x, int y, int sz) {
        // get images
        Image buttonImg = new Image("Assets/MainMenu/" + path + "Button.png");
        Image buttonImgHover = new Image("Assets/MainMenu/" + path + "ButtonHover.png");

        ImageView btn = new ImageView(buttonImg);
        btn.setPreserveRatio(true);
        btn.setFitWidth(sz);

        // create button
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setOnMouseEntered(e -> btn.setImage(buttonImgHover));
        btn.setOnMouseExited(e -> btn.setImage(buttonImg));

        root.getChildren().add(btn);

        return btn;
    }
}
