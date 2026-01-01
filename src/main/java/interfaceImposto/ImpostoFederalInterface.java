package interfaceImposto;

public interface ImpostoFederalInterface {

    public float impostoImpotacao(float valorAduaneiro, float aliquotaImportacao);

    public float impostoIpi(float baseCalculo,float aliquotaIPI);

    public float impostoIrpf(float valor);

    public float impostoIrpj(float valor);

    public float impostoConfins(float valor);

    public float impostoPisPasep(float valor);

    public float impostoCsll(float valor);
    public float impostoInss(float valor);

}
