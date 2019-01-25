package objects.handlers;


import game.Game;

import objects.gameObjects.Player;

public class StateHandler {
    private Game game;

    public StateHandler(Game game) {
        this.game = game;
        game.objectHandler.add(new Player(100,100,1,50,50,game));
    }

    public void update(){

    }


}
