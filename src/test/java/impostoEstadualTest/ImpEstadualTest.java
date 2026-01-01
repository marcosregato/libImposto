package impostoEstadualTest;

import interfaceImposto.ImpostoEstadualInterface;

public class ImpEstadualTest implements ImpostoEstadualInterface {


    @Override
    public float impostoIcms(float valorOperacao, float aliquotaIcms) {
        return 0;
    }

    @Override
    public float impostoIpva(float valorVeiculo, float porcentagemEstado) {
        return 0;
    }

    @Override
    public float impostoItcmd(float baseCalculo, float aliquota) {
        return 0;
    }
}
