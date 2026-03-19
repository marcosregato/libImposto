package impostoEstadualTest;

import impostoEstadual.ImpEstadual;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ImpEstadualTest {

    private ImpEstadual impEstadual;

    @Before
    public void setUp() {
        impEstadual = new ImpEstadual();
    }

    @Test
    public void testImpostoIcms() {
        float valorOperacao = 100.0f;
        float aliquota = 0.18f; // 18%
        // Base de cálculo "por dentro": 100 / (1 - 0.18) = 121.95
        // Valor do imposto: 121.95 * 0.18 = 21.95
        float expected = 21.951219f;
        float result = impEstadual.impostoIcms(valorOperacao, aliquota);
        assertEquals("O cálculo do ICMS (por dentro) está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoIcmsSimples() {
        float valorOperacao = 100.0f;
        float aliquota = 0.18f; // 18%
        // Cálculo simples: 100 * 0.18 = 18.0
        float expected = 18.0f;
        float result = impEstadual.impostoIcmsSimples(valorOperacao, aliquota);
        assertEquals("O cálculo do ICMS (simples) está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoDifal() {
        float baseCalculo = 1000.0f;
        float aliquotaDestino = 0.18f; // 18% (ex: SP)
        float aliquotaOrigem = 0.12f;  // 12% (ex: Interestadual)
        
        // Diferença: 18% - 12% = 6%
        // DIFAL: 1000 * 0.06 = 60.0
        float expected = 60.0f;
        float result = impEstadual.impostoDifal(baseCalculo, aliquotaDestino, aliquotaOrigem);
        assertEquals("O cálculo do DIFAL está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoDifalSemDiferencaPositiva() {
        float baseCalculo = 1000.0f;
        float aliquotaDestino = 0.07f; // 7%
        float aliquotaOrigem = 0.12f;  // 12%
        
        // Diferença negativa, então DIFAL deve ser 0
        float expected = 0.0f;
        float result = impEstadual.impostoDifal(baseCalculo, aliquotaDestino, aliquotaOrigem);
        assertEquals("O DIFAL deveria ser zero quando a alíquota de destino é menor", expected, result, 0.001);
    }

    @Test
    public void testImpostoIpvaComPorcentagem() {
        float valorVeiculo = 30000.0f;
        float porcentagemEstado = 4.0f; // 4%
        float expected = 1200.0f;
        float result = impEstadual.impostoIpva(valorVeiculo, porcentagemEstado);
        assertEquals("O cálculo do IPVA com porcentagem está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoIpvaComDecimal() {
        float valorVeiculo = 30000.0f;
        double aliquotaDecimal = 0.04; // 4%
        float expected = 1200.0f;
        float result = impEstadual.impostoIpva(valorVeiculo, aliquotaDecimal);
        assertEquals("O cálculo do IPVA com decimal está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoItcmd() {
        float baseCalculo = 500000.0f; // Ex: Valor de um imóvel
        float aliquota = 0.04f; // 4%
        float expected = 20000.0f;
        float result = impEstadual.impostoItcmd(baseCalculo, aliquota);
        assertEquals("O cálculo do ITCMD está incorreto", expected, result, 0.001);
    }
}
