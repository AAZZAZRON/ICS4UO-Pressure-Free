/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * TextBox.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 3.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 2 (Aaron)
 * - initialize textBox + message
 * - display and hide textBox
 *
 * Version 3 (Aaron)
 * - moved warning textboxes to TextBox.java
 */

/**
 * @author Aaron Zhu
 * May 21st, 2022
 * @version 2.0
 * Time: 20 minutes
 * Set up TextBox framework + helper methods
 */

/**
 * @author Aaron Zhu
 * May 30th, 2022
 * @version 3.0
 * Time: 30 minutes
 * moved warning text boxes to Textbox.java
 */

import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

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
        // initialize textbox
        textBox = new ImageView("Assets/Textboxes/Textbox" + colour + ".png");
        textBox.setPreserveRatio(true);
        textBox.setFitWidth(750);
        textBox.setX(25);
        textBox.setY(30);
        textBox.setOpacity(0.8);

        // initialize message
        message = new Text();
        message.setX(50);
        message.setY(70);
        message.setWrappingWidth(700);
        message.setFill(Color.BLACK);
        message.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
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
        message.setText(text);
    }

    public void setTextboxColour(String colour) {
        textBox.setImage(new Image("Assets/Textboxes/Textbox" + colour + ".png"));
    }

    /**
     * creates a warning textbox if user makes mistake
     * @param seconds the amount of time to display the warning
     */
    public void setWarning(int seconds) {
        toggleOn();
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(e -> {
            toggleOff();
        });
        pause.play();
    }

    /**
     * is the textbox open
     * @return is the textbox open
     */
    public boolean isVisible() {
        return isVisible;
    }
}
