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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    private boolean createAuto;
    
    public enum ClickAction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        GRASS,
        
        NONE
    }
    
    @Override
    public void init() throws Exception {
        root = new VBox();
        
        grid = new GridPane();
        grid.setCursor(Cursor.CROSSHAIR);
        
        for (int y = 0; y < CELL_ROWS; y++) {
            for (int x = 0; x < CELL_COLS; x++) {
                Cell cell = new Grass();
                cell.setOnMouseClicked(cellHandler);
                grid.add(cell, x, y);
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
        Button autoMove = createUniformButton("Muovi auto");
        Button autoReset = createUniformButton("Reset auto");
        
        autoAdd.setOnAction(ev -> {
            createAuto = true;
            autoMove.setDisable(false);
            autoAdd.setDisable(true);
        });
        
        autoMove.setDisable(true);
        autoMove.setOnAction(ev -> {
            
        });
        
        autoReset.setOnAction(ev -> {
            createAuto = false;
            autoMove.setDisable(true);
            autoAdd.setDisable(false);
        });
        
        autoButtons.add(autoAdd);
        autoButtons.add(autoMove);
        autoButtons.add(autoReset);
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
        button.setFocusTraversable(false);
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
                    btn.setDisable(btn == target);
                });
                break;
            }
        }
        this.action = action;
    }
    
    private Road.Direction dirFromAction(ClickAction action) {
        switch (action) {
            case NORTH:
                return Road.Direction.NORTH;
            case SOUTH:
                return Road.Direction.SOUTH;
            case EAST:
                return Road.Direction.EAST;
            case WEST:
                return Road.Direction.WEST;
        }
        throw new RuntimeException("Unreachable code");
    }

    EventHandler cellHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                if(event.getSource() instanceof Cell){
                    Cell source = (Cell) event.getSource();
                    int col = GridPane.getColumnIndex(source);
                    int row = GridPane.getRowIndex(source);
                    switch (action) {
                        case NORTH:
                        case SOUTH:
                        case EAST:
                        case WEST:
                            grid.getChildren().remove(source);
                            Cell road = new Road(dirFromAction(action));
                            road.setOnMouseClicked(cellHandler);
                            grid.add(road, col, row);
                            break;
                        case GRASS:
                            grid.getChildren().remove(source);
                            Cell grass = new Grass();
                            grass.setOnMouseClicked(cellHandler);
                            grid.add(grass, col, row);
                            break;
                    }
                    System.out.println("Clicked cell " + row + " - " + col);
                }
            }
        };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
}
