# Atividade 14 — Padrões de Projeto Strategy e Singleton

## 1. Refatoração de cálculo de desconto com Strategy

O sistema possui o seguinte método:

```java
public double calcularDesconto(String tipoCliente, double valorCompra) {
    if (tipoCliente.equals("COMUM")) {
        return valorCompra * 0.05;
    } else if (tipoCliente.equals("VIP")) {
        return valorCompra * 0.10;
    } else if (tipoCliente.equals("FUNCIONARIO")) {
        return valorCompra * 0.20;
    }
    return 0;
}
```

Refatore esse código utilizando o padrão **Strategy**.

---

## 2. Solução sem Strategy

Considere o seguinte código:

```java
public class CalculadoraImposto {
    public double calcular(String estado, double valor) {
        if (estado.equals("PR")) {
            return valor * 0.12;
        } else if (estado.equals("SP")) {
            return valor * 0.18;
        } else if (estado.equals("RJ")) {
            return valor * 0.20;
        }
        return valor * 0.10;
    }
}
```

Analise criticamente esse projeto e depois proponha uma nova implementação utilizando **Strategy**.

---

## 3. Sistema de notificações com Strategy

Uma aplicação precisa enviar notificações aos clientes por diferentes canais:

- e-mail;
- SMS;
- WhatsApp;
- push notification.

Cada canal possui uma forma diferente de envio.

Projete e implemente uma solução com **Strategy** para permitir que o canal de notificação seja escolhido em tempo de execução.

---

## 4. Singleton para gerenciamento de configurações

O sistema precisa acessar configurações globais, como:

- URL do banco de dados;
- nome da aplicação;
- ambiente de execução;
- chave de API.

Implemente uma classe `ConfiguracaoSistema` utilizando o padrão **Singleton**.

---

## 5. Singleton para serviço de log

Crie uma classe `LoggerSistema` usando **Singleton** para registrar mensagens importantes do sistema.

A classe deve permitir registrar mensagens como:

```java
LoggerSistema.getInstance().log("Pedido criado com sucesso");
LoggerSistema.getInstance().log("Pagamento aprovado");
LoggerSistema.getInstance().log("Erro ao calcular frete");
```


---

## 6. Integração entre Strategy e Singleton

Implemente um sistema no qual diferentes estratégias de desconto utilizem uma configuração global.

Exemplo:

- desconto para cliente comum;
- desconto para cliente VIP;
- desconto promocional;
- desconto máximo permitido configurado no sistema.

A configuração do desconto máximo deve ser acessada por uma classe Singleton chamada `ConfiguracaoDesconto`.

As estratégias devem consultar essa configuração antes de retornar o desconto final.

---

## 7. Substituição de condicionais por Strategy em um sistema de pagamento

Um sistema possui diferentes formas de pagamento:

- cartão de crédito;
- Pix;
- boleto;
- vale-presente.

Cada forma possui uma lógica diferente de processamento.

Projete uma solução com **Strategy** para representar as formas de pagamento. Em seguida, crie uma classe `ProcessadorPagamento` que receba a estratégia escolhida e execute o pagamento.

---

## 8. Projeto final integrado

Desenvolva um pequeno sistema de pedidos que utilize **Strategy** e **Singleton** de forma integrada.

O sistema deve conter:

- uma classe `Pedido`;
- estratégias de frete;
- estratégias de desconto;
- um serviço de log Singleton;
- uma configuração global Singleton;
- uma classe principal demonstrando o funcionamento.

O sistema deve permitir:

- criar um pedido com valor inicial;
- escolher uma estratégia de desconto;
- escolher uma estratégia de frete;
- calcular o valor final;
- registrar no log as principais operações realizadas;
- acessar configurações globais do sistema.

---

## 9. Estratégias de pontuação para programa de fidelidade

Uma loja deseja criar um programa de fidelidade. Cada cliente acumula pontos de acordo com uma regra diferente:

- cliente comum: 1 ponto a cada R$ 10,00;
- cliente prata: 1 ponto a cada R$ 5,00;
- cliente ouro: 1 ponto a cada R$ 2,00;
- cliente diamante: pontuação dobrada em compras acima de R$ 500,00.

Implemente essa funcionalidade utilizando **Strategy**.

---

## 10. Escolha dinâmica de algoritmo de recomendação

Um sistema de e-commerce precisa recomendar produtos usando diferentes algoritmos:

- recomendação por produtos mais vendidos;
- recomendação por histórico de compras;
- recomendação por categoria favorita;
- recomendação por produtos similares.

Cada algoritmo possui uma lógica diferente.

Projete uma solução com **Strategy** para permitir que o algoritmo de recomendação seja escolhido dinamicamente.

---

## 11. Singleton para conexão simulada com banco de dados

Um sistema precisa acessar uma conexão com banco de dados. Para simplificar o exercício, a conexão não precisa ser real, mas deve ser representada por uma classe que simule as operações de conectar, desconectar e executar comandos.

Implemente uma classe `ConexaoBanco` usando **Singleton**.

---

## 12. Refatoração de relatório com Strategy e Logger Singleton

Um sistema gera relatórios em diferentes formatos. A versão atual utiliza o seguinte código:

```java
public class GeradorRelatorio {
    public void gerar(String formato, String conteudo) {
        if (formato.equals("PDF")) {
            System.out.println("Gerando relatório em PDF: " + conteudo);
        } else if (formato.equals("CSV")) {
            System.out.println("Gerando relatório em CSV: " + conteudo);
        } else if (formato.equals("HTML")) {
            System.out.println("Gerando relatório em HTML: " + conteudo);
        }
    }
}
```

Refatore essa solução combinando **Strategy** e **Singleton**.

Sua implementação deve conter:

- estratégias concretas para PDF, CSV e HTML;
- uma classe `GeradorRelatorio` que use a estratégia escolhida;
- um `LoggerSistema` Singleton para registrar qual relatório foi gerado;
- um exemplo de uso no `main`.

---

# Entrega