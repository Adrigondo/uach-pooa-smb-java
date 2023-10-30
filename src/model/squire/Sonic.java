package model.squire;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sonic extends Squire {
    private BufferedImage rightImage;

    public Sonic(double x, double y, BufferedImage style) {
        super(x, y, style);
        setFalling(false);
        setJumping(false);
        setVelX(3);
    }

    @Override
    public void draw(Graphics g) {
        if (getVelX() > 0) {
            g.drawImage(rightImage, (int) getX(), (int) getY(), null);
        } else
            super.draw(g);
    }

    @Override
    public void update() {
        this.setLocation();
    }

    public void setLocation() {
        setVelX(getHero().getVelX());
        setVelY(getHero().getVelY());
        setX(getHero().getX());
        setY(getHero().getY());
        setJumping(false);
        setFalling(true);
    }

    @Override
    public void updateLocation() {
        setVelX(getHero().getVelX());
        setVelY(getHero().getVelY());
        setX(getHero().getX());
        setY(getHero().getY());
        setJumping(false);
        setFalling(true);
    }
}
