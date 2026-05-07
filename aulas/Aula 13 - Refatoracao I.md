# Retaforacão

### Extract Method

Use Extract Method quando um método faz muitas coisas ao mesmo tempo. A ideia é separar partes do código em métodos menores, com nomes que expliquem melhor cada responsabilidade.

```java
public void emitirResumoPedido(String cliente, int quantidade, double precoUnitario, boolean cupomAplicado) {
    double subtotal = quantidade * precoUnitario;

    double desconto = 0;
    if (cupomAplicado) {
        desconto = subtotal * 0.15;
    }

    double total = subtotal - desconto;

    System.out.println("Cliente: " + cliente);
    System.out.println("Quantidade: " + quantidade);
    System.out.println("Preço unitário: R$ " + precoUnitario);
    System.out.println("Subtotal: R$ " + subtotal);
    System.out.println("Desconto: R$ " + desconto);
    System.out.println("Total: R$ " + total);

    if (total >= 100) {
        System.out.println("Pedido com frete grátis");
    } else {
        System.out.println("Pedido com frete pago");
    }
}
```


## Extract Variable
Use **Extract Variable** quando uma expressão é difícil de entender diretamente. A ideia é criar variáveis com nomes explicativos para deixar a regra mais clara.

```java
public void verificarAcesso(boolean usuarioAtivo,
                            boolean senhaCorreta,
                            boolean administrador,
                            boolean bloqueado,
                            boolean acessoInterno) {
    if ((usuarioAtivo && senhaCorreta && !bloqueado) || (administrador && senhaCorreta && acessoInterno)) {
        System.out.println("Acesso permitido");
    } else {
        System.out.println("Acesso negado");
    }
}
```


## Inline Method
Use **Inline Method** quando um método é simples demais e sua existência não melhora a leitura do código.

```java
public void liberarCertificado(double notaFinal) {
    if (notaMinimaAtingida(notaFinal)) {
        System.out.println("Certificado liberado");
    } else {
        System.out.println("Certificado não liberado");
    }
}

private boolean notaMinimaAtingida(double notaFinal) {
    return notaFinal >= 6.0;
}
```

## Inline Temp
Use **Inline Temp** quando uma variável temporária apenas guarda uma expressão simples e é usada uma única vez.

```java
public void verificarDesconto(double totalCompra) {
    boolean possuiDesconto = totalCompra >= 200;

    if (possuiDesconto) {
        System.out.println("Desconto aplicado");
    } else {
        System.out.println("Sem desconto");
    }
}
```