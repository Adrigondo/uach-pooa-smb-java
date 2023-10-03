package factories;

import manager.Spritesheet;
import model.enemy.Goomba;
import model.enemy.KoopaTroopa;
import model.hero.Mario;
import view.ImageLoader;

public interface GUIFactory {
    public String style = "";
    public ImageLoader imageLoader = new ImageLoader();
    public Spritesheet spritesheet = new Spritesheet();

    Mario createMario(int xLocation, int yLocation);

    Goomba createGoomba(int xLocation, int yLocation);

    KoopaTroopa createKoopaTroopa(int xLocation, int yLocation);

    Spritesheet createSpritesheet();

    ImageLoader createImageLoader();
}
