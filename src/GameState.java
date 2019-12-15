import canvas.*;
import canvas.Snake.Direction;

public class GameState {
    private static final Ground GROUND = new Ground();
    private static final Wall WALL = new Wall();
    private static final Fruit FRUIT = new Fruit();

    private static Position fruitPosition;

    private int m;
    private int n;

    private Tile[][] canvas;
    private Snake snake;

    public GameState(Tile[][] canvas) {
        fruitPosition = new Position((int) (Math.random() * 13) + 1, (int) (Math.random() * 14) + 1);
        System.out.println("Fruit position: " + fruitPosition);
        this.canvas = canvas;
        m = canvas.length;
        n = canvas[0].length;
        snake = new Snake(() -> {
            display();
        });
    }

    private void display() {
        updateCanvas();
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
        if (!isSnakeNextToWall()) {
            snake.stepForward();
        } else {
            System.out.println("*************");
            System.out.println("* GAME OVER *");
            System.out.println("*************");
            System.exit(0);
        }
    }

    private void updateCanvas() {
        //todo: handle snake hits fruit

        clearCanvas();

        // put fruit on canvas
        canvas[fruitPosition.getX()][fruitPosition.getY()] = FRUIT;

        // put snake on canvas
        snake.getCorePositions().forEach(position -> {
            canvas[position.getX()][position.getY()] = snake;
        });
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

    private boolean isSnakeNextToWall() {
        Position headPosition = snake.getHeadPosition();
        int x = headPosition.getX();
        int y = headPosition.getY();
        System.out.println(headPosition);
        return (x <= 1 || x >= m - 2) || (y <= 1 || y >= n - 2);
    }
}
