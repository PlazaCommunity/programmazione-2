package esame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Minesweeper extends Application {
    public final static int SIZE = 9;

    public Cell[][] cells;

    private VBox root;
    private GridPane grid;

    private Button test;
    private Button random;
    private Button exit;
    private Button peek;

    public int score;
    public boolean lost;
    private boolean isPeeking;
    private boolean isPlaying;

    public void setCell(int x, int y, Cell cell) {
        cells[y][x] = cell;
        grid.add(cell, x, y);
        cell.setOnMouseClicked(event -> {
            if (isPlaying) {
                if (isPeeking) {
                    cell.onReveal(this, x, y);
                    isPeeking = false;
                } else {
                    cell.onClick(this, x, y);
                }
                if (lost) {
                    alert("Hai perso, mona!");
                    isPlaying = false;
                } else if (score <= 10) {
                    alert("Hai vinto!");
                    isPlaying = false;
                }
            }
        });
    }

    private void alert(String text) {
        Stage prompt = new Stage();
        VBox root = new VBox();
        Label won = new Label(text);
        Button ok = new Button("Ok");
        ok.setOnMouseClicked(event -> {
            menu();
            prompt.close();
        });
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(won, ok);

        Scene scene = new Scene(root);
        prompt.setScene(scene);
        prompt.showAndWait();
    }

    public void fillTest() {
        cells = new Cell[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (x == y || (y == 0 && x == SIZE - 1)) {
                    setCell(x, y, new Bomb());
                } else {
                    setCell(x, y, new Normal());
                }
            }
        }
    }

    public void fillRandom() {
        cells = new Cell[SIZE][SIZE];
        ArrayList<Cell> pool = new ArrayList<>();

        int bombs = 10;
        int normal = SIZE * SIZE - bombs;

        for (int i = 0; i < bombs; i++) {
            pool.add(new Bomb());
        }
        for (int i = 0; i < normal; i++) {
            pool.add(new Normal());
        }

        Collections.shuffle(pool);

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                setCell(x, y, pool.remove(0));
            }
        }
    }

    public void menu() {
        peek.setDisable(true);
        exit.setDisable(true);
        random.setDisable(false);
        test.setDisable(false);

        isPlaying = false;

        fillTest();
    }


    public void game() {
        grid.setCursor(Cursor.CROSSHAIR);

        isPlaying = true;
        isPeeking = false;
        peek.setDisable(false);
        exit.setDisable(false);
        random.setDisable(true);
        test.setDisable(true);

        score = SIZE * SIZE;
        lost = false;

        grid.getChildren().clear();
    }

    @Override
    public void init() {
        root = new VBox();

        grid = new GridPane();

        HBox controls = new HBox();
        controls.setSpacing(50);
        controls.setPadding(new Insets(10));

        test = new Button("Test");
        test.setOnMouseClicked(event -> {
            game();
            fillTest();
        });

        random = new Button("Random");
        random.setOnMouseClicked(event -> {
            game();
            fillRandom();
        });

        exit = new Button("Abbandona");
        exit.setOnMouseClicked(event -> menu());

        peek = new Button("Sbircia");
        peek.setOnMouseClicked(event -> {
            isPeeking = true;
            peek.setDisable(true);
        });

        controls.getChildren().addAll(test, random, exit, peek);
        root.getChildren().addAll(grid, controls);

        menu();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Minesweeper");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Minesweeper.launch(args);
    }
}
