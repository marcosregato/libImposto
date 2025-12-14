package impostoMunicipal;

import interfaceImposto.ImpostoFederalInterface;
import interfaceImposto.ImpostoMunicipalInterface;

public class ImpMunicipal implements ImpostoMunicipalInterface{


    @Override
    public Float impostoIptu(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoIss(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoItbi(Float valor) {
        return 0f;
    }
}
