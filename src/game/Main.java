package game;

public class Main {
    /**This is simply the main method. It creates the handler and sends it to the game. It also adds the game to a new
     * Window object and runs LevelSelect's selectLevel method to set up the level.
     *
     * @param args - Not used
     */
    public static boolean debug = true;

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        new Game();
    }
}
