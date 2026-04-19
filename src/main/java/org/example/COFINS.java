package org.example;

import org.example.libImposto.enums.TaxRate;

public class COFINS {
    
    public static final float ALIQUOTA = TaxRate.COFINS.getAliquota(); // 3%
    
    public float calcular(float baseCalculo) {
        return TaxRate.COFINS.calcularImposto(baseCalculo);
    }
}
