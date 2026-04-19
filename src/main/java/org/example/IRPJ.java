package org.example;

import org.example.libImposto.enums.TaxRate;

public class IRPJ {
    
    public static final float ALIQUOTA = TaxRate.IRPJ.getAliquota(); // 15%
    
    public float calcular(float baseCalculo) {
        return TaxRate.IRPJ.calcularImposto(baseCalculo);
    }
}
