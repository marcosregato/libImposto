# libImposto

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

Biblioteca Java simples para cálculo de impostos brasileiros.

## Funcionalidades

- **Impostos Federais**: IRPJ, COFINS, PIS, CSLL, IPI
- **Impostos Estaduais**: ICMS, IPVA, ITCMD
- **Impostos Municipais**: ISS
- **API Simples**: Interface direta e fácil de usar
- **Cálculo Rápido**: Alíquotas fixas pré-definidas

## Instalação

### Maven

```xml
<dependency>
    <groupId>com.libimposto</groupId>
    <artifactId>libimposto</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.libimposto:libimposto:1.0.0'
```

## Uso Rápido

### Cálculo Simples

```java
import org.example.libImposto.LibImposto;
import org.example.libImposto.model.ResultadoImpostos;

// Cálculo direto com alíquotas padrão
ResultadoImpostos resultado = LibImposto.calcular(10000f);
System.out.println("Total de impostos: R$ " + resultado.getTotal());
System.out.println(resultado); // Mostra todos os impostos calculados
```

### Cálculo por Tipo de Imposto

```java
// Apenas impostos federais
ResultadoImpostos federais = LibImposto.calcularFederais(8000f, null);

// Apenas impostos estaduais
ResultadoImpostos estaduais = LibImposto.calcularEstaduais(8000f, null);

// Apenas impostos municipais
ResultadoImpostos municipais = LibImposto.calcularMunicipais(8000f, null);
```

## Alíquotas Padrão

A biblioteca utiliza alíquotas fixas simplificadas:

### Federais
- **IRPJ**: 15%
- **COFINS**: 3%
- **PIS**: 0,65%
- **CSLL**: 9%
- **IPI**: 15%

### Estaduais
- **ICMS**: 18%
- **IPVA**: 3%
- **ITCMD**: 4%

### Municipais
- **ISS**: 2%

## Resultados

### Objeto ResultadoImpostos

```java
ResultadoImpostos resultado = LibImposto.calcular(10000f);

// Valores individuais
float irpj = resultado.getIrpj();
float cofins = resultado.getCofins();
float pis = resultado.getPis();
float csll = resultado.getCsll();
float icms = resultado.getIcms();

// Total geral
float total = resultado.getTotal();

// Exibir resultado formatado
System.out.println(resultado);
```

## Exemplo Completo

```java
import org.example.libImposto.LibImposto;
import org.example.libImposto.model.ResultadoImpostos;

public class ExemploSimples {
    public static void main(String[] args) {
        // Calcular impostos sobre R$ 10.000
        ResultadoImpostos resultado = LibImposto.calcular(10000f);
        
        System.out.println("=== CÁLCULO DE IMPOSTOS ===");
        System.out.println(resultado);
        
        System.out.printf("Total de impostos: R$ %.2f%n", resultado.getTotal());
    }
}
```

## Impostos Suportados

### Federais
- **IRPJ**: Imposto de Renda Pessoa Jurídica (15%)
- **COFINS**: Contribuição para Financiamento da Seguridade Social (3%)
- **PIS**: Programa de Integração Social (0,65%)
- **CSLL**: Contribuição Social sobre Lucro Líquido (9%)
- **IPI**: Imposto sobre Produtos Industrializados (15%)

### Estaduais
- **ICMS**: Imposto sobre Circulação de Mercadorias e Serviços (18%)
- **IPVA**: Imposto sobre Propriedade de Veículos Automotores (3%)
- **ITCMD**: Imposto sobre Transmissão Causa Mortis e Doação (4%)

### Municipais
- **ISS**: Imposto Sobre Serviços (2%)

## Documentação

- **Exemplos**: Veja a classe `ExemploUso` para exemplos básicos

## Requisitos

- Java 17 ou superior
- Maven 3.6+ (para desenvolvimento)

## Desenvolvimento

### Build

```bash
mvn clean package -Dmaven.test.skip=true
```

### Testes

```bash
mvn test
```

## Licença

Este projeto está licenciado sob a Licença MIT.

## Changelog

### v1.0.0
- Versão inicial simplificada
- Suporte a impostos federais, estaduais e municipais
- API simples e direta
- Cálculo com alíquotas fixas