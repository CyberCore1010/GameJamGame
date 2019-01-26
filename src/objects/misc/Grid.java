package objects.misc;

import java.awt.image.BufferedImage;

public class Grid {

    private int[][] matrix = new int[19][19];
    public Grid(){
        BufferedImageLoader loader = new BufferedImageLoader();
        readGridFromFile(loader.loadImage("/map/Floor1.png"));
        for(int[] row : matrix) {
            System.out.println();
            for(int number : row) {
                System.out.print(number+",");
            }
        }
    }

    private void readGridFromFile(BufferedImage image) {
        int w = image.getWidth(); //gets the width of the image
        int h = image.getHeight(); //gets the height of the image

        for(int y = 0; y < h; y++) { //first for loop, starts in top right, progressively goes to the down
            for(int x = 0; x < w; x++) { //second for loop, starts in top right, progressively moves right.
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255 && green == 0 && blue == 0) {
                    matrix[x][y] = 0;
                    System.out.print("0,");
                }
                if(red == 0 && green == 255 && blue == 0) {
                    matrix[x][y] = 1;
                    System.out.print("1,");
                }
            }
            System.out.println();
        }
    }
}
