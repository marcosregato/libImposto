package impostoMunicipalTest;

import impostoMunicipal.ImpMunicipal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ImpMunicipalTest {

    private ImpMunicipal impMunicipal;

    @Before
    public void setUp() {
        impMunicipal = new ImpMunicipal();
    }

    @Test
    public void testImpostoIptu() {
        // Exemplo: IPTU de 1% sobre o valor venal do imóvel
        Float valorVenal = 250000.0f;
        Float expected = 2500.0f; // 1% de 250000
        Float result = impMunicipal.impostoIptu(valorVenal);
        assertEquals("O cálculo do IPTU está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoIss() {
        // Exemplo: ISS de 5% sobre o valor do serviço
        Float valorServico = 1000.0f;
        Float expected = 50.0f; // 5% de 1000
        Float result = impMunicipal.impostoIss(valorServico);
        assertEquals("O cálculo do ISS está incorreto", expected, result, 0.001);
    }
}
