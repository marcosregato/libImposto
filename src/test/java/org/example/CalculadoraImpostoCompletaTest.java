package org.example;

import interfaceImposto.ImpostoEstadualInterface;
import interfaceImposto.ImpostoFederalInterface;
import interfaceImposto.ImpostoMunicipalInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner; // Import correto para Mockito 1.x

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculadoraImpostoCompletaTest {

    @Mock
    private ImpostoFederalInterface impostoFederal;

    @Mock
    private ImpostoEstadualInterface impostoEstadual;

    @Mock
    private ImpostoMunicipalInterface impostoMunicipal;

    @InjectMocks
    private CalculadoraImpostoCompleta calculadora;

    @Test
    public void testCalcularVendaInterestadual() {
        float valorProduto = 1000.0f;
        float frete = 50.0f;
        float aliquotaIpi = 0.10f; // 10%
        float aliquotaIcmsDestino = 0.18f; // 18%
        float aliquotaIcmsInterestadual = 0.12f; // 12%

        // Mock do cálculo do IPI
        // A calculadora chamará impostoIpi(valorProduto, frete, 0, 0, aliquotaIpi)
        // Base do IPI: 1000 + 50 = 1050
        // Resultado esperado do mock: 105.0
        when(impostoFederal.impostoIpi(valorProduto, frete, 0f, 0f, aliquotaIpi)).thenReturn(105.0f);

        // Mock do cálculo do DIFAL
        // A calculadora chamará impostoDifal(baseIcms, destino, interestadual)
        // Base ICMS na calculadora: valorProduto + frete + IPI = 1000 + 50 + 105 = 1155
        // Resultado esperado do mock: 69.3
        when(impostoEstadual.impostoDifal(1155.0f, aliquotaIcmsDestino, aliquotaIcmsInterestadual)).thenReturn(69.3f);

        // Executa o método a ser testado
        float result = calculadora.calcularVendaInterestadual(valorProduto, frete, aliquotaIpi, aliquotaIcmsDestino, aliquotaIcmsInterestadual);

        // Verifica o resultado
        // Total esperado: 105.0 + 69.3 = 174.3
        assertEquals(174.3f, result, 0.001f);
    }

    @Test
    public void testCalcularServico() {
        float valorServico = 500.0f;
        float aliquotaIss = 0.05f; // 5%
        float aliquotaPis = 0.0065f; // 0.65%
        float aliquotaCofins = 0.03f; // 3%

        when(impostoMunicipal.impostoIss(valorServico, aliquotaIss)).thenReturn(25.0f);
        when(impostoFederal.impostoPisPasep(valorServico, aliquotaPis)).thenReturn(3.25f);
        when(impostoFederal.impostoConfins(valorServico, aliquotaCofins)).thenReturn(15.0f);

        float result = calculadora.calcularServico(valorServico, aliquotaIss, aliquotaPis, aliquotaCofins);

        assertEquals(43.25f, result, 0.001f);
    }
}
