package org.example;

public class PISPASEP {
    
    public static final float ALIQUOTA = 0.0065f; // 0,65%
    
    public float calcular(float baseCalculo) {
        return baseCalculo * ALIQUOTA;
    }
}
