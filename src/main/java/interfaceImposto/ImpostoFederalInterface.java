package interfaceImposto;

public interface ImpostoFederalInterface {

    public float impostoImpotacao(float valorAduaneiro, float aliquotaImportacao);

    public float impostoIpi(float baseCalculo, float aliquotaIPI);
    
    public float impostoIpi(float valorProduto, float frete, float seguro, float outrasDespesas, float aliquotaIPI);

    public float impostoIrpf(float valor);
    public float impostoIrpf(float baseCalculo, float aliquota, float deducao);
    public float impostoIrpf(float baseCalculo, float[] limites, float[] aliquotas, float[] deducoes);

    // IRPJ
    public float impostoIrpj(float valor);
    public float impostoIrpj(float baseCalculo, float aliquota);
    
    // COFINS
    public float impostoConfins(float valor);
    public float impostoConfins(float baseCalculo, float aliquota);

    // PIS/PASEP
    public float impostoPisPasep(float valor);
    public float impostoPisPasep(float baseCalculo, float aliquota);

    // CSLL
    public float impostoCsll(float valor);
    public float impostoCsll(float baseCalculo, float aliquota);

    // INSS
    public float impostoInss(float valor);
    public float impostoInss(float salario, float aliquota);
    public float impostoInss(float salario, float[] limites, float[] aliquotas); // Cálculo progressivo por faixas
}
