package esame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BlackJack extends Application {
    private AnchorPane root;

    Deck deck;
    private Player pippo;
    private Player pluto;

    @Override
    public void init() throws Exception {
        root = new AnchorPane();

        deck = new Deck();
        deck.shuffle();

        pippo = new Player(this, "PIPPO", Player.HandPosition.TOP, pluto);
        pluto = new Player(this, "PLUTO", Player.HandPosition.BOTTOM, pippo);

        for (int i = 0; i < 4; i++) {
            pippo.drawCard();
            pluto.drawCard();
        }

        root.getChildren().addAll(pluto, pippo);

        AnchorPane.setTopAnchor(pluto, 0.);
        AnchorPane.setLeftAnchor(pluto, 0.);
        AnchorPane.setRightAnchor(pluto, 0.);
        AnchorPane.setBottomAnchor(pippo, 0.);
        AnchorPane.setLeftAnchor(pippo, 0.);
        AnchorPane.setRightAnchor(pippo, 0.);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root, 500, 300);

        primaryStage.setTitle("Black Jack!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        BlackJack.launch(args);
    }
}
