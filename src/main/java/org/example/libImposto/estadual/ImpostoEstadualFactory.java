package org.example.libImposto.estadual;

import org.example.libImposto.config.ConfiguracaoImpostos;

/**
 * Fábrica para cálculo de impostos estaduais brasileiros.
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class ImpostoEstadualFactory {
    
    public float calcularICMS(float valorBase, ConfiguracaoImpostos config) {
        return valorBase * 0.18f;
    }
    
    public float calcularIPVA(float valorBase, ConfiguracaoImpostos config) {
        return valorBase * 0.03f;
    }
    
    public float calcularITCMD(float valorBase, ConfiguracaoImpostos config) {
        return valorBase * 0.04f;
    }
}
