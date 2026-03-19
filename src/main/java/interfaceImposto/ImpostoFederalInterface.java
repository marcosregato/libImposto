package interfaceImposto;

public interface ImpostoFederalInterface {

    public float impostoImpotacao(float valorAduaneiro, float aliquotaImportacao);

    public float impostoIpi(float baseCalculo, float aliquotaIPI);
    
    public float impostoIpi(float valorProduto, float frete, float seguro, float outrasDespesas, float aliquotaIPI);

    // Método simples existente
    public float impostoIrpf(float valor);

    // Novo método com alíquota e dedução explícitas
    public float impostoIrpf(float baseCalculo, float aliquota, float deducao);

    // Novo método progressivo com faixas
    public float impostoIrpf(float baseCalculo, float[] limites, float[] aliquotas, float[] deducoes);

    public float impostoIrpj(float valor);

    public float impostoConfins(float valor);

    public float impostoPisPasep(float valor);

    public float impostoCsll(float valor);
    public float impostoInss(float valor);

}
