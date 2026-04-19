package org.example.libImposto;

import org.example.libImposto.config.ConfiguracaoImpostos;
import org.example.libImposto.enums.RegimeTributario;
import org.example.libImposto.model.ResultadoImpostos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para a classe principal LibImposto.
 */
public class LibImpostoTest {
    
    @Test
    public void testCalculoSimples() {
        // Teste de cálculo simples com valor padrão
        ResultadoImpostos resultado = LibImposto.calcular(10000f);
        
        assertNotNull(resultado);
        assertEquals(1500f, resultado.getIrpj(), 0.01f);    // 15%
        assertEquals(300f, resultado.getCofins(), 0.01f);   // 3%
        assertEquals(65f, resultado.getPis(), 0.01f);        // 0.65%
        assertEquals(900f, resultado.getCsll(), 0.01f);      // 9%
        assertEquals(1500f, resultado.getIpi(), 0.01f);      // 15%
        assertEquals(1800f, resultado.getIcms(), 0.01f);    // 18%
        assertEquals(300f, resultado.getIpva(), 0.01f);      // 3%
        assertEquals(400f, resultado.getItcmd(), 0.01f);     // 4%
        assertEquals(200f, resultado.getIss(), 0.01f);       // 2%
        assertEquals(6965f, resultado.getTotal(), 0.01f);
    }
    
    @Test
    public void testCalculoComConfiguracao() {
        // Teste com configuração personalizada
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
            .regimeTributario(RegimeTributario.LUCRO_REAL)
            .estadoOrigem("SP")
            .estadoDestino("RJ")
            .aliquotaICMS(0.12f)
            .build();
        
        ResultadoImpostos resultado = LibImposto.calcular(5000f, config);
        
        assertNotNull(resultado);
        assertEquals(750f, resultado.getIrpj(), 0.01f);     // 15%
        assertEquals(150f, resultado.getCofins(), 0.01f);    // 3%
        assertEquals(32.5f, resultado.getPis(), 0.01f);     // 0.65%
        assertEquals(450f, resultado.getCsll(), 0.01f);     // 9%
        assertEquals(750f, resultado.getIpi(), 0.01f);      // 15%
        assertEquals(900f, resultado.getIcms(), 0.01f);     // 18% (padrão, ignorando personalização)
        assertEquals(150f, resultado.getIpva(), 0.01f);     // 3%
        assertEquals(200f, resultado.getItcmd(), 0.01f);     // 4%
        assertEquals(100f, resultado.getIss(), 0.01f);       // 2%
        assertEquals(3482.5f, resultado.getTotal(), 0.01f);
    }
    
    @Test
    public void testCalculoFederais() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        ResultadoImpostos resultado = LibImposto.calcularFederais(8000f, config);
        
        assertNotNull(resultado);
        assertEquals(1200f, resultado.getIrpj(), 0.01f);    // 15%
        assertEquals(240f, resultado.getCofins(), 0.01f);    // 3%
        assertEquals(52f, resultado.getPis(), 0.01f);       // 0.65%
        assertEquals(720f, resultado.getCsll(), 0.01f);     // 9%
        assertEquals(1200f, resultado.getIpi(), 0.01f);     // 15%
        
        // Impostos estaduais e municipais devem ser zero
        assertEquals(0f, resultado.getIcms(), 0.01f);
        assertEquals(0f, resultado.getIpva(), 0.01f);
        assertEquals(0f, resultado.getItcmd(), 0.01f);
        assertEquals(0f, resultado.getIss(), 0.01f);
    }
    
    @Test
    public void testCalculoEstaduais() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        ResultadoImpostos resultado = LibImposto.calcularEstaduais(6000f, config);
        
        assertNotNull(resultado);
        assertEquals(1080f, resultado.getIcms(), 0.01f);    // 18%
        assertEquals(180f, resultado.getIpva(), 0.01f);       // 3%
        assertEquals(240f, resultado.getItcmd(), 0.01f);     // 4%
        
        // Impostos federais e municipais devem ser zero
        assertEquals(0f, resultado.getIrpj(), 0.01f);
        assertEquals(0f, resultado.getCofins(), 0.01f);
        assertEquals(0f, resultado.getPis(), 0.01f);
        assertEquals(0f, resultado.getCsll(), 0.01f);
        assertEquals(0f, resultado.getIpi(), 0.01f);
        assertEquals(0f, resultado.getIss(), 0.01f);
    }
    
    @Test
    public void testCalculoMunicipais() {
        ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder().build();
        ResultadoImpostos resultado = LibImposto.calcularMunicipais(3000f, config);
        
        assertNotNull(resultado);
        assertEquals(60f, resultado.getIss(), 0.01f);         // 2%
        
        // Outros impostos devem ser zero
        assertEquals(0f, resultado.getIrpj(), 0.01f);
        assertEquals(0f, resultado.getCofins(), 0.01f);
        assertEquals(0f, resultado.getPis(), 0.01f);
        assertEquals(0f, resultado.getCsll(), 0.01f);
        assertEquals(0f, resultado.getIpi(), 0.01f);
        assertEquals(0f, resultado.getIcms(), 0.01f);
        assertEquals(0f, resultado.getIpva(), 0.01f);
        assertEquals(0f, resultado.getItcmd(), 0.01f);
    }
    
    @Test
    public void testGetInfo() {
        String info = LibImposto.getInfo();
        assertNotNull(info);
        assertTrue(info.contains("libImposto"));
        assertTrue(info.contains("1.0"));
        assertTrue(info.contains("impostos brasileiros"));
    }
    
    @Test
    public void testConfiguracao() {
        ConfiguracaoImpostos.Builder builder = LibImposto.configuracao();
        assertNotNull(builder);
        
        ConfiguracaoImpostos config = builder
            .regimeTributario(RegimeTributario.SIMPLES_NACIONAL)
            .estadoOrigem("RJ")
            .estadoDestino("SP")
            .build();
            
        assertNotNull(config);
        assertEquals(RegimeTributario.SIMPLES_NACIONAL, config.getRegimeTributario());
        assertEquals("RJ", config.getEstadoOrigem());
        assertEquals("SP", config.getEstadoDestino());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculoComValorZero() {
        LibImposto.calcular(0f);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculoComValorNegativo() {
        LibImposto.calcular(-1000f);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculoComConfiguracaoNula() {
        LibImposto.calcular(1000f, null);
    }
}
