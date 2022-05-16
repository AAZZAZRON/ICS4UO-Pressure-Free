import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game {
    MainMenu mainMenu;
    Stage stage;

    public Game(Stage stage) {
        mainMenu = new MainMenu();
        this.stage = stage;
    }

    public void screenSetup() {
        stage.setTitle("Parallel Studios - Pressure-Free");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.setResizable(false);

        // add logo
        Image logo = new Image("Assets/logo.png");
        stage.getIcons().add(logo);
    }

    public void splashScreen() {
        System.out.println("Splash Screen");
    }

    public void play() {
        mainMenu.mainMenu(stage);
    }
}
