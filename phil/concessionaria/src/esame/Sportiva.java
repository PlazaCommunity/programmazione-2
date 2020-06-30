/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.Arrays;
import java.util.Collection;
/**
 *
 * @author rossi
 */
public class Sportiva extends Auto {
    private final Collection<String> accessories;
    
    public Sportiva(String model, int displacement, Engine engine, int year, double basePrice, int month, String... accessories) {
        super("Sportiva", model, displacement, engine, year, basePrice, month);
        this.accessories = Arrays.asList(accessories);
    }
    
    public void addAccessory(String acc) {
        this.accessories.add(acc);
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n"
                + "accessori=%s",
                super.toString(),
                accessories.toString()
        );
    }

    @Override
    public double getPrice() {
        return this.basePrice;
    }
}
