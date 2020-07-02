package esame;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends VBox {
    public enum HandPosition {
        TOP,
        BOTTOM
    }

    private final BlackJack game;
    private final String name;
    private final List<Card> hand;
    private final Player opponent;

    private final Button drawOpponent;
    private final Button findDupes;
    private final Button drawDeck;

    private final HBox handBox;

    public Player(BlackJack game, String name, HandPosition position, Player opponent) {
        this.game = game;
        this.name = name;
        this.hand = new ArrayList<>();
        this.opponent = opponent;

        HBox controls = new HBox();

        Label nameLabel = new Label(name);

        drawOpponent = new Button("Pesca dall'avversario");
        findDupes = new Button("Cerca coppie");
        drawDeck = new Button("Pesca dal mazzo");

        controls.getChildren().addAll(nameLabel, drawOpponent, findDupes, drawDeck);
        controls.setAlignment(Pos.CENTER);
        controls.setSpacing(20);

        handBox = new HBox();
        handBox.setAlignment(Pos.CENTER);
        handBox.setSpacing(10);

        switch (position) {
            case TOP:
                getChildren().addAll(handBox, controls);
                break;
            case BOTTOM:
                getChildren().addAll(controls, handBox);
                break;
        }

        setSpacing(10);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, hand.toString().replaceAll("[\\[\\]]", ""));
    }

    public void drawCard() {
        if (!game.deck.isEmpty()) {
            Card card = game.deck.draw();
            addCard(card);
        }
    }

    public void addCard(Card card) {
        hand.add(card);
        handBox.getChildren().add(card);
    }
}
