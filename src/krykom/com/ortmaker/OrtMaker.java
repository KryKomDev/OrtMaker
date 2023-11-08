package krykom.com.ortmaker;

import java.util.Random;

public class OrtMaker {

    private long seed;
    private int[][] valueArr;
    private final int size;
    private int density;
    private final int maxValue;
    private final int colorFactor;

    public OrtMaker(long seed, int size, double density, int maxValue, int colorFactor) {
        this.seed = seed;
        this.size = size;
        this.maxValue = maxValue;
        this.colorFactor = colorFactor;
        this.density = (int) ( density * ((this.size * this.size) / 100) );
        valueArr = new int[size][size];
        init();
    }

    public OrtMaker(int size, double density, int maxValue, int colorFactor) {
        this.seed = new Random().nextLong();
        this.size = size;
        this.maxValue = maxValue;
        this.colorFactor = colorFactor;
        this.density = (int) ( density * ((this.size * this.size) / 100) );
        valueArr = new int[size][size];
        init();
    }

    public void init () {
        Random r = new Random();
        r.setSeed(this.seed);
        int x = 0;
        int y = 0;
        boolean occupied;

        // array of point where 1st number is x 2nd is y and 3rd is value
//        int[][] points = new int[2][3];
        int[][] points = new int[density][3];

        for (int i = 0; i < density; i++) {
            occupied = false;

            x = r.nextInt(0, size);
            y = r.nextInt(0, size);

            for (int j = 0; j < density; j++) {
                if (points[j][0] == x && points[j][1] == y) {
                    i--;
                    occupied = true;
                    break;
                }
            }

            if (!occupied) {
                points[i][0] = x;
                points[i][1] = y;
                points[i][2] = r.nextInt(1, maxValue) + colorFactor;
            }
        }
//        points[0][0] = 20;
//        points[0][1] = 20;
//        points[0][2] = 20;
//        points[1][0] = 80;
//        points[1][1] = 80;
//        points[1][2] = 80;

        spread(points);
    }

    private void spread (int[][] points) {

        // x, y distance from points
        for (int j = 1; j < density * 2; j++) {

            // iterate through the points
            for (int i = 0; i < points.length; i++) {

                try {
                    // top
                    for (int x = points[i][0] - j; x < points[i][0] + j + 1; x++) {
                        if (valueArr[x][points[i][1] + j] == 0) valueArr[x][points[i][1] + j] = points[i][2];
                    }

                    // bottom
                    for (int x = points[i][0] - j; x < points[i][0] + j + 1; x++) {
                        if (valueArr[x][points[i][1] - j] == 0) valueArr[x][points[i][1] - j] = points[i][2];
                    }

                    // right
                    for (int y = points[i][0] - j; y < points[i][0] + j + 1; y++) {
                        if (valueArr[points[i][0] + j][y] == 0) valueArr[points[i][0] + j][y] = points[i][2];
                    }

                    // left
                    for (int y = points[i][0] - j; y < points[i][0] + j + 1; y++) {
                        if (valueArr[points[i][0] - j][y] == 0) valueArr[points[i][0] - j][y] = points[i][2];
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }

            }
        }

    }

    public void printNoise () {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                System.out.print(ConsoleColors.hsvToColor(valueArr[x][y], 100, 100) + "   ");
            }
            System.out.println();
        }
    }

    public int get (int x, int y) {
        return valueArr[x][y];
    }

    // getters and setters

    public void setSeed (long seed) {
        this.seed = seed;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public long getSeed () {
        return seed;
    }

    public int[][] getValueArr() {
        return valueArr;
    }

    public int getSize () {
        return size;
    }

    public int getDensity () {
        return density;
    }
}
