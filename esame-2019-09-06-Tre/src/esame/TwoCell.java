package esame;

import static esame.Cell.CELL_SIZE;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class TwoCell extends Cell{
    private Button randomBtn;

    public TwoCell() {
        super();
        randomBtn = new Button("R");
        buttons.getChildren().add(randomBtn);
        
        randomBtn.setOnAction(e -> {
            shapePane.getChildren().remove(shape);
            
            if(new Random().nextDouble() < 0.5){
                shape = new Circle(CELL_SIZE / 2 * 0.75);
                shape.setStroke(Color.RED);
                shape.setFill(Color.YELLOW);
            }else{
                shape = new Rectangle(CELL_SIZE * 0.75, CELL_SIZE * 0.75);
                shape.setStroke(Color.LIGHTBLUE);
                shape.setFill(Color.LIGHTGRAY);
            }
            shape.setStrokeWidth(BORDER_SIZE);
            shape.setVisible(true);
            
            shapePane.getChildren().add(shape);
        });
    }
    
    
}
