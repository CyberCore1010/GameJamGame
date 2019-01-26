package objects.handlers;

import game.Game;

import objects.gameObjects.*;
import objects.misc.ObjectList;
import objects.misc.PathList;

import java.awt.geom.Point2D;

public class StateHandler {
    private Game game;

    public StateHandler(Game game) {
        this.game = game;
        ObjectList<ObjectList<Node>> nodeList = game.grid.getNodes();
        game.grid.setJunctions(nodeList);
        for(ObjectList<Node> row : nodeList){
            for(Node node : row){
                game.objectHandler.add(node);
            }
        }

        //Floor
        game.objectHandler.add(new Floor(300, 299, 400, 301, game));//Utility Room
        game.objectHandler.add(new Floor(501, 299, 400, 301, game));//Kitchen
        game.objectHandler.add(new Floor(900, 400, 100, 201, game));//Stairs
        game.objectHandler.add(new Floor(1000, 400, 200, 201, game));//Study1
        game.objectHandler.add(new Floor(1200, 400, 150, 201, game));//Study2
        game.objectHandler.add(new Floor(501, 600, 700, 101, game));//HallwayH1
        game.objectHandler.add(new Floor(1201, 601, 100, 550, game));//HallwayH3

        //PathList pathList = new PathList(new Node(100,100,game),game);
        //pathList.add(new Node(100,200,game));
        //game.objectHandler.add(new Hunter(400, 100, game,pathList));
        //game.objectHandler.add(new RoomBounds(0, 0, 300, 500, game));

        //Room Bounds
        game.objectHandler.add(new RoomBounds(501, 299, 400, 301, game));//Kitchen
        game.objectHandler.add(new RoomBounds(1000, 400, 200, 200, game));//Study1
        game.objectHandler.add(new RoomBounds(1200, 400, 150, 201, game));//Study2
        game.objectHandler.add(new RoomBounds(501, 600, 700, 101, game));//HallwayH1
        game.objectHandler.add(new RoomBounds(1201, 601, 100, 550, game));//HallwayH3

        //Player
        game.objectHandler.add(new Player(747,447,1,50,50,game));

        //Utility Room
        game.objectHandler.add(new Wall(297, 395, 200, 10, game));
        game.objectHandler.add(new Wall(295, 395, 10, 160, game));
        game.objectHandler.add(new Wall(295, 545, 80, 10, game));
        game.objectHandler.add(new Wall(425, 545, 80, 10, game));

        //Kitchen
        game.objectHandler.add(new Wall(497, 595, 308, 10, game));
        game.objectHandler.add(new Wall(497, 300, 10, 325, game));
        game.objectHandler.add(new Wall(497, 295, 409, 10, game));
        game.objectHandler.add(new Wall(896, 300, 10, 305, game));
        game.objectHandler.add(new Wall(875, 595, 30, 10, game));

        //Stairs
        game.objectHandler.add(new Wall(900, 396, 100, 10, game));

        //Study1
        game.objectHandler.add(new Wall(996, 396, 10, 209, game));
        game.objectHandler.add(new Wall(996, 396, 209, 10, game));
        game.objectHandler.add(new Wall(1196, 396, 10, 79, game));
        game.objectHandler.add(new Wall(996, 595, 79, 10, game));
        game.objectHandler.add(new Wall(1125, 595, 100, 10, game));
        game.objectHandler.add(new Wall(1196, 525, 10, 100, game));

        //Study2
        game.objectHandler.add(new Wall(1205, 396, 140, 10, game));
        game.objectHandler.add(new Wall(1345, 396, 10, 200, game));
        game.objectHandler.add(new Wall(1275, 595, 80, 10, game));

        //HallwayH1
        game.objectHandler.add(new Wall(497, 675, 10, 50, game));
        game.objectHandler.add(new Wall(497, 696, 328, 10, game));
        game.objectHandler.add(new Wall(875, 696, 328, 10, game));

        //HallwayH3
        game.objectHandler.add(new Wall(1295, 600, 10, 650, game));
        game.objectHandler.add(new Wall(1195, 675, 10, 300, game));
        game.objectHandler.add(new Wall(1195, 1025, 10, 125, game));
        game.objectHandler.add(new Wall(1275, 1145, 30, 10, game));
    }

    public void update(){

    }
}
