# libImposto

[![Maven Central](https://img.shields.io/maven-central/v/com.libimposto/libimposto.svg)](https://search.maven.org/artifact/com.libimposto/libimposto)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

Biblioteca Java para cálculo de impostos brasileiros (federais, estaduais e municipais).

## Funcionalidades

- **Impostos Federais**: IRPJ, COFINS, PIS, CSLL, IPI
- **Impostos Estaduais**: ICMS (incluindo DIFAL), IPVA, ITCMD
- **Impostos Municipais**: ISS
- **Regimes Tributários**: Lucro Presumido, Lucro Real, Simples Nacional
- **Operações Interestaduais**: Suporte completo a DIFAL
- **API Simples**: Interface intuitiva e fácil de usar

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

// Cálculo com configuração padrão (Lucro Presumido, São Paulo)
ResultadoImpostos resultado = LibImposto.calcular(10000f);
System.out.println("Total de impostos: R$ " + resultado.getTotal());
```

### Cálculo Personalizado

```java
import org.example.libImposto.LibImposto;
import org.example.libImposto.config.ConfiguracaoImpostos;
import org.example.libImposto.enums.RegimeTributario;
import org.example.libImposto.model.ResultadoImpostos;

// Configuração personalizada
ConfiguracaoImpostos config = LibImposto.configuracao()
    .regimeTributario(RegimeTributario.LUCRO_REAL)
    .estadoOrigem("SP")
    .estadoDestino("RJ")
    .aliquotaICMS(0.18f)
    .aliquotaIPI(0.10f)
    .build();

ResultadoImpostos resultado = LibImposto.calcular(15000f, config);
System.out.println(resultado.getResumoCompleto());
```

### Cálculo por Tipo de Imposto

```java
// Apenas impostos federais
ResultadoImpostos federais = LibImposto.calcularFederais(8000f, config);

// Apenas impostos estaduais
ResultadoImpostos estaduais = LibImposto.calcularEstaduais(8000f, config);

// Apenas impostos municipais
ResultadoImpostos municipais = LibImposto.calcularMunicipais(8000f, config);
```

## Configuração

### Builder Pattern

Use o Builder para criar configurações personalizadas:

```java
ConfiguracaoImpostos config = new ConfiguracaoImpostos.Builder()
    .regimeTributario(RegimeTributario.LUCRO_PRESUMIDO)
    .estadoOrigem("SP")
    .estadoDestino("RJ")
    .aliquotaICMS(0.18f)
    .aliquotaIPI(0.15f)
    .aliquotaISS(0.05f)
    .percentualPresuncao(0.32f)
    .simplesNacional(false)
    .receitaBruta12Meses(500000f)
    .build();
```

### Regimes Tributários

- **LUCRO_PRESUMIDO**: Cálculo baseado em percentuais de presunção
- **LUCRO_REAL**: Cálculo sobre lucro contábil real
- **SIMPLES_NACIONAL**: Alíquota única para micro e pequenas empresas

### Operações Interestaduais

```java
ConfiguracaoImpostos config = LibImposto.configuracao()
    .estadoOrigem("SP")  // São Paulo
    .estadoDestino("BA") // Bahia
    .build();

ResultadoImpostos resultado = LibImposto.calcular(12000f, config);
// Inclui ICMS origem + DIFAL automaticamente
```

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

// Totais por tipo
float totalFederal = resultado.getTotalFederal();
float totalEstadual = resultado.getTotalEstadual();
float totalMunicipal = resultado.getTotalMunicipal();
float totalGeral = resultado.getTotal();

// Relatórios formatados
System.out.println(resultado.getResumoFederal());
System.out.println(resultado.getResumoEstadual());
System.out.println(resultado.getResumoMunicipal());
System.out.println(resultado.getResumoCompleto());
```

## Exemplos Completos

### Comparação de Regimes

```java
public void compararRegimes(float valor) {
    // Lucro Presumido
    ConfiguracaoImpostos configLP = LibImposto.configuracao()
        .regimeTributario(RegimeTributario.LUCRO_PRESUMIDO)
        .build();
    ResultadoImpostos resultadoLP = LibImposto.calcular(valor, configLP);
    
    // Lucro Real
    ConfiguracaoImpostos configLR = LibImposto.configuracao()
        .regimeTributario(RegimeTributario.LUCRO_REAL)
        .build();
    ResultadoImpostos resultadoLR = LibImposto.calcular(valor, configLR);
    
    // Simples Nacional
    ConfiguracaoImpostos configSN = LibImposto.configuracao()
        .regimeTributario(RegimeTributario.SIMPLES_NACIONAL)
        .simplesNacional(true)
        .receitaBruta12Meses(500000f)
        .build();
    ResultadoImpostos resultadoSN = LibImposto.calcular(valor, configSN);
    
    System.out.println("Lucro Presumido: R$ " + resultadoLP.getTotalFederal());
    System.out.println("Lucro Real: R$ " + resultadoLR.getTotalFederal());
    System.out.println("Simples Nacional: R$ " + resultadoSN.getTotalFederal());
}
```

## Impostos Suportados

### Federais
- **IRPJ**: Imposto de Renda Pessoa Jurídica (15% + 10% adicional)
- **COFINS**: Contribuição para Financiamento da Seguridade Social
- **PIS**: Programa de Integração Social
- **CSLL**: Contribuição Social sobre Lucro Líquido
- **IPI**: Imposto sobre Produtos Industrializados

### Estaduais
- **ICMS**: Imposto sobre Circulação de Mercadorias e Serviços
- **DIFAL**: Diferencial de Alíquotas (operações interestaduais)
- **IPVA**: Imposto sobre Propriedade de Veículos Automotores
- **ITCMD**: Imposto sobre Transmissão Causa Mortis e Doação

### Municipais
- **ISS**: Imposto Sobre Serviços

## Documentação

- **Javadoc**: [Documentação completa da API](https://javadoc.io/doc/com.libimposto/libimposto)
- **Exemplos**: Veja a classe `ExemploUso` para exemplos detalhados

## Requisitos

- Java 17 ou superior
- Maven 3.6+ (para desenvolvimento)

## Desenvolvimento

### Compilação

```bash
mvn clean compile
```

### Testes

```bash
mvn test
```

### Build

```bash
mvn clean package
```

### Instalação Local

```bash
mvn clean install
```

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## Contribuição

Contribuições são bem-vindas! Por favor:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## Suporte

- **Issues**: [GitHub Issues](https://github.com/libimposto/libimposto/issues)
- **Email**: contato@libimposto.com
- **Documentação**: [Wiki do Projeto](https://github.com/libimposto/libimposto/wiki)

## Changelog

### v1.0.0
- Versão inicial
- Suporte a impostos federais, estaduais e municipais
- API simplificada com Builder pattern
- Suporte a operações interestaduais
- Documentação completa e exemplos