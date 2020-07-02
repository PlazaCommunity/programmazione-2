package esame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bomb extends Cell {

    @Override
    public void onClick(Minesweeper game, int x, int y) {
        onReveal(game, x, y);
        game.lost = true;
    }

    @Override
    public void onReveal(Minesweeper game, int x, int y) {
        game.score--;

        setColor(Color.INDIANRED);
        Circle circle = new Circle(10, Color.BLACK);
        getChildren().add(circle);
    }
}
