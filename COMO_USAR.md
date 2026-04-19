# Como Usar o JAR da libImposto

## Arquivos Gerados

O build Maven gerou os seguintes arquivos no diretório `target/`:

- **libimposto-1.0.0.jar** - JAR principal para uso em outros sistemas
- **libimposto-1.0.0-sources.jar** - Código fonte
- **libimposto-1.0.0-javadoc.jar** - Documentação Javadoc

## Como Usar em Outro Sistema

### 1. Adicionar o JAR ao Classpath

Copie o arquivo `libimposto-1.0.0.jar` para o seu projeto e adicione ao classpath.

#### Maven (repositório local)
```xml
<dependency>
    <groupId>com.libimposto</groupId>
    <artifactId>libimposto</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle
```groovy
implementation files('caminho/para/libimposto-1.0.0.jar')
```

#### Linha de Comando
```bash
java -cp .:libimposto-1.0.0.jar SuaClasse
```

### 2. Exemplo de Uso Básico

```java
import org.example.libImposto.LibImposto;
import org.example.libImposto.model.ResultadoImpostos;

public class MeuSistema {
    public static void main(String[] args) {
        // Cálculo simples com configuração padrão
        ResultadoImpostos resultado = LibImposto.calcular(10000f);
        System.out.println("Total de impostos: R$ " + resultado.getTotal());
        
        // Cálculo personalizado
        ConfiguracaoImpostos config = LibImposto.configuracao()
            .aliquotaICMS(0.18f)
            .build();
            
        ResultadoImpostos resultadoPersonalizado = LibImposto.calcular(15000f, config);
        System.out.println(resultadoPersonalizado);
    }
}
```

### 3. Classes Principais

- **LibImposto** - Classe principal para cálculos
- **ResultadoImpostos** - Resultado com todos os impostos
- **ConfiguracaoImpostos** - Configuração personalizada (Builder pattern)

### 4. Teste de Funcionamento

Para testar se o JAR está funcionando corretamente:

```bash
java -cp libimposto-1.0.0.jar org.example.libImposto.example.ExemploUso
```

Saída esperada:
```
=== EXEMPLOS DE USO - libImposto ===

1. CÁLCULO SIMPLES (CONFIGURAÇÃO PADRÃO)
Valor: R$ 10.000,00
IRPJ: R$ 1500,00, COFINS: R$ 300,00, PIS: R$ 65,00, CSLL: R$ 900,00, IPI: R$ 1500,00, ICMS: R$ 1800,00, IPVA: R$ 300,00, ITCMD: R$ 400,00, ISS: R$ 200,00, TOTAL: R$ 6965,00

2. CÁLCULO PERSONALIZADO
Valor: R$ 15.000,00
IRPJ: R$ 2250,00, COFINS: R$ 450,00, PIS: R$ 97,50, CSLL: R$ 1350,00, IPI: R$ 2250,00, ICMS: R$ 2700,00, IPVA: R$ 450,00, ITCMD: R$ 600,00, ISS: R$ 300,00, TOTAL: R$ 10447,50

libImposto v1.0 - Biblioteca para cálculo de impostos brasileiros
```

## Informações do JAR

- **Tamanho**: 28.6 KB
- **Versão**: 1.0.0
- **Java**: 17+
- **Dependências**: Nenhuma (standalone)

## Estrutura do JAR

O JAR contém todas as classes compiladas necessárias:
- Classes principais da biblioteca
- Classes de exemplo
- Interfaces e enums
- Manifesto com metadados

O JAR está pronto para uso em qualquer sistema Java 17+!
