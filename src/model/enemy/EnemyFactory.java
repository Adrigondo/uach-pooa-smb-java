package model.enemy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import manager.Spritesheet;


public class EnemyFactory {

    private static final Map<String, BufferedImage> rightImages=new HashMap<String,BufferedImage>();

    public static BufferedImage getRightImage(String enemy, BufferedImage rightImage){
        if(rightImages.get(enemy)==null){
            rightImages.put(enemy,rightImage);
        }
        return rightImages.get(enemy);
    }
}
