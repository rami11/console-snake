package canvas;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Tile {
    private OnStepForward listener;
    private Direction direction;
    private List<Position> corePositions;

    public Snake(Position position, OnStepForward listener) {
        this.direction = Direction.LEFT;
        this.corePositions = new ArrayList<>();
        this.corePositions.add(position);
        this.listener = listener;
    }

    public void stepForward() {
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
        System.out.println("Yam yam!");
        extendCore();
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
    }

    private void updateCorePositions() {
        Position headPosition = corePositions.get(0);
        corePositions.remove(corePositions.size() - 1);

        Position position = new Position(headPosition.getX(), headPosition.getY());
        switch (direction) {
            case UP:
                position.setX(headPosition.getX() - 1);
                break;
            case DOWN:
                position.setX(headPosition.getX() + 1);
                break;
            case LEFT:
                position.setY(headPosition.getY() - 1);
                break;
            case RIGHT:
                position.setY(headPosition.getY() + 1);
                break;
        }
        corePositions.add(0, position);
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
