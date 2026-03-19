package impostoEstadual;

import interfaceImposto.ImpostoEstadualInterface;

public class ImpEstadual implements ImpostoEstadualInterface {

    /**
     * No Brasil, o ICMS é um imposto que incide "por dentro", ou seja,
     * o seu valor já está embutido na própria base de cálculo.
     * A fórmula correta para encontrar a base de cálculo (BC) é:
     * @param valorOperacao Valor da operação.
     * @param aliquotaIcms Alíquota do ICMS (ex: 0.18 para 18%).
     * @return A base de cálculo com o imposto embutido.
     */
    public float getBaseCalculo(float valorOperacao, float aliquotaIcms) {
        return valorOperacao / (1 - aliquotaIcms);
    }

    /**
     * Calcula o valor do ICMS devido após encontrar a base de cálculo (por dentro).
     * @param valorOperacao Valor da operação.
     * @param aliquotaIcms Alíquota do ICMS.
     * @return O valor resultante do imposto.
     */
    @Override
    public float impostoIcms(float valorOperacao, float aliquotaIcms) {
        return getBaseCalculo(valorOperacao,aliquotaIcms) * aliquotaIcms;
    }

    /**
     * Calcula o valor do ICMS de forma simples (por fora), sem embutir o imposto na base de cálculo.
     * @param valorOperacao Valor da operação.
     * @param aliquotaIcms Alíquota do ICMS.
     * @return O valor do imposto.
     */
    @Override
    public float impostoIcmsSimples(float valorOperacao, float aliquotaIcms) {
        return valorOperacao * aliquotaIcms;
    }

    /**
     * Calcula o Diferencial de Alíquota (DIFAL) do ICMS.
     * Utilizado em operações interestaduais para consumidor final.
     * @param baseCalculo Valor base sobre o qual incide o imposto.
     * @param aliquotaInternaDestino Alíquota interna do estado de destino.
     * @param aliquotaInterestadual Alíquota interestadual.
     * @return O valor do DIFAL. Se a alíquota de destino for menor ou igual à interestadual, retorna 0.
     */
    @Override
    public float impostoDifal(float baseCalculo, float aliquotaInternaDestino, float aliquotaInterestadual) {
        float diferencaAliquota = aliquotaInternaDestino - aliquotaInterestadual;
        if (diferencaAliquota <= 0) {
            return 0.0f;
        }
        return baseCalculo * diferencaAliquota;
    }

    /**
     * Calcula o IPVA com base na porcentagem inteira (ex: 4 para 4%).
     * @param valorVeiculo Valor venal do veículo.
     * @param porcentagemEstado Alíquota do IPVA em porcentagem (ex: 4.0).
     * @return Valor do imposto.
     */
    @Override
    public float impostoIpva(float valorVeiculo, float porcentagemEstado) {
        return valorVeiculo * (porcentagemEstado / 100);
    }

    /**
     * Calcula o IPVA com base na alíquota decimal (ex: 0.04 para 4%).
     * @param valorVeiculo Valor venal do veículo.
     * @param aliquotaDecimal Alíquota do IPVA em decimal (ex: 0.04).
     * @return Valor do imposto.
     */
    @Override
    public float impostoIpva(float valorVeiculo, double aliquotaDecimal) {
        return valorVeiculo * (float) aliquotaDecimal;
    }

    /**
     * Calcula o ITCMD (Imposto sobre Transmissão Causa Mortis e Doação).
     * @param baseCalculo Valor venal dos bens ou direitos transmitidos.
     * @param aliquota Alíquota do imposto (ex: 0.04 para 4%).
     * @return Valor do imposto.
     */
    @Override
    public float impostoItcmd(float baseCalculo, float aliquota) {
        return baseCalculo * aliquota;
    }

    /**
     * Calcula o ITCMD de forma progressiva.
     * @param baseCalculo Valor venal dos bens.
     * @param limites Array com os valores limites de cada faixa. Ex: {100000f} significa que a primeira faixa vai de 0 a 100k.
     * @param aliquotas Array com as alíquotas. Deve ter tamanho limites.length + 1. Ex: {0.02f, 0.04f} (2% até 100k, 4% acima).
     * @return Valor total do imposto calculado progressivamente.
     */
    @Override
    public float impostoItcmdProgressivo(float baseCalculo, float[] limites, float[] aliquotas) {
        if (aliquotas.length != limites.length + 1) {
            throw new IllegalArgumentException("O array de alíquotas deve ter um elemento a mais que o array de limites.");
        }

        float impostoTotal = 0.0f;
        float baseRestante = baseCalculo;
        float limiteAnterior = 0.0f;

        for (int i = 0; i < limites.length; i++) {
            float limiteAtual = limites[i];
            float faixaTamanho = limiteAtual - limiteAnterior;

            if (baseRestante <= 0) break;

            if (baseRestante > faixaTamanho) {
                // Preenche toda a faixa atual
                impostoTotal += faixaTamanho * aliquotas[i];
                baseRestante -= faixaTamanho;
                limiteAnterior = limiteAtual; // Avança para o próximo limite
            } else {
                // O restante cabe nesta faixa
                impostoTotal += baseRestante * aliquotas[i];
                baseRestante = 0;
                break;
            }
        }

        // Se ainda sobrou valor após o último limite, aplica a última alíquota
        if (baseRestante > 0) {
            impostoTotal += baseRestante * aliquotas[aliquotas.length - 1];
        }

        return impostoTotal;
    }
}
