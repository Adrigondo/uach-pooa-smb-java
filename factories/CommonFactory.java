package factories;

import java.awt.Dimension;

import manager.Spritesheet;
import model.enemy.Goomba;
import model.enemy.KoopaTroopa;
import model.hero.Mario;
import view.ImageLoader;

public class CommonFactory implements GUIFactory {

    @Override
    public Mario createMario(int xLocation, int yLocation) {
        Mario mario = new Mario(xLocation, yLocation, 15, 4, new Dimension(48, 48), 4);
        return mario;
    }

    @Override
    public Goomba createGoomba(int xLocation, int yLocation) {
        Goomba goomba = new Goomba(xLocation, yLocation, spritesheet.goombaRight);
        ((Goomba) goomba).setRightImage(spritesheet.goombaRight);
        return goomba;
    }

    @Override
    public KoopaTroopa createKoopaTroopa(int xLocation, int yLocation) {
        KoopaTroopa koopaTroopa = new KoopaTroopa(xLocation, yLocation, spritesheet.goombaRight);
        ((KoopaTroopa) koopaTroopa).setRightImage(spritesheet.goombaRight);
        return koopaTroopa;
    }

    @Override
    public Spritesheet createSpritesheet() {
        Spritesheet spritesheet = new Spritesheet(imageLoader);
        spritesheet.setSprite("/sprite.png");
        spritesheet.setBackgroundImage("/background.png");
        spritesheet.coin(1, 5, 48, 48);
        spritesheet.endFlag(5, 1, 48, 48);
        spritesheet.fireFlower(4, 5, 48, 48);
        spritesheet.goombaLeft(2, 4, 48, 48);
        spritesheet.goombaRight(5, 4, 48, 48);
        spritesheet.groundBrick(2, 2, 48, 48);
        spritesheet.koopaLeft(1, 3, 48, 48);
        spritesheet.koopaRight(4, 3, 48, 48);
        spritesheet.oneUpMushroom(3, 5, 48, 48);
        spritesheet.ordinaryBrick(1, 1, 48, 48);
        spritesheet.pipe(3, 1, 96, 96);
        spritesheet.superMushroom(2, 5, 48, 48);
        spritesheet.surpriseBrick(2, 1, 48, 48);
        return spritesheet;
    }

    @Override
    public ImageLoader createImageLoader() {
        imageLoader.setMarioForms("/mario-forms.png");
        imageLoader.setBrickAnimation("/brick-animation.png");
        return imageLoader;
    }

}
