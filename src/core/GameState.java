package core;

import canvas.*;
import canvas.Snake.Direction;
import util.Util;

public class GameState {
    private static int score = 0;
    private static int stepCount = 0;

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
            stepCount++;
        });
        fruit = new Fruit(getRandomPositionInCanvas());
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
            snake.eatFruit(() -> {
                score++;
                fruit.setPosition(getRandomPositionInCanvas());
            });
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
        System.out.printf("Score: %d, Steps: %d%n", score, stepCount);
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

    public Position getRandomPositionInCanvas() {
        return new Position(Util.getRandomInt(m - 2), Util.getRandomInt(n - 2));
    }
}
