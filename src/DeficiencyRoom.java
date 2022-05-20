/**
 * Pressure-Free, a game by Parallel Studios to teach users to overcome peer pressure
 * DeficiencyRoom.java
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 1.0
 * @author Aaron Zhu, Sion Gang
 *
 * Version 1
 * - Deficiency Room that displays the different lessons
 * - mostly framework at this point
 */



import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class DeficiencyRoom extends Room {

    private FadeTransition fade = new FadeTransition();


    public static int counter = 1;
    Room[] defRooms = new DeficiencyRoom[4];

    /**
     * Constructor for DeficiencyRoom.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public DeficiencyRoom(Stage stage) {
        super(stage);
    }

    /**
     * @return ImageView of the selected image
     * @param path
     */
    public ImageView displayLesson (String path) {
        ImageView image = new ImageView("Assets/Rooms/Lessons/" +path+".png");
        image.setPreserveRatio(true);
        image.setFitWidth(786);

        // Fade animation
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(image);
        fade.play();
        return image;
    }

    /**
     * sets up the GUI for deficiency room
     * sets up deficiency room logic flow
     */
    public void deficiencyRoom () {
        ImageView bg = Tools.createBackgroundImage("Rooms/ClassBg.png");
        // set scene
        Group root = new Group();
        // add background
        root.getChildren().add(bg);
        // add Lesson
        root.getChildren().add(displayLesson("Lesson " + counter));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // add button
        ImageView backButton = Tools.createButton(root, "MainMenu/Buttons/", "x", 650, 170, 40);
        fade.setDuration(Duration.millis(500));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(backButton);
        fade.play();

        backButton.setOnMouseClicked(e -> {
            // increments counter for the different rooms
            if (counter < defRooms.length) counter++;
            else counter = 1;
            System.out.println(counter);

            MainMenu mainMenu = new MainMenu(stage);
            mainMenu.mainMenu();
        });
    }



    public void exit() {


    }
}
