package org.example.libImposto.municipal;

import org.example.libImposto.config.ConfiguracaoImpostos;
import org.example.libImposto.enums.TaxRate;

/**
 * Fábrica para cálculo de impostos municipais brasileiros.
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class ImpostoMunicipalFactory {
    
    public float calcularISS(float valorBase, ConfiguracaoImpostos config) {
        return TaxRate.ISS.calcularImposto(valorBase);
    }
}
