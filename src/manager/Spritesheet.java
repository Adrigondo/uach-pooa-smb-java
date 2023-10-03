package manager;

import java.awt.image.BufferedImage;

import view.ImageLoader;

public class Spritesheet {
    ImageLoader imageLoader;
    public BufferedImage sprite;
    BufferedImage backgroundImage;
    BufferedImage superMushroom;
    BufferedImage oneUpMushroom;
    BufferedImage fireFlower;
    BufferedImage coin;
    BufferedImage ordinaryBrick;
    BufferedImage surpriseBrick;
    BufferedImage groundBrick;
    BufferedImage pipe;
    public BufferedImage goombaLeft;
    public BufferedImage goombaRight;
    public BufferedImage koopaLeft;
    public BufferedImage koopaRight;
    BufferedImage endFlag;

    public Spritesheet() {
    }

    public Spritesheet(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void setBackgroundImage(String path) {
        backgroundImage = imageLoader.loadImage(path);
    }

    public void setSprite(String path) {
        sprite = imageLoader.loadImage(path);
    }

    public void superMushroom(int col, int row, int xSize, int ySize) {
        this.superMushroom = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void oneUpMushroom(int col, int row, int xSize, int ySize) {
        this.oneUpMushroom = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void fireFlower(int col, int row, int xSize, int ySize) {
        this.fireFlower = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void coin(int col, int row, int xSize, int ySize) {
        this.coin = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void ordinaryBrick(int col, int row, int xSize, int ySize) {
        this.ordinaryBrick = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void surpriseBrick(int col, int row, int xSize, int ySize) {
        this.surpriseBrick = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void groundBrick(int col, int row, int xSize, int ySize) {
        this.groundBrick = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void pipe(int col, int row, int xSize, int ySize) {
        this.pipe = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void goombaLeft(int col, int row, int xSize, int ySize) {
        this.goombaLeft = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void goombaRight(int col, int row, int xSize, int ySize) {
        this.goombaRight = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void koopaLeft(int col, int row, int xSize, int ySize) {
        this.koopaLeft = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void koopaRight(int col, int row, int xSize, int ySize) {
        this.koopaRight = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }

    public void endFlag(int col, int row, int xSize, int ySize) {
        this.endFlag = imageLoader.getSubImage(sprite, col, row, xSize, ySize);
    }
}
