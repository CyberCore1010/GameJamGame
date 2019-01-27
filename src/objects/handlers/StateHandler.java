package objects.handlers;

import game.Game;

import objects.gameObjects.Hunter;
import objects.gameObjects.*;
import objects.misc.BufferedImageLoader;
import objects.misc.ObjectList;
import objects.misc.PathGenerator;
import objects.misc.PathList;

import java.awt.geom.Point2D;

public class StateHandler {
    public StateHandler(Game game) {
        init(game);
    }

    public void init(Game game){
        BufferedImageLoader loader = new BufferedImageLoader();
        ObjectList<ObjectList<Node>> nodeList = game.grid.getNodes();
        for(ObjectList<Node> row : nodeList){
            for(Node node : row){
                game.objectHandler.add(node);
            }
        }
        //Floor
        game.objectHandler.add(new Floor(-200, -200, 2500, 2500, loader.loadImage("/sprites/floor/grass.png"), game));//Grass

        game.objectHandler.add(new Floor(300, 400, 200, 150, loader.loadImage("/sprites/floor/redBrick.png"), game));//Utility Room
        game.objectHandler.add(new Floor(300, 550, 201, 450, loader.loadImage("/sprites/floor/garage.png"), game));//Garage
        game.objectHandler.add(new Floor(501, 299, 400, 301, loader.loadImage("/sprites/floor/kitchen.png"), game));//Kitchen
        game.objectHandler.add(new Floor(900, 400, 100, 201, loader.loadImage("/sprites/floor/wood.png"), game));//Stairs
        game.objectHandler.add(new Floor(1000, 400, 200, 201, loader.loadImage("/sprites/floor/blueCarpet.png"), game));//Study1
        game.objectHandler.add(new Floor(1200, 400, 150, 201, loader.loadImage("/sprites/floor/blueCarpet.png"), game));//Study2
        game.objectHandler.add(new Floor(501, 600, 700, 101, loader.loadImage("/sprites/floor/wood.png"), game));//HallwayH1
        game.objectHandler.add(new Floor(501, 700, 299, 300, loader.loadImage("/sprites/floor/lounge.png"), game));//Lounge
        game.objectHandler.add(new Floor(900, 700, 299, 200, loader.loadImage("/sprites/floor/blueCarpet.png"), game));//Fireplace room
        game.objectHandler.add(new Floor(900, 900, 302, 250, loader.loadImage("/sprites/floor/pinkCarpet.png"), game));//Dining room
        game.objectHandler.add(new Floor(800, 700, 100, 450, loader.loadImage("/sprites/floor/wood.png"), game));//HallwayH2
        game.objectHandler.add(new Floor(550, 1000, 250, 100, loader.loadImage("/sprites/floor/wood.png"), game));//HallwayH2-sub
        game.objectHandler.add(new Floor(550, 1100, 250, 250, loader.loadImage("/sprites/floor/wood.png"), game));//Pantry
        game.objectHandler.add(new Floor(800, 1150, 300, 200, loader.loadImage("/sprites/floor/blueCarpet.png"), game));//TV room
        game.objectHandler.add(new Floor(1100, 1250, 200, 100, loader.loadImage("/sprites/floor/bathroom.png"), game));//Bathroom
        game.objectHandler.add(new Floor(1201, 601, 100, 550, loader.loadImage("/sprites/floor/wood.png"), game));//HallwayH3
        game.objectHandler.add(new Floor(1100, 1150, 200, 100, loader.loadImage("/sprites/floor/wood.png"), game));//HallwayH3-sub

        //Player
        game.objectHandler.add(new Player(747,447,1,50,50,game));

        PathGenerator generator = new PathGenerator(game);

        PathList path = generator.getPathList(new Point2D.Double(1200,1300),game.grid.getRandomNode().getPoint());
        game.repaint();
        game.objectHandler.add(new Hunter(1200, 1300, game, path, generator));



        //Utility Room and Garage
        game.objectHandler.add(new Wall(297, 395, 200, 10, game));
        game.objectHandler.add(new Wall(295, 395, 10, 610, game));
        game.objectHandler.add(new Wall(297, 995, 210, 10, game));
        game.objectHandler.add(new Wall(497, 775, 10, 225, game));
        game.objectHandler.add(new Wall(295, 545, 80, 10, game));
        game.objectHandler.add(new Wall(425, 545, 80, 10, game));
        game.objectHandler.add(new Door(372, 504, true, game));

        //Kitchen
        game.objectHandler.add(new Wall(497, 595, 325, 10, game));
        game.objectHandler.add(new Wall(497, 300, 10, 325, game));
        game.objectHandler.add(new Wall(497, 295, 409, 10, game));
        game.objectHandler.add(new Wall(896, 300, 10, 305, game));
        game.objectHandler.add(new Wall(875, 595, 30, 10, game));
        game.objectHandler.add(new Door(822, 555, true, game));

        //Stairs
        game.objectHandler.add(new Wall(900, 396, 100, 10, game));

        //Study1
        game.objectHandler.add(new Wall(996, 396, 10, 209, game));
        game.objectHandler.add(new Wall(996, 396, 209, 10, game));
        game.objectHandler.add(new Wall(1196, 396, 10, 79, game));
        game.objectHandler.add(new Wall(996, 595, 79, 10, game));
        game.objectHandler.add(new Wall(1125, 595, 100, 10, game));
        game.objectHandler.add(new Wall(1196, 525, 10, 100, game));
        game.objectHandler.add(new Door(1072, 555, true, game));

        //Study2
        game.objectHandler.add(new Wall(1205, 396, 140, 10, game));
        game.objectHandler.add(new Wall(1345, 396, 10, 200, game));
        game.objectHandler.add(new Wall(1275, 595, 80, 10, game));
        game.objectHandler.add(new Door(1222, 555, true, game));

        //HallwayH1
        game.objectHandler.add(new Wall(497, 675, 10, 50, game));
        game.objectHandler.add(new Wall(497, 696, 328, 10, game));
        game.objectHandler.add(new Wall(875, 696, 328, 10, game));
        game.objectHandler.add(new Door(822, 655, true, game));
        game.objectHandler.add(new Door(497, 625, false, game));

        //Lounge
        game.objectHandler.add(new Wall(496, 995, 308, 10, game));
        game.objectHandler.add(new Wall(795, 700, 10, 25, game));
        game.objectHandler.add(new Wall(795, 775, 10, 250, game));

        //Fireplace room/Dining room
        game.objectHandler.add(new Wall(895, 700, 10, 25, game));
        game.objectHandler.add(new Wall(895, 895, 300, 10, game));
        game.objectHandler.add(new Wall(895, 775, 10, 375, game));
        game.objectHandler.add(new Wall(875, 1145, 350, 10, game));

        //HallwayH2
        game.objectHandler.add(new Wall(545, 1000, 10, 350, game));
        game.objectHandler.add(new Wall(800, 1145, 25, 10, game));
        game.objectHandler.add(new Door(822, 1105, true, game));

        //Pantry
        game.objectHandler.add(new Wall(545, 1095, 250, 10, game));
        game.objectHandler.add(new Wall(545, 1345, 250, 10, game));
        game.objectHandler.add(new Wall(794, 1075, 10, 150, game));
        game.objectHandler.add(new Wall(794, 1275, 10, 80, game));

        //TV room
        game.objectHandler.add(new Wall(800, 1345, 500, 10, game));
        game.objectHandler.add(new Wall(1095, 1150, 10, 25, game));
        game.objectHandler.add(new Wall(1095, 1225, 10, 130, game));

        //Bathroom
        game.objectHandler.add(new Wall(1100, 1245, 75, 10, game));
        game.objectHandler.add(new Wall(1225, 1245, 75, 10, game));
        game.objectHandler.add(new Door(1172, 1205, true, game));

        //HallwayH3
        game.objectHandler.add(new Wall(1295, 600, 10, 755, game));
        game.objectHandler.add(new Wall(1195, 675, 10, 300, game));
        game.objectHandler.add(new Wall(1195, 1025, 10, 125, game));
        game.objectHandler.add(new Wall(1275, 1145, 30, 10, game));
        game.objectHandler.add(new Door(1222, 1105, true, game));

        //Room Bounds

        game.objectHandler.add(new RoomBounds(300, 400, 201, 150, game));//Utility Room
        game.objectHandler.add(new RoomBounds(300, 550, 201, 450, game));//Garage
        game.objectHandler.add(new RoomBounds(501, 299, 400, 301, game));//Kitchen
        game.objectHandler.add(new RoomBounds(900, 400, 100, 200, game));//Stairs
        game.objectHandler.add(new RoomBounds(1000, 400, 200, 200, game));//Study1
        game.objectHandler.add(new RoomBounds(1200, 400, 150, 201, game));//Study2
        game.objectHandler.add(new RoomBounds(501, 600, 700, 101, game));//HallwayH1
        game.objectHandler.add(new RoomBounds(501, 700, 299, 300, game));//Lounge
        game.objectHandler.add(new RoomBounds(900, 700, 301, 200, game));//Fireplace room
        game.objectHandler.add(new RoomBounds(900, 900, 301, 250, game));//Dining room
        game.objectHandler.add(new RoomBounds(800, 700, 100, 450, game));//HallwayH2
        game.objectHandler.add(new RoomBounds(550, 1000, 250, 100, game));//HallwayH2-sub
        game.objectHandler.add(new RoomBounds(550, 1100, 250, 250, game));//Pantry
        game.objectHandler.add(new RoomBounds(800, 1150, 300, 200, game));//TV room
        game.objectHandler.add(new RoomBounds(1100, 1250, 200, 100, game));//Bathroom
        game.objectHandler.add(new RoomBounds(1201, 601, 100, 549, game));//HallwayH3
        game.objectHandler.add(new RoomBounds(1100, 1150, 200, 100, game));//HallwayH3-sub

        game.objectHandler.add(new LightSource(0, 0, 0, game));

        game.objectHandler.add(new Score(-200,-450,game));
    }

    public void update(){

    }
}
