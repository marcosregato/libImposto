package impostoEstadual;

import interfaceImposto.ImpostoEstadualInterface;

public class ImpEstadual implements ImpostoEstadualInterface {


    @Override
    public Float impostoIcms(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoIpva(Float valor) {
        return 0f;
    }

    @Override
    public Float impostoItcmd(Float valor) {
        return 0f;
    }
}
