package esame;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Normal extends Cell {
    private final Label text;

    public Normal() {
        text = new Label();
        getChildren().add(text);
    }

    public void setText(String string) {
        text.setText(string);
    }

    @Override
    public void onClick(Minesweeper game, int x, int y) {
        onReveal(game, x, y);
    }

    @Override
    public void onReveal(Minesweeper game, int x, int y) {
        game.score--;

        setColor(Color.YELLOW);

        int bombs = 0;

        if (x > 0) bombs += game.cells[y][x - 1] instanceof Bomb ? 1 : 0;
        if (x < Minesweeper.SIZE - 1) bombs += game.cells[y][x + 1] instanceof Bomb ? 1 : 0;
        if (y > 0) bombs += game.cells[y - 1][x] instanceof Bomb ? 1 : 0;
        if (y < Minesweeper.SIZE - 1) bombs += game.cells[y + 1][x] instanceof Bomb ? 1 : 0;

        if (x > 0 && y > 0) bombs += game.cells[y - 1][x - 1] instanceof Bomb ? 1 : 0;
        if (x < Minesweeper.SIZE - 1 && y > 0) bombs += game.cells[y - 1][x + 1] instanceof Bomb ? 1 : 0;
        if (x > 0 && y < Minesweeper.SIZE - 1) bombs += game.cells[y + 1][x - 1] instanceof Bomb ? 1 : 0;
        if (x < Minesweeper.SIZE - 1 && y < Minesweeper.SIZE - 1)
            bombs += game.cells[y + 1][x + 1] instanceof Bomb ? 1 : 0;

        setText(String.valueOf(bombs));
    }
}
