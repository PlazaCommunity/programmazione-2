/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import javafx.scene.shape.Polygon;

/**
 *
 * @author Thomas
 */
public class Triangle extends Polygon{

    public Triangle(double base, double height) {
        super();
        this.getPoints().addAll(new Double[]{
            base/2, 0.0,
            0.0, height,
            base, height
        });
    }

    
    
}
