package model.prize;

import manager.GameEngine;
import model.hero.Mario;
import model.hero.HeroForm;
import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class SuperMushroom extends BoostItem {

    public SuperMushroom(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(125);
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {
        mario.acquirePoints(getPoint());

        ImageLoader imageLoader = ImageLoader.getInstance();

        if (!mario.getHeroForm().isSuper()) {
            BufferedImage[] leftFrames = imageLoader.getLeftFrames(HeroForm.SUPER);
            BufferedImage[] rightFrames = imageLoader.getRightFrames(HeroForm.SUPER);

            Animation animation = new Animation(leftFrames, rightFrames);
            HeroForm newForm = new HeroForm(animation, true, false, imageLoader);
            mario.setHeroForm(newForm);
            mario.setDimension(48, 96);

            engine.playSuperMushroom();
        }
    }
}
