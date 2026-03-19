package interfaceImposto;

public interface ImpostoMunicipalInterface {


    /**
     * Calcula IPTU com alíquota fixa de 1%.
     */
    public Float impostoIptu(Float valor);

    /**
     * Calcula IPTU com alíquota específica.
     * @param valor Valor venal do imóvel.
     * @param aliquota Alíquota (ex: 0.015 para 1.5%).
     */
    public Float impostoIptu(Float valor, Float aliquota);

    /**
     * Calcula IPTU progressivo por faixas de valor venal.
     * @param valor Valor venal do imóvel.
     * @param limites Limites superiores das faixas.
     * @param aliquotas Alíquotas correspondentes.
     */
    public Float impostoIptuProgressivo(Float valor, float[] limites, float[] aliquotas);

    /**
     * Calcula ISS com alíquota fixa de 5%.
     */
    public Float impostoIss(Float valor);

    /**
     * Calcula ISS com alíquota específica.
     * @param valor Valor do serviço.
     * @param aliquota Alíquota (ex: 0.03 para 3%).
     */
    public Float impostoIss(Float valor, Float aliquota);

    /**
     * Calcula ITBI com alíquota padrão (atualmente retorna 0 na implementação antiga).
     */
    public Float impostoItbi(Float valor);

    /**
     * Calcula ITBI com alíquota específica.
     * @param valor Valor da transação ou venal (o maior).
     * @param aliquota Alíquota (ex: 0.02 para 2%).
     */
    public Float impostoItbi(Float valor, Float aliquota);

}
