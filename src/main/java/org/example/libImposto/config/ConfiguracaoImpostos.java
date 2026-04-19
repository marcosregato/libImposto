package org.example.libImposto.config;

import org.example.libImposto.enums.RegimeTributario;

/**
 * Classe de configuração para cálculo de impostos.
 * 
 * <p>Esta classe centraliza todas as configurações necessárias para o cálculo
 * dos diferentes tipos de impostos, incluindo regime tributário, alíquotas
 * personalizadas e informações geográficas.</p>
 * 
 * <p>Utilize o Builder para criar instâncias:</p>
 * <pre>{@code
 * ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
 *     .regimeTributario(RegimeTributario.LUCRO_PRESUMIDO)
 *     .estadoOrigem("SP")
 *     .estadoDestino("RJ")
 *     .aliquotaICMS(0.18f)
 *     .build();
 * }</pre>
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class ConfiguracaoImpostos {
    
    private final RegimeTributario regimeTributario;
    private final String estadoOrigem;
    private final String estadoDestino;
    private final Float aliquotaICMS;
    private final Float aliquotaIPI;
    private final Float aliquotaISS;
    private final Float percentualPresuncao;
    private final boolean isSimplesNacional;
    private final float receitaBruta12Meses;
    
    private ConfiguracaoImpostos(Builder builder) {
        this.regimeTributario = builder.regimeTributario;
        this.estadoOrigem = builder.estadoOrigem;
        this.estadoDestino = builder.estadoDestino;
        this.aliquotaICMS = builder.aliquotaICMS;
        this.aliquotaIPI = builder.aliquotaIPI;
        this.aliquotaISS = builder.aliquotaISS;
        this.percentualPresuncao = builder.percentualPresuncao;
        this.isSimplesNacional = builder.isSimplesNacional;
        this.receitaBruta12Meses = builder.receitaBruta12Meses;
    }
    
    public RegimeTributario getRegimeTributario() {
        return regimeTributario;
    }
    
    public String getEstadoOrigem() {
        return estadoOrigem;
    }
    
    public String getEstadoDestino() {
        return estadoDestino;
    }
    
    public Float getAliquotaICMS() {
        return aliquotaICMS;
    }
    
    public Float getAliquotaIPI() {
        return aliquotaIPI;
    }
    
    public Float getAliquotaISS() {
        return aliquotaISS;
    }
    
    public Float getPercentualPresuncao() {
        return percentualPresuncao;
    }
    
    public boolean isSimplesNacional() {
        return isSimplesNacional;
    }
    
    public float getReceitaBruta12Meses() {
        return receitaBruta12Meses;
    }
    
    /**
     * Builder para criação de ConfiguracaoImpostos.
     */
    public static class Builder {
        private RegimeTributario regimeTributario = RegimeTributario.LUCRO_PRESUMIDO;
        private String estadoOrigem = "SP";
        private String estadoDestino = "SP";
        private Float aliquotaICMS;
        private Float aliquotaIPI;
        private Float aliquotaISS;
        private Float percentualPresuncao = 0.32f; // Padrão para serviços
        private boolean isSimplesNacional = false;
        private float receitaBruta12Meses = 0f;
        
        public Builder regimeTributario(RegimeTributario regimeTributario) {
            this.regimeTributario = regimeTributario;
            return this;
        }
        
        public Builder estadoOrigem(String estadoOrigem) {
            this.estadoOrigem = estadoOrigem;
            return this;
        }
        
        public Builder estadoDestino(String estadoDestino) {
            this.estadoDestino = estadoDestino;
            return this;
        }
        
        public Builder aliquotaICMS(Float aliquotaICMS) {
            this.aliquotaICMS = aliquotaICMS;
            return this;
        }
        
        public Builder aliquotaIPI(Float aliquotaIPI) {
            this.aliquotaIPI = aliquotaIPI;
            return this;
        }
        
        public Builder aliquotaISS(Float aliquotaISS) {
            this.aliquotaISS = aliquotaISS;
            return this;
        }
        
        public Builder percentualPresuncao(Float percentualPresuncao) {
            this.percentualPresuncao = percentualPresuncao;
            return this;
        }
        
        public Builder simplesNacional(boolean simplesNacional) {
            this.isSimplesNacional = simplesNacional;
            return this;
        }
        
        public Builder receitaBruta12Meses(float receitaBruta12Meses) {
            this.receitaBruta12Meses = receitaBruta12Meses;
            return this;
        }
        
        public ConfiguracaoImpostos build() {
            return new ConfiguracaoImpostos(this);
        }
    }
    
    @Override
    public String toString() {
        return "ConfiguracaoImpostos{" +
                "regimeTributario=" + regimeTributario +
                ", estadoOrigem='" + estadoOrigem + '\'' +
                ", estadoDestino='" + estadoDestino + '\'' +
                ", aliquotaICMS=" + aliquotaICMS +
                ", aliquotaIPI=" + aliquotaIPI +
                ", aliquotaISS=" + aliquotaISS +
                ", percentualPresuncao=" + percentualPresuncao +
                ", isSimplesNacional=" + isSimplesNacional +
                ", receitaBruta12Meses=" + receitaBruta12Meses +
                '}';
    }
}
