import canvas.*;
import canvas.Snake.Direction;

import java.util.ArrayList;
import java.util.List;

public class GameState implements Snake.ValueChangeListener {
    private int m;
    private int n;

    private Tile[][] canvas;
    private Snake snake;

    private List<ActionButtonClickListener> actionButtonClickEvents;

    public GameState(Tile[][] canvas) {
        actionButtonClickEvents = new ArrayList<>();

        this.canvas = canvas;
        m = canvas.length;
        n = canvas[0].length;
        snake = new Snake(this);
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
    }

    public void moveSnake() {
        if (isSnakeInCanvas(snake.getHeadPosition())) {
            Direction direction = snake.getDirection();
            switch (direction) {
                case UP:
                    snake.moveUp();
                    break;
                case DOWN:
                    snake.moveDown();
                    break;
                case LEFT:
                    snake.moveLeft();
                    break;
                case RIGHT:
                    snake.moveRight();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }
        } else {
            System.out.println("*************");
            System.out.println("* GAME OVER *");
            System.out.println("*************");
            System.exit(0);
        }
    }

    private void putSnakeOnCanvas() {
        //todo: handle snake hits fruit
        snake.getCorePositions().forEach(position -> {
            canvas[position.getX()][position.getY()] = snake;
        });
    }

    private boolean isSnakeInCanvas(Position position) {
        int x = position.getX();
        int y = position.getY();
        return (x > 0 && x < m - 1) && (y > 0 && y < n - 1);
    }

    public void onActionButtonClick(ActionButtonClickListener listener) {
        actionButtonClickEvents.add(listener);
    }

    @Override
    public void notifyValueChanged() {
        display();
    }

    interface ActionButtonClickListener {
        void actionButtonClick();
    }
}
