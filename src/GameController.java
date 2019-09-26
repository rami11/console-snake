import canvas.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameController extends Component implements KeyListener {
    private GameState state;

    private Thread playThread;

    public GameController(GameState state) {
        this.state = state;
        this.state.onActionClick(() -> {
            Thread thread = new Thread(() -> {
                try {
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
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.setPriority(9);
            thread.start();
        });
        play();
    }

    private void play() {
        playThread = new Thread(() -> {
            while (true) {
                state.moveSnake();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        playThread.setPriority(1);
        playThread.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key Typed!");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
