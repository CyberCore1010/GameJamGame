package objects.gameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Light {
    private BufferedImage lightImage;
    private int x, y;
    public Light(int x, int y, int radius, int luminosity) {
        this.x = x;
        this.y = y;
        lightImage = new BufferedImage(radius*2, radius*2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) lightImage.getGraphics();

        int step = 4;
        int numSteps = radius/step;
        g2d.setColor(new Color(0, 0, 0, luminosity));
        for(int i = 0; i < numSteps; i++) {
            g2d.fillOval(radius - i * step, radius - i * step, i * step * 2, i * step * 2);
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(lightImage, x, y,null);
    }
}
