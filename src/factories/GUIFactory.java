package factories;

import manager.Spritesheet;
import model.enemy.Goomba;
import model.enemy.KoopaTroopa;
import model.hero.Mario;
import view.ImageLoader;

public interface GUIFactory {
    public String style = "";

    public Mario createMario(int xLocation, int yLocation, ImageLoader imageLoader);

    public Goomba createGoomba(int xLocation, int yLocation, Spritesheet spritesheet);

    public KoopaTroopa createKoopaTroopa(int xLocation, int yLocation, Spritesheet spritesheet);

    public Spritesheet createSpritesheet(ImageLoader imageLoader);

    public ImageLoader createImageLoader();
}
