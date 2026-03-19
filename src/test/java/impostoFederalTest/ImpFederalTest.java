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

    @Test
    public void testImpostoIrpj() {
        float baseCalculo = 10000.0f;
        float aliquota = 0.15f; // 15%
        float expected = 1500.0f;
        float result = impFederal.impostoIrpj(baseCalculo, aliquota);
        assertEquals("O cálculo do IRPJ está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoConfins() {
        float baseCalculo = 5000.0f;
        float aliquota = 0.03f; // 3% (regime cumulativo)
        float expected = 150.0f;
        float result = impFederal.impostoConfins(baseCalculo, aliquota);
        assertEquals("O cálculo do COFINS está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoPisPasep() {
        float baseCalculo = 5000.0f;
        float aliquota = 0.0065f; // 0.65% (regime cumulativo)
        float expected = 32.5f;
        float result = impFederal.impostoPisPasep(baseCalculo, aliquota);
        assertEquals("O cálculo do PIS/PASEP está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoCsll() {
        float baseCalculo = 10000.0f;
        float aliquota = 0.09f; // 9%
        float expected = 900.0f;
        float result = impFederal.impostoCsll(baseCalculo, aliquota);
        assertEquals("O cálculo do CSLL está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoInssSimples() {
        float salario = 2000.0f;
        float aliquota = 0.09f; // 9%
        float expected = 180.0f;
        float result = impFederal.impostoInss(salario, aliquota);
        assertEquals("O cálculo do INSS simples está incorreto", expected, result, 0.001);
    }

    @Test
    public void testImpostoInssProgressivo() {
        // Tabela INSS 2023 (fictícia para teste, com limites e alíquotas)
        float[] limites = {1320.00f, 2571.29f, 3856.94f, 7507.49f};
        float[] aliquotas = {0.075f, 0.09f, 0.12f, 0.14f};

        // Caso 1: Salário na primeira faixa
        // 1000 * 0.075 = 75
        assertEquals(75.0f, impFederal.impostoInss(1000.0f, limites, aliquotas), 0.001f);

        // Caso 2: Salário na segunda faixa
        // 1320 * 0.075 = 99
        // (2000 - 1320) * 0.09 = 680 * 0.09 = 61.2
        // Total = 99 + 61.2 = 160.2
        assertEquals(160.2f, impFederal.impostoInss(2000.0f, limites, aliquotas), 0.001f);

        // Caso 3: Salário de 3000.0f (calculado anteriormente)
        // Faixa 1: 1320.00 * 0.075 = 99.00
        // Faixa 2: (2571.29 - 1320.00) * 0.09 = 1251.29 * 0.09 = 112.6161
        // Faixa 3: (3000.00 - 2571.29) * 0.12 = 428.71 * 0.12 = 51.4452
        // Total = 99.00 + 112.6161 + 51.4452 = 263.0613
        assertEquals(263.0613f, impFederal.impostoInss(3000.0f, limites, aliquotas), 0.001f);

        // Caso 4: Salário acima do teto (7507.49f)
        // O cálculo progressivo deve parar no teto
        // Para 7507.49f, o cálculo é:
        // 1320.00 * 0.075 = 99.00
        // (2571.29 - 1320.00) * 0.09 = 112.6161
        // (3856.94 - 2571.29) * 0.12 = 1285.65 * 0.12 = 154.278
        // (7507.49 - 3856.94) * 0.14 = 3650.55 * 0.14 = 511.077
        // Total = 99.00 + 112.6161 + 154.278 + 511.077 = 876.9711
        assertEquals(876.9711f, impFederal.impostoInss(8000.0f, limites, aliquotas), 0.001f);
    }
}
