package org.example.libImposto.enums;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes para a enum TaxRate.
 */
public class TaxRateTest {

    @Test
    public void testTaxRatesValues() {
        assertEquals(0.15f, TaxRate.IRPJ.getAliquota(), 0.001f);
        assertEquals(0.03f, TaxRate.COFINS.getAliquota(), 0.001f);
        assertEquals(0.0065f, TaxRate.PIS.getAliquota(), 0.001f);
        assertEquals(0.09f, TaxRate.CSLL.getAliquota(), 0.001f);
        assertEquals(0.15f, TaxRate.IPI.getAliquota(), 0.001f);
        assertEquals(0.18f, TaxRate.ICMS.getAliquota(), 0.001f);
        assertEquals(0.03f, TaxRate.IPVA.getAliquota(), 0.001f);
        assertEquals(0.04f, TaxRate.ITCMD.getAliquota(), 0.001f);
        assertEquals(0.02f, TaxRate.ISS.getAliquota(), 0.001f);
    }

    @Test
    public void testTaxRatesPercentual() {
        assertEquals(15.0f, TaxRate.IRPJ.getAliquotaPercentual(), 0.001f);
        assertEquals(3.0f, TaxRate.COFINS.getAliquotaPercentual(), 0.001f);
        assertEquals(0.65f, TaxRate.PIS.getAliquotaPercentual(), 0.001f);
        assertEquals(9.0f, TaxRate.CSLL.getAliquotaPercentual(), 0.001f);
        assertEquals(15.0f, TaxRate.IPI.getAliquotaPercentual(), 0.001f);
        assertEquals(18.0f, TaxRate.ICMS.getAliquotaPercentual(), 0.001f);
        assertEquals(3.0f, TaxRate.IPVA.getAliquotaPercentual(), 0.001f);
        assertEquals(4.0f, TaxRate.ITCMD.getAliquotaPercentual(), 0.001f);
        assertEquals(2.0f, TaxRate.ISS.getAliquotaPercentual(), 0.001f);
    }

    @Test
    public void testCalcularImposto() {
        float baseCalculo = 1000f;
        
        assertEquals(150f, TaxRate.IRPJ.calcularImposto(baseCalculo), 0.001f);
        assertEquals(30f, TaxRate.COFINS.calcularImposto(baseCalculo), 0.001f);
        assertEquals(6.5f, TaxRate.PIS.calcularImposto(baseCalculo), 0.001f);
        assertEquals(90f, TaxRate.CSLL.calcularImposto(baseCalculo), 0.001f);
        assertEquals(150f, TaxRate.IPI.calcularImposto(baseCalculo), 0.001f);
        assertEquals(180f, TaxRate.ICMS.calcularImposto(baseCalculo), 0.001f);
        assertEquals(30f, TaxRate.IPVA.calcularImposto(baseCalculo), 0.001f);
        assertEquals(40f, TaxRate.ITCMD.calcularImposto(baseCalculo), 0.001f);
        assertEquals(20f, TaxRate.ISS.calcularImposto(baseCalculo), 0.001f);
    }

    @Test
    public void testDescricao() {
        assertEquals("Imposto de Renda da Pessoa Jurídica", TaxRate.IRPJ.getDescricao());
        assertEquals("Contribuição para o Financiamento da Seguridade Social", TaxRate.COFINS.getDescricao());
        assertEquals("Programa de Integração Social", TaxRate.PIS.getDescricao());
        assertEquals("Contribuição Social sobre o Lucro Líquido", TaxRate.CSLL.getDescricao());
        assertEquals("Imposto sobre Produtos Industrializados", TaxRate.IPI.getDescricao());
        assertEquals("Imposto sobre Circulação de Mercadorias e Serviços", TaxRate.ICMS.getDescricao());
        assertEquals("Imposto sobre a Propriedade de Veículos Automotores", TaxRate.IPVA.getDescricao());
        assertEquals("Imposto sobre Transmissão Causa Mortis e Doação", TaxRate.ITCMD.getDescricao());
        assertEquals("Imposto Sobre Serviços", TaxRate.ISS.getDescricao());
    }

    @Test
    public void testToString() {
        assertEquals("Imposto de Renda da Pessoa Jurídica (15.00%)", TaxRate.IRPJ.toString());
        assertEquals("Contribuição para o Financiamento da Seguridade Social (3.00%)", TaxRate.COFINS.toString());
        assertEquals("Programa de Integração Social (0.65%)", TaxRate.PIS.toString());
        assertEquals("Contribuição Social sobre o Lucro Líquido (9.00%)", TaxRate.CSLL.toString());
        assertEquals("Imposto sobre Produtos Industrializados (15.00%)", TaxRate.IPI.toString());
        assertEquals("Imposto sobre Circulação de Mercadorias e Serviços (18.00%)", TaxRate.ICMS.toString());
        assertEquals("Imposto sobre a Propriedade de Veículos Automotores (3.00%)", TaxRate.IPVA.toString());
        assertEquals("Imposto sobre Transmissão Causa Mortis e Doação (4.00%)", TaxRate.ITCMD.toString());
        assertEquals("Imposto Sobre Serviços (2.00%)", TaxRate.ISS.toString());
    }
}
