package impostoFederal;

import interfaceImposto.ImpostoEstadualInterface;
import interfaceImposto.ImpostoFederalInterface;

public class ImpFederal implements ImpostoFederalInterface{


    @Override
    public Float impostoII(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoIpi(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoIrpf(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoIrpj(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoConfins(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoPisPasep(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoCsll(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoInss(Float valor) {
        return 0f;
    }
}
