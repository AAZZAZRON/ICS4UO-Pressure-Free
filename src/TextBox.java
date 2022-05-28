/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * Character.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 2.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 2 (Aaron)
 * - initialize textBox + message
 * - display and hide textBox
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 20 minutes
 * Set up TextBox framework + helper methods
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextBox {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;

    /** group for the textbox */
    private Group root;

    /** scene for the textbox */
    private Scene scene;

    /** message displayed in the textbox */
    private Text message;

    /** the textbox image */
    private ImageView textBox;

    /** is the textbox open */
    private boolean isVisible;

    /**
     * Constructor for the TextBox class
     * @param stage the primary stage for this application
     * @param root the group for the textbox
     * @param scene the scene for the textbox
     * @param text the message displayed in the textbox
     * @param colour the colour of the textbox
     */
    public TextBox(Stage stage, Group root, Scene scene, String text, String colour) {
        this.stage = stage;
        this.root = root;
        this.scene = scene;
        isVisible = false;

        initialize(colour);
        setMessage(text);
    }

    /**
     * Initialize the textbox
     */
    private void initialize(String colour) {
        textBox = new ImageView("Assets/School/Textboxes/Textbox" + colour + ".png");
        textBox.setPreserveRatio(true);
        textBox.setFitWidth(750);
        textBox.setX(25);
        textBox.setY(30);
        textBox.setOpacity(0.75);
    }

    /**
     * Display the textbox
     */
    private void display() {
        root.getChildren().add(textBox);
        root.getChildren().add(message);
    }

    /**
     * Hide the textbox
     */
    private void hide() {
        root.getChildren().remove(textBox);
        root.getChildren().remove(message);
    }

    /**
     * Show the textbox
     */
    public void toggleOn() {
        if (!isVisible) {
            display();
            isVisible = true;
        }
    }

    /**
     * Hide the textbox
     */
    public void toggleOff() {
        if (isVisible) {
            hide();
            isVisible = false;
        }
    }

    /**
     * Set the message displayed in the textbox
     * @param text the message to be displayed
     */
    public void setMessage(String text) {
        message = new Text(text);
        message.setX(50);
        message.setY(70);
        message.setWrappingWidth(700);
        message.setFill(Color.BLACK);
        message.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    }

    public void setTextboxColour(String colour) {
        textBox.setImage(new Image("Assets/School/Textboxes/Textbox" + colour + ".png"));
    }
}
