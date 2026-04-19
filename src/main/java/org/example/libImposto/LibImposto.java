package org.example.libImposto;

import org.example.libImposto.config.ConfiguracaoImpostos;
import org.example.libImposto.enums.RegimeTributario;
import org.example.libImposto.model.ResultadoImpostos;

/**
 * Classe principal de entrada da biblioteca libImposto.
 * 
 * <p>Esta é a classe principal que os clientes da biblioteca devem utilizar
 * para calcular impostos de forma simples e direta.</p>
 * 
 * <p>Exemplo de uso básico:</p>
 * <pre>{@code
 * // Cálculo simples com configuração padrão
 * ResultadoImpostos resultado = LibImposto.calcular(10000f);
 * System.out.println("Total: R$ " + resultado.getTotal());
 * 
 * // Cálculo com configuração personalizada
 * ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
 *     .regimeTributario(RegimeTributario.LUCRO_REAL)
 *     .estadoOrigem("SP")
 *     .estadoDestino("RJ")
 *     .aliquotaICMS(0.18f)
 *     .build();
 * 
 * ResultadoImpostos resultado = LibImposto.calcular(10000f, config);
 * System.out.println(resultado.getResumoCompleto());
 * }</pre>
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class LibImposto {
    
    private static final CalculadoraImpostos CALCULADORA = new CalculadoraImpostos();
    
    /**
     * Calcula todos os impostos usando configuração padrão.
     * 
     * <p>Utiliza Lucro Presumido como regime tributário padrão e alíquotas
     * padrão para o estado de São Paulo.</p>
     * 
     * @param valorBase Valor base para cálculo dos impostos
     * @return Resultado com todos os impostos calculados
     * @throws IllegalArgumentException se valorBase for menor ou igual a zero
     */
    public static ResultadoImpostos calcular(float valorBase) {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
            .regimeTributario(RegimeTributario.LUCRO_PRESUMIDO)
            .estadoOrigem("SP")
            .estadoDestino("SP")
            .build();
        
        return CALCULADORA.calcularTodos(valorBase, config);
    }
    
    /**
     * Calcula todos os impostos usando configuração personalizada.
     * 
     * @param valorBase Valor base para cálculo dos impostos
     * @param config Configuração personalizada dos impostos
     * @return Resultado com todos os impostos calculados
     * @throws IllegalArgumentException se parâmetros forem inválidos
     */
    public static ResultadoImpostos calcular(float valorBase, ConfiguracaoImpostos config) {
        return CALCULADORA.calcularTodos(valorBase, config);
    }
    
    /**
     * Calcula apenas os impostos federais.
     * 
     * @param valorBase Valor base para cálculo
     * @param config Configuração dos impostos
     * @return Resultado com apenas impostos federais
     */
    public static ResultadoImpostos calcularFederais(float valorBase, ConfiguracaoImpostos config) {
        return CALCULADORA.calcularFederais(valorBase, config);
    }
    
    /**
     * Calcula apenas os impostos estaduais.
     * 
     * @param valorBase Valor base para cálculo
     * @param config Configuração dos impostos
     * @return Resultado com apenas impostos estaduais
     */
    public static ResultadoImpostos calcularEstaduais(float valorBase, ConfiguracaoImpostos config) {
        return CALCULADORA.calcularEstaduais(valorBase, config);
    }
    
    /**
     * Calcula apenas os impostos municipais.
     * 
     * @param valorBase Valor base para cálculo
     * @param config Configuração dos impostos
     * @return Resultado com apenas impostos municipais
     */
    public static ResultadoImpostos calcularMunicipais(float valorBase, ConfiguracaoImpostos config) {
        return CALCULADORA.calcularMunicipais(valorBase, config);
    }
    
    /**
     * Cria uma nova configuração de impostos usando o Builder.
     * 
     * <p>Método de conveniência para facilitar a criação de configurações.</p>
     * 
     * @return Builder para configuração de impostos
     */
    public static ConfiguracaoImpostos.Builder configuracao() {
        return new ConfiguracaoImpostos.Builder();
    }
    
    /**
     * Obtém informações sobre a biblioteca.
     * 
     * @return Informações da versão atual
     */
    public static String getInfo() {
        return "libImposto v1.0 - Biblioteca para cálculo de impostos brasileiros";
    }
}
