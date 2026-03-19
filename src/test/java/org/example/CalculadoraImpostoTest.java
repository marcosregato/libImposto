package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculadoraImpostoTest {

    @Mock
    private ServicoTaxa servicoTaxa;

    @InjectMocks
    private CalculadoraImposto calculadoraImposto;

    @Test
    public void testCalcularImposto() {
        // Configurar o comportamento do mock
        when(servicoTaxa.obterTaxa("ISS")).thenReturn(0.05);

        // Executar o método a ser testado
        double resultado = calculadoraImposto.calcular(100.0, "ISS");

        // Verificar o resultado
        assertEquals(105.0, resultado, 0.001);
    }
}
