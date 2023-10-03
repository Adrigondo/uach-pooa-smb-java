package manager;

import model.EndFlag;
import model.brick.*;
import model.prize.*;
import view.ImageLoader;
import model.Map;
import model.enemy.Enemy;
import model.enemy.Goomba;
import model.enemy.KoopaTroopa;
import model.hero.Mario;

import java.awt.*;
import java.awt.image.BufferedImage;

import factories.GUIFactory;

class MapCreator {

    private ImageLoader imageLoader;

    private GUIFactory factory;
    private Spritesheet spritesheet;
    private BufferedImage backgroundImage;
    private BufferedImage superMushroom, oneUpMushroom, fireFlower, coin;
    private BufferedImage ordinaryBrick, surpriseBrick, groundBrick, pipe;
    private BufferedImage goombaLeft, goombaRight, koopaLeft, koopaRight, endFlag;

    MapCreator(GUIFactory factory) {
        this.factory = factory;
        this.spritesheet = factory.createSpritesheet();
    }

    Map createMap(String mapPath, double timeLimit) {
        BufferedImage mapImage = factory.imageLoader.loadImage(mapPath);

        if (mapImage == null) {
            System.out.println("Given path is invalid...");
            return null;
        }

        Map createdMap = new Map(timeLimit, backgroundImage);
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
                    Brick brick = new OrdinaryBrick(xLocation, yLocation, this.ordinaryBrick);
                    createdMap.addBrick(brick);
                } else if (currentPixel == surpriseBrick) {
                    Prize prize = generateRandomPrize(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation, this.surpriseBrick, prize);
                    createdMap.addBrick(brick);
                } else if (currentPixel == pipe) {
                    Brick brick = new Pipe(xLocation, yLocation, this.pipe);
                    createdMap.addGroundBrick(brick);
                } else if (currentPixel == groundBrick) {
                    Brick brick = new GroundBrick(xLocation, yLocation, this.groundBrick);
                    createdMap.addGroundBrick(brick);
                } else if (currentPixel == goomba) {
                    createdMap.addEnemy(factory.createGoomba(xLocation, yLocation));
                } else if (currentPixel == koopa) {
                    createdMap.addEnemy(factory.createKoopaTroopa(xLocation, yLocation));
                } else if (currentPixel == mario) {
                    createdMap.setMario(factory.createMario(xLocation, yLocation));
                } else if (currentPixel == end) {
                    EndFlag endPoint = new EndFlag(xLocation + 24, yLocation, endFlag);
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
            generated = new SuperMushroom(x, y, this.superMushroom);
        } else if (random == 1) { // fire flower
            generated = new FireFlower(x, y, this.fireFlower);
        } else if (random == 2) { // one up mushroom
            generated = new OneUpMushroom(x, y, this.oneUpMushroom);
        } else { // coin
            generated = new Coin(x, y, this.coin, 50);
        }

        return generated;
    }

}
