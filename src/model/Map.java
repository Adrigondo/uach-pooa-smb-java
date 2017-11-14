package model;

import model.brick.Brick;
import model.enemy.Enemy;
import model.hero.Mario;
import model.prize.BoostItem;
import model.prize.Coin;
import model.prize.Prize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Map {

    private double timeLimit;
    private Mario mario;
    private ArrayList<Brick> bricks = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Brick> groundBricks = new ArrayList<>();
    private ArrayList<Fireball> fireballs = new ArrayList<>();
    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<BoostItem> boostItems = new ArrayList<>();
    private BufferedImage backgroundImage;
    private double bottomBorder = 720 - 96;


    public Map(double timeLimit, BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.timeLimit = timeLimit;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Brick> getGroundBricks() {
        return groundBricks;
    }

    public void setGroundBricks(ArrayList<Brick> groundBricks) {
        this.groundBricks = groundBricks;
    }

    public ArrayList<Fireball> getFireballs() {
        return fireballs;
    }

    public void setFireballs(ArrayList<Fireball> fireballs) {
        this.fireballs = fireballs;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }

    public ArrayList<BoostItem> getBoostItems() {
        return boostItems;
    }

    public void setBoostItems(ArrayList<BoostItem> boostItems) {
        this.boostItems = boostItems;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void addBrick(Brick brick) {
        this.bricks.add(brick);
    }

    public void addGroundBrick(Brick brick) {
        this.groundBricks.add(brick);
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void drawMap(Graphics2D g2){
        drawBackground(g2);
        drawCoins(g2);
        drawBricks(g2);
        drawEnemies(g2);
        drawMario(g2);
    }

    private void drawCoins(Graphics2D g2) {
        for(Coin coin : coins){
            coin.draw(g2);
        }
    }

    private void drawBackground(Graphics2D g2){
        g2.drawImage(backgroundImage, 0, 0, null);
    }

    private void drawBricks(Graphics2D g2) {
        for(Brick brick : bricks){
            brick.draw(g2);
        }

        for(Brick brick : groundBricks){
            brick.draw(g2);
        }
    }

    private void drawEnemies(Graphics2D g2) {
        for(Enemy enemy : enemies){
            enemy.draw(g2);
        }
    }

    private void drawMario(Graphics2D g2) {
        mario.draw(g2);
    }

    public void updateLocations() {
        mario.updateLocation();
        for(Enemy enemy : enemies){
            enemy.updateLocation();
        }

        for(Iterator<Coin> coinIterator = coins.iterator(); coinIterator.hasNext();){
            Coin coin = coinIterator.next();
            coin.updateLocation();
            if(coin.getY() <= coin.getRevealBoundary()){
                coinIterator.remove();
            }
        }
    }

    public ArrayList<Brick> getAllBricks() {
        ArrayList<Brick> allBricks = new ArrayList<>();

        allBricks.addAll(bricks);
        allBricks.addAll(groundBricks);

        return allBricks;
    }

    public double getBottomBorder() {
        return bottomBorder;
    }

    public void addCoin(Prize prize) {
        coins.add((Coin)prize);
    }
}
