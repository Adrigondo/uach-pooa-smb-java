package model.hero;

import manager.Camera;
import manager.GameEngine;
import model.squire.Squire;
import view.Animation;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wario extends Hero {
    public Squire squire;
    private ImageLoader imageLoader;
    private int remainingLives;
    private int coins;
    private int points;
    private double invincibilityTimer;
    private HeroForm heroForm;
    private boolean toRight = true;
    private float jumpForce = 10;
    private float moveSpeed = 5;

    public Wario(double x, double y, ImageLoader imageLoader) {
        super(x, y, imageLoader);
        remainingLives = 4;
    }

    public Wario(double x, double y, ImageLoader imageLoader, float jumpForce, float moveSpeed, Dimension dimension,
            int remainingLives) {
        super(x, y, imageLoader, jumpForce, moveSpeed, dimension, remainingLives);
    }

    @Override
    public void draw(Graphics g) {
        boolean movingInX = (getVelX() != 0);
        boolean movingInY = (getVelY() != 0);

        setStyle(heroForm.getCurrentStyle(toRight, movingInX, movingInY));

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

        if (!heroForm.isSuper() && !heroForm.isFire()) {
            remainingLives--;
            engine.playMarioDies();
            return true;
        } else {
            engine.shakeCamera();
            heroForm = heroForm.onTouchEnemy();
            setDimension(48, 48);
            return false;
        }
    }

    public Fireball fire() {
        return heroForm.fire(toRight, getX(), getY());
    }

    public void acquireCoin() {
        coins++;
    }

    public void acquirePoints(int point) {
        points = points + point;
    }

    public int getRemainingLives() {
        return remainingLives;
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

    public HeroForm getMarioForm() {
        return heroForm;
    }

    public void setMarioForm(HeroForm heroForm) {
        this.heroForm = heroForm;
    }

    public boolean isSuper() {
        return heroForm.isSuper();
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
