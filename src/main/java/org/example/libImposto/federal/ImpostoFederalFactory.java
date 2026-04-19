package org.example.libImposto.federal;

import org.example.libImposto.config.ConfiguracaoImpostos;
import org.example.libImposto.enums.TaxRate;

/**
 * Fábrica para cálculo de impostos federais brasileiros.
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class ImpostoFederalFactory {
    
    public float calcularIRPJ(float valorBase, ConfiguracaoImpostos config) {
        return TaxRate.IRPJ.calcularImposto(valorBase);
    }
    
    public float calcularCOFINS(float valorBase, ConfiguracaoImpostos config) {
        return TaxRate.COFINS.calcularImposto(valorBase);
    }
    
    public float calcularPIS(float valorBase, ConfiguracaoImpostos config) {
        return TaxRate.PIS.calcularImposto(valorBase);
    }
    
    public float calcularCSLL(float valorBase, ConfiguracaoImpostos config) {
        return TaxRate.CSLL.calcularImposto(valorBase);
    }
    
    public float calcularIPI(float valorBase, ConfiguracaoImpostos config) {
        return TaxRate.IPI.calcularImposto(valorBase);
    }
}
