package Init;

import Objects.Utility.ObjectHandler;
import Objects.Utility.EventHandler;
import Objects.Utility.StateHandler;

import javax.swing.*;
import java.awt.*;

public class Game extends JComponent {
    public EventHandler eventHandler;

    //This is the Object handler which is set in the constructor. It's passed throughout the entire program, and is used to manipulate the objects in the game
    static ObjectHandler objectHandler;
    private StateHandler stateHandler;

    //This is the camera which is an object of type Camera from my Camera class. It's used for updating the camera each tick of the game
    static Camera camera;

    //These variables are simply used to tell the program that it is running. It also creates a new Thread object which
    //Is used for separating the game time from the game logic
    private static boolean isRunning = true;
    private Thread thread;

    Game(ObjectHandler objectHandler) {
        Game.objectHandler = objectHandler;
        camera = new Camera(0, 0);

        stateHandler = new StateHandler(objectHandler);
        stateHandler.StateSetTester(camera);

        thread = new Thread(this::start);
        thread.start();

        eventHandler = new EventHandler(stateHandler, Game.objectHandler);
    }

    /**This method is the method that controls the time used in the game. It basically works on the basis that each
     * time the game loops it will check the difference between the current system time and the last system time
     * and then runs the update method on those game objects for the time difference between the two. Finally it will
     * render the objects, showing them on the screen. It also has a timer which increases each time, which just shows
     * how much time has passed since the game has started. as of now it doesn't really do anything however it can be
     * used with System.out for debugging purposes.
     */
    private void start() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                delta--;
            }
            repaint();

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    /**This is the update method which is called from the game timer in the "start" method. It basically will first
     * find the player in the handler and send it to the camera so that it can change it's position to the cameras
     * players current position. Afterwords it runs the update method in the object handler
     */
    private void update() {
        objectHandler.update();
    }

    /**This method is called from the start method when the game is no longer running and simply joins the two threads
     * together to prevent system problems.
     */
    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        ////////DRAWING AREA////////

        g2d.translate(-camera.getX(), -camera.getY());
        objectHandler.render(g); //displays objects passed from handler
        g2d.translate(0, 0);

        ////////MENU DRAWING////////
        g2d.dispose();
        g.dispose();
    }
}
