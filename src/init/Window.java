package init;

import objects.handlers.KeyHandler;
import objects.handlers.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static int gameWidth = (int)screenSize.getWidth();
    public static int gameHeight = (int)screenSize.getHeight()-50;

    public Window(Component comp, String title) {
        JFrame frame = new JFrame(title);
        frame.setLocation((int)((screenSize.getWidth()/2)-(gameWidth/2)), (int)((screenSize.getHeight()/2)-(gameHeight/2))-20);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, comp);
        frame.getContentPane().setBackground(Color.black);
        frame.setSize(gameWidth, gameHeight);

        /*frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);*/
        frame.setVisible(true);
        frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        frame.addKeyListener(new KeyHandler());
        frame.addMouseMotionListener(new MouseHandler());
    }
}
