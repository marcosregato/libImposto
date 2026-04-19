package org.example.libImposto.municipal;

import org.example.libImposto.config.ConfiguracaoImpostos;

/**
 * Fábrica para cálculo de impostos municipais brasileiros.
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class ImpostoMunicipalFactory {
    
    public float calcularISS(float valorBase, ConfiguracaoImpostos config) {
        return valorBase * 0.02f;
    }
}
