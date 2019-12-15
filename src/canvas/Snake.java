package canvas;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Tile {
    private OnStepForward listener;
    private Direction direction;
    private List<Position> corePositions;

    private Position oldTailPosition;

    public Snake(OnStepForward listener) {
        //todo: set random position
        this(listener, new Position(8, 8));
    }

    public Snake(OnStepForward listener, Position position) {
        this.direction = Direction.LEFT;
        this.corePositions = new ArrayList<>();
        this.corePositions.add(position);
        this.listener = listener;

        oldTailPosition = position;
    }

    public void stepForward() {
        updateCorePositions(this.direction);
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

    public Position getOldTailPosition() {
        return oldTailPosition;
    }

    private void updateCorePositions(Direction direction) {
        Position headPosition = corePositions.get(0);
        this.oldTailPosition = corePositions.get(corePositions.size() - 1);
        for (int i = 0; i < corePositions.size() - 1; i++) {
            corePositions.set(i, corePositions.get(i + 1));
        }
        corePositions.remove(corePositions.size() - 1);

        switch (direction) {
            case UP:
                headPosition.setX(headPosition.getX() - 1);
                break;
            case DOWN:
                headPosition.setX(headPosition.getX() + 1);
                break;
            case LEFT:
                headPosition.setY(headPosition.getY() - 1);
                break;
            case RIGHT:
                headPosition.setY(headPosition.getY() + 1);
                break;
        }
        corePositions.add(0, headPosition);
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
}
