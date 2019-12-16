package core;

import canvas.Ground;
import canvas.Tile;
import canvas.Wall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameLoader {
    private static final String CANVAS_PATH = "resources/canvas.txt";

    public GameLoader() {
        loadGame();
    }

    private void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CANVAS_PATH))) {
            String[] dims = reader.readLine().trim().split(" ");
            int m = Integer.parseInt(dims[0]);
            int n = Integer.parseInt(dims[1]);

            Tile[][] canvas = new Tile[m][n];
            for (int i = 0; i < m; i++) {
                String[] rowCells = reader.readLine().trim().split(" ");
                for (int j = 0; j < n; j++) {
                    switch (rowCells[j]) {
                        case "#":
                            canvas[i][j] = new Wall();
                            break;
                        case ".":
                            canvas[i][j] = new Ground();
                            break;
                    }
                }
            }
            GameState gameState = new GameState(canvas);
            GameController controller = new GameController(gameState);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
