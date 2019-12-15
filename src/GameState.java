import canvas.*;
import canvas.Snake.Direction;

import java.util.Random;

public class GameState {
    private static final Ground GROUND = new Ground();
    private static final Wall WALL = new Wall();

    private int m;
    private int n;

    private Tile[][] canvas;
    private Snake snake;
    private Fruit fruit;

    public GameState(Tile[][] canvas) {
        this.canvas = canvas;
        m = canvas.length;
        n = canvas[0].length;
        snake = new Snake(new Position(8, 8), () -> {
            updateCanvas();
        });
        fruit = new Fruit(new Position(getRandomInt(14), getRandomInt(15)));
    }

    private void updateCanvas() {
        Position headPosition = snake.getHeadPosition();
        Tile tileAtHead = canvas[headPosition.getX()][headPosition.getY()];

        if (tileAtHead instanceof Wall) {
            System.out.println("*************");
            System.out.println("* GAME OVER *");
            System.out.println("*************");
            System.exit(0);
        } else if (tileAtHead instanceof Fruit) {
            //todo: handle snake hits fruit
        }

        clearCanvas();

        // put fruit on canvas
        canvas[fruit.getPosition().getX()][fruit.getPosition().getY()] = fruit;

        // put snake on canvas
        snake.getCorePositions().forEach(position -> {
            canvas[position.getX()][position.getY()] = snake;
        });

        displayCanvas();
    }

    private void displayCanvas() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(canvas[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void changeSnakeDirection(Direction direction) {
        snake.setDirection(direction);
        System.out.println(direction);
    }

    public void moveSnake() {
        snake.stepForward();
    }

    private void clearCanvas() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    canvas[i][j] = WALL;
                } else {
                    canvas[i][j] = GROUND;
                }
            }
        }
    }

//    private boolean isSnakeInCanvasBorder() {
//        Position headPosition = snake.getHeadPosition();
//        int x = headPosition.getX();
//        int y = headPosition.getY();
//        System.out.println(headPosition);
//        return x > 0 && x < m - 1 && y > 0 && y < n - 1;
//    }

    /*
    Utils
     */

    private int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().orElse(1);
    }

    private int getRandomInt(int max) {
        return getRandomInt(1, max);
    }
}
