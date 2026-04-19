package org.example.libImposto.enums;

/**
 * Enumeração das alíquotas de impostos brasileiros.
 * 
 * <p>Centraliza todas as alíquotas padrão dos impostos federais, estaduais e municipais
 * utilizados no sistema de cálculo de impostos.</p>
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public enum TaxRate {
    
    /**
     * Imposto de Renda da Pessoa Jurídica - 15%
     */
    IRPJ(0.15f, "Imposto de Renda da Pessoa Jurídica"),
    
    /**
     * Contribuição para o Financiamento da Seguridade Social - 3%
     */
    COFINS(0.03f, "Contribuição para o Financiamento da Seguridade Social"),
    
    /**
     * Programa de Integração Social - 0,65%
     */
    PIS(0.0065f, "Programa de Integração Social"),
    
    /**
     * Contribuição Social sobre o Lucro Líquido - 9%
     */
    CSLL(0.09f, "Contribuição Social sobre o Lucro Líquido"),
    
    /**
     * Imposto sobre Produtos Industrializados - 15%
     */
    IPI(0.15f, "Imposto sobre Produtos Industrializados"),
    
    /**
     * Imposto sobre Circulação de Mercadorias e Serviços - 18%
     */
    ICMS(0.18f, "Imposto sobre Circulação de Mercadorias e Serviços"),
    
    /**
     * Imposto sobre a Propriedade de Veículos Automotores - 3%
     */
    IPVA(0.03f, "Imposto sobre a Propriedade de Veículos Automotores"),
    
    /**
     * Imposto sobre Transmissão Causa Mortis e Doação - 4%
     */
    ITCMD(0.04f, "Imposto sobre Transmissão Causa Mortis e Doação"),
    
    /**
     * Imposto Sobre Serviços - 2%
     */
    ISS(0.02f, "Imposto Sobre Serviços");
    
    private final float aliquota;
    private final String descricao;
    
    TaxRate(float aliquota, String descricao) {
        this.aliquota = aliquota;
        this.descricao = descricao;
    }
    
    /**
     * Retorna a alíquota do imposto como valor decimal (ex: 0.15 para 15%).
     * 
     * @return alíquota do imposto
     */
    public float getAliquota() {
        return aliquota;
    }
    
    /**
     * Retorna a alíquota do imposto como percentual (ex: 15.0 para 15%).
     * 
     * @return alíquota em percentual
     */
    public float getAliquotaPercentual() {
        return aliquota * 100;
    }
    
    /**
     * Retorna a descrição completa do imposto.
     * 
     * @return descrição do imposto
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Calcula o valor do imposto com base na base de cálculo.
     * 
     * @param baseCalculo valor base para cálculo do imposto
     * @return valor do imposto calculado
     */
    public float calcularImposto(float baseCalculo) {
        return baseCalculo * aliquota;
    }
    
    @Override
    public String toString() {
        return String.format(java.util.Locale.US, "%s (%.2f%%)", descricao, getAliquotaPercentual());
    }
}
