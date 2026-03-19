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
    public float impostoIrpf(float valor) {
        return 0f;
    }

    @Override
    public float impostoIrpf(float baseCalculo, float aliquota, float deducao) {
        float impostoBruto = baseCalculo * aliquota;
        // O imposto não pode ser negativo
        return Math.max(0, impostoBruto - deducao);
    }

    /**
     * Calcula o IRPF com base na tabela progressiva.
     * @param baseCalculo Base de cálculo do imposto.
     * @param limites Array com os limites superiores de cada faixa. Ex: {2112.00f, 2826.65f...}
     * @param aliquotas Array com as alíquotas. Deve ter tamanho limites.length + 1.
     * @param deducoes Array com as parcelas a deduzir. Deve ter tamanho limites.length + 1.
     * @return Valor do IRPF a pagar.
     */
    @Override
    public float impostoIrpf(float baseCalculo, float[] limites, float[] aliquotas, float[] deducoes) {
        if (aliquotas.length != limites.length + 1 || deducoes.length != limites.length + 1) {
            throw new IllegalArgumentException("Os arrays de alíquotas e deduções devem ter um elemento a mais que o array de limites.");
        }

        for (int i = 0; i < limites.length; i++) {
            if (baseCalculo <= limites[i]) {
                return impostoIrpf(baseCalculo, aliquotas[i], deducoes[i]);
            }
        }
        
        // Se ultrapassou todos os limites, cai na última faixa
        return impostoIrpf(baseCalculo, aliquotas[aliquotas.length - 1], deducoes[aliquotas.length - 1]);
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
