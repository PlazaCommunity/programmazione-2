package esame;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public abstract class Cell extends VBox{
    protected static final double CELL_SIZE = 50.0; //px
    protected static final double BORDER_SIZE = 1; //px
    protected StackPane shapePane;
    protected Shape shape;
    protected HBox buttons;
    private Button clearBtn;
    
    Cell(){
        clearBtn = new Button("C");
        shape = new Rectangle(CELL_SIZE * 0.75, CELL_SIZE * 0.75); //placeholder
        shape.setVisible(false);
       
        buttons = new HBox(clearBtn);
        buttons.setAlignment(Pos.CENTER);
        shapePane = new StackPane();
        shapePane.getChildren().add(shape);
        shapePane.setPadding(new Insets(CELL_SIZE * 0.15));
        
        clearBtn.setOnAction(e -> clear());
        this.setOnMouseClicked(e -> clear());
        
        this.getChildren().addAll(shapePane, buttons);
        this.setAlignment(Pos.CENTER);
        this.setMinWidth(CELL_SIZE);
        this.setMinHeight(CELL_SIZE);
        this.setPadding(new Insets(CELL_SIZE * 0.1));
    }
    
    public void clear(){
        System.out.println("Clearing cell");
        shape.setVisible(false);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
       
        return( this.shape.getClass() == other.shape.getClass() );
    }
    

    
    
    

}
