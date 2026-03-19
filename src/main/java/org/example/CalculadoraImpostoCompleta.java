package org.example;

import interfaceImposto.ImpostoEstadualInterface;
import interfaceImposto.ImpostoFederalInterface;
import interfaceImposto.ImpostoMunicipalInterface;

public class CalculadoraImpostoCompleta {

    private ImpostoFederalInterface impostoFederal;
    private ImpostoEstadualInterface impostoEstadual;
    private ImpostoMunicipalInterface impostoMunicipal;

    public CalculadoraImpostoCompleta(ImpostoFederalInterface impostoFederal, 
                                      ImpostoEstadualInterface impostoEstadual, 
                                      ImpostoMunicipalInterface impostoMunicipal) {
        this.impostoFederal = impostoFederal;
        this.impostoEstadual = impostoEstadual;
        this.impostoMunicipal = impostoMunicipal;
    }

    /**
     * Calcula o custo total de uma venda interestadual de produto para consumidor final.
     * Inclui IPI, ICMS (DIFAL) e valor do produto.
     */
    public float calcularVendaInterestadual(float valorProduto, float frete, float aliquotaIpi, 
                                            float aliquotaIcmsDestino, float aliquotaIcmsInterestadual) {
        
        // 1. Calcula IPI
        float ipi = impostoFederal.impostoIpi(valorProduto, frete, 0, 0, aliquotaIpi);

        // 2. Base de cálculo do ICMS (Valor + Frete + IPI)
        float baseIcms = valorProduto + frete + ipi;

        // 3. Calcula DIFAL (Diferencial de Alíquota)
        float difal = impostoEstadual.impostoDifal(baseIcms, aliquotaIcmsDestino, aliquotaIcmsInterestadual);

        // Retorna o total da nota (Valor + Frete + IPI + DIFAL - se for cobrado por fora, ou apenas impostos)
        // Aqui vamos retornar o valor total dos impostos calculados
        return ipi + difal;
    }

    /**
     * Calcula impostos sobre serviço (ISS + PIS + COFINS).
     */
    public float calcularServico(float valorServico, float aliquotaIss, float aliquotaPis, float aliquotaCofins) {
        float iss = impostoMunicipal.impostoIss(valorServico, aliquotaIss);
        float pis = impostoFederal.impostoPisPasep(valorServico, aliquotaPis);
        float cofins = impostoFederal.impostoConfins(valorServico, aliquotaCofins);

        return iss + pis + cofins;
    }
}
