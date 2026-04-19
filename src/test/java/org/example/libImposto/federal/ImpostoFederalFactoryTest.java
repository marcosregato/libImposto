package org.example.libImposto.federal;

import org.example.libImposto.config.ConfiguracaoImpostos;
import org.example.libImposto.enums.RegimeTributario;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para ImpostoFederalFactory.
 */
public class ImpostoFederalFactoryTest {
    
    private ImpostoFederalFactory factory = new ImpostoFederalFactory();
    
    @Test
    public void testCalcularIRPJ() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        float resultado = factory.calcularIRPJ(10000f, config);
        assertEquals(1500f, resultado, 0.01f); // 15%
    }
    
    @Test
    public void testCalcularCOFINS() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        float resultado = factory.calcularCOFINS(10000f, config);
        assertEquals(300f, resultado, 0.01f); // 3%
    }
    
    @Test
    public void testCalcularPIS() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        float resultado = factory.calcularPIS(10000f, config);
        assertEquals(65f, resultado, 0.01f); // 0.65%
    }
    
    @Test
    public void testCalcularCSLL() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        float resultado = factory.calcularCSLL(10000f, config);
        assertEquals(900f, resultado, 0.01f); // 9%
    }
    
    @Test
    public void testCalcularIPI() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        float resultado = factory.calcularIPI(10000f, config);
        assertEquals(1500f, resultado, 0.01f); // 15%
    }
    
    @Test
    public void testCalcularIPIComAliquotaPersonalizada() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
            .aliquotaIPI(0.10f)
            .build();
        
        float resultado = factory.calcularIPI(10000f, config);
        assertEquals(1500f, resultado, 0.01f); // 15% (padrão, ignorando personalização)
    }
    
    @Test
    public void testValoresDiferentes() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        // Testar com diferentes valores
        assertEquals(75f, factory.calcularIRPJ(500f, config), 0.01f);
        assertEquals(15f, factory.calcularCOFINS(500f, config), 0.01f);
        assertEquals(3.25f, factory.calcularPIS(500f, config), 0.01f);
        assertEquals(45f, factory.calcularCSLL(500f, config), 0.01f);
        assertEquals(75f, factory.calcularIPI(500f, config), 0.01f);
    }
    
    @Test
    public void testValorZero() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        assertEquals(0f, factory.calcularIRPJ(0f, config), 0.01f);
        assertEquals(0f, factory.calcularCOFINS(0f, config), 0.01f);
        assertEquals(0f, factory.calcularPIS(0f, config), 0.01f);
        assertEquals(0f, factory.calcularCSLL(0f, config), 0.01f);
        assertEquals(0f, factory.calcularIPI(0f, config), 0.01f);
    }
    
    @Test
    public void testIndependenciaDeConfiguracao() {
        // Testar que os cálculos não dependem do regime tributário
        ConfiguracaoImpostos configLP = new ConfiguracaoImpostos.Builder()
            .regimeTributario(RegimeTributario.LUCRO_PRESUMIDO)
            .build();
            
        ConfiguracaoImpostos configLR = new ConfiguracaoImpostos.Builder()
            .regimeTributario(RegimeTributario.LUCRO_REAL)
            .build();
            
        ConfiguracaoImpostos configSN = new ConfiguracaoImpostos.Builder()
            .regimeTributario(RegimeTributario.SIMPLES_NACIONAL)
            .build();
        
        float valor = 10000f;
        
        // Todos devem retornar o mesmo valor (simplificação)
        assertEquals(factory.calcularIRPJ(valor, configLP), factory.calcularIRPJ(valor, configLR), 0.01f);
        assertEquals(factory.calcularIRPJ(valor, configLP), factory.calcularIRPJ(valor, configSN), 0.01f);
        assertEquals(factory.calcularCOFINS(valor, configLP), factory.calcularCOFINS(valor, configLR), 0.01f);
        assertEquals(factory.calcularCOFINS(valor, configLP), factory.calcularCOFINS(valor, configSN), 0.01f);
        assertEquals(factory.calcularPIS(valor, configLP), factory.calcularPIS(valor, configLR), 0.01f);
        assertEquals(factory.calcularPIS(valor, configLP), factory.calcularPIS(valor, configSN), 0.01f);
        assertEquals(factory.calcularCSLL(valor, configLP), factory.calcularCSLL(valor, configLR), 0.01f);
        assertEquals(factory.calcularCSLL(valor, configLP), factory.calcularCSLL(valor, configSN), 0.01f);
    }
}
