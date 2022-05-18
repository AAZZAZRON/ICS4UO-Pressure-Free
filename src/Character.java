import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Character {
    private ImageView character;
    private final Stage stage;
    private final int speed = 5;
    private boolean N, E, W, S;

    public Character(Stage stage) {
        this.stage = stage;
    }

    public void build(Group root, Scene scene) {
        character = new ImageView("Assets/Character/characterDown.png");
        character.setPreserveRatio(true);
        root.getChildren().add(character);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    N = true;
                    changeCharacterDirection("Up");
                    break;
                case DOWN:
                    S = true;
                    changeCharacterDirection("Down");
                    break;
                case LEFT:
                    W = true;
                    changeCharacterDirection("Left");
                    break;
                case RIGHT:
                    E = true;
                    changeCharacterDirection("Right");
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    N = false;
                    break;
                case DOWN:
                    S = false;
                    break;
                case LEFT:
                    W = false;
                    break;
                case RIGHT:
                    E = false;
                    break;
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (N) character.setY(character.getY() - speed);
                if (S) character.setY(character.getY() + speed);
                if (W) character.setX(character.getX() - speed);
                if (E) character.setX(character.getX() + speed);
            }
        };
        timer.start();
    }

    public void changeCharacterDirection(String direction) {
        character.setImage(new Image("Assets/Character/character" + direction + ".png"));
    }
}
