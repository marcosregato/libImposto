package org.example;

public class CalculadoraImpostosProduto {
    
    private IRPJ irpj;
    private COFINS cofins;
    private PISPASEP pisPasep;
    private CSLL csll;
    
    public CalculadoraImpostosProduto() {
        this.irpj = new IRPJ();
        this.cofins = new COFINS();
        this.pisPasep = new PISPASEP();
        this.csll = new CSLL();
    }
    
    public ResultadoImpostos calcularImpostos(float valorProduto) {
        ResultadoImpostos resultado = new ResultadoImpostos();
        
        resultado.irpj = irpj.calcular(valorProduto);
        resultado.cofins = cofins.calcular(valorProduto);
        resultado.pis = pisPasep.calcular(valorProduto);
        resultado.csll = csll.calcular(valorProduto);
        
        return resultado;
    }
    
    public static class ResultadoImpostos {
        public float irpj;
        public float cofins;
        public float pis;
        public float csll;
        
        public float getTotal() {
            return irpj + cofins + pis + csll;
        }
        
        @Override
        public String toString() {
            return String.format(
                "IRPJ: R$ %.2f\n" +
                "COFINS: R$ %.2f\n" +
                "PIS: R$ %.2f\n" +
                "CSLL: R$ %.2f\n" +
                "TOTAL: R$ %.2f",
                irpj, cofins, pis, csll, getTotal()
            );
        }
    }
    
    public static void main(String[] args) {
        CalculadoraImpostosProduto calculadora = new CalculadoraImpostosProduto();
        
        System.out.println("=== Cálculo de Impostos sobre Produto R$ 10.000 ===");
        ResultadoImpostos resultado = calculadora.calcularImpostos(10000f);
        System.out.println(resultado);
    }
}
