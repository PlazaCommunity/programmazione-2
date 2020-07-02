/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import esame.Auto.Engine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author rossi
 */
public class Concessionaria extends Application {
    
    private List<Auto> archive;

    private AnchorPane root;
    
    private Label text;
    
    private HBox controls;
    private Button sort;
    private Button next;
    private Button prev;
    
    private int index;
    
    @Override
    public void init() throws Exception {
        archive = new ArrayList();
        archive.add(new Berlina("B1", 2000, Engine.BENZINA, 2016, 20000, 2));
        archive.add(new Berlina("B2", 1800, Engine.DIESEL,  2018, 30000, 6));
        archive.add(new Berlina("B3", 2200, Engine.IBRIDO,  2017, 35000, 4));
        
        archive.add(new Utilitaria("U1", 1000, Engine.BENZINA, 2018, 10000, 1));
        archive.add(new Utilitaria("U2", 1300, Engine.IBRIDO,  2014, 18000, 2));
        archive.add(new Utilitaria("I3", 1200, Engine.DIESEL,  2016, 12000, 6));
        
        archive.add(new Sportiva("S1", 3000, Engine.BENZINA, 2015, 50000, 3, "spoiler", "cambio automatico"));
        archive.add(new Sportiva("S2", 2800, Engine.BENZINA, 2018, 30000, 6, "tetto a scomparsa", "sedili in pelle"));
        archive.add(new Sportiva("S3", 3000, Engine.BENZINA, 2013, 65000, 5, "cromature", "volante ergonomico"));
        
        Collections.sort(archive, new Auto.CompareId());
        
        root = new AnchorPane();
        
        text = new Label(archive.get(0).toString());
        
        controls = new HBox();
        
        sort = new Button("id");
        
        sort.setOnMouseClicked((e) -> {
            if(sort.getText().equals("id")){
                sort.setText("mese");
                Collections.sort(archive, new Auto.CompareMonth());
            } else {
                sort.setText("id");
                Collections.sort(archive, new Auto.CompareId());
            }
            setIndex(0);
        });
        
        next = new Button(">");
        prev = new Button("<");
        
        next.setOnMouseClicked((e) -> setIndex(this.index + 1));
        prev.setOnMouseClicked((e) -> setIndex(this.index - 1));
        
        setIndex(0);
        
        controls.getChildren().addAll(sort, prev, next);
        controls.setAlignment(Pos.BOTTOM_RIGHT);
        
        root.getChildren().addAll(text, controls);
        AnchorPane.setTopAnchor(text, 0.);
        AnchorPane.setBottomAnchor(controls, 0.);
        AnchorPane.setRightAnchor(controls, 0.);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 400, 200);
        
        primaryStage.setTitle("Concessionaria auto");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setIndex(int index) {
        this.index = index;
        text.setText(archive.get(index).toString());
        prev.setDisable(index <= 0);
        next.setDisable(index >= archive.size() - 1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
