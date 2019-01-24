package Objects.Utility;

import Init.Camera;
import Init.Game;
import Init.Window;
import Objects.Player.Player;

public class StateHandler {
    private ObjectHandler objectHandler;

    public StateHandler(ObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
    }

    public void StateSetTester(Game game) {
        objectHandler.objects.clear();
        objectHandler.objects.add(new Player("Player", 0, 0, 0, Window.gameWidth/10, Window.gameWidth/10,game));
    }
}
