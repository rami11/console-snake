package canvas;

public class Fruit implements Tile {
    Position position;

    public Fruit(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
