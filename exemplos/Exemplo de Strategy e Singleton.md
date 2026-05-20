# Códigos para Refatoração com Strategy e Singleton

## Objetivo do material

Este material apresenta dois códigos em Java que foram propositalmente escritos com problemas de projeto. Eles podem ser usados em aula para discutir a necessidade de refatoração e introduzir os padrões de projeto **Strategy** e **Singleton**.

A ideia é que os alunos analisem os problemas existentes, identifiquem limitações de manutenção e proponham uma solução melhor utilizando os padrões adequados.

---

# Código 1 — Refatoração com Strategy

## Cenário

Uma loja virtual possui diferentes formas de cálculo de frete. Atualmente, todas as regras estão concentradas em uma única classe, utilizando várias estruturas condicionais.

Com o tempo, novas formas de entrega podem ser adicionadas, como entrega por aplicativo, entrega internacional ou entrega agendada. O código atual tende a ficar cada vez maior, mais difícil de testar e mais difícil de modificar.

---

## Código inicial sem Strategy

```java
public class Pedido {
    private double valor;
    private double peso;
    private String tipoEntrega;

    public Pedido(double valor, double peso, String tipoEntrega) {
        this.valor = valor;
        this.peso = peso;
        this.tipoEntrega = tipoEntrega;
    }

    public double getValor() {
        return valor;
    }

    public double getPeso() {
        return peso;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }
}
```

```java
public class CalculadoraFrete {

    public double calcularFrete(Pedido pedido) {
        if (pedido.getTipoEntrega().equals("CORREIOS")) {
            return 10.0 + pedido.getPeso() * 2.5;
        }

        if (pedido.getTipoEntrega().equals("TRANSPORTADORA")) {
            if (pedido.getValor() > 500.0) {
                return 0.0;
            }
            return 30.0 + pedido.getPeso() * 1.8;
        }

        if (pedido.getTipoEntrega().equals("EXPRESSA")) {
            return 50.0 + pedido.getPeso() * 4.0;
        }

        if (pedido.getTipoEntrega().equals("RETIRADA_LOJA")) {
            return 0.0;
        }

        throw new IllegalArgumentException("Tipo de entrega inválido");
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Pedido pedido1 = new Pedido(300.0, 4.0, "CORREIOS");
        Pedido pedido2 = new Pedido(800.0, 8.0, "TRANSPORTADORA");
        Pedido pedido3 = new Pedido(120.0, 2.0, "EXPRESSA");

        CalculadoraFrete calculadora = new CalculadoraFrete();

        System.out.println("Frete pedido 1: " + calculadora.calcularFrete(pedido1));
        System.out.println("Frete pedido 2: " + calculadora.calcularFrete(pedido2));
        System.out.println("Frete pedido 3: " + calculadora.calcularFrete(pedido3));
    }
}
```

---

## Problemas do código atual

O código atual concentra várias regras de negócio dentro do método `calcularFrete`. Isso gera alguns problemas importantes:

1. **Baixa extensibilidade**: sempre que uma nova forma de entrega for adicionada, será necessário alterar a classe `CalculadoraFrete`.

2. **Violação do princípio aberto/fechado**: a classe não está fechada para modificação, pois novas regras exigem alterações no código existente.

3. **Aumento da complexidade condicional**: quanto mais tipos de frete forem adicionados, maior será a quantidade de `if`, tornando o método mais difícil de entender.

4. **Dificuldade de teste**: as regras de frete estão misturadas em um único método. Testar cada regra isoladamente se torna menos direto.

5. **Acoplamento com strings**: o comportamento do sistema depende de valores textuais, como `"CORREIOS"` e `"EXPRESSA"`, o que pode causar erros difíceis de detectar em tempo de compilação.

---

## Por que utilizar Strategy neste cenário?

O padrão **Strategy** é adequado quando existem diferentes algoritmos ou comportamentos que podem variar de acordo com o contexto. Neste caso, cada forma de entrega representa uma estratégia diferente de cálculo de frete.

Ao aplicar Strategy, cada cálculo de frete pode ser colocado em uma classe separada. Por exemplo:

- `FreteCorreios`;
- `FreteTransportadora`;
- `FreteExpresso`;
- `FreteRetiradaLoja`.

Todas essas classes podem implementar uma mesma interface, como `EstrategiaFrete`.

Com isso, a classe principal deixa de decidir qual regra aplicar por meio de condicionais. Em vez disso, ela recebe uma estratégia pronta e apenas executa o cálculo.

---

## Possível direção para a refatoração

Uma possível solução seria criar uma interface como:

```java
public interface EstrategiaFrete {
    double calcular(Pedido pedido);
}
```

Depois, cada tipo de frete seria representado por uma classe concreta:

```java
public class FreteCorreios implements EstrategiaFrete {
    @Override
    public double calcular(Pedido pedido) {
        return 10.0 + pedido.getPeso() * 2.5;
    }
}
```

A classe de contexto poderia receber uma estratégia de frete:

```java
public class CalculadoraFrete {
    private EstrategiaFrete estrategiaFrete;

    public CalculadoraFrete(EstrategiaFrete estrategiaFrete) {
        this.estrategiaFrete = estrategiaFrete;
    }

    public double calcular(Pedido pedido) {
        return estrategiaFrete.calcular(pedido);
    }
}
```

---

# Código 2 — Refatoração com Singleton

## Cenário

Um sistema precisa registrar logs de operações importantes, como criação de pedidos, aprovação de pagamentos e erros de processamento.

Na versão atual, cada classe cria sua própria instância de `LoggerSistema`. Isso dificulta o controle centralizado do log e pode gerar inconsistências, especialmente se no futuro o logger precisar escrever em arquivo, banco de dados ou serviço externo.

---

## Código inicial sem Singleton

```java
import java.time.LocalDateTime;

public class LoggerSistema {

    public void log(String mensagem) {
        System.out.println(LocalDateTime.now() + " - " + mensagem);
    }
}
```

```java
public class ServicoPedido {

    public void criarPedido(String cliente, double valor) {
        LoggerSistema logger = new LoggerSistema();

        logger.log("Iniciando criação do pedido para o cliente: " + cliente);

        if (valor <= 0) {
            logger.log("Erro: valor do pedido inválido");
            return;
        }

        logger.log("Pedido criado com sucesso no valor de R$ " + valor);
    }
}
```

```java
public class ServicoPagamento {

    public void processarPagamento(double valor, String formaPagamento) {
        LoggerSistema logger = new LoggerSistema();

        logger.log("Iniciando pagamento via " + formaPagamento);

        if (valor <= 0) {
            logger.log("Erro: valor do pagamento inválido");
            return;
        }

        logger.log("Pagamento aprovado no valor de R$ " + valor);
    }
}
```

```java
public class ServicoEntrega {

    public void despacharPedido(String codigoPedido) {
        LoggerSistema logger = new LoggerSistema();

        logger.log("Preparando despacho do pedido: " + codigoPedido);

        if (codigoPedido == null || codigoPedido.isBlank()) {
            logger.log("Erro: código do pedido inválido");
            return;
        }

        logger.log("Pedido " + codigoPedido + " despachado com sucesso");
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        ServicoPedido servicoPedido = new ServicoPedido();
        ServicoPagamento servicoPagamento = new ServicoPagamento();
        ServicoEntrega servicoEntrega = new ServicoEntrega();

        servicoPedido.criarPedido("Ana", 250.0);
        servicoPagamento.processarPagamento(250.0, "PIX");
        servicoEntrega.despacharPedido("PED-001");
    }
}
```

---

## Problemas do código atual

O código atual cria várias instâncias de `LoggerSistema`, mesmo que o logger represente um recurso que deveria ser compartilhado por todo o sistema.

Esse projeto apresenta alguns problemas:

1. **Falta de controle centralizado**: cada serviço cria seu próprio logger, dificultando mudanças globais no comportamento de log.

2. **Repetição de instanciação**: a criação de `new LoggerSistema()` aparece em várias classes.

3. **Dificuldade de configuração futura**: se o logger precisar escrever em arquivo, banco de dados ou serviço externo, cada instância pode precisar ser configurada separadamente.

4. **Possível inconsistência**: diferentes partes do sistema poderiam usar configurações diferentes de log sem necessidade.

5. **Maior acoplamento entre serviços e criação de objetos**: as classes de serviço sabem como criar o logger, quando deveriam apenas usá-lo.

---

## Por que utilizar Singleton neste cenário?

O padrão **Singleton** é adequado quando um recurso precisa ter uma única instância compartilhada no sistema. Neste cenário, o logger pode ser tratado como um serviço centralizado, usado por diferentes partes da aplicação.

Ao aplicar Singleton, a classe `LoggerSistema` controla sua própria instância e fornece um ponto global de acesso, como:

```java
LoggerSistema.getInstance().log("Mensagem de log");
```

Isso evita que cada classe crie seu próprio logger manualmente.

---

## Cuidados ao usar Singleton

Apesar de ser útil em alguns contextos, Singleton deve ser usado com cuidado.

Alguns riscos incluem:

1. **Estado global**: se a instância Singleton armazenar muitos dados mutáveis, o sistema pode se tornar difícil de entender e testar.

2. **Dificuldade em testes automatizados**: como a instância é global, pode ser mais difícil substituí-la por uma versão falsa ou simulada durante os testes.

3. **Acoplamento forte**: muitas classes podem ficar diretamente dependentes de `LoggerSistema.getInstance()`.

4. **Problemas de concorrência**: em sistemas com múltiplas threads, uma implementação simples de Singleton pode gerar problemas se duas threads tentarem criar a instância ao mesmo tempo.
