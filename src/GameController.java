import canvas.Snake;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameController extends Component {
    private GameState state;

    public GameController(GameState state) throws InterruptedException {
        this.state = state;
        /*try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String action;
            while (true) {
                action = reader.readLine();
                switch (action) {
                    case "a":
                        state.changeSnakeDirection(Snake.Direction.LEFT);
                        break;
                    case "d":
                        state.changeSnakeDirection(Snake.Direction.RIGHT);
                        break;
                    case "w":
                        state.changeSnakeDirection(Snake.Direction.UP);
                        break;
                    case "s":
                        state.changeSnakeDirection(Snake.Direction.DOWN);
                        break;
                }
                state.moveSnake();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        play();
    }

    private void play() throws InterruptedException {
        while (true) {
            state.moveSnake();
            Thread.sleep(1000);
        }
    }
}
