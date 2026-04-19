package org.example;

public class IRPJ {
    
    public static final float ALIQUOTA = 0.15f; // 15%
    
    public float calcular(float baseCalculo) {
        return baseCalculo * ALIQUOTA;
    }
}
