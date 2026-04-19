package org.example.libImposto;

import org.example.libImposto.config.ConfiguracaoImpostos;
import org.example.libImposto.federal.ImpostoFederalFactory;
import org.example.libImposto.estadual.ImpostoEstadualFactory;
import org.example.libImposto.municipal.ImpostoMunicipalFactory;
import org.example.libImposto.model.ResultadoImpostos;

/**
 * Classe principal da biblioteca libImposto para cálculo de impostos brasileiros.
 * 
 * <p>Esta classe fornece uma API unificada para calcular todos os tipos de impostos
 * federais, estaduais e municipais, com suporte a diferentes regimes tributários.</p>
 * 
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 * CalculadoraImpostos calculadora = new CalculadoraImpostos();
 * ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
 *     .regimeTributario(RegimeTributario.LUCRO_PRESUMIDO)
 *     .estadoDestino("SP")
 *     .build();
 * 
 * ResultadoImpostos resultado = calculadora.calcularTodos(10000f, config);
 * System.out.println("Total de impostos: R$ " + resultado.getTotal());
 * }</pre>
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class CalculadoraImpostos {
    
    private final ImpostoFederalFactory federalFactory;
    private final ImpostoEstadualFactory estadualFactory;
    private final ImpostoMunicipalFactory municipalFactory;
    
    /**
     * Construtor padrão que inicializa as fábricas de impostos.
     */
    public CalculadoraImpostos() {
        this.federalFactory = new ImpostoFederalFactory();
        this.estadualFactory = new ImpostoEstadualFactory();
        this.municipalFactory = new ImpostoMunicipalFactory();
    }
    
    /**
     * Construtor que permite injeção de fábricas personalizadas (útil para testes).
     * 
     * @param federalFactory Fábrica de impostos federais
     * @param estadualFactory Fábrica de impostos estaduais  
     * @param municipalFactory Fábrica de impostos municipais
     */
    public CalculadoraImpostos(ImpostoFederalFactory federalFactory,
                              ImpostoEstadualFactory estadualFactory,
                              ImpostoMunicipalFactory municipalFactory) {
        this.federalFactory = federalFactory;
        this.estadualFactory = estadualFactory;
        this.municipalFactory = municipalFactory;
    }
    
    /**
     * Calcula todos os impostos aplicáveis a um valor base.
     * 
     * @param valorBase Valor base para cálculo dos impostos
     * @param config Configuração dos impostos e regime tributário
     * @return Resultado com todos os impostos calculados
     * @throws IllegalArgumentException se parâmetros forem inválidos
     */
    public ResultadoImpostos calcularTodos(float valorBase, ConfiguracaoImpostos config) {
        if (valorBase <= 0) {
            throw new IllegalArgumentException("Valor base deve ser maior que zero");
        }
        if (config == null) {
            throw new IllegalArgumentException("Configuração não pode ser nula");
        }
        
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        // Cálculo de impostos federais
        resultado.setIrpj(federalFactory.calcularIRPJ(valorBase, config));
        resultado.setCofins(federalFactory.calcularCOFINS(valorBase, config));
        resultado.setPis(federalFactory.calcularPIS(valorBase, config));
        resultado.setCsll(federalFactory.calcularCSLL(valorBase, config));
        resultado.setIpi(federalFactory.calcularIPI(valorBase, config));
        
        // Cálculo de impostos estaduais
        resultado.setIcms(estadualFactory.calcularICMS(valorBase, config));
        resultado.setIpva(estadualFactory.calcularIPVA(valorBase, config));
        resultado.setItcmd(estadualFactory.calcularITCMD(valorBase, config));
        
        // Cálculo de impostos municipais
        resultado.setIss(municipalFactory.calcularISS(valorBase, config));
        
        return resultado;
    }
    
    /**
     * Calcula apenas os impostos federais.
     * 
     * @param valorBase Valor base para cálculo
     * @param config Configuração dos impostos
     * @return Resultado com apenas impostos federais
     */
    public ResultadoImpostos calcularFederais(float valorBase, ConfiguracaoImpostos config) {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.setIrpj(federalFactory.calcularIRPJ(valorBase, config));
        resultado.setCofins(federalFactory.calcularCOFINS(valorBase, config));
        resultado.setPis(federalFactory.calcularPIS(valorBase, config));
        resultado.setCsll(federalFactory.calcularCSLL(valorBase, config));
        resultado.setIpi(federalFactory.calcularIPI(valorBase, config));
        
        return resultado;
    }
    
    /**
     * Calcula apenas os impostos estaduais.
     * 
     * @param valorBase Valor base para cálculo
     * @param config Configuração dos impostos
     * @return Resultado com apenas impostos estaduais
     */
    public ResultadoImpostos calcularEstaduais(float valorBase, ConfiguracaoImpostos config) {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.setIcms(estadualFactory.calcularICMS(valorBase, config));
        resultado.setIpva(estadualFactory.calcularIPVA(valorBase, config));
        resultado.setItcmd(estadualFactory.calcularITCMD(valorBase, config));
        
        return resultado;
    }
    
    /**
     * Calcula apenas os impostos municipais.
     * 
     * @param valorBase Valor base para cálculo
     * @param config Configuração dos impostos
     * @return Resultado com apenas impostos municipais
     */
    public ResultadoImpostos calcularMunicipais(float valorBase, ConfiguracaoImpostos config) {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.setIss(municipalFactory.calcularISS(valorBase, config));
        
        return resultado;
    }
}
