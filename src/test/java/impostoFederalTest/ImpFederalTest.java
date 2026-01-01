package impostoFederalTest;

import interfaceImposto.ImpostoFederalInterface;

public class ImpFederalTest implements ImpostoFederalInterface{


    @Override
    public float impostoImpotacao(float valorAduaneiro, float aliquotaImportacao) {
        return 0f;
    }

    @Override
    public float impostoIpi(float baseCalculo,float aliquotaIPI) {
        return 0f;
    }

    @Override
    public float impostoIrpf(float valor) {
        return 0f;
    }

    @Override
    public float impostoIrpj(float valor) {
        return 0f;
    }

    @Override
    public float impostoConfins(float valor) {
        return 0f;
    }

    @Override
    public float impostoPisPasep(float valor) {
        return 0f;
    }

    @Override
    public float impostoCsll(float valor) {
        return 0f;
    }

    @Override
    public float impostoInss(float valor) {
        return 0f;
    }
}
