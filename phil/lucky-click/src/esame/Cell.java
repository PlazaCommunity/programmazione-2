/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author rossi
 */
public abstract class Cell extends StackPane {
    
    public static final double SIZE = 50.;
    
    private final Rectangle rect;
    private final Label text;
    
    private boolean active;
    
    public Cell() {
        rect = new Rectangle();
        
        rect.setWidth(SIZE);
        rect.setHeight(SIZE);
        rect.setFill(Color.BLANCHEDALMOND);
        rect.setStroke(Color.BLACK);
        
        text = new Label("Ø");
        
        getChildren().addAll(rect, text);
    }
    
    public void onClick(LuckyClick game, int x, int y) {
        if (!active) {
            active = true;
            onClickEvent(game, x, y);
        }
    }
    
    protected abstract void onClickEvent(LuckyClick game, int x, int y);
    
    protected void setLabel(String string) {
        text.setText(string);
    }
    
    protected void setFill(Paint paint) {
        rect.setFill(paint);
    }
}
