package util;

import java.util.Random;


public class Rand {

    public static int getRandom(int left, int right){
        Random random = new  Random();
        return random.nextInt(right);
    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++) {
            System.out.println(getRandom(1, 100));
        }
    }

    public static double nextDouble(final double min, final double max) {
        return min + ((max - min) * new Random().nextDouble());
    }

}
