/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.Comparator;

/**
 *
 * @author rossi
 */
public abstract class Auto {
    public enum Engine {
        BENZINA(0),
        DIESEL(0),
        IBRIDO(5000);
        
        private final int discount;
        Engine(int discount) {
            this.discount = discount;
        }

        public int getDiscount() {
            return discount;
        }
    }
    
    private static int increment = 0;
    
    protected final int id;
    protected final int month;
    
    protected final String type;
    protected final int year;
    protected final double basePrice;
    protected final String model;
    protected final Engine engine;
    protected final int displacement;

    public Auto(String type, String model, int displacement, Engine engine, int year, double basePrice, int month) {
        this.id = ++increment;
        this.type = type;
        this.month = month;
        this.year = year;
        this.basePrice = basePrice;
        this.model = model;
        this.engine = engine;
        this.displacement = displacement;
    }

    public abstract double getPrice();
    
    @Override
    public String toString() {
        return String.format(
                  "id=%d\n"
                + "tipologia=%s\n"
                + "modello=%s\n"
                + "cilindrata=%d\n"
                + "motore=%s\n"
                + "anno=%d\n"
                + "prezzo base=%.2f\n"
                + "prezzo vendita=%.2f\n"
                + "esposto=%d",
                id, 
                type, 
                model,
                displacement, 
                engine.toString().toLowerCase(),
                year,
                basePrice,
                this.getPrice(),
                month
        );
    }
    
    public static class CompareId implements Comparator<Auto> {
        @Override
        public int compare(Auto o1, Auto o2) {
            return o1.id - o2.id;
        }
    }
    
    public static class CompareMonth implements Comparator<Auto> {
        @Override
        public int compare(Auto o1, Auto o2) {
            return o2.month - o1.month;
        }
    }
}
