package model.hero;

import manager.Camera;
import manager.GameEngine;
import model.squire.Squire;
import view.Animation;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mario extends Hero {
    public Squire squire;
    private ImageLoader imageLoader;
    private int remainingLives;
    private int coins;
    private int points;
    private double invincibilityTimer;
    private boolean toRight = true;
    private float jumpForce = 10;
    private float moveSpeed = 5;

    public Mario(double x, double y, ImageLoader imageLoader) {
        super(x, y, imageLoader);
        remainingLives = 3;
    }

    public Mario(double x, double y, ImageLoader imageLoader, float jumpForce, float moveSpeed, Dimension dimension,
            int remainingLives) {
        super(x, y, imageLoader, jumpForce, moveSpeed, dimension, remainingLives);
        System.out.println("MARIO CONSTRUCTOR:"+this.getHeroForm());
    }

    @Override
    public void draw(Graphics g) {
        boolean movingInX = (getVelX() != 0);
        boolean movingInY = (getVelY() != 0);

        setStyle(this.getHeroForm().getCurrentStyle(toRight, movingInX, movingInY));

        super.draw(g);
    }

    public void jump(GameEngine engine) {
        if (!isJumping() && !isFalling()) {
            setJumping(true);
            setVelY(jumpForce);
            engine.playJump();
        }
    }

    public void move(boolean toRight, Camera camera) {
        if (toRight) {
            setVelX(moveSpeed);
        } else if (camera.getX() < getX()) {
            setVelX(-moveSpeed);
        }

        this.toRight = toRight;
    }

    public boolean onTouchEnemy(GameEngine engine) {

        if (!this.getHeroForm().isSuper() && !this.getHeroForm().isFire()) {
            remainingLives--;
            engine.playMarioDies();
            return true;
        } else {
            engine.shakeCamera();
            this.setHeroForm(this.getHeroForm().onTouchEnemy());
            setDimension(48, 48);
            return false;
        }
    }

    public Fireball fire() {
        return this.getHeroForm().fire(toRight, getX(), getY());
    }

    public void acquireCoin() {
        coins++;
    }

    public void acquirePoints(int point) {
        points = points + point;
    }

    public int getRemainingLives() {
        return this.remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    public int getPoints() {
        return points;
    }

    public int getCoins() {
        return coins;
    }

    public boolean isSuper() {
        return this.getHeroForm().isSuper();
    }

    public boolean getToRight() {
        return toRight;
    }

    public void resetLocation() {
        setVelX(0);
        setVelY(0);
        setX(9120);
        setY(384);
        setJumping(false);
        setFalling(true);
    }
}
