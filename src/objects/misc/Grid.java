package objects.misc;

import game.Game;
import objects.gameObjects.Node;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.FormatFlagsConversionMismatchException;

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

    public int[][] getMatrix() {
        return matrix;
    }

    public ObjectList<Node> getNodes(Game game){
        ObjectList<Node> nodes = new ObjectList<>();
        for (int row = 0;row < matrix.length;row++){
            for (int column = 0; column < matrix[0].length; column ++){
                Node temp = new Node(row*Node.size,column*Node.size,game);
                if(matrix[row][column] == 1){
                    temp.setColor(Color.green);
                }
                else if(matrix[row][column] == 0){
                    temp.setColor(Color.red);
                }
                nodes.add(temp);
            }
        }
        return nodes;
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
                }
                if(red == 0 && green == 255 && blue == 0) {
                    matrix[x][y] = 1;
                }
            }
        }
    }
}
