package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.handlers.KeyHandler;
import objects.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Popup extends GameObject{

    private boolean active;
    private String text;

    public Popup(double x, double y, int z, double rotation, GameObjectID id, Game game) {
        super(x, y, z, rotation, id, game);
        drawStart();
    }

    public void drawStart(){
        active = true;
        game.paused=true;
        text="Start";
    }

    public void drawLoose(){
        active = true;
        game.paused=true;
        text = "Loose";
    }

    @Override
    public void update() {
        if(KeyHandler.isKeyPressed("Enter")&&active){
            active=false;
            game.paused=false;
        }
    }

    @Override
    public void render(Graphics g) {
        if(active) {
            Drawable popup = (graphics) -> {
                graphics.setColor(new Color(255,0,255));
                graphics.fillRect(300, 300, 500, 500);
                System.out.println("Drawing");
            };
            Graphics2D g2d = (Graphics2D) g;
            renderToCamera(popup, g2d, game.cameraMap.get(CameraID.Screen));
        }
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return null;
    }
}
