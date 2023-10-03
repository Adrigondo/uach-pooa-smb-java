package model.hero;

import manager.Camera;
import manager.GameEngine;
import view.Animation;
import model.GameObject;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mario extends GameObject {

    private ImageLoader imageLoader;
    private int remainingLives;
    private int coins;
    private int points;
    private double invincibilityTimer;
    private MarioForm marioForm;
    private boolean toRight = true;
    private float jumpForce = 10;
    private float moveSpeed = 5;

    public Mario(double x, double y, ImageLoader imageLoader) {
        super(x, y, null);
        setDimension(48, 48);

        remainingLives = 3;
        points = 0;
        coins = 0;
        invincibilityTimer = 0;
        this.imageLoader = imageLoader;
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);

        Animation animation = new Animation(leftFrames, rightFrames);
        marioForm = new MarioForm(animation, false, false, imageLoader);
        setStyle(marioForm.getCurrentStyle(toRight, false, false));
    }

    public Mario(double x, double y, ImageLoader imageLoader, float jumpForce, float moveSpeed, Dimension dimension,
            int remainingLives) {
        super(x, y, null);
        setDimension(dimension);
        this.jumpForce = jumpForce;
        this.moveSpeed = moveSpeed;
        this.remainingLives = remainingLives;
        points = 0;
        coins = 0;
        invincibilityTimer = 0;

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);

        Animation animation = new Animation(leftFrames, rightFrames);
        marioForm = new MarioForm(animation, false, false, imageLoader);
        setStyle(marioForm.getCurrentStyle(toRight, false, false));
    }

    @Override
    public void draw(Graphics g) {
        boolean movingInX = (getVelX() != 0);
        boolean movingInY = (getVelY() != 0);

        setStyle(marioForm.getCurrentStyle(toRight, movingInX, movingInY));

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

        if (!marioForm.isSuper() && !marioForm.isFire()) {
            remainingLives--;
            engine.playMarioDies();
            return true;
        } else {
            engine.shakeCamera();
            marioForm = marioForm.onTouchEnemy();
            setDimension(48, 48);
            return false;
        }
    }

    public Fireball fire() {
        return marioForm.fire(toRight, getX(), getY());
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

    public MarioForm getMarioForm() {
        return marioForm;
    }

    public void setMarioForm(MarioForm marioForm) {
        this.marioForm = marioForm;
    }

    public boolean isSuper() {
        return marioForm.isSuper();
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
