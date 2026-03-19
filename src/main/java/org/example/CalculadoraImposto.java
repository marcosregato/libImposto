package org.example;

public class CalculadoraImposto {
    private ServicoTaxa servicoTaxa;

    public CalculadoraImposto(ServicoTaxa servicoTaxa) {
        this.servicoTaxa = servicoTaxa;
    }

    public double calcular(double valor, String tipo) {
        double taxa = servicoTaxa.obterTaxa(tipo);
        return valor + (valor * taxa);
    }
}
