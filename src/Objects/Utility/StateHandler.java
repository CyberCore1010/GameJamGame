package Objects.Utility;

import Init.Camera;
import Init.Window;
import Objects.Player.Player;

public class StateHandler {
    private ObjectHandler objectHandler;

    public StateHandler(ObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
    }

    public void StateSetTester(Camera camera) {
        objectHandler.objects.clear();
        camera.setX(0);
        camera.setY(0);
        objectHandler.objects.add(new Player("Player", 0, 0, 0, Window.gameWidth/10, Window.gameWidth/10));
    }
}
