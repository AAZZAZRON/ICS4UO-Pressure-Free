import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Character {
    private ImageView character;
    private int sizeX, sizeY;
    private final Stage stage;
    private final int speed = 5;
    private boolean N, E, W, S;
    private int posX, posY;
    private int footOffsetY;
    private int[][] grid;

    public Character(Stage stage, int size, int[][] grid) {
        this.stage = stage;
        this.sizeY = size;
        posX = 0;
        posY = 0;
        footOffsetY = (int) (size * 10 / 11.0);
        sizeX = (int) (size * 3 / 7.0);
        this.grid = grid;
        System.out.println(size + " " + (int) (size * 3 / 7.0));
    }

    public Character(Stage stage, int size, int posX, int posY, int[][] canEnter) {
        this(stage, size, canEnter);
        this.posX = posX;
        this.posY = posY;
    }

    public void build(Group root, Scene scene) {
        character = new ImageView("Assets/Character/characterDown.png");
        character.setPreserveRatio(true);
        character.setFitHeight(sizeY);
        character.setX(posX);
        character.setY(posY);
        root.getChildren().add(character);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    N = true;
                    break;
                case S:
                    S = true;
                    break;
                case A:
                    W = true;
                    break;
                case D:
                    E = true;
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W:
                    N = false;
                    break;
                case S:
                    S = false;
                    break;
                case A:
                    W = false;
                    break;
                case D:
                    E = false;
                    break;
            }
        });

        scene.onMouseClickedProperty().set(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.println("Mouse clicked at: " + event.getX() + ", " + event.getY());
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (N) {
                    posY -= speed;
                    if (maxInstruction() != 0) posY += speed;
                    else changeCharacterDirection("Up");
                }
                if (S) {
                    posY += speed;
                    if (maxInstruction() != 0) posY -= speed;
                    else changeCharacterDirection("Down");
                }
                if (W) {
                    posX -= speed;
                    if (maxInstruction() != 0) posX += speed;
                    else changeCharacterDirection("Left");
                }
                if (E) {
                    posX += speed;
                    if (maxInstruction() != 0) posX -= speed;
                    else changeCharacterDirection("Right");
                }
                character.setX(posX);
                character.setY(posY);

                int instruction = maxInstruction();
            }
        };
        timer.start();
    }

    public int maxInstruction() {
        int maxDetect = 0;
        for (int i = posX; i < posX + sizeX; i++) {
            for (int j = posY + footOffsetY; j < posY + sizeY; j++) {
                maxDetect = Math.max(maxDetect, grid[i][j]);
            }
        }
//        System.out.println(posX + ", " + posY + ": " + maxDetect);
//        System.out.println((posX) + " " + (posY + footOffsetY) + ", " + (posX + sizeX) + " " + (posY + sizeY) + " " + maxDetect);
        return maxDetect;
    }

    public void changeCharacterDirection(String direction) {
        character.setImage(new Image("Assets/Character/character" + direction + ".png"));
    }
}
