package org.example.libImposto.estadual;

import org.example.libImposto.config.ConfiguracaoImpostos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para ImpostoEstadualFactory.
 */
public class ImpostoEstadualFactoryTest {
    
    private ImpostoEstadualFactory factory = new ImpostoEstadualFactory();
    
    @Test
    public void testCalcularICMS() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        float resultado = factory.calcularICMS(10000f, config);
        assertEquals(1800f, resultado, 0.01f); // 18%
    }
    
    @Test
    public void testCalcularIPVA() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        float resultado = factory.calcularIPVA(50000f, config);
        assertEquals(1500f, resultado, 0.01f); // 3%
    }
    
    @Test
    public void testCalcularITCMD() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        float resultado = factory.calcularITCMD(100000f, config);
        assertEquals(4000f, resultado, 0.01f); // 4%
    }
    
    @Test
    public void testValoresDiferentes() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        // Testar com diferentes valores
        assertEquals(900f, factory.calcularICMS(5000f, config), 0.01f);
        assertEquals(150f, factory.calcularIPVA(5000f, config), 0.01f);
        assertEquals(200f, factory.calcularITCMD(5000f, config), 0.01f);
    }
    
    @Test
    public void testValorZero() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        assertEquals(0f, factory.calcularICMS(0f, config), 0.01f);
        assertEquals(0f, factory.calcularIPVA(0f, config), 0.01f);
        assertEquals(0f, factory.calcularITCMD(0f, config), 0.01f);
    }
    
    @Test
    public void testIndependenciaDeConfiguracao() {
        // Testar que os cálculos não dependem de configurações específicas
        ConfiguracaoImpostos configSP = new ConfiguracaoImpostos.Builder()
            .estadoOrigem("SP")
            .estadoDestino("RJ")
            .build();
            
        ConfiguracaoImpostos configRJ = new ConfiguracaoImpostos.Builder()
            .estadoOrigem("RJ")
            .estadoDestino("SP")
            .build();
        
        float valor = 10000f;
        
        // Todos devem retornar o mesmo valor (simplificação)
        assertEquals(factory.calcularICMS(valor, configSP), factory.calcularICMS(valor, configRJ), 0.01f);
        assertEquals(factory.calcularIPVA(valor, configSP), factory.calcularIPVA(valor, configRJ), 0.01f);
        assertEquals(factory.calcularITCMD(valor, configSP), factory.calcularITCMD(valor, configRJ), 0.01f);
    }
    
    @Test
    public void testPrecisaoDecimal() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        // Testar valores com casas decimais
        assertEquals(180.05f, factory.calcularICMS(1000.25f, config), 0.01f);
        assertEquals(30.01f, factory.calcularIPVA(1000.25f, config), 0.01f);
        assertEquals(40.01f, factory.calcularITCMD(1000.25f, config), 0.01f);
    }
}
