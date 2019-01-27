package objects.gameObjects;

import game.CameraID;
import game.Game;
import objects.interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LightSource extends GameObject{
    private Point2D center;
    @SuppressWarnings("FieldCanBeLocal")
    private float radius = 4000f;
    private Color color;

    private boolean doOnce = true;

    /**
     * This is the abstract GameObject class. All other classes extend to this one in some manor. It contains abstract
     * methods to be used with every game object. The constructor simply sets the variables that will be used by each
     * GameObject, such as the x and y position.
     *
     * @param x        - x location of the object
     * @param y        - y location of the object
     * @param Z        - Z location of the object
     */
    public LightSource(int x, int y, int Z, Game game) {
        super(x, y, Z, 0, GameObjectID.Light, game);
        this.color = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        center = new Point2D.Float((float)game.window.gameWidth/2, (float)game.window.gameHeight/2);
    }

    @Override
    public void update() { }

    @Override
    public void render(Graphics g) {
        Drawable drawable = (graphics)->{
            float[] distance = {0.0f, 1.0f};
            Color[] colors = {color, Color.BLACK};
            RadialGradientPaint p = new RadialGradientPaint(center, radius, distance, colors);
            graphics.setPaint(p);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
            graphics.fillRect(-game.window.gameWidth/2, -game.window.gameHeight/2, game.window.gameWidth+200, game.window.gameHeight);
        };

        Graphics2D g2d = (Graphics2D) g;
        renderToCamera(drawable, g2d, game.cameraMap.get(CameraID.Screen));
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return null;
    }
}
