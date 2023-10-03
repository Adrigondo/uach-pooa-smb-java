package model.prize;

import manager.GameEngine;
import model.hero.Mario;
import model.hero.MarioForm;
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

        if (!mario.getMarioForm().isFire()) {
            engine.playFireFlower();
            BufferedImage[] leftFrames = engine.getImageLoader().getLeftFrames(MarioForm.FIRE);
            engine.playFireFlower();
            BufferedImage[] rightFrames = engine.getImageLoader().getRightFrames(MarioForm.FIRE);

            Animation animation = new Animation(leftFrames, rightFrames);
            MarioForm newForm = new MarioForm(animation, true, true, engine.getImageLoader());
            mario.setMarioForm(newForm);
            mario.setDimension(48, 96);

            engine.playFireFlower();
        }
    }

    @Override
    public void updateLocation() {
    }

}
