package model.prize;

import manager.GameEngine;
import model.hero.Mario;
import model.hero.HeroForm;
import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class FireFlower extends BoostItem {

    public FireFlower(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(150);
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {
        mario.acquirePoints(getPoint());

        if (!mario.getHeroForm().isFire()) {
            engine.playFireFlower();
            BufferedImage[] leftFrames = engine.getImageLoader().getLeftFrames(HeroForm.FIRE);
            engine.playFireFlower();
            BufferedImage[] rightFrames = engine.getImageLoader().getRightFrames(HeroForm.FIRE);

            Animation animation = new Animation(leftFrames, rightFrames);
            HeroForm newForm = new HeroForm(animation, true, true, engine.getImageLoader());
            mario.setHeroForm(newForm);
            mario.setDimension(48, 96);

            engine.playFireFlower();
        }
    }

    @Override
    public void updateLocation() {
    }

}
