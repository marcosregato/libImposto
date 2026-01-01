package impostoEstadual;

import interfaceImposto.ImpostoEstadualInterface;

public class ImpEstadual implements ImpostoEstadualInterface {

    /**
     * No Brasil, o ICMS é um imposto que incide "por dentro", ou seja,
     * o seu valor já está embutido na própria base de cálculo.
     * A fórmula correta para encontrar a base de cálculo (BC) é:
     * @param valorOperacao
     * @param aliquotaIcms
     * @return
     */
    public float getBaseCalculo(float valorOperacao, float aliquotaIcms) {
        return valorOperacao / (1 - aliquotaIcms);
    }

    /**
     * Após encontrar a BC, você pode calcular o valor do ICMS devido:
     * @param valorOperacao
     * @param aliquotaIcms
     * @return
     */
    @Override
    public float impostoIcms(float valorOperacao, float aliquotaIcms) {
        return getBaseCalculo(valorOperacao,aliquotaIcms) * aliquotaIcms;
    }

    @Override
    public float impostoIpva(float valorVeiculo, float porcentagemEstado) {

        return valorVeiculo * (porcentagemEstado / 100);
    }

    @Override
    public float impostoItcmd(float baseCalculo, float aliquota) {

        return baseCalculo * aliquota;
    }








}
