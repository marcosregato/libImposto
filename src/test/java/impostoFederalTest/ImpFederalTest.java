package impostoFederalTest;

import impostoFederal.ImpFederal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ImpFederalTest {

    private ImpFederal impFederal;

    @Before
    public void setUp() {
        impFederal = new ImpFederal();
    }

    @Test
    public void testImpostoImportacao() {
        float valorAduaneiro = 1000.0f;
        float aliquota = 0.6f; // 60%
        float expected = 600.0f;
        float result = impFederal.impostoImpotacao(valorAduaneiro, aliquota);
        assertEquals("O cálculo do imposto de importação está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoIpi() {
        float baseCalculo = 500.0f;
        float aliquota = 0.1f; // 10%
        float expected = 50.0f;
        float result = impFederal.impostoIpi(baseCalculo, aliquota);
        assertEquals("O cálculo do IPI está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoIpiComDespesas() {
        float valorProduto = 1000.0f;
        float frete = 50.0f;
        float seguro = 10.0f;
        float outrasDespesas = 40.0f;
        float aliquota = 0.1f; // 10%
        
        // Base de cálculo: 1000 + 50 + 10 + 40 = 1100
        // Imposto: 1100 * 0.10 = 110
        float expected = 110.0f;
        float result = impFederal.impostoIpi(valorProduto, frete, seguro, outrasDespesas, aliquota);
        assertEquals("O cálculo do IPI com despesas está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoIrpfSimples() {
        float baseCalculo = 3000.0f;
        float aliquota = 0.15f; // 15%
        float deducao = 370.40f;
        
        // 3000 * 0.15 = 450
        // 450 - 370.40 = 79.60
        float expected = 79.60f;
        float result = impFederal.impostoIrpf(baseCalculo, aliquota, deducao);
        assertEquals("O cálculo do IRPF simples está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoIrpfProgressivo() {
        // Tabela fictícia para teste
        float[] limites = {2000f, 3000f};
        float[] aliquotas = {0f, 0.1f, 0.2f};
        float[] deducoes = {0f, 200f, 500f};

        // Caso 1: Isento
        assertEquals(0f, impFederal.impostoIrpf(1500f, limites, aliquotas, deducoes), 0.001);

        // Caso 2: Faixa 2
        // 2500 * 0.1 = 250 - 200 = 50
        assertEquals(50f, impFederal.impostoIrpf(2500f, limites, aliquotas, deducoes), 0.001);

        // Caso 3: Faixa 3
        // 4000 * 0.2 = 800 - 500 = 300
        assertEquals(300f, impFederal.impostoIrpf(4000f, limites, aliquotas, deducoes), 0.001);
    }
}
