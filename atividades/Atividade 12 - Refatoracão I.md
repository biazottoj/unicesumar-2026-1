# Lista de 20 métodos para refatoração em Java

Esta lista contém 20 métodos simples para os alunos praticarem quatro técnicas de refatoração:

1. Extract Method
2. Extract Variable
3. Inline Method
4. Inline Temp

A ideia é refatorar os métodos mantendo o mesmo comportamento do código.

---

# 1. Extract Method

Use **Extract Method** quando um método faz muitas coisas ao mesmo tempo.  
O objetivo é dividir o código em métodos menores, com nomes que representem melhor cada responsabilidade.

---

## 1.1. Emitir recibo de compra

```java
public void emitirRecibo(String cliente, int quantidade, double precoUnitario) {
    double subtotal = quantidade * precoUnitario;
    double imposto = subtotal * 0.10;
    double total = subtotal + imposto;

    System.out.println("Cliente: " + cliente);
    System.out.println("Quantidade: " + quantidade);
    System.out.println("Preço unitário: R$ " + precoUnitario);
    System.out.println("Subtotal: R$ " + subtotal);
    System.out.println("Imposto: R$ " + imposto);
    System.out.println("Total: R$ " + total);

    if (total > 500) {
        System.out.println("Compra de alto valor");
    } else {
        System.out.println("Compra comum");
    }
}
```

---

## 1.2. Processar matrícula

```java
public void processarMatricula(String aluno, int disciplinas, boolean bolsista) {
    double valorBase = disciplinas * 120.0;

    double desconto = 0;
    if (bolsista) {
        desconto = valorBase * 0.4;
    }

    double valorFinal = valorBase - desconto;

    System.out.println("Aluno: " + aluno);
    System.out.println("Disciplinas: " + disciplinas);
    System.out.println("Valor base: R$ " + valorBase);
    System.out.println("Desconto: R$ " + desconto);
    System.out.println("Valor final: R$ " + valorFinal);

    if (valorFinal == 0) {
        System.out.println("Matrícula gratuita");
    } else {
        System.out.println("Pagamento necessário");
    }
}
```

---

## 1.3. Gerar relatório de funcionário

```java
public void gerarRelatorioFuncionario(String nome, int horas, double valorHora) {
    double salarioBruto = horas * valorHora;
    double desconto = salarioBruto * 0.08;
    double salarioLiquido = salarioBruto - desconto;

    System.out.println("Funcionário: " + nome);
    System.out.println("Horas trabalhadas: " + horas);
    System.out.println("Valor da hora: R$ " + valorHora);
    System.out.println("Salário bruto: R$ " + salarioBruto);
    System.out.println("Desconto: R$ " + desconto);
    System.out.println("Salário líquido: R$ " + salarioLiquido);

    if (salarioLiquido >= 3000) {
        System.out.println("Faixa salarial alta");
    } else {
        System.out.println("Faixa salarial comum");
    }
}
```

---

## 1.4. Finalizar venda online

```java
public void finalizarVenda(String produto, int quantidade, double preco, boolean entregaExpressa) {
    double subtotal = quantidade * preco;

    double frete = 20;
    if (entregaExpressa) {
        frete = 40;
    }

    double total = subtotal + frete;

    System.out.println("Produto: " + produto);
    System.out.println("Quantidade: " + quantidade);
    System.out.println("Preço: R$ " + preco);
    System.out.println("Frete: R$ " + frete);
    System.out.println("Total: R$ " + total);

    if (entregaExpressa) {
        System.out.println("Entrega expressa selecionada");
    } else {
        System.out.println("Entrega comum selecionada");
    }
}
```

---

## 1.5. Avaliar pedido de empréstimo

```java
public void avaliarEmprestimo(String cliente, double renda, double valorSolicitado) {
    double limite = renda * 0.3;
    double parcela = valorSolicitado / 12;

    System.out.println("Cliente: " + cliente);
    System.out.println("Renda mensal: R$ " + renda);
    System.out.println("Valor solicitado: R$ " + valorSolicitado);
    System.out.println("Parcela estimada: R$ " + parcela);
    System.out.println("Limite recomendado: R$ " + limite);

    if (parcela <= limite) {
        System.out.println("Empréstimo aprovado");
    } else {
        System.out.println("Empréstimo negado");
    }
}
```

---

# 2. Extract Variable

Use **Extract Variable** quando uma expressão é difícil de entender diretamente.  
O objetivo é criar variáveis com nomes explicativos para partes importantes da expressão.

---

## 2.1. Verificar aprovação em disciplina

```java
public void verificarAprovacao(double notaFinal, int frequencia, boolean fezRecuperacao) {
    if ((notaFinal >= 6.0 && frequencia >= 75) || (fezRecuperacao && notaFinal >= 5.0 && frequencia >= 80)) {
        System.out.println("Aluno aprovado");
    } else {
        System.out.println("Aluno reprovado");
    }
}
```

---

## 2.2. Verificar acesso ao sistema

```java
public void verificarAcesso(boolean usuarioAtivo, boolean senhaCorreta, boolean admin, boolean bloqueado) {
    if ((usuarioAtivo && senhaCorreta && !bloqueado) || (admin && senhaCorreta)) {
        System.out.println("Acesso liberado");
    } else {
        System.out.println("Acesso negado");
    }
}
```

---

## 2.3. Avaliar entrega de trabalho

```java
public void avaliarEntrega(boolean entregouNoPrazo, double nota, boolean justificativaAceita) {
    if ((entregouNoPrazo && nota >= 6.0) || (!entregouNoPrazo && justificativaAceita && nota >= 7.0)) {
        System.out.println("Trabalho aceito");
    } else {
        System.out.println("Trabalho recusado");
    }
}
```

---

## 2.4. Verificar desconto em compra

```java
public void verificarDesconto(double totalCompra, boolean clientePremium, int quantidadeItens) {
    if ((totalCompra >= 300 && quantidadeItens >= 3) || (clientePremium && totalCompra >= 150)) {
        System.out.println("Desconto aplicado");
    } else {
        System.out.println("Sem desconto");
    }
}
```

---

## 2.5. Verificar disponibilidade de reserva

```java
public void verificarReserva(boolean salaDisponivel, int participantes, boolean eventoInstitucional) {
    if ((salaDisponivel && participantes <= 40) || (eventoInstitucional && participantes <= 60)) {
        System.out.println("Reserva permitida");
    } else {
        System.out.println("Reserva recusada");
    }
}
```

---

# 3. Inline Method

Use **Inline Method** quando um método é simples demais e sua existência não melhora a leitura do código.  
O objetivo é substituir a chamada pelo conteúdo do próprio método.

---

## 3.1. Validar idade mínima

```java
public void cadastrarParticipante(int idade) {
    if (temIdadeMinima(idade)) {
        System.out.println("Cadastro permitido");
    } else {
        System.out.println("Cadastro negado");
    }
}

private boolean temIdadeMinima(int idade) {
    return idade >= 18;
}
```

---

## 3.2. Verificar valor mínimo de pedido

```java
public void confirmarPedido(double total) {
    if (atingiuValorMinimo(total)) {
        System.out.println("Pedido confirmado");
    } else {
        System.out.println("Valor mínimo não atingido");
    }
}

private boolean atingiuValorMinimo(double total) {
    return total >= 50;
}
```

---

## 3.3. Verificar nota suficiente

```java
public void publicarResultado(double nota) {
    if (notaSuficiente(nota)) {
        System.out.println("Resultado: aprovado");
    } else {
        System.out.println("Resultado: reprovado");
    }
}

private boolean notaSuficiente(double nota) {
    return nota >= 6.0;
}
```

---

## 3.4. Verificar limite de participantes

```java
public void inscreverEmEvento(int participantes) {
    if (dentroDoLimite(participantes)) {
        System.out.println("Inscrição realizada");
    } else {
        System.out.println("Evento lotado");
    }
}

private boolean dentroDoLimite(int participantes) {
    return participantes <= 100;
}
```

---

## 3.5. Verificar frete grátis

```java
public void exibirTipoFrete(double totalCompra) {
    if (possuiFreteGratis(totalCompra)) {
        System.out.println("Frete grátis");
    } else {
        System.out.println("Frete pago");
    }
}

private boolean possuiFreteGratis(double totalCompra) {
    return totalCompra >= 200;
}
```

---

# 4. Inline Temp

Use **Inline Temp** quando uma variável temporária apenas guarda uma expressão simples e é usada uma única vez.  
O objetivo é remover a variável e usar a expressão diretamente.

---

## 4.1. Verificar maioridade

```java
public void verificarMaioridade(int idade) {
    boolean maiorDeIdade = idade >= 18;

    if (maiorDeIdade) {
        System.out.println("Pessoa maior de idade");
    } else {
        System.out.println("Pessoa menor de idade");
    }
}
```

---

## 4.2. Verificar média final

```java
public void verificarMedia(double prova1, double prova2) {
    double media = (prova1 + prova2) / 2;

    if (media >= 6.0) {
        System.out.println("Aluno aprovado");
    } else {
        System.out.println("Aluno reprovado");
    }
}
```

---

## 4.3. Verificar estoque baixo

```java
public void verificarEstoque(int quantidade) {
    boolean estoqueBaixo = quantidade < 10;

    if (estoqueBaixo) {
        System.out.println("Estoque baixo");
    } else {
        System.out.println("Estoque suficiente");
    }
}
```

---

## 4.4. Verificar bônus salarial

```java
public void verificarBonus(double salario) {
    boolean recebeBonus = salario < 2500;

    if (recebeBonus) {
        System.out.println("Funcionário recebe bônus");
    } else {
        System.out.println("Funcionário não recebe bônus");
    }
}
```

---

## 4.5. Verificar aprovação de projeto

```java
public void verificarProjeto(double notaDocumento, double notaApresentacao) {
    double notaFinal = (notaDocumento * 0.6) + (notaApresentacao * 0.4);

    if (notaFinal >= 7.0) {
        System.out.println("Projeto aprovado");
    } else {
        System.out.println("Projeto precisa de revisão");
    }
}
```


---

# Link para entrega

## https://forms.gle/zfrCbSKdo36h3EPw6
