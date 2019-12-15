import canvas.Ground;
import canvas.Position;
import canvas.Snake;
import canvas.Snake.Direction;
import canvas.Tile;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private static final Ground GROUND = new Ground();
    List<Position> oldSnakeCorePositions = new ArrayList<>();

    private int m;
    private int n;

    private Tile[][] canvas;
    private Snake snake;

    public GameState(Tile[][] canvas) {
        this.canvas = canvas;
        m = canvas.length;
        n = canvas[0].length;
        snake = new Snake(() -> {
            display();
            System.out.println();
        });
        display();
    }

    private void display() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(canvas[i][j] + " ");
            }
            System.out.println();
        }
        putSnakeOnCanvas();
    }

    public void changeSnakeDirection(Direction direction) {
        snake.setDirection(direction);
        System.out.println("Direction now: " + direction);
    }

    public void moveSnake() {
        if (isSnakeOnCanvas(snake.getHeadPosition())) {
            snake.stepForward();
        } else {
            System.out.println("*************");
            System.out.println("* GAME OVER *");
            System.out.println("*************");
            System.exit(0);
        }
    }

    private void putSnakeOnCanvas() {
        //todo: handle snake hits fruit

//        oldSnakeCorePositions.forEach(position -> canvas[position.getX()][position.getY()] = GROUND);
//        System.out.println("old positions:");
//        oldSnakeCorePositions.forEach(position -> System.out.print(position + " "));
//        System.out.println();
//
        oldSnakeCorePositions.forEach(position -> canvas[position.getX()][position.getY()] = GROUND);
        System.out.println("new positions:");
        snake.getCorePositions().forEach(position -> System.out.print(position + " "));
        System.out.println();

        snake.getCorePositions().forEach(position -> {
            canvas[position.getX()][position.getY()] = snake;
        });
        oldSnakeCorePositions.clear();
        oldSnakeCorePositions.addAll(snake.getCorePositions());
    }

    private boolean isSnakeOnCanvas(Position position) {
        int x = position.getX();
        int y = position.getY();
        return (x > 0 && x < m - 1) && (y > 0 && y < n - 1);
    }
}
