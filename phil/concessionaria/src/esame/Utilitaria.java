/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

/**
 *
 * @author rossi
 */
public class Utilitaria extends Auto {
    public Utilitaria(String model, int displacement, Engine engine, int year, double basePrice, int month) {
        super("Utilitaria", model, displacement, engine, year, basePrice, month);
    }

    @Override
    public double getPrice() {
        double price = basePrice - basePrice * .05;
        price -= engine.getDiscount();
        return price;
    }
}
