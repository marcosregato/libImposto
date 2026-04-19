package org.example.libImposto.example;

import org.example.libImposto.LibImposto;
import org.example.libImposto.config.ConfiguracaoImpostos;
import org.example.libImposto.enums.RegimeTributario;
import org.example.libImposto.model.ResultadoImpostos;

/**
 * Exemplo de uso da biblioteca libImposto.
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class ExemploUso {
    
    public static void main(String[] args) {
        System.out.println("=== EXEMPLOS DE USO - libImposto ===\n");
        
        exemplo1CalculoSimples();
        exemplo2CalculoPersonalizado();
        
        System.out.println("\n" + LibImposto.getInfo());
    }
    
    private static void exemplo1CalculoSimples() {
        System.out.println("1. CÁLCULO SIMPLES (CONFIGURAÇÃO PADRÃO)");
        System.out.println("Valor: R$ 10.000,00");
        
        ResultadoImpostos resultado = LibImposto.calcular(10000f);
        System.out.println(resultado);
        System.out.println();
    }
    
    private static void exemplo2CalculoPersonalizado() {
        System.out.println("2. CÁLCULO PERSONALIZADO");
        System.out.println("Valor: R$ 15.000,00");
        
        ConfiguracaoImpostos config = LibImposto.configuracao()
            .regimeTributario(RegimeTributario.LUCRO_REAL)
            .estadoOrigem("SP")
            .estadoDestino("RJ")
            .aliquotaICMS(0.18f)
            .build();
        
        ResultadoImpostos resultado = LibImposto.calcular(15000f, config);
        System.out.println(resultado);
        System.out.println();
    }
}
