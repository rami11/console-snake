package canvas;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Tile, Movable {
    private Direction direction;
    private List<Position> corePositions;
    private Position position;

    public Snake(Position position) {
        direction = Direction.UP;
        corePositions = new ArrayList<>();
        corePositions.add(position);
    }

    @Override
    public void moveUp() {
        corePositions.forEach(position -> position.setX(position.getX() - 1));
    }

    @Override
    public void moveDown() {
        corePositions.forEach(position -> position.setX(position.getX() + 1));
    }

    @Override
    public void moveLeft() {
        corePositions.forEach(position -> position.setY(position.getY() - 1));
    }

    @Override
    public void moveRight() {
        corePositions.forEach(position -> position.setY(position.getY() + 1));
    }

    public Position getPosition() {
        return corePositions.get(0);
    }

    public int getPositionX() {
        return corePositions.get(0).getX();
    }

    public int getPositionY() {
        return corePositions.get(0).getY();
    }

    public synchronized Direction getDirection() {
        return direction;
    }

    public synchronized void setDirection(Direction direction) {
        this.direction = direction;
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
}
