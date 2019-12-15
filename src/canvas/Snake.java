package canvas;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Tile, Movable {
    ValueChangeListener listener;
    private Direction direction;
    private List<Position> corePositions;

    public Snake(ValueChangeListener listener) {
        this(listener, new Position(8, 8));
    }

    public Snake(ValueChangeListener listener, Position position) {
        this.direction = Direction.UP;
        this.corePositions = new ArrayList<>();
        this.corePositions.add(position);
        this.listener = listener;
    }

    @Override
    public void moveUp() {
        updateCorePositions(Direction.UP);
        listener.notifyValueChanged();
    }

    @Override
    public void moveDown() {
        updateCorePositions(Direction.DOWN);
        listener.notifyValueChanged();
    }

    @Override
    public void moveLeft() {
        updateCorePositions(Direction.LEFT);
        listener.notifyValueChanged();
    }

    @Override
    public void moveRight() {
        updateCorePositions(Direction.RIGHT);
        listener.notifyValueChanged();
    }

    public Position getHeadPosition() {
        return corePositions.get(0);
    }

    public List<Position> getCorePositions() {
        return corePositions;
    }

    public synchronized Direction getDirection() {
        return direction;
    }

    public synchronized void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void updateCorePositions(Direction direction) {
        Position headPosition = corePositions.get(0);
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
                headPosition.setX(headPosition.getY() - 1);
                break;
            case RIGHT:
                headPosition.setX(headPosition.getY() + 1);
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

    public interface ValueChangeListener {
        void notifyValueChanged();
    }
}
