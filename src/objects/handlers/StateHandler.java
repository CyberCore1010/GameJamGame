package objects.handlers;

import game.Game;

import objects.gameObjects.Player;
import objects.gameObjects.Enemy;
import objects.gameObjects.Wall;

public class StateHandler {
    private Game game;

    public StateHandler(Game game) {
        this.game = game;
        game.objectHandler.add(new Player(100,100,1,50,50,game));
        game.objectHandler.add(new Enemy(400, 100, game));
        game.objectHandler.add(new Wall(300, 0, 50, 500, game));
    }

    public void update(){

    }
}
