package org.example;

import impostoEstadual.ImpEstadual;
import impostoFederal.ImpFederal;
import impostoMunicipal.ImpMunicipal;
import interfaceImposto.ImpostoEstadualInterface;
import interfaceImposto.ImpostoFederalInterface;
import interfaceImposto.ImpostoMunicipalInterface;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Cálculo de Impostos ===");

        // 1. Instanciar as implementações dos impostos
        ImpostoFederalInterface impFederal = new ImpFederal();
        ImpostoEstadualInterface impEstadual = new ImpEstadual();
        ImpostoMunicipalInterface impMunicipal = new ImpMunicipal();

        // 2. Instanciar a calculadora completa
        CalculadoraImpostoCompleta calculadora = new CalculadoraImpostoCompleta(impFederal, impEstadual, impMunicipal);

        // --- Cenário 1: Venda Interestadual de Produto ---
        System.out.println("\n--- Cenário 1: Venda Interestadual de Produto ---");
        float valorProduto = 1000.0f;
        float frete = 50.0f;
        float aliquotaIpi = 0.10f; // 10%
        float aliquotaIcmsDestino = 0.18f; // 18% (ex: SP)
        float aliquotaIcmsInterestadual = 0.12f; // 12%

        System.out.printf("Valor do Produto: R$ %.2f%n", valorProduto);
        System.out.printf("Frete: R$ %.2f%n", frete);

        // Calcular IPI separadamente para mostrar
        float valorIpi = impFederal.impostoIpi(valorProduto, frete, 0, 0, aliquotaIpi);
        System.out.printf("IPI (10%%): R$ %.2f%n", valorIpi);

        // Calcular DIFAL separadamente
        float baseIcms = valorProduto + frete + valorIpi;
        float valorDifal = impEstadual.impostoDifal(baseIcms, aliquotaIcmsDestino, aliquotaIcmsInterestadual);
        System.out.printf("DIFAL (18%% - 12%%): R$ %.2f%n", valorDifal);

        float totalImpostosVenda = calculadora.calcularVendaInterestadual(valorProduto, frete, aliquotaIpi, aliquotaIcmsDestino, aliquotaIcmsInterestadual);
        System.out.printf("Total de Impostos (IPI + DIFAL): R$ %.2f%n", totalImpostosVenda);


        // --- Cenário 2: Prestação de Serviço ---
        System.out.println("\n--- Cenário 2: Prestação de Serviço ---");
        float valorServico = 2000.0f;
        float aliquotaIss = 0.05f; // 5%
        float aliquotaPis = 0.0065f; // 0.65%
        float aliquotaCofins = 0.03f; // 3%

        System.out.printf("Valor do Serviço: R$ %.2f%n", valorServico);
        
        float valorIss = impMunicipal.impostoIss(valorServico, aliquotaIss);
        System.out.printf("ISS (5%%): R$ %.2f%n", valorIss);
        
        float valorPis = impFederal.impostoPisPasep(valorServico, aliquotaPis);
        System.out.printf("PIS (0.65%%): R$ %.2f%n", valorPis);
        
        float valorCofins = impFederal.impostoConfins(valorServico, aliquotaCofins);
        System.out.printf("COFINS (3%%): R$ %.2f%n", valorCofins);

        float totalImpostosServico = calculadora.calcularServico(valorServico, aliquotaIss, aliquotaPis, aliquotaCofins);
        System.out.printf("Total de Impostos Serviço: R$ %.2f%n", totalImpostosServico);

        // --- Cenário 3: Cálculo de INSS Progressivo (Funcionário) ---
        System.out.println("\n--- Cenário 3: Cálculo de INSS Progressivo ---");
        float salario = 3000.0f;
        // Tabela INSS 2023 simplificada
        float[] limitesInss = {1320.00f, 2571.29f, 3856.94f, 7507.49f};
        float[] aliquotasInss = {0.075f, 0.09f, 0.12f, 0.14f};
        
        float valorInss = impFederal.impostoInss(salario, limitesInss, aliquotasInss);
        System.out.printf("Salário Bruto: R$ %.2f%n", salario);
        System.out.printf("INSS a descontar: R$ %.2f%n", valorInss);
        System.out.printf("Salário Líquido (aprox.): R$ %.2f%n", salario - valorInss);
    }
}
