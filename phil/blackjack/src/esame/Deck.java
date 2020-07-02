package esame;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private final LinkedList<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>();
        cards.addAll(Arrays.asList(Card.getCards()));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void sort() {
        Collections.sort(cards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card draw() {
        return cards.pop();
    }

    @Override
    public String toString() {
        return cards.toString().replaceAll("[\\[\\]]", "");
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
        deck.sort();
        System.out.println(deck);
    }
}
