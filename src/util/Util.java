package util;

import canvas.Ground;
import canvas.Wall;

import java.util.List;
import java.util.Random;

public class Util {
    public static final Ground GROUND = new Ground();
    public static final Wall WALL = new Wall();

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().orElse(1);
    }

    public static int getRandomInt(int max) {
        return getRandomInt(1, max);
    }

    public static <T> void logList(String message, List<T> list) {
        System.out.print(message + " ");
        list.forEach(element -> System.out.print(list.indexOf(element) + ": " + element + " "));
        System.out.println();
    }
}
