package org.example.libImposto.municipal;

import org.example.libImposto.config.ConfiguracaoImpostos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para ImpostoMunicipalFactory.
 */
public class ImpostoMunicipalFactoryTest {
    
    private ImpostoMunicipalFactory factory = new ImpostoMunicipalFactory();
    
    @Test
    public void testCalcularISS() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        float resultado = factory.calcularISS(10000f, config);
        assertEquals(200f, resultado, 0.01f); // 2%
    }
    
    @Test
    public void testCalcularISSComAliquotaPersonalizada() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
            .aliquotaISS(0.05f)
            .build();
        
        float resultado = factory.calcularISS(10000f, config);
        assertEquals(200f, resultado, 0.01f); // 2% (padrão, ignorando personalização)
    }
    
    @Test
    public void testValoresDiferentes() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        // Testar com diferentes valores
        assertEquals(100f, factory.calcularISS(5000f, config), 0.01f);
        assertEquals(20f, factory.calcularISS(1000f, config), 0.01f);
        assertEquals(2f, factory.calcularISS(100f, config), 0.01f);
    }
    
    @Test
    public void testValorZero() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        assertEquals(0f, factory.calcularISS(0f, config), 0.01f);
    }
    
    @Test
    public void testPrecisaoDecimal() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        // Testar valores com casas decimais
        assertEquals(20.01f, factory.calcularISS(1000.25f, config), 0.01f);
        assertEquals(0.20f, factory.calcularISS(10.01f, config), 0.01f);
    }
    
    @Test
    public void testIndependenciaDeEstado() {
        // Testar que o cálculo não depende do estado (simplificação)
        ConfiguracaoImpostos configSP = new ConfiguracaoImpostos.Builder()
            .estadoOrigem("SP")
            .build();
            
        ConfiguracaoImpostos configRJ = new ConfiguracaoImpostos.Builder()
            .estadoOrigem("RJ")
            .build();
            
        ConfiguracaoImpostos configMG = new ConfiguracaoImpostos.Builder()
            .estadoOrigem("MG")
            .build();
        
        float valor = 10000f;
        
        // Todos devem retornar o mesmo valor (simplificação)
        assertEquals(factory.calcularISS(valor, configSP), factory.calcularISS(valor, configRJ), 0.01f);
        assertEquals(factory.calcularISS(valor, configSP), factory.calcularISS(valor, configMG), 0.01f);
    }
}
