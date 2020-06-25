package esame;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class ThreeCell extends Cell{
    private static final Color BORDER_COLOR = Color.RED;
    private static final Color COLOR = Color.YELLOW;
    private Button circleBtn;
    private Button triangleBtn;

    public ThreeCell() {
        super();
        
        circleBtn = new Button("O");
        triangleBtn = new Button("T");
        buttons.getChildren().addAll(circleBtn, triangleBtn);
        
        circleBtn.setOnAction(e -> {
            System.out.println("Circle set");
            shapePane.getChildren().remove(shape);
            
            shape = new Circle(CELL_SIZE / 2 * 0.75);
            shape.setFill(COLOR);
            shape.setStroke(BORDER_COLOR);
            shape.setStrokeWidth(BORDER_SIZE);
            shape.setVisible(true);
            
            shapePane.getChildren().add(shape);
            
        });
        
        triangleBtn.setOnAction(e -> {
            System.out.println("Triangle set");
            shapePane.getChildren().remove(shape);
            
            //TODO: change to triangle polygon
            shape = new Rectangle(CELL_SIZE * 0.75, CELL_SIZE * 0.75);
            shape.setFill(COLOR);
            shape.setStroke(BORDER_COLOR);
            shape.setStrokeWidth(BORDER_SIZE);
            shape.setVisible(true);
            
            shapePane.getChildren().add(shape);
        });
        
    }
}
