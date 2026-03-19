package interfaceImposto;

public interface ImpostoEstadualInterface {

    public float impostoIcms(float valorOperacao, float aliquotaIcms);
    public float impostoIcmsSimples(float valorOperacao, float aliquotaIcms);
    public float impostoDifal(float baseCalculo, float aliquotaInternaDestino, float aliquotaInterestadual);
    public float impostoIpva(float valorVeiculo, float porcentagemEstado);
    public float impostoIpva(float valorVeiculo, double aliquotaDecimal);
    public float impostoItcmd(float baseCalculo, float aliquota);
    public float impostoItcmdProgressivo(float baseCalculo, float[] limites, float[] aliquotas);
}
