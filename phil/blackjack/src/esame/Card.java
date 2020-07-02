package esame;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Card extends StackPane implements Comparable<Card> {

    public enum Suite {
        C,
        Q,
        F,
        P,
        J;
    }

    public static final int SIZE = 60;
    public static final Paint COLOR = Color.TURQUOISE;

    public static final int MIN_VAL = 1;
    public static final int MAX_VAL = 4;
    public static final int BJ_VAL = 0;

    private static final Card[] cards;

    static { // executed in static context, to fill `cards` array
        cards = new Card[(Suite.values().length - 1) * (MAX_VAL - MIN_VAL + 1) + 1];
        int i = 0;
        for (Suite s : Suite.values()) {
            if (s == Suite.J) continue;
            for (int val = MIN_VAL; val <= MAX_VAL; val++) {
                cards[i] = new Card(val, s);
                i++;
            }
        }
        cards[i] = blackjack();
    }

    final int value;
    final Suite suite;

    public Card(int value, Suite suite) {
        if (suite == Suite.J && value != BJ_VAL) {
            throw new IllegalArgumentException("Cards with J suite must have a value of 0");
        }
        if (suite != Suite.J && value < MIN_VAL || value > MAX_VAL) {
            throw new IllegalArgumentException("Cards with C, Q, F, P suite must have a value ranging %d-%d");
        }
        this.value = value;
        this.suite = suite;

        Rectangle rect = new Rectangle(SIZE, SIZE, COLOR);
        Label label = new Label(toString());
        getChildren().addAll(rect, label);
    }

    public static Card blackjack() {
        return new Card(0, Suite.J);
    }

    public static Card[] getCards() {
        return cards.clone();
    }

    public boolean eq(Card card) {
        return value == card.value;
    }

    @Override
    public int compareTo(Card other) {
        if (value - other.value != 0) {
            return value - other.value;
        }
        return suite.toString().compareTo(other.suite.toString());
    }

    @Override
    public String toString() {
        return String.format("%d-%s", value, suite.toString());
    }
}
