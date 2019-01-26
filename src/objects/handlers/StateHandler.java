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
        game.objectHandler.add(new Floor(0, 0, 300, 500, game));
        game.objectHandler.add(new Floor(310, 0, 300, 500, game));
        game.objectHandler.add(new Player(100,100,1,50,50,game));
        PathList pathList = new PathList(new Node(100,100,game),game);
        pathList.add(new Node(100,200,game));
        game.objectHandler.add(new Hunter(400, 100, game,pathList));
        game.objectHandler.add(new Wall(300, 0, 10, 225, game));
        game.objectHandler.add(new Wall(300, 275, 10, 225, game));
        game.objectHandler.add(new RoomBounds(0, 0, 300, 500, game));
        game.objectHandler.add(new RoomBounds(310, 0, 300, 500, game));

    }

    public void update(){

    }
}
