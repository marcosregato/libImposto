package org.example;

import org.example.libImposto.enums.TaxRate;

public class CSLL {
    
    public static final float ALIQUOTA = TaxRate.CSLL.getAliquota(); // 9%
    
    public float calcular(float baseCalculo) {
        return TaxRate.CSLL.calcularImposto(baseCalculo);
    }
}
