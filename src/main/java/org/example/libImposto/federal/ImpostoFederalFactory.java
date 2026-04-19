package org.example.libImposto.federal;

import org.example.libImposto.config.ConfiguracaoImpostos;

/**
 * Fábrica para cálculo de impostos federais brasileiros.
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class ImpostoFederalFactory {
    
    public float calcularIRPJ(float valorBase, ConfiguracaoImpostos config) {
        return valorBase * 0.15f;
    }
    
    public float calcularCOFINS(float valorBase, ConfiguracaoImpostos config) {
        return valorBase * 0.03f;
    }
    
    public float calcularPIS(float valorBase, ConfiguracaoImpostos config) {
        return valorBase * 0.0065f;
    }
    
    public float calcularCSLL(float valorBase, ConfiguracaoImpostos config) {
        return valorBase * 0.09f;
    }
    
    public float calcularIPI(float valorBase, ConfiguracaoImpostos config) {
        return valorBase * 0.15f;
    }
}
