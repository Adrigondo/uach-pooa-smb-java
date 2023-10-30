package model.enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class KoopaTroopa extends Enemy{

    private final BufferedImage rightImage;

    public KoopaTroopa(double x, double y, BufferedImage style, BufferedImage rightImage ) {
        super(x, y, style);
        setVelX(3);
        this.rightImage = rightImage;
    }


    @Override
    public void draw(Graphics g){
        if(getVelX() > 0){
            g.drawImage(rightImage, (int)getX(), (int)getY(), null);
        }
        else
            super.draw(g);
    }

}
