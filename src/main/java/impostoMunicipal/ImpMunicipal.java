package impostoMunicipal;

import interfaceImposto.ImpostoMunicipalInterface;

public class ImpMunicipal implements ImpostoMunicipalInterface{


    @Override
    public Float impostoIptu(Float valor) {
        // Assumindo uma alíquota de 1%
        return valor * 0.01f;
    }

    @Override
    public Float impostoIss(Float valor) {
        // Assumindo uma alíquota de 5%
        return valor * 0.05f;
    }

    @Override
    public Float impostoItbi(Float valor) {
        return 0f;
    }
}
