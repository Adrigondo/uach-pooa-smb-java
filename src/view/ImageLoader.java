package view;

import javax.imageio.ImageIO;

import model.hero.HeroForm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private BufferedImage marioForms;
    private BufferedImage brickAnimation;
    private static ImageLoader instance;

    private ImageLoader() {
        // marioForms = loadImage("/mario-forms.png");
        // brickAnimation = loadImage("/brick-animation.png");
    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
            return instance;
        }
        return instance;
    }

    public void setMarioForms(String path) {
        marioForms = loadImage(path);
    }

    public void setBrickAnimation(String path) {
        brickAnimation = loadImage(path);
    }

    public BufferedImage loadImage(String path) {
        BufferedImage imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(getClass().getResource("/media" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

    public BufferedImage loadImage(File file) {
        BufferedImage imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

    public BufferedImage getSubImage(BufferedImage image, int col, int row, int w, int h) {
        if ((col == 1 || col == 4) && row == 3) { // koopa
            return image.getSubimage((col - 1) * 48, 128, w, h);
        }
        return image.getSubimage((col - 1) * 48, (row - 1) * 48, w, h);
    }

    public BufferedImage[] getLeftFrames(int heroForm) {
        BufferedImage[] leftFrames = new BufferedImage[5];
        int col = 1;
        int width = 52, height = 48;

        if (heroForm == HeroForm.SUPER) { // super mario
            col = 4;
            width = 48;
            height = 96;
        } else if (heroForm == HeroForm.FIRE) { // fire mario
            col = 7;
            width = 48;
            height = 96;
        }

        for (int i = 0; i < 5; i++) {
            leftFrames[i] = marioForms.getSubimage((col - 1) * width, (i) * height, width, height);
        }
        return leftFrames;
    }

    public BufferedImage[] getRightFrames(int heroForm) {
        BufferedImage[] rightFrames = new BufferedImage[5];
        int col = 2;
        int width = 52, height = 48;

        if (heroForm == HeroForm.SUPER) { // super mario
            col = 5;
            width = 48;
            height = 96;
        } else if (heroForm == HeroForm.FIRE) { // fire mario
            col = 8;
            width = 48;
            height = 96;
        }

        for (int i = 0; i < 5; i++) {
            rightFrames[i] = marioForms.getSubimage((col - 1) * width, (i) * height, width, height);
        }
        return rightFrames;
    }

    public BufferedImage[] getBrickFrames() {
        BufferedImage[] frames = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            frames[i] = brickAnimation.getSubimage(i * 105, 0, 105, 105);
        }
        return frames;
    }
}
