/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import javafx.scene.paint.Color;

/**
 *
 * @author rossi
 */
public class Mul extends Cell {

    public Mul() {
        super();
    }

    @Override
    protected void onClickEvent(LuckyClick game, int x, int y) {
        game.setScore(game.getScore() * 2);
        setLabel("x2");
        setFill(Color.AQUAMARINE);
    }
}
