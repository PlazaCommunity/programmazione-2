/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author rossi
 */
public class Road extends Cell {
    
    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
    
    final Direction direction;
    private BorderPane border;
    private Circle circle;
    
    public Road(Direction direction) {
        super(Color.GRAY);
        this.direction = direction;
        
        border = new BorderPane();
        circle = new Circle();
        
        border.setStyle("-fx-background-color: #666666;");
        circle.setRadius(5);
        circle.setStroke(Color.YELLOW);
        border.setAlignment(circle,Pos.CENTER);
        
        switch (direction) {
            case NORTH:
                border.setTop(circle);
                break;
            case SOUTH:
                border.setBottom(circle);
                break;
            case EAST:
                border.setRight(circle);
                break;
            case WEST:
                border.setLeft(circle);
                break;
            
        }
        
        this.getChildren().add(border);
    }
}
