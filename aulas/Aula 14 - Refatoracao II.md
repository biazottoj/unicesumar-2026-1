# Refatoração II

Este material apresenta exemplos com **código antes** e **código refatorado** para explicar quatro técnicas de refatoração:

1. Introduce Local Extension
2. Move Method
3. Move Field
4. Extract Class

A ideia é usar cada exemplo em sala para mostrar como identificar o problema no código e como aplicar a refatoração sem alterar o comportamento esperado do sistema.

---

# 1. Introduce Local Extension

Use **Introduce Local Extension** quando você precisa adicionar comportamentos a uma classe que **não pode alterar diretamente**, por exemplo uma classe da linguagem, de uma biblioteca externa ou de um framework.

Neste exemplo, queremos adicionar comportamentos úteis a `LocalDate`, mas não podemos modificar a classe `LocalDate`.

---

## Código antes da refatoração

```java
import java.time.DayOfWeek;
import java.time.LocalDate;

public class GeradorBoleto {

    public void gerarBoleto(String cliente, LocalDate dataVencimento) {
        System.out.println("Cliente: " + cliente);

        String dataFormatada = dataVencimento.getDayOfMonth() + "/"
                + dataVencimento.getMonthValue() + "/"
                + dataVencimento.getYear();

        System.out.println("Vencimento: " + dataFormatada);

        if (dataVencimento.getDayOfWeek() == DayOfWeek.SATURDAY
                || dataVencimento.getDayOfWeek() == DayOfWeek.SUNDAY) {
            System.out.println("Atenção: vencimento em final de semana");
        } else {
            System.out.println("Vencimento em dia útil");
        }
    }
}
```

---

## Problema

A classe `GeradorBoleto` está fazendo operações que poderiam pertencer a uma versão estendida de `LocalDate`, como:

```java
dataVencimento.getDayOfWeek() == DayOfWeek.SATURDAY
```

e:

```java
dataVencimento.getDayOfMonth() + "/" 
        + dataVencimento.getMonthValue() + "/" 
        + dataVencimento.getYear()
```

O problema é que não podemos alterar diretamente `LocalDate`, pois ela pertence à biblioteca padrão do Java.

---

## Código refatorado

Criamos uma classe local que encapsula `LocalDate` e adiciona os comportamentos necessários.

```java
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DataEstendida {

    private LocalDate data;

    public DataEstendida(LocalDate data) {
        this.data = data;
    }

    public boolean ehFinalDeSemana() {
        return data.getDayOfWeek() == DayOfWeek.SATURDAY
                || data.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public String formatarParaPadraoBrasileiro() {
        return data.getDayOfMonth() + "/"
                + data.getMonthValue() + "/"
                + data.getYear();
    }

    public LocalDate getDataOriginal() {
        return data;
    }
}
```

Agora a classe `GeradorBoleto` fica mais simples:

```java
import java.time.LocalDate;

public class GeradorBoleto {

    public void gerarBoleto(String cliente, LocalDate dataVencimento) {
        DataEstendida vencimento = new DataEstendida(dataVencimento);

        System.out.println("Cliente: " + cliente);
        System.out.println("Vencimento: " + vencimento.formatarParaPadraoBrasileiro());

        if (vencimento.ehFinalDeSemana()) {
            System.out.println("Atenção: vencimento em final de semana");
        } else {
            System.out.println("Vencimento em dia útil");
        }
    }
}
```

---

# 2. Move Method

Use **Move Method** quando um método está em uma classe, mas usa muito mais dados de outra classe. Nesse caso, o método provavelmente pertence à outra classe.

---

## Código antes da refatoração

```java
public class Pedido {

    private double valorTotal;

    public Pedido(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double calcularDescontoCliente(Cliente cliente) {
        if (cliente.isVip()) {
            return cliente.getTotalComprasAnteriores() * 0.05;
        }

        if (cliente.getTotalComprasAnteriores() > 1000) {
            return cliente.getTotalComprasAnteriores() * 0.03;
        }

        return 0;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
```

```java
public class Cliente {

    private boolean vip;
    private double totalComprasAnteriores;

    public Cliente(boolean vip, double totalComprasAnteriores) {
        this.vip = vip;
        this.totalComprasAnteriores = totalComprasAnteriores;
    }

    public boolean isVip() {
        return vip;
    }

    public double getTotalComprasAnteriores() {
        return totalComprasAnteriores;
    }
}
```

---

## Problema

O método `calcularDescontoCliente` está dentro de `Pedido`, mas usa quase somente dados de `Cliente`:

```java
cliente.isVip()
cliente.getTotalComprasAnteriores()
```

Isso indica que o método provavelmente deveria estar em `Cliente`.

---

## Código refatorado

Movemos o método para a classe `Cliente`.

```java
public class Cliente {

    private boolean vip;
    private double totalComprasAnteriores;

    public Cliente(boolean vip, double totalComprasAnteriores) {
        this.vip = vip;
        this.totalComprasAnteriores = totalComprasAnteriores;
    }

    public double calcularDesconto() {
        if (vip) {
            return totalComprasAnteriores * 0.05;
        }

        if (totalComprasAnteriores > 1000) {
            return totalComprasAnteriores * 0.03;
        }

        return 0;
    }

    public boolean isVip() {
        return vip;
    }

    public double getTotalComprasAnteriores() {
        return totalComprasAnteriores;
    }
}
```

A classe `Pedido` fica mais simples:

```java
public class Pedido {

    private double valorTotal;

    public Pedido(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double calcularValorFinal(Cliente cliente) {
        return valorTotal - cliente.calcularDesconto();
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
```

---

# 3. Move Field

Use **Move Field** quando um atributo está em uma classe, mas conceitualmente pertence a outra.

---

## Código antes da refatoração

```java
public class Produto {

    private String nome;
    private double preco;
    private String cepEntrega;

    public Produto(String nome, double preco, String cepEntrega) {
        this.nome = nome;
        this.preco = preco;
        this.cepEntrega = cepEntrega;
    }

    public String getCepEntrega() {
        return cepEntrega;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }
}
```

```java
public class Pedido {

    private Produto produto;
    private int quantidade;

    public Pedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void imprimirResumo() {
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("CEP de entrega: " + produto.getCepEntrega());
    }
}
```

---

## Problema

O atributo `cepEntrega` está dentro de `Produto`.

Mas o CEP de entrega não é uma característica do produto. O mesmo produto pode ser entregue em vários endereços diferentes.

Portanto, `cepEntrega` pertence ao `Pedido`, não ao `Produto`.

---

## Código refatorado

Movemos o campo `cepEntrega` para `Pedido`.

```java
public class Produto {

    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }
}
```

```java
public class Pedido {

    private Produto produto;
    private int quantidade;
    private String cepEntrega;

    public Pedido(Produto produto, int quantidade, String cepEntrega) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.cepEntrega = cepEntrega;
    }

    public void imprimirResumo() {
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("CEP de entrega: " + cepEntrega);
    }
}
```

---

# 4. Extract Class

Use **Extract Class** quando uma classe possui responsabilidades demais ou muitos atributos que formam um conceito separado.

---

## Código antes da refatoração

```java
public class Aluno {

    private String nome;
    private String ra;

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Aluno(String nome, String ra, String rua, String numero,
                 String bairro, String cidade, String estado, String cep) {
        this.nome = nome;
        this.ra = ra;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public void imprimirDados() {
        System.out.println("Aluno: " + nome);
        System.out.println("RA: " + ra);
        System.out.println("Endereço: " + rua + ", " + numero + " - " + bairro);
        System.out.println("Cidade: " + cidade + " - " + estado);
        System.out.println("CEP: " + cep);
    }

    public String getEnderecoCompleto() {
        return rua + ", " + numero + " - " + bairro + ", " 
                + cidade + " - " + estado + ", " + cep;
    }
}
```

---

## Problema

A classe `Aluno` mistura duas responsabilidades:

1. dados acadêmicos do aluno;
2. dados de endereço.

Os atributos abaixo formam um conceito separado:

```java
private String rua;
private String numero;
private String bairro;
private String cidade;
private String estado;
private String cep;
```

Esse conceito pode virar uma nova classe: `Endereco`.

---

## Código refatorado

Extraímos a classe `Endereco`.

```java
public class Endereco {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String rua, String numero, String bairro,
                    String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getEnderecoCompleto() {
        return rua + ", " + numero + " - " + bairro + ", "
                + cidade + " - " + estado + ", " + cep;
    }

    public void imprimirEndereco() {
        System.out.println("Endereço: " + rua + ", " + numero + " - " + bairro);
        System.out.println("Cidade: " + cidade + " - " + estado);
        System.out.println("CEP: " + cep);
    }
}
```

Agora `Aluno` fica mais focada:

```java
public class Aluno {

    private String nome;
    private String ra;
    private Endereco endereco;

    public Aluno(String nome, String ra, Endereco endereco) {
        this.nome = nome;
        this.ra = ra;
        this.endereco = endereco;
    }

    public void imprimirDados() {
        System.out.println("Aluno: " + nome);
        System.out.println("RA: " + ra);
        endereco.imprimirEndereco();
    }

    public String getEnderecoCompleto() {
        return endereco.getEnderecoCompleto();
    }
}
```


---

# Resumo das técnicas

| Técnica | Problema no código | Solução |
|---|---|---|
| Introduce Local Extension | Preciso adicionar comportamento a uma classe que não posso modificar | Criar uma classe local que estende ou encapsula a classe original |
| Move Method | Um método usa mais dados de outra classe do que da própria classe | Mover o método para a classe que possui os dados |
| Move Field | Um atributo está na classe errada | Mover o atributo para a classe onde ele faz mais sentido |
| Extract Class | Uma classe tem responsabilidades demais | Criar uma nova classe para representar parte dessas responsabilidades |