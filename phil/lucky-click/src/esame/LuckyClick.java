/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author rossi
 */
public class LuckyClick extends Application {
    
    public static final int SIZE = 10;
    
    public Cell cells[][];
    
    private int score;
    private int tries;
    
    private VBox root;
    
    private Label scoreLabel;
    private Label triesLabel;
    
    private GridPane grid;

    @Override
    public void init() throws Exception {
        score = 0;
        tries = 10;
        
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        
        scoreLabel = new Label(String.format("Punteggio = %d", score));
        scoreLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        triesLabel = new Label(String.format("Tentativi = %d", tries));
        triesLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        
        int normalCellCount = 75;
        int mulCellCount = 10;
        int divCellCount = 10;
        int bombCellCount = 5;

        Random r = new Random();
        List<Cell> pool = new LinkedList();
        for (int i = 0; i < normalCellCount; i++) {
            pool.add(new Base(r));
        }
        for (int i = 0; i < mulCellCount; i++) {
            pool.add(new Mul());
        }
        for (int i = 0; i < divCellCount; i++) {
            pool.add(new Div());
        }
        for (int i = 0; i < bombCellCount; i++) {
            pool.add(new Bomb());
        }
        
        Collections.shuffle(pool, r);
        
        grid = new GridPane();
        grid.setCursor(Cursor.CROSSHAIR);
        
        cells = new Cell[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Cell cell = pool.remove(0);
                int xx = x;
                int yy = y;
                cells[y][x] = cell;
                grid.add(cell, x, y);
                cell.setOnMouseClicked((ev) -> {
                    if (tries > 0) {
                        setTries(tries - 1);
                        cell.onClick(this, xx, yy);
                    }
                });
            }    
        }
        
        root.getChildren().addAll(scoreLabel, triesLabel, grid);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Lucky Click!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
        scoreLabel.setText(String.format("Punteggio = %d", score));
    }
    
    public void setTries(int tries) {
        this.tries = tries;
        if (tries <= 0) {
            grid.setCursor(Cursor.DEFAULT);
            triesLabel.setText("GAME OVER!");
        } else {
            triesLabel.setText(String.format("Tentativi = %d", tries));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
