package canvas;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Tile {
    private OnStepForward listener;
    private Direction direction;
    private List<Position> corePositions;

    private boolean eatFruit;

    public Snake(Position position, OnStepForward listener) {
        this.direction = Direction.LEFT;
        this.corePositions = new ArrayList<>();
        this.corePositions.add(position);
        this.listener = listener;
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

    public void eatFruit(OnEatFruit listener) {
        this.eatFruit = true;
        listener.notifyEatFruit();
        System.out.println("Yam yam!");
    }

    private void updateCorePositions(Direction direction) {
        Position headPosition = corePositions.get(0);
        for (int i = 0; i < corePositions.size() - 1; i++) {
            corePositions.set(i, corePositions.get(i + 1));
        }
        //System.out.println("Eat fruit? " + eatFruit);
        if (!eatFruit) {
            corePositions.remove(corePositions.size() - 1);
        } else {
            System.out.println("don't remove tail position: " + corePositions.get(corePositions.size() - 1));
            eatFruit = false;
        }

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

    public interface OnEatFruit {
        void notifyEatFruit();
    }
}
