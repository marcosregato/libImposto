package impostoFederal;

import interfaceImposto.ImpostoFederalInterface;

public class ImpFederal implements ImpostoFederalInterface{


    @Override
    public float impostoImpotacao(float valorAduaneiro, float aliquotaImportacao) {
        return valorAduaneiro * aliquotaImportacao;
    }

    @Override
    public float impostoIpi(float baseCalculo, float aliquotaIPI) {
        return baseCalculo * aliquotaIPI;
    }

    @Override
    public float impostoIpi(float valorProduto, float frete, float seguro, float outrasDespesas, float aliquotaIPI) {
        float baseCalculo = valorProduto + frete + seguro + outrasDespesas;
        return baseCalculo * aliquotaIPI;
    }

    @Override
    public float impostoIrpf(float valor) { return 0f; }

    @Override
    public float impostoIrpf(float baseCalculo, float aliquota, float deducao) {
        return Math.max(0, (baseCalculo * aliquota) - deducao);
    }

    @Override
    public float impostoIrpf(float baseCalculo, float[] limites, float[] aliquotas, float[] deducoes) {
        if (aliquotas.length != limites.length + 1 || deducoes.length != limites.length + 1) {
            throw new IllegalArgumentException("Arrays de alíquotas/deduções devem ter um elemento a mais que o de limites.");
        }
        for (int i = 0; i < limites.length; i++) {
            if (baseCalculo <= limites[i]) {
                return impostoIrpf(baseCalculo, aliquotas[i], deducoes[i]);
            }
        }
        return impostoIrpf(baseCalculo, aliquotas[aliquotas.length - 1], deducoes[aliquotas.length - 1]);
    }

    @Override
    public float impostoIrpj(float valor) { return 0f; }
    
    @Override
    public float impostoIrpj(float baseCalculo, float aliquota) {
        return baseCalculo * aliquota;
    }

    @Override
    public float impostoConfins(float valor) { return 0f; }

    @Override
    public float impostoConfins(float baseCalculo, float aliquota) {
        return baseCalculo * aliquota;
    }

    @Override
    public float impostoPisPasep(float valor) { return 0f; }

    @Override
    public float impostoPisPasep(float baseCalculo, float aliquota) {
        return baseCalculo * aliquota;
    }

    @Override
    public float impostoCsll(float valor) { return 0f; }

    @Override
    public float impostoCsll(float baseCalculo, float aliquota) {
        return baseCalculo * aliquota;
    }

    @Override
    public float impostoInss(float valor) { return 0f; }

    @Override
    public float impostoInss(float salario, float aliquota) {
        return salario * aliquota;
    }

    @Override
    public float impostoInss(float salario, float[] limites, float[] aliquotas) {
        if (aliquotas.length != limites.length + 1) {
            throw new IllegalArgumentException("O array de alíquotas deve ter um elemento a mais que o de limites.");
        }

        float impostoTotal = 0.0f;
        float baseRestante = salario;
        float limiteAnterior = 0.0f;

        for (int i = 0; i < limites.length; i++) {
            float limiteAtual = limites[i];
            float faixaTamanho = limiteAtual - limiteAnterior;

            if (baseRestante <= 0) break;

            if (baseRestante > faixaTamanho) {
                impostoTotal += faixaTamanho * aliquotas[i];
                baseRestante -= faixaTamanho;
            } else {
                impostoTotal += baseRestante * aliquotas[i];
                baseRestante = 0;
            }
            limiteAnterior = limiteAtual;
        }

        if (baseRestante > 0) {
            impostoTotal += baseRestante * aliquotas[aliquotas.length - 1];
        }

        return impostoTotal;
    }
}
