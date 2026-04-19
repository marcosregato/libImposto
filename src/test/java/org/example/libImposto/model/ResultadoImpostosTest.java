package org.example.libImposto.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para ResultadoImpostos.
 */
public class ResultadoImpostosTest {
    
    @Test
    public void testConstrutorPadrao() {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        assertNotNull(resultado);
        assertEquals(0f, resultado.getIrpj(), 0.01f);
        assertEquals(0f, resultado.getCofins(), 0.01f);
        assertEquals(0f, resultado.getPis(), 0.01f);
        assertEquals(0f, resultado.getCsll(), 0.01f);
        assertEquals(0f, resultado.getIpi(), 0.01f);
        assertEquals(0f, resultado.getIcms(), 0.01f);
        assertEquals(0f, resultado.getIpva(), 0.01f);
        assertEquals(0f, resultado.getItcmd(), 0.01f);
        assertEquals(0f, resultado.getIss(), 0.01f);
        assertEquals(0f, resultado.getTotal(), 0.01f);
    }
    
    @Test
    public void testSettersAndGetters() {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.setIrpj(1000f);
        resultado.setCofins(200f);
        resultado.setPis(65f);
        resultado.setCsll(900f);
        resultado.setIpi(1500f);
        resultado.setIcms(1800f);
        resultado.setIpva(300f);
        resultado.setItcmd(400f);
        resultado.setIss(200f);
        
        assertEquals(1000f, resultado.getIrpj(), 0.01f);
        assertEquals(200f, resultado.getCofins(), 0.01f);
        assertEquals(65f, resultado.getPis(), 0.01f);
        assertEquals(900f, resultado.getCsll(), 0.01f);
        assertEquals(1500f, resultado.getIpi(), 0.01f);
        assertEquals(1800f, resultado.getIcms(), 0.01f);
        assertEquals(300f, resultado.getIpva(), 0.01f);
        assertEquals(400f, resultado.getItcmd(), 0.01f);
        assertEquals(200f, resultado.getIss(), 0.01f);
    }
    
    @Test
    public void testGetTotal() {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.setIrpj(1500f);
        resultado.setCofins(300f);
        resultado.setPis(65f);
        resultado.setCsll(900f);
        resultado.setIpi(1500f);
        resultado.setIcms(1800f);
        resultado.setIpva(300f);
        resultado.setItcmd(400f);
        resultado.setIss(200f);
        
        assertEquals(6965f, resultado.getTotal(), 0.01f);
    }
    
    @Test
    public void testGetTotalComValoresZero() {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.setIrpj(0f);
        resultado.setCofins(0f);
        resultado.setPis(0f);
        resultado.setCsll(0f);
        resultado.setIpi(0f);
        resultado.setIcms(0f);
        resultado.setIpva(0f);
        resultado.setItcmd(0f);
        resultado.setIss(0f);
        
        assertEquals(0f, resultado.getTotal(), 0.01f);
    }
    
    @Test
    public void testGetTotalComValoresNegativos() {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.setIrpj(-100f);
        resultado.setCofins(200f);
        resultado.setPis(0f);
        resultado.setCsll(50f);
        resultado.setIpi(0f);
        resultado.setIcms(100f);
        resultado.setIpva(0f);
        resultado.setItcmd(0f);
        resultado.setIss(50f);
        
        assertEquals(300f, resultado.getTotal(), 0.01f);
    }
    
    @Test
    public void testToString() {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.setIrpj(1500f);
        resultado.setCofins(300f);
        resultado.setPis(65f);
        resultado.setCsll(900f);
        resultado.setIpi(1500f);
        resultado.setIcms(1800f);
        resultado.setIpva(300f);
        resultado.setItcmd(400f);
        resultado.setIss(200f);
        
        String resultadoStr = resultado.toString();
        
        assertNotNull(resultadoStr);
        assertTrue(resultadoStr.contains("1500,00"));
        assertTrue(resultadoStr.contains("300,00"));
        assertTrue(resultadoStr.contains("65,00"));
        assertTrue(resultadoStr.contains("900,00"));
        assertTrue(resultadoStr.contains("6965,00"));
        assertTrue(resultadoStr.contains("IRPJ"));
        assertTrue(resultadoStr.contains("COFINS"));
        assertTrue(resultadoStr.contains("TOTAL"));
    }
    
    @Test
    public void testToStringComValoresZero() {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        String resultadoStr = resultado.toString();
        
        assertNotNull(resultadoStr);
        assertTrue(resultadoStr.contains("0,00"));
        assertTrue(resultadoStr.contains("IRPJ"));
        assertTrue(resultadoStr.contains("TOTAL"));
    }
    
    @Test
    public void testPrecisaoDecimal() {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.setIrpj(1000.123f);
        resultado.setCofins(200.456f);
        resultado.setPis(65.789f);
        
        String resultadoStr = resultado.toString();
        
        assertNotNull(resultadoStr);
        assertTrue(resultadoStr.contains("1000,12")); // Arredondado para 2 casas decimais
        assertTrue(resultadoStr.contains("200,46"));
        assertTrue(resultadoStr.contains("65,79"));
    }
}
