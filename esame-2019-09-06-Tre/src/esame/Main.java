package esame;

import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Main extends Application{
    static final int COLS = 3;
    static final int ROWS = COLS;
    
    Scene scene;
    VBox root;
    GridPane grid;
    HBox buttons;
    Button clearBtn;
    Button checkBtn;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init(){
        clearBtn = new Button("clear All");
        checkBtn = new Button("Check");
        buttons = new HBox(clearBtn, checkBtn);
        grid = new GridPane();
        root = new VBox(grid, buttons);
        scene = new Scene(root);
        
        buttons.setSpacing(30.0);
        buttons.setAlignment(Pos.CENTER);
        
        fill();
        
        clearBtn.setOnAction(clearHandler);
        checkBtn.setOnAction(checkHandler);
        scene.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case A:
                    clearHandler.handle(e);
                    break;
                    
                case C:
                    checkHandler.handle(e);
                    break;
            }
        });
    }
    
    @Override
    public void start(Stage window) {
        window.setScene(scene);
        window.setTitle("TRE!");
        window.setResizable(false);
        window.show();
    }
    
    private void fill(){
        for(int y = 0; y < ROWS; y++){
            for(int x = 0; x < COLS; x++){
                if(new Random().nextDouble() < 0.5){
                    grid.add(new TwoCell(), x, y);
                }else{
                    grid.add(new ThreeCell(), x, y);
                }
            }
        }
    }
    
    Cell getCellByCoords(int col, int row){
        for(Node n : grid.getChildren()){
            if(GridPane.getColumnIndex(n) == col && GridPane.getRowIndex(n) == row && n instanceof Cell){
                return ((Cell) n);
            }
        }
        return null;
    }
    
    
    EventHandler clearHandler = e -> {
        for(Node n : grid.getChildren()){
            if(n instanceof Cell){
                ((Cell) n).clear();
            }
        }
    };
    
    EventHandler checkHandler = e -> {
        throw new UnsupportedOperationException("Function checkHandler is not available yet");
    };
    
}
