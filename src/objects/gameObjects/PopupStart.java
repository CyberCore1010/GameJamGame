package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.handlers.KeyHandler;
import objects.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PopupStart extends GameObject{

    private boolean active;
    private String text1;
    private String text2;
    private String text3;
    private String text4;

    public PopupStart(double x, double y, int z, double rotation, GameObjectID id, Game game) {
        super(x, y, z, rotation, id, game);
        drawStart();
        text1="Welcome to Home,";
        text2="Use W A S D to move around and avoid the";
        text3="bandits that have broken into your house";
        text4="Press the Enter key to start";

    }

    public void drawStart(){
        active = true;
        game.paused=true;
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
                graphics.fillRect(-250, -200, 500, 400);
                graphics.setColor( new Color(255,255,255));
                graphics.setFont(new Font("Big Caslon",Font.BOLD,15));
                graphics.drawString(text1,-200, -150);
                graphics.drawString(text2,-200, -100);
                graphics.drawString(text3,-200, -80);
                graphics.drawString(text4,-200, -30);

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
