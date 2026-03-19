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
        // Exemplo: IPTU de 1% sobre o valor venal do imóvel (padrão)
        Float valorVenal = 250000.0f;
        Float expected = 2500.0f; // 1% de 250000
        Float result = impMunicipal.impostoIptu(valorVenal);
        assertEquals("O cálculo do IPTU padrão está incorreto", expected, result, 0.001f);
    }

    @Test
    public void testImpostoIptuComAliquota() {
        Float valorVenal = 250000.0f;
        Float aliquota = 0.015f; // 1.5%
        Float expected = 3750.0f;
        Float result = impMunicipal.impostoIptu(valorVenal, aliquota);
        assertEquals("O cálculo do IPTU com alíquota está incorreto", expected, result, 0.001f);
    }

    @Test
    public void testImpostoIptuProgressivo() {
        Float valorVenal = 600000.0f;
        // Limites superiores das faixas
        float[] limites = {100000f, 500000f};
        // Alíquotas: até 100k (0.5%), 100k-500k (1.0%), acima de 500k (1.5%)
        float[] aliquotas = {0.005f, 0.01f, 0.015f};

        // Cálculo esperado:
        // 1. 100.000 * 0.005 = 500.0
        // 2. 400.000 * 0.01 = 4000.0
        // 3. 100.000 * 0.015 = 1500.0
        // Total = 6000.0
        Float expected = 6000.0f;

        Float result = impMunicipal.impostoIptuProgressivo(valorVenal, limites, aliquotas);
        assertEquals("O cálculo do IPTU progressivo está incorreto", expected, result, 0.001f);
    }

    @Test
    public void testImpostoIss() {
        // Exemplo: ISS de 5% sobre o valor do serviço (padrão)
        Float valorServico = 1000.0f;
        Float expected = 50.0f; // 5% de 1000
        Float result = impMunicipal.impostoIss(valorServico);
        assertEquals("O cálculo do ISS padrão está incorreto", expected, result, 0.001f);
    }

    @Test
    public void testImpostoIssComAliquota() {
        Float valorServico = 1000.0f;
        Float aliquota = 0.03f; // 3%
        Float expected = 30.0f;
        Float result = impMunicipal.impostoIss(valorServico, aliquota);
        assertEquals("O cálculo do ISS com alíquota está incorreto", expected, result, 0.001f);
    }

    @Test
    public void testImpostoItbi() {
        // Exemplo: ITBI de 2% sobre o valor (novo padrão)
        Float valorTransacao = 500000.0f;
        Float expected = 10000.0f; // 2% de 500000
        Float result = impMunicipal.impostoItbi(valorTransacao);
        assertEquals("O cálculo do ITBI padrão está incorreto", expected, result, 0.001f);
    }

    @Test
    public void testImpostoItbiComAliquota() {
        Float valorTransacao = 500000.0f;
        Float aliquota = 0.025f; // 2.5%
        Float expected = 12500.0f;
        Float result = impMunicipal.impostoItbi(valorTransacao, aliquota);
        assertEquals("O cálculo do ITBI com alíquota está incorreto", expected, result, 0.001f);
    }
}
