package canvas;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Tile {
    private static final int INIT_SPEED = 1000;
    private static final int SPEED_CONST = 50;

    boolean needToGrow;
    private int speed;
    private Direction direction;
    private List<Position> corePositions;

    private OnStepForward listener;

    public Snake(Position position, OnStepForward listener) {
        this.direction = Direction.LEFT;
        this.corePositions = new ArrayList<>();
        this.corePositions.add(position);
        this.speed = INIT_SPEED;
        this.needToGrow = false;
        this.listener = listener;
    }

    public boolean didReachSpeedCapacity() {
        return speed <= 200;
    }

    public void goFaster() {
        this.speed -= SPEED_CONST;
    }

    public void stepForward() throws InterruptedException {
        Thread.sleep(speed);
        if (needToGrow) {
            extendCore();
        }
        updateCorePositions();
        listener.notifyStepForward();
    }

    public Position getHeadPosition() {
        return corePositions.get(0);
    }

    public List<Position> getCorePositions() {
        return corePositions;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void eatFruit(OnEatFruit listener) {
        System.out.println("nom nom nom!");
        needToGrow = true;
        listener.notifyEatFruit();
    }

    private void extendCore() {
        Position headPosition = getHeadPosition();
        switch (direction) {
            case UP:
                corePositions.add(0, new Position(headPosition.getX() - 1, headPosition.getY()));
                break;
            case DOWN:
                corePositions.add(0, new Position(headPosition.getX() + 1, headPosition.getY()));
                break;
            case LEFT:
                corePositions.add(0, new Position(headPosition.getX(), headPosition.getY() - 1));
                break;
            case RIGHT:
                corePositions.add(0, new Position(headPosition.getX(), headPosition.getY() + 1));
                break;
        }
        needToGrow = false;
    }

    private void updateCorePositions() {
        Position headPosition = corePositions.get(0);

        corePositions.remove(corePositions.size() - 1);

        Position newHeadPosition = new Position(headPosition.getX(), headPosition.getY());
        switch (direction) {
            case UP:
                newHeadPosition.setX(headPosition.getX() - 1);
                break;
            case DOWN:
                newHeadPosition.setX(headPosition.getX() + 1);
                break;
            case LEFT:
                newHeadPosition.setY(headPosition.getY() - 1);
                break;
            case RIGHT:
                newHeadPosition.setY(headPosition.getY() + 1);
                break;
        }
        corePositions.add(0, newHeadPosition);
    }

    @Override
    public String toString() {
        return "@";
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public interface OnStepForward {
        void notifyStepForward();
    }

    public interface OnEatFruit {
        void notifyEatFruit();
    }
}
