package objects.misc;

import game.Game;
import objects.gameObjects.Node;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Grid {
    
    private Game game;
    private ObjectList<ObjectList<Node>> nodeList;
    private int[][] matrix = new int[33][33]; //y, x

    public Grid(Game game){
        this.game = game;
        nodeList = new ObjectList<>();
        BufferedImageLoader loader = new BufferedImageLoader();
        readGridFromFile(loader.loadImage("/map/Floor1.png"));
        generateNodes();
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public ObjectList<ObjectList<Node>> getNodes(){
        return nodeList;
    }

    private void generateNodes(){
        ObjectList<ObjectList<Node>> nodes = new ObjectList<>();
        for (int row = 0;row < matrix.length;row++){
            ObjectList<Node> rowNodes = new ObjectList<Node>();
            for (int column = 0; column < matrix[0].length; column ++){
                Node temp = new Node(column*Node.size,row*Node.size,game);
                if(matrix[row][column] == 1){
                    temp.setColor(Color.green);
                } else if(matrix[row][column] == 0){
                    temp.setColor(Color.red);
                } else if(matrix[row][column] == 2){
                    temp.setColor(Color.blue);
                }
                rowNodes.add(temp);
            }
            nodes.add(rowNodes);
        }
        setJunctions(nodes);
        this.nodeList = nodes;
    }

    @SuppressWarnings("Duplicates")
    public void setJunctions(ObjectList<ObjectList<Node>> nodeList){
        for(ObjectList<Node> row : nodeList){
            for(Node temp : row){
                if(temp.getColor().equals(Color.red) || temp.getColor().equals(Color.blue)){
                    continue;
                }
                else if(!isCorridor(temp)){
                    temp.junction = true;
                }
            }
        }

        for(int row = 0;row < nodeList.size();row++){
            for(int column = 0;column < nodeList.get(0).size();column++){
                Node temp = nodeList.get(row).get(column);
                if(temp.junction){
                    int upCount = 1;
                    int downCount = 1;
                    int leftCount = 1;
                    int rightCount = 1;
                    while (true){
                        Node next = nodeList.get(row-upCount).get(column);
                        if(next.getColor().equals(Color.red) || next.getColor().equals(Color.blue)){
                            break;
                        }
                        else if(next.junction){
                            next.parent = temp;
                            temp.children.add(next);
                            break;
                        }
                        upCount++;
                    }
                    while (true){
                        Node next = nodeList.get(row+downCount).get(column);
                        if(next.getColor().equals(Color.red)||next.getColor().equals(Color.blue)){
                            break;
                        }
                        else if(next.junction){
                            next.parent = temp;
                            temp.children.add(next);
                            break;
                        }
                        downCount++;
                    }
                    while (true){
                        Node next = nodeList.get(row).get(column-leftCount);
                        if(next.getColor().equals(Color.red)||next.getColor().equals(Color.blue)){
                            break;
                        }
                        else if(next.junction){
                            next.parent = temp;
                            temp.children.add(next);
                            break;
                        }
                        leftCount++;
                    }
                    while (true){
                        Node next = nodeList.get(row).get(column+rightCount);
                        if(next.getColor().equals(Color.red)||next.getColor().equals(Color.blue)){
                            break;
                        }
                        else if(next.junction){
                            next.parent = temp;
                            temp.children.add(next);
                            break;
                        }
                        rightCount++;
                    }
                }
            }
        }
    }

    private boolean isCorridor(Node test){
        Point2D.Double testPoint = test.getPoint();
        try{
            boolean up = matrix[(int)(testPoint.y/Node.size) +1][(int)(testPoint.x/Node.size)] != 0;
            boolean down = matrix[(int)(testPoint.y/Node.size)-1][(int)(testPoint.x/Node.size)] != 0;
            boolean left = matrix[(int)(testPoint.y/Node.size)][(int)(testPoint.x/Node.size)-1] != 0;
            boolean right = matrix[(int)(testPoint.y/Node.size)][(int)(testPoint.x/Node.size)+1] != 0;

            if((up && down && !left && !right) || (left && right && !up && !down)){
                return false;
            }
            else {
                return false;
            }
        }catch (Exception e){
            System.out.println("error");
            return false;
        }



    }
    
    private void readGridFromFile(BufferedImage image) {
        int w = image.getWidth(); //gets the width of the image
        int h = image.getHeight(); //gets the height of the image

        for(int y = 0; y < h; y++) { //first for loop, starts in top left, progressively goes to the down
            for(int x = 0; x < w; x++) { //second for loop, starts in top left, progressively moves right.
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

//                System.out.println(blue);

                if(red == 255 && green == 0 && blue == 0) {
                    matrix[y][x] = 0;
                }
                if(red == 0 && green == 255 && blue == 0) {
                    matrix[y][x] = 1;
                }
                if(red == 0 && green == 0 && blue == 255) {
                    matrix[y][x] = 2;
                }
            }
        }
    }

    public void printGrid(){
        for(int[] row: matrix){
            for(int column : row){
                System.out.print(column+" ");
            }
            System.out.print("\n");
        }
    }

    public Node getNearestNode(Point2D.Double point){
        int row = (int)Math.round(point.y/Node.size);
        int column = (int)Math.round(point.x/Node.size);
        return nodeList.get(row).get(column);
    }

    public Node getRandomNode() {

        while(true) {
            int randomRow = (int)Math.floor(Math.random() * nodeList.size());
            int randomColumn = (int)Math.floor(Math.random() * nodeList.get(0).size());
            System.out.println("rand row : "+randomRow+" rand col : "+randomColumn);
            if(nodeList.get(randomRow).get(randomColumn).junction) {
                nodeList.get(randomRow).get(randomColumn).setColor(Color.pink);
                return nodeList.get(randomRow).get(randomColumn);
            }
        }


        //return nodeList.get(15).get(18);
    }
}
