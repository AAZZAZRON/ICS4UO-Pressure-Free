/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Tools.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1 (Aaron)
 * - added template to create hoverable button
 * - added template to create background image
 *
 * Version 3 (Aaron)
 * - displayIntermissionText() to transition between levels
 *
 * Version 3 (Sion)
 * - addFade() to fade in components of a scene
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

/**
 * @author Aaron Zhu
 * May 28th, 2022
 * @version 3.0
 * Time: 1 hour
 * createButton() and createBackgroundImage() now require the Assets/ in the path
 * displayIntermissionText()
 * - displays text on the screen
 * - returns the imageview of the button that the user can click on to continue
 */

/**
 * @author Sion Gang
 * May 31th, 2022
 * @version 3.0
 * Time: 10 minutes
 * addFade() method to fade in nodes
 * - added overloaded method for different fade lengths
 */

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

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
     * Create a static button (panic room) and add it to root
     * @param root the root of the scene
     * @param path the path to the image
     * @param x the x coordinate of the button
     * @param y the y coordinate of the button
     * @param sz the size of the button
     */
    public static void createStaticImage(Group root, String path, int x, int y, int sz) {
        ImageView image = new ImageView(path);
        image.setFitWidth(sz);
        image.setFitHeight(sz);
        image.setX(x);
        image.setY(y);
        root.getChildren().add(image);
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
        Image buttonImg = new Image(path + fileName + "Button.png");
        Image buttonImgHover = new Image(path + fileName + "ButtonHover.png");
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
        ImageView image = new ImageView(path);
        //image.setPreserveRatio(true);
        image.setFitWidth(800);
        image.setFitHeight(600);
        return image;
    }

    /**
     * Displays intermission text on screen (between lessons)
     * @param stage The primary stage for this application
     * @param text The text to display
     * @return The imageview of the button that the user can click on to continue
     */
    public static ImageView displayIntermissionText(Stage stage, String text) {
        ImageView image = Tools.createBackgroundImage("Assets/MainMenu/MainMenuBackground.png");
        image.setOpacity(0.1);

        Group root = new Group(image);
        Scene scene = new Scene(root);
        Text message = new Text(text);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setWrappingWidth(700);

        message.setX((800 - message.getLayoutBounds().getWidth()) / 2);
        message.setY((600 - message.getLayoutBounds().getHeight()) / 2);
        System.out.println(message.getX() + " " + message.getY() + " " + message.getLayoutBounds().getWidth() + " " + message.getLayoutBounds().getHeight());
        message.setFill(Color.BLACK);
        message.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        root.getChildren().add(message);
        stage.setScene(scene);

        ImageView next = createButton(root, "Assets/Buttons/", "x", 50, 50, 50);
        Tools.addFadeOn(next);
        Tools.addFadeOn(message);
        return next;
    }

    /**
     * Adds a fade-on transition to a node.
     * @param item The node to add the fade transition to.
     * @return The fade transition object.
     */
    public static Node addFadeOn(Node item) {
        /** Stores transition */
        FadeTransition fade = new FadeTransition();
        // Fade animation
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(item);
        fade.play();
        return item;
    }

    /**
     * Adds a fade-off transition to a node.
     * @param item The node to add the fade transition to.
     * @return The fade transition object.
     */
    public static Node addFadeOff(Node item) {
        /** Stores transition */
        FadeTransition fade = new FadeTransition();
        // Fade animation
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setNode(item);
        fade.play();
        return item;
    }

    /**
     * Adds a fade transition to a node.
     * @param item The node to add the fade transition to.
     * @param ms The duration of the fade transition in milliseconds.
     * @return The node with the fade transition.
     */
    public static Node addFadeOn(Node item, int ms) {
        /** Stores transition */
        FadeTransition fade = new FadeTransition();
        // Fade animation
        fade.setDuration(Duration.millis(ms));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(item);
        fade.play();
        return item;
    }
}

