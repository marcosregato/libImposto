package org.example;

public class INSS {
    
    public static final float ALIQUOTA = 0.20f; // 20%
    
    public float calcular(float baseCalculo) {
        return baseCalculo * ALIQUOTA;
    }
}
