package objects.handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler extends KeyAdapter {

    private static Map<String, Boolean> keyMap;

    public KeyHandler() {
        keyMap = new HashMap<>();
        keyMap.put("W", false);
        keyMap.put("A", false);
        keyMap.put("S", false);
        keyMap.put("D", false);
        keyMap.put("Enter",false);
    }

    public static boolean isKeyPressed(String key) {
        if(keyMap != null) {
            return keyMap.get(key);
        } else {
            return false;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            keyMap.replace("W", true);
        }
        if(key == KeyEvent.VK_A) {
            keyMap.replace("A", true);
        }
        if(key == KeyEvent.VK_S) {
            keyMap.replace("S", true);
        }
        if(key == KeyEvent.VK_D) {
            keyMap.replace("D", true);
        }
        if(key == KeyEvent.VK_ENTER) {
            keyMap.replace("Enter", true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            keyMap.replace("W", false);
        }
        if(key == KeyEvent.VK_A) {
            keyMap.replace("A", false);
        }
        if(key == KeyEvent.VK_S) {
            keyMap.replace("S", false);
        }
        if(key == KeyEvent.VK_D) {
            keyMap.replace("D", false);
        }
        if(key == KeyEvent.VK_ENTER) {
            keyMap.replace("Enter", false);
        }
    }
}
