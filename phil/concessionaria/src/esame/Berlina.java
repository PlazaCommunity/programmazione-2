/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.Calendar;

/**
 *
 * @author rossi
 */
public class Berlina extends Auto {
    public Berlina(String model, int displacement, Engine engine, int year, double basePrice, int month) {
        super("Berlina", model, displacement, engine, year, basePrice, month);
    }

    @Override
    public double getPrice() {
        int now = Calendar.getInstance().get(Calendar.MONTH);
        double diff = Math.max(0, Math.min(0.05, (now - month) * 0.01));
        double price = basePrice - basePrice * diff;
        System.out.println(diff);
        price -= engine.getDiscount();
        return price;
    }
}
