package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.handlers.KeyHandler;
import objects.handlers.ObjectHandler;
import objects.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PopupLose extends GameObject{

    private boolean active;
    private String score;
    private String text1;
    private String text2;
    private String text3;
    private String text4;

    public PopupLose(double x, double y, int z, double rotation, GameObjectID id, Game game) {
        super(x, y, z, rotation, id, game);
        drawStart();
        text1="You Died.";
        text2="Congratulations on surviving...";
        for(GameObject g :game.objectHandler.objects){
            if(g.getId()==GameObjectID.Score){
                Score temp = (Score)g;
                score = String.valueOf(temp.getScore());
            }
        }
        text3="    " + score + "points, really thats all?";
        text4="Come on. Press Enter to try again";
    }

    public void drawStart(){
        active = true;
    }

    @Override
    public void update() {
        if(KeyHandler.isKeyPressed("Enter")&&active){
            active=false;
            game.objectHandler = new ObjectHandler();
            game.paused=false;
        }
    }

    @Override
    public void render(Graphics g) {
        if(active) {
            Drawable popup = (graphics) -> {
                System.out.println("Drawing");
                graphics.setColor(new Color(38, 255, 41,200));
                graphics.fillRect(-250, -200, 500, 400);
                graphics.setFont(new Font("Big Caslon",Font.BOLD,15));
                graphics.setColor( new Color(255,255,255));
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
