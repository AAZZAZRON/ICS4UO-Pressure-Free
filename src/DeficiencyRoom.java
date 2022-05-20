import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
public class DeficiencyRoom extends Room {
    /** The primary stage for this application. Passed by reference. */
    private final Stage stage;

    public static int counter = 1;
    Room defRooms [] = new DeficiencyRoom[5];

    /**
     * Constructor for DeficiencyRoom.
     * @param stage The primary stage for this application. Passed by reference.
     */
    public DeficiencyRoom(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return ImageView of the selected image
     * @param path
     */
    public ImageView displayLesson (String path) {
        ImageView image = new ImageView("Assets/Room/Lessons/"+path+".png");
        image.setPreserveRatio(true);
        image.setFitWidth(786);
        return image;
    }

    /**
     * sets up the GUI for deficiency room
     * sets up deficiency room logic flow
     */
    public void deficiencyRoom () {
        ImageView bg = Tools.createBackgroundImage("Room/ClassBg.png");
        // set scene
        Group root = new Group();
        // add background
        root.getChildren().add(bg);
        // add Lesson
        root.getChildren().add(displayLesson("Lesson "+counter));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // add button
        ImageView backButton = Tools.createButton(root, "MainMenu/Buttons/", "x", 650, 170, 40);
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
