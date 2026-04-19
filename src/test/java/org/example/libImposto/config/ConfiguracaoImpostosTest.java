package org.example.libImposto.config;

import org.example.libImposto.enums.RegimeTributario;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para ConfiguracaoImpostos.
 */
public class ConfiguracaoImpostosTest {
    
    @Test
    public void testBuilderPadrao() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        
        assertNotNull(config);
        assertEquals(RegimeTributario.LUCRO_PRESUMIDO, config.getRegimeTributario());
        assertEquals("SP", config.getEstadoOrigem());
        assertEquals("SP", config.getEstadoDestino());
        assertNull(config.getAliquotaICMS());
        assertNull(config.getAliquotaIPI());
        assertNull(config.getAliquotaISS());
        assertEquals(Float.valueOf(0.32f), config.getPercentualPresuncao());
        assertFalse(config.isSimplesNacional());
        assertEquals(0f, config.getReceitaBruta12Meses(), 0.01f);
    }
    
    @Test
    public void testBuilderCompleto() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
            .regimeTributario(RegimeTributario.LUCRO_REAL)
            .estadoOrigem("RJ")
            .estadoDestino("MG")
            .aliquotaICMS(0.18f)
            .aliquotaIPI(0.15f)
            .aliquotaISS(0.05f)
            .percentualPresuncao(0.20f)
            .simplesNacional(true)
            .receitaBruta12Meses(500000f)
            .build();
        
        assertNotNull(config);
        assertEquals(RegimeTributario.LUCRO_REAL, config.getRegimeTributario());
        assertEquals("RJ", config.getEstadoOrigem());
        assertEquals("MG", config.getEstadoDestino());
        assertEquals(Float.valueOf(0.18f), config.getAliquotaICMS());
        assertEquals(Float.valueOf(0.15f), config.getAliquotaIPI());
        assertEquals(Float.valueOf(0.05f), config.getAliquotaISS());
        assertEquals(Float.valueOf(0.20f), config.getPercentualPresuncao());
        assertTrue(config.isSimplesNacional());
        assertEquals(500000f, config.getReceitaBruta12Meses(), 0.01f);
    }
    
    @Test
    public void testBuilderParcial() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
            .regimeTributario(RegimeTributario.SIMPLES_NACIONAL)
            .aliquotaICMS(0.12f)
            .build();
        
        assertNotNull(config);
        assertEquals(RegimeTributario.SIMPLES_NACIONAL, config.getRegimeTributario());
        assertEquals("SP", config.getEstadoOrigem()); // Valor padrão
        assertEquals("SP", config.getEstadoDestino()); // Valor padrão
        assertEquals(Float.valueOf(0.12f), config.getAliquotaICMS());
        assertNull(config.getAliquotaIPI()); // Não definido
        assertNull(config.getAliquotaISS()); // Não definido
    }
    
    @Test
    public void testBuilderReutilizacao() {
        ConfiguracaoImpostos.Builder builder = new ConfiguracaoImpostos.Builder()
            .regimeTributario(RegimeTributario.LUCRO_REAL)
            .estadoOrigem("BA");
        
        ConfiguracaoImpostos config1 = builder.build();
        ConfiguracaoImpostos config2 = builder
            .estadoDestino("PE")
            .build();
        
        assertNotSame(config1, config2); // São instâncias diferentes
        assertEquals(RegimeTributario.LUCRO_REAL, config1.getRegimeTributario());
        assertEquals(RegimeTributario.LUCRO_REAL, config2.getRegimeTributario());
        assertEquals("BA", config1.getEstadoOrigem());
        assertEquals("BA", config2.getEstadoOrigem());
        assertEquals("SP", config1.getEstadoDestino()); // Padrão
        assertEquals("PE", config2.getEstadoDestino()); // Personalizado
    }
    
    @Test
    public void testToString() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
            .regimeTributario(RegimeTributario.LUCRO_PRESUMIDO)
            .estadoOrigem("SP")
            .estadoDestino("RJ")
            .aliquotaICMS(0.18f)
            .build();
        
        String resultado = config.toString();
        
        assertNotNull(resultado);
        // toString() padrão do Object, apenas verifica que não é nulo
    }
}
