package esame;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class Cell extends StackPane {
    private static final double SIZE = 50;

    private final Rectangle rectangle;
    private boolean activated;

    public Cell() {
        rectangle = new Rectangle(SIZE, SIZE, Color.BLUE);
        rectangle.setStroke(Color.BLACK);
        getChildren().addAll(rectangle);
        activated = false;
    }

    public final void click(Minesweeper game, int x, int y) {
        if (!activated) {
            onClick(game, x, y);
            activated = true;
        }
    }

    public final void reveal(Minesweeper game, int x, int y) {
        if (!activated) {
            onReveal(game, x, y);
            activated = true;
        }
    }

    protected abstract void onClick(Minesweeper game, int x, int y);

    protected abstract void onReveal(Minesweeper game, int x, int y);

    public void setColor(Paint paint) {
        rectangle.setFill(paint);
    }
}
