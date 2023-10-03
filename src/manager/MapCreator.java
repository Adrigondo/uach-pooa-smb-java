package manager;

import model.EndFlag;
import model.brick.*;
import model.prize.*;
import view.ImageLoader;
import model.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

import factories.GUIFactory;

class MapCreator {

    private GUIFactory factory;
    private Spritesheet spritesheet;
    private ImageLoader imageLoader;

    MapCreator(GUIFactory factory, ImageLoader imageLoader, Spritesheet spritesheet) {
        this.factory = factory;
        this.imageLoader = imageLoader;
        this.spritesheet = spritesheet;
    }

    Map createMap(String mapPath, double timeLimit) {
        BufferedImage mapImage = imageLoader.loadImage(mapPath);

        if (mapImage == null) {
            System.out.println("Given path is invalid...");
            return null;
        }

        Map createdMap = new Map(timeLimit, spritesheet.backgroundImage);
        String[] paths = mapPath.split("/");
        createdMap.setPath(paths[paths.length - 1]);

        int pixelMultiplier = 48;

        int mario = new Color(160, 160, 160).getRGB();
        int ordinaryBrick = new Color(0, 0, 255).getRGB();
        int surpriseBrick = new Color(255, 255, 0).getRGB();
        int groundBrick = new Color(255, 0, 0).getRGB();
        int pipe = new Color(0, 255, 0).getRGB();
        int goomba = new Color(0, 255, 255).getRGB();
        int koopa = new Color(255, 0, 255).getRGB();
        int end = new Color(160, 0, 160).getRGB();

        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {

                int currentPixel = mapImage.getRGB(x, y);
                int xLocation = x * pixelMultiplier;
                int yLocation = y * pixelMultiplier;

                if (currentPixel == ordinaryBrick) {
                    Brick brick = new OrdinaryBrick(xLocation, yLocation, spritesheet.ordinaryBrick, imageLoader);
                    createdMap.addBrick(brick);
                } else if (currentPixel == surpriseBrick) {
                    Prize prize = generateRandomPrize(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation, spritesheet.surpriseBrick, prize);
                    createdMap.addBrick(brick);
                } else if (currentPixel == pipe) {
                    Brick brick = new Pipe(xLocation, yLocation, spritesheet.pipe);
                    createdMap.addGroundBrick(brick);
                } else if (currentPixel == groundBrick) {
                    Brick brick = new GroundBrick(xLocation, yLocation, spritesheet.groundBrick);
                    createdMap.addGroundBrick(brick);
                } else if (currentPixel == goomba) {
                    createdMap.addEnemy(factory.createGoomba(xLocation, yLocation, spritesheet));
                } else if (currentPixel == koopa) {
                    createdMap.addEnemy(factory.createKoopaTroopa(xLocation, yLocation, spritesheet));
                } else if (currentPixel == mario) {
                    createdMap.setMario(factory.createMario(xLocation, yLocation, imageLoader));
                } else if (currentPixel == end) {
                    EndFlag endPoint = new EndFlag(xLocation + 24, yLocation, spritesheet.endFlag);
                    createdMap.setEndPoint(endPoint);
                }
            }
        }

        System.out.println("Map is created..");
        return createdMap;
    }

    private Prize generateRandomPrize(double x, double y) {
        Prize generated;
        int random = (int) (Math.random() * 12);

        if (random == 0) { // super mushroom
            generated = new SuperMushroom(x, y, spritesheet.superMushroom);
        } else if (random == 1) { // fire flower
            generated = new FireFlower(x, y, spritesheet.fireFlower);
        } else if (random == 2) { // one up mushroom
            generated = new OneUpMushroom(x, y, spritesheet.oneUpMushroom);
        } else { // coin
            generated = new Coin(x, y, spritesheet.coin, 50);
        }

        return generated;
    }

}
