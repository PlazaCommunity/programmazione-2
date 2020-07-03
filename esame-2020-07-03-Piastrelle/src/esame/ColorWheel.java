package esame;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class ColorWheel {
    ArrayList<Color> colors = new ArrayList<>();
    
    int currentColor;

    public ColorWheel() {
        currentColor = 0;
        
        //TODO: check colors
        colors.add(Color.WHEAT);
        colors.add(Color.TEAL);
        colors.add(Color.DARKBLUE);
        colors.add(Color.OLIVE);
        colors.add(Color.GOLD);
    }
    
    public Color getColor(int i){
        currentColor = i % colors.size();
        return colors.get(currentColor);
    }
    
    public Color getNextColor(){
        return getColor(++currentColor);
    }
    
    
}
