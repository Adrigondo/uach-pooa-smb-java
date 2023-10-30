package model.squire;

import java.awt.image.BufferedImage;

import model.GameObject;
import model.hero.Hero;

public abstract class Squire extends GameObject {
    private static Hero hero;
    private BufferedImage rightImage;

    public Squire(double x, double y, BufferedImage style) {
        super(x, y, style);
        // TODO Auto-generated constructor stub
    }

    public void setRightImage(BufferedImage rightImage) {
        this.rightImage = rightImage;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        Squire.hero = hero;
    }

    public abstract void update();

    public abstract void updateLocation();
}
