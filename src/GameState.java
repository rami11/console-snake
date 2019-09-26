import canvas.*;
import canvas.Snake.Direction;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private static final Ground GROUND = new Ground();
    private static final Tail TAIL = new Tail();

    private int m;
    private int n;

    private Tile[][] canvas;
    private Snake snake;

    private List<ActionClickListener> actionClickEvents;

    public GameState(Tile[][] canvas) {
        actionClickEvents = new ArrayList<>();

        this.canvas = canvas;
        m = canvas.length;      // rows
        n = canvas[0].length;   // columns
        snake = new Snake(new Position(8, 8));
    }

    private void display() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(canvas[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void changeSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    public void moveSnake() {
        actionClickEvents.forEach(event -> event.actionClick());
        if (isInCanvas(snake.getPosition())) {
            Direction direction = snake.getDirection();
            switch (direction) {
                case UP:
                    snake.moveUp();
                    putSnakeOnCanvas();
                    break;
                case DOWN:
                    snake.moveDown();
                    putSnakeOnCanvas();
                    break;
                case LEFT:
                    snake.moveLeft();
                    putSnakeOnCanvas();
                    break;
                case RIGHT:
                    snake.moveRight();
                    putSnakeOnCanvas();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }
            display();
        } else {
            System.out.println("*************");
            System.out.println("* GAME OVER *");
            System.out.println("*************");
            System.exit(0);
        }
    }

    private void putSnakeOnCanvas() {
        int x = snake.getPositionX();
        int y = snake.getPositionY();
        Tile tile = canvas[x][y];
        if (tile instanceof Ground) {
            canvas[x][y] = snake;
        } else if (tile instanceof Fruit) {
            canvas[x][y] = snake;
        } else if (tile instanceof Wall) {
            // do nothing
        }
    }

    private boolean isInCanvas(Position position) {
        int x = position.getX();
        int y = position.getY();
        return (x > 0 && x < m - 1) && (y > 0 && y < n - 1);
    }

    public void onActionClick(ActionClickListener listener) {
        actionClickEvents.add(listener);
    }

    interface ActionClickListener {
        void actionClick();
    }
}
