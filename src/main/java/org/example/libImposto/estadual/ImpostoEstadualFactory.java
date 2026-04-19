package org.example.libImposto.estadual;

import org.example.libImposto.config.ConfiguracaoImpostos;
import org.example.libImposto.enums.TaxRate;

/**
 * Fábrica para cálculo de impostos estaduais brasileiros.
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class ImpostoEstadualFactory {
    
    public float calcularICMS(float valorBase, ConfiguracaoImpostos config) {
        return TaxRate.ICMS.calcularImposto(valorBase);
    }
    
    public float calcularIPVA(float valorBase, ConfiguracaoImpostos config) {
        return TaxRate.IPVA.calcularImposto(valorBase);
    }
    
    public float calcularITCMD(float valorBase, ConfiguracaoImpostos config) {
        return TaxRate.ITCMD.calcularImposto(valorBase);
    }
}
