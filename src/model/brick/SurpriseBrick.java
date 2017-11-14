package model.brick;

import model.Map;
import model.hero.Mario;
import model.prize.Prize;

import java.awt.image.BufferedImage;

public class SurpriseBrick extends Brick{

    private Prize prize;

    public SurpriseBrick(double x, double y, BufferedImage style, Prize prize) {
        super(x, y, style);
        setBreakable(false);
        setEmpty(false);
        this.prize = prize;
    }

    @Override
    public void reveal(Map gameMap){
        if(prize != null){
            gameMap.addRevealedPrize(prize);
            prize.reveal(gameMap);
        }

        setEmpty(true);
        this.prize = null;
    }

    @Override
    public Prize getPrize(){
        return prize;
    }
}
