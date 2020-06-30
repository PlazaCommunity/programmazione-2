/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.LinkedList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author rossi
 */
public class Bomb extends Cell {

    public Bomb() {
        super();
    }

    @Override
    protected void onClickEvent(LuckyClick game, int x, int y) {
        Timeline timeline = new Timeline();
        List<KeyFrame> keyFrames = new LinkedList();
        
        for (int i = 0; i < LuckyClick.SIZE; i++) {
            int pos = i;
            keyFrames.add(new KeyFrame(Duration.millis(i * 20), (e) -> {
                game.cells[y][pos].onClick(game, pos, y);
                game.cells[pos][x].onClick(game, x, pos);
            }));
        }
        
        timeline.getKeyFrames().addAll(keyFrames);
        timeline.setCycleCount(1);
        timeline.play();
        
        setLabel("✚");
        setFill(Color.CORNFLOWERBLUE);
    }
}
