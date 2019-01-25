package objects.FileIO;

import game.Game;

public class LevelLoader {
    ObjectFileReader mapContent;
    Game game;

    LevelLoader(Game game) {
        this.game = game;
    }

    public void LoadLevel(String fileName) {
        mapContent = new ObjectFileReader(fileName, game);
        while(mapContent.hasNextObject()) {
            game.objectHandler.objects.add(mapContent.getNextObject());
        }
    }
}
