package objects.handlers;

import game.Game;

import objects.gameObjects.*;

public class StateHandler {
    private Game game;

    public StateHandler(Game game) {
        this.game = game;
        game.objectHandler.add(new Floor(0, 0, 300, 500, game));
        game.objectHandler.add(new Floor(310, 0, 300, 500, game));
        game.objectHandler.add(new Player(100,100,1,50,50,game));
        game.objectHandler.add(new TempObject(400, 100, game));
        game.objectHandler.add(new Wall(300, 0, 10, 225, game));
        game.objectHandler.add(new Wall(300, 275, 10, 225, game));
        game.objectHandler.add(new RoomBounds(0, 0, 300, 500, game));
        game.objectHandler.add(new RoomBounds(310, 0, 300, 500, game));
    }

    public void update(){

    }
}
