package org.example;

import org.example.libImposto.enums.TaxRate;

public class PISPASEP {
    
    public static final float ALIQUOTA = TaxRate.PIS.getAliquota(); // 0,65%
    
    public float calcular(float baseCalculo) {
        return TaxRate.PIS.calcularImposto(baseCalculo);
    }
}
