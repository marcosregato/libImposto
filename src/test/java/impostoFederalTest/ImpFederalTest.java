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
}
