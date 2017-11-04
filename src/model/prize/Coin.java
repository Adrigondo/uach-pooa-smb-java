package model.prize;

import model.GameObject;

import java.awt.*;


public class Coin extends GameObject{

    public static final Dimension DIMENSION = new Dimension(10, 10);

    private int point;

    public Coin(int point){
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public void draw() {

    }
}
