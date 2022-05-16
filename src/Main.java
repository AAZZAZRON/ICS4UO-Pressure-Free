import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // set default stage settings
        stage.setTitle("Parallel Studios - Pressure-Free");
        stage.setHeight(600);
        stage.setWidth(800);
        stage.setResizable(false);

        // add logo
        Image logo = new Image("assets/logo.png");
        stage.getIcons().add(logo);

        MainMenu.mainMenu(stage);
    }
}