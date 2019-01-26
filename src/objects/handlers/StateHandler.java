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
        for(ObjectList<Node> row : nodeList){
            for(Node node : row){
                game.objectHandler.add(node);
            }
        }

        //Kitchen floor
        game.objectHandler.add(new Floor(501, 299, 400, 301, game));

        game.objectHandler.add(new Player(747,447,1,50,50,game));
        //PathList pathList = new PathList(new Node(100,100,game),game);
        //pathList.add(new Node(100,200,game));
        //game.objectHandler.add(new Hunter(400, 100, game,pathList));
        //game.objectHandler.add(new RoomBounds(0, 0, 300, 500, game));

        //Kitchen
        game.objectHandler.add(new RoomBounds(501, 299, 400, 301, game));
        game.objectHandler.add(new Wall(497, 595, 328, 10, game));
        game.objectHandler.add(new Wall(497, 300, 10, 300, game));
        game.objectHandler.add(new Wall(497, 295, 409, 10, game));
        game.objectHandler.add(new Wall(896, 300, 10, 305, game));
        game.objectHandler.add(new Wall(875, 595, 30, 10, game));

        //HallwayH1
        game.objectHandler.add(new Wall(497, 696, 328, 10, game));
        game.objectHandler.add(new Wall(875, 696, 328, 10, game));
    }

    public void update(){

    }
}
