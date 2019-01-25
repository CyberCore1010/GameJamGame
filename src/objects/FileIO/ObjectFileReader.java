package objects.FileIO;

import game.Game;
import objects.gameObjects.GameObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjectFileReader {
    private BufferedReader bufferedReader;
    ObjectFileReader(String fileName, Game game) {
        try {
            FileReader fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean hasNextObject() {
        try {
            return (bufferedReader.readLine() != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public GameObject getNextObject() {
        try {
            String line = bufferedReader.readLine();
            ArrayList<String> list = new ArrayList<>(Arrays.asList(line.split(",")));
            if(list.get(0).equals("Player")) {
                //return new Player();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
