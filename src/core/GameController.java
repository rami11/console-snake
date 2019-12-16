package core;

import canvas.Snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameController {
    private GameState state;

    public GameController(GameState state) throws InterruptedException {
        this.state = state;

        new Thread(() -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String action = reader.readLine();
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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        play();
    }

    private void play() throws InterruptedException {
        while (true) {
            state.moveSnake();
        }
    }
}
