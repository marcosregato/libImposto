package org.example;

public class COFINS {
    
    public static final float ALIQUOTA = 0.03f; // 3%
    
    public float calcular(float baseCalculo) {
        return baseCalculo * ALIQUOTA;
    }
}
