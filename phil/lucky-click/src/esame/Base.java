/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author rossi
 */
public class Base extends Cell {
    
    int val;

    public Base(Random r) {
        super();
        val = (r.nextInt(10) + 1) * 100;
    }

    @Override
    protected void onClickEvent(LuckyClick game, int x, int y) {
        game.setScore(game.getScore() + val);
        setLabel(String.valueOf(val));
        setFill(Color.WHITE);
    }
}
