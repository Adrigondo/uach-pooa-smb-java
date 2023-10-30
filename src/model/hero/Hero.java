package model.hero;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import manager.Camera;
import manager.GameEngine;
import model.GameObject;
import model.squire.Squire;
import view.Animation;
import view.ImageLoader;

public abstract class Hero extends GameObject {
    public Squire squire;
    private ImageLoader imageLoader;
    private int remainingLives;
    private int coins;
    private int points;
    private double invincibilityTimer;
    private boolean toRight = true;
    private HeroForm heroForm;
    private float jumpForce = 10;
    private float moveSpeed = 5;

    public Hero(double x, double y, ImageLoader imageLoader) {
        super(x, y, null);
        setDimension(48, 48);
        points = 0;
        coins = 0;
        invincibilityTimer = 0;

        this.imageLoader = imageLoader;
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(HeroForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(HeroForm.SMALL);

        Animation animation = new Animation(leftFrames, rightFrames);
        heroForm = new HeroForm(animation, false, false, imageLoader);
        setStyle(heroForm.getCurrentStyle(toRight, false, false));
    }

    public Hero(double x, double y, ImageLoader imageLoader, float jumpForce, float moveSpeed, Dimension dimension,
            int remainingLives) {
        super(x, y, null);
        setDimension(dimension);
        this.jumpForce = jumpForce;
        this.moveSpeed = moveSpeed;
        this.setRemainingLives(remainingLives);
        System.out.println("HERO CONSTRUCTOR:"+this.getRemainingLives());
        points = 0;
        coins = 0;
        invincibilityTimer = 0;

        this.imageLoader = imageLoader;
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(HeroForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(HeroForm.SMALL);

        Animation animation = new Animation(leftFrames, rightFrames);
        setHeroForm(new HeroForm(animation, false, false, imageLoader));
        setStyle(heroForm.getCurrentStyle(toRight, false, false));
    }

    // public abstract void draw(Graphics g);

    public void move(boolean toRight, Camera camera) {
        if (toRight) {
            setVelX(moveSpeed);
        } else if (camera.getX() < getX()) {
            setVelX(-moveSpeed);
        }

        this.toRight = toRight;
    }

    public abstract boolean onTouchEnemy(GameEngine engine);

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

    public void setHeroForm(HeroForm heroForm) {
        this.heroForm = heroForm;
    }
    public HeroForm getHeroForm() {
        return heroForm;
    }

    public int getPoints() {
        return points;
    }

    public int getCoins() {
        return coins;
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
