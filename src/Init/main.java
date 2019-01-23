package Init;

import Objects.Utility.ObjectHandler;

public class main {
    /**This is simply the main method. It creates the handler and sends it to the game. It also adds the game to a new
     * Window object and runs LevelSelect's selectLevel method to set up the level.
     *
     * @param args - Not used
     */
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");

        ObjectHandler objectHandler = new ObjectHandler();
        Game game = new Game(objectHandler);
        new Window(game, "TTMEngine");
    }
}
