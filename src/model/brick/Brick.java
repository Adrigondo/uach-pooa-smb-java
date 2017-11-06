package model.brick;

import model.GameObject;

import javax.swing.*;
import java.awt.*;

public class Brick extends GameObject{

    public static final Dimension DIMENSION = new Dimension(10, 10);

    private BrickType type;

    public Brick(Point location, ImageIcon style){
        setLocation(location);
        setStyle(style);
    }

    public BrickType getType() {
        return type;
    }

    public void setType(BrickType type) {
        this.type = type;
    }


    @Override
    public void draw() {

    }
}
