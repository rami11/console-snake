package util;

import java.util.Random;

public class Util {

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().orElse(1);
    }

    public static int getRandomInt(int max) {
        return getRandomInt(1, max);
    }
}
