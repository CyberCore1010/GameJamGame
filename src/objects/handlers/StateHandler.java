package objects.handlers;

import init.Game;
import init.Window;
import objects.gameObjects.Light;
import objects.gameObjects.Player;

public class StateHandler {
    private Game game;

    public StateHandler(Game game) {
        this.game = game;
        game.objectHandler.add(new Player(100,100,1,50,50,game));
        //game.lightHandler.addLight(new Light(200, 200, 20, 10));
        //game.lightHandler.addLight(new Light(800, 800, 20, 10));
        //game.lightHandler.remakeLightMap();
    }

    public void update(){

    }


}
