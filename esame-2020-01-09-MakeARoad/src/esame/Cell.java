/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author rossi
 */
public abstract class Cell extends StackPane {
    
    public static final int SIZE = 50;
    public static final int INSETS = 1;
    public static final int SIZE_WITH_INSETS = SIZE + INSETS * 2;
    
    protected Rectangle rect;

    public Cell(Color color) {
        super();
        rect = new Rectangle(SIZE, SIZE, color);
        setMargin(rect, new Insets(INSETS));
        this.getChildren().add(rect);
    }
}
