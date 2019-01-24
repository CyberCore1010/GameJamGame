package init;

import objects.handlers.ObjectHandler;
import objects.handlers.EventHandler;
import objects.handlers.StateHandler;
import objects.misc.Camera;
import objects.misc.ObjectList;

import javax.swing.*;
import java.awt.*;

public class Game extends JComponent {
    public Window window;

    public EventHandler eventHandler;
    public GameState state;


    public ObjectHandler objectHandler;
    private StateHandler stateHandler;

    //This is the camera which is an object of type Camera from my Camera class. It's used for updating the camera each tick of the game
    public ObjectList<Camera> cameraList;

    //These variables are simply used to tell the program that it is running. It also creates a new Thread object which
    //Is used for separating the game time from the game logic
    private static boolean isRunning = true;
    private Thread thread;


    Game() {
        //setting default game state
        state = GameState.Main;

        //creating and adding the initial camera to the camera list
        cameraList = new ObjectList<>();
        Camera main = new Camera(100, 100, 1);
        cameraList.add(main);

        //initialising the handlers for the game
        objectHandler = new ObjectHandler();
        stateHandler = new StateHandler(this);
        eventHandler = new EventHandler(this);

        //creating the window
        window = new Window(this,"Scythe Engine");

        //creating and starting the thread
        thread = new Thread(this::start);
        thread.start();

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
        stateHandler.update();
        objectHandler.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        ////////DRAWING AREA////////

        objectHandler.render(g); //displays objects passed from handler

        ////////MENU DRAWING////////
        g2d.dispose();
        g.dispose();
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


}
