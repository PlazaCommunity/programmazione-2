/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.ArrayList;
import java.util.Collection;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author rossi
 */
public class MakeARoad extends Application {

    private static final int CELL_COLS = 10;
    private static final int CELL_ROWS = 10;
    
    private VBox root;
    private GridPane grid;
    
    private HBox controls;
    
    private GridPane pathControls;
    private VBox autoControls;
    
    private Collection<Button> pathButtons;
    
    private Collection<Button> autoButtons;
    
    private ClickAction action;
    
    public enum ClickAction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        GRASS,
        
        AUTO,
        
        NONE
    }
    
    @Override
    public void init() throws Exception {
        root = new VBox();
        
        grid = new GridPane();
        grid.setCursor(Cursor.CROSSHAIR);
        
        for (int y = 0; y < CELL_ROWS; y++) {
            for (int x = 0; x < CELL_COLS; x++) {
                grid.add(new Grass(), x, y);
            }
        }
        
        controls = new HBox();
        controls.setSpacing(40);
        controls.setPadding(new Insets(4));
        
        pathControls = new GridPane();
        pathButtons = new ArrayList();
        Button north = createUniformButton("Strada NORD");
        north.setOnAction(ev -> setAction(ev.getTarget(), ClickAction.NORTH));
        pathControls.add(north, 1, 0);
        pathButtons.add(north);
        Button west = createUniformButton("Strada OVEST");
        west.setOnAction(ev -> setAction(ev.getTarget(), ClickAction.WEST));
        pathControls.add(west, 0, 1);
        pathButtons.add(west);
        Button grass = createUniformButton("PRATO");
        grass.setOnAction(ev -> setAction(ev.getTarget(), ClickAction.GRASS));
        pathControls.add(grass, 1, 1);
        pathButtons.add(grass);
        Button east = createUniformButton("Strada EST");
        east.setOnAction(ev -> setAction(ev.getTarget(), ClickAction.EAST));
        pathControls.add(east, 2, 1);
        pathButtons.add(east);
        Button south = createUniformButton("Strada SUD");
        south.setOnAction(ev -> setAction(ev.getTarget(), ClickAction.SOUTH));
        pathControls.add(south, 1, 2);
        pathButtons.add(south);
        
        autoControls = new VBox();
        autoButtons = new ArrayList();
        Button autoAdd = createUniformButton("Aggiungi auto");
        autoAdd.setOnAction(ev -> setAction(ev.getTarget(), ClickAction.AUTO));
        autoButtons.add(autoAdd);
        autoButtons.add(createUniformButton("Muovi auto"));
        autoButtons.add(createUniformButton("Reset auto"));
        autoControls.getChildren().addAll(autoButtons);
        
        controls.getChildren().addAll(pathControls, autoControls);
        
        root.getChildren().addAll(grid, controls);
        
        action = ClickAction.NONE;
    }
    
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(
                root, 
                CELL_COLS * Cell.SIZE_WITH_INSETS,
                CELL_ROWS * Cell.SIZE_WITH_INSETS + 120
        );
        
        primaryStage.setTitle("Make A Road!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static final int BUTTON_WIDTH = 110;
    private static final int BUTTON_HEIGHT = 28;
    
    private Button createUniformButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(BUTTON_WIDTH);
        button.setMaxWidth(BUTTON_WIDTH);
        button.setMinHeight(BUTTON_HEIGHT);
        button.setMaxHeight(BUTTON_HEIGHT);
        return button;
    }
    
    private void setAction(EventTarget target, ClickAction action) {
        switch (action) {
            case NORTH:
            case SOUTH:
            case EAST:
            case WEST:
            case GRASS: {
                pathButtons.forEach(btn -> {
                    if (btn != target) {
                        btn.setDisable(true);
                    }
                });
                break;
            }
            default:
                pathButtons.forEach(btn -> btn.setDisable(false));
                break;
        }
        System.out.println(action);
        this.action = action;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
}
