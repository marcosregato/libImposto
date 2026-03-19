package impostoMunicipal;

import interfaceImposto.ImpostoMunicipalInterface;

public class ImpMunicipal implements ImpostoMunicipalInterface{


    @Override
    public Float impostoIptu(Float valor) {
        // Assumindo uma alíquota fixa padrão de 1%
        return valor * 0.01f;
    }

    @Override
    public Float impostoIptu(Float valor, Float aliquota) {
        return valor * aliquota;
    }

    @Override
    public Float impostoIptuProgressivo(Float valor, float[] limites, float[] aliquotas) {
        if (aliquotas.length != limites.length + 1) {
            throw new IllegalArgumentException("O array de alíquotas deve ter um elemento a mais que o array de limites.");
        }

        float impostoTotal = 0.0f;
        float baseRestante = valor;
        float limiteAnterior = 0.0f;

        for (int i = 0; i < limites.length; i++) {
            float limiteAtual = limites[i];
            float faixaTamanho = limiteAtual - limiteAnterior;

            if (baseRestante <= 0) break;

            if (baseRestante > faixaTamanho) {
                // Preenche toda a faixa atual
                impostoTotal += faixaTamanho * aliquotas[i];
                baseRestante -= faixaTamanho;
            } else {
                // O restante cabe nesta faixa
                impostoTotal += baseRestante * aliquotas[i];
                baseRestante = 0.0f;
            }
            limiteAnterior = limiteAtual;
        }

        // Se ainda sobrou valor após o último limite, aplica a última alíquota
        if (baseRestante > 0) {
            impostoTotal += baseRestante * aliquotas[aliquotas.length - 1];
        }

        return impostoTotal;
    }

    @Override
    public Float impostoIss(Float valor) {
        // Assumindo uma alíquota padrão de 5%
        return valor * 0.05f;
    }

    @Override
    public Float impostoIss(Float valor, Float aliquota) {
        return valor * aliquota;
    }

    @Override
    public Float impostoItbi(Float valor) {
        // Assumindo uma alíquota padrão de 2%
        return valor * 0.02f;
    }

    @Override
    public Float impostoItbi(Float valor, Float aliquota) {
        return valor * aliquota;
    }
}
