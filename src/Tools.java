/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Tools.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron)
 * - added template to create hoverable button
 * - added template to create background image
 */

/**
 * @author Aaron Zhu
 * May 16th, 2022
 * @version 1.0
 * Time: 5 minutes
 * Move createButton() to HoverButton.java
 * static method that would return button
 */

/**
 * @author Aaron Zhu
 * May 17th, 2022
 * @version 1.0
 * Time: 15 minutes
 * Rename HoverButton.java to Tools.java
 * createBackgroundImage()
 * - creates a background image for the scene (with the correct size)
 */

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Tools {
    /**
     * Sets up the screen for the game.
     * Screen dimensions, logo, title, etc.
     * @param stage the primary stage for this application
     */
    public static void screenSetup(Stage stage) {
        stage.setTitle("Parallel Studios - Pressure-Free");
        stage.setHeight(636);
        stage.setWidth(814);
        stage.setResizable(false);

        // add logo
        Image logo = new Image("Assets/logo.png");
        stage.getIcons().add(logo);
    }

    /**
     * Template to create a hoverable button.
     * @param root The root of the scene.
     * @param path The path to the normal and hover button images.
     * @param fileName The name of the image files.
     * @param x The x coordinate to place the button
     * @param y The y coordinate to place the button
     * @param sz The width of the button (ratio is preserved)
     * @return The button object so events can be added to it.
     */
    public static ImageView createButton(Group root, String path, String fileName, int x, int y, int sz) {
        // get images
        Image buttonImg = new Image("Assets/" + path + fileName + "Button.png");
        Image buttonImgHover = new Image("Assets/" + path + fileName + "ButtonHover.png");

        ImageView btn = new ImageView(buttonImg);
        btn.setPreserveRatio(true);
        btn.setFitWidth(sz);

        // create button
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setOnMouseEntered(e -> {
            btn.setImage(buttonImgHover);
            root.setCursor(javafx.scene.Cursor.HAND);
        });
        btn.setOnMouseExited(e -> {
            btn.setImage(buttonImg);
            root.setCursor(javafx.scene.Cursor.DEFAULT);
        });

        root.getChildren().add(btn);

        return btn;
    }

    /**
     * Template to create a background image.
     * @param path The path to the background image.
     * @return The background image object.
     */
    public static ImageView createBackgroundImage(String path) {
        ImageView image = new ImageView("Assets/" + path);
        //image.setPreserveRatio(true);
        image.setFitWidth(800);
        image.setFitHeight(600);
        return image;
    }
}
