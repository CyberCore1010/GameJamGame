package objects.handlers;

import init.Window;
import objects.gameObjects.Light;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class LightHandler {
    private BufferedImage lightMap;
    private java.util.List<Light> lightList;
    private int width, height;
    public LightHandler(int width, int height) {
        this.width = width;
        this.height = height;
        lightList = new ArrayList<>();
    }

    public void addLight(Light light) {
        lightList.add(light);
    }

    public void remakeLightMap() {
        lightMap = new BufferedImage(5000,5000,BufferedImage.TYPE_INT_RGB);
        Graphics2D lightGraphics = (Graphics2D) lightMap.getGraphics();
        lightGraphics.setColor(new Color(0, 0, 0, 255));
        lightGraphics.fillRect(0, 0, width, height);

        lightGraphics.setComposite(AlphaComposite.DstOut);
        for(Light l : lightList) {
            l.draw(lightGraphics);
        }
        lightGraphics.dispose();
    }

    public void Paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 0, 0, 255));
        g2d.fillRect(0, 0, Window.gameWidth, Window.gameHeight);
        g2d.setComposite(AlphaComposite.DstOut);

    }
}
