package org.example.libImposto.enums;

/**
 * Enumeração dos regimes tributários brasileiros.
 * 
 * <p>Define os principais regimes tributários utilizados no Brasil para
 * cálculo de impostos federais, cada um com suas características específicas.</p>
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public enum RegimeTributario {
    
    /**
     * Lucro Presumido - regime simplificado baseado em percentuais pré-definidos
     * de presunção de lucro sobre a receita bruta.
     */
    LUCRO_PRESUMIDO("Lucro Presumido"),
    
    /**
     * Lucro Real - regime baseado no lucro contábil real da empresa,
     * com ajustes previstos na legislação.
     */
    LUCRO_REAL("Lucro Real"),
    
    /**
     * Simples Nacional - regime unificado para micro e pequenas empresas
     * com alíquotas progressivas e recolhimento mensal único.
     */
    SIMPLES_NACIONAL("Simples Nacional");
    
    private final String descricao;
    
    RegimeTributario(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}
