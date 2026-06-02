# Atividade — Criando Adapters para Integração de Produtos

## Objetivo

Nesta atividade, você deverá aplicar o padrão de projeto **Adapter** para integrar dois fornecedores externos de produtos a um sistema de loja virtual.

Diferentemente de uma conversão simples de dados, esta atividade exige que você:

- crie uma interface comum para produtos;
- adapte dois sistemas externos diferentes;
- converta dados entre formatos incompatíveis;
- calcule uma nova informação durante a adaptação;
- permita que a aplicação use diferentes fornecedores sem conhecer seus detalhes internos.

---

# 1. Contexto

Uma loja virtual está desenvolvendo um módulo para consultar produtos disponíveis em fornecedores externos.

A loja deseja trabalhar com produtos no seguinte formato:

```java
Produto
```

Cada produto da loja deve possuir:

- código;
- nome;
- preço final;
- quantidade em estoque;
- nome do fornecedor;
- disponibilidade.

A disponibilidade deve ser calculada da seguinte forma:

```text
produto disponível = quantidade em estoque maior que zero
```

O problema é que os fornecedores externos não entregam os dados nesse formato.

Cada fornecedor possui uma estrutura diferente.

---

# 2. Fornecedor A — Sistema Atacado Brasil

O primeiro fornecedor, chamado `AtacadoBrasilApi`, retorna os produtos como uma lista de vetores de texto:

```java
List<String[]>
```

Cada vetor possui os dados nesta ordem:

```text
codigo, nome, precoBase, estoque
```

Exemplo:

```java
{"A100", "Teclado Mecânico", "250.00", "12"}
```

O preço desse fornecedor já vem em reais e não precisa de conversão de moeda.

---

# 3. Fornecedor B — Sistema Global Market

O segundo fornecedor, chamado `GlobalMarketApi`, retorna os produtos como uma lista de objetos de outro tipo:

```java
List<ItemGlobal>
```

A classe `ItemGlobal` possui os seguintes atributos:

- `sku`;
- `description`;
- `priceInDollars`;
- `availableUnits`.

Exemplo:

```java
new ItemGlobal("G900", "Mouse Gamer", 40.0, 5)
```

O preço desse fornecedor vem em dólar.

Durante a adaptação, esse valor deve ser convertido para reais usando a cotação fixa:

```java
1 dólar = 5 reais
```

---

# 4. Problema de projeto

A aplicação da loja não deve depender diretamente de `AtacadoBrasilApi` nem de `GlobalMarketApi`.

O sistema deve usar apenas uma interface comum chamada:

```java
CatalogoProdutos
```

Essa interface deve retornar produtos no formato esperado pela aplicação:

```java
List<Produto> listarProdutos();
```

Para resolver a incompatibilidade entre os fornecedores externos e o sistema da loja, você deverá criar dois adapters:

```java
AtacadoBrasilAdapter
```

```java
GlobalMarketAdapter
```

Cada adapter será responsável por transformar os dados de um fornecedor externo em objetos `Produto`.

---

# 5. Estrutura sugerida do projeto

Organize o projeto da seguinte forma:

```text
src/
├── Main.java
├── adapter/
│   ├── AtacadoBrasilAdapter.java
│   └── GlobalMarketAdapter.java
├── domain/
│   ├── Produto.java
│   └── ItemGlobal.java
├── externo/
│   ├── AtacadoBrasilApi.java
│   └── GlobalMarketApi.java
└── repository/
    └── CatalogoProdutos.java
```

---

# 6. Classe `Produto`

Crie a classe `Produto` no pacote `domain`.

Essa é a classe que representa os produtos dentro do sistema da loja.

```java
package domain;

public class Produto {
    private String codigo;
    private String nome;
    private double precoFinal;
    private int quantidadeEstoque;
    private String fornecedor;
    private boolean disponivel;

    public Produto() {
    }

    public Produto(String codigo, String nome, double precoFinal, int quantidadeEstoque, String fornecedor, boolean disponivel) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoFinal = precoFinal;
        this.quantidadeEstoque = quantidadeEstoque;
        this.fornecedor = fornecedor;
        this.disponivel = disponivel;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", precoFinal=" + precoFinal +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", fornecedor='" + fornecedor + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}
```

---

# 7. Interface `CatalogoProdutos`

Crie a interface `CatalogoProdutos` no pacote `repository`.

Essa interface representa a forma como a aplicação deseja acessar produtos.

```java
package repository;

import domain.Produto;

import java.util.List;

public interface CatalogoProdutos {
    List<Produto> listarProdutos();
}
```

Essa interface será o **Target** do padrão Adapter.

O restante da aplicação deverá depender dessa interface, e não das APIs externas.

---

# 8. Sistema externo `AtacadoBrasilApi`

Crie a classe `AtacadoBrasilApi` no pacote `externo`.

Considere que essa classe representa uma API externa. Ela não deve ser alterada depois de criada.

```java
package externo;

import java.util.ArrayList;
import java.util.List;

public class AtacadoBrasilApi {

    public List<String[]> buscarProdutosAtacado() {
        List<String[]> produtos = new ArrayList<>();

        produtos.add(new String[]{"A100", "Teclado Mecânico", "250.00", "12"});
        produtos.add(new String[]{"A200", "Monitor 24 Polegadas", "899.90", "4"});
        produtos.add(new String[]{"A300", "Cadeira Gamer", "1200.00", "0"});

        return produtos;
    }
}
```

Essa API retorna:

```java
List<String[]>
```

Cada vetor segue a estrutura:

```text
codigo, nome, precoBase, estoque
```

---

# 9. Classe externa `ItemGlobal`

Crie a classe `ItemGlobal` no pacote `domain`.

Ela representa o formato usado pelo fornecedor internacional.

```java
package domain;

public class ItemGlobal {
    private String sku;
    private String description;
    private double priceInDollars;
    private int availableUnits;

    public ItemGlobal(String sku, String description, double priceInDollars, int availableUnits) {
        this.sku = sku;
        this.description = description;
        this.priceInDollars = priceInDollars;
        this.availableUnits = availableUnits;
    }

    public String getSku() {
        return sku;
    }

    public String getDescription() {
        return description;
    }

    public double getPriceInDollars() {
        return priceInDollars;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }
}
```

Essa classe possui nomes de atributos diferentes dos nomes usados em `Produto`.

Por exemplo:

| `ItemGlobal` | `Produto` |
|---|---|
| `sku` | `codigo` |
| `description` | `nome` |
| `priceInDollars` | `precoFinal` |
| `availableUnits` | `quantidadeEstoque` |

---

# 10. Sistema externo `GlobalMarketApi`

Crie a classe `GlobalMarketApi` no pacote `externo`.

Considere que essa classe também representa uma API externa. Ela não deve ser alterada depois de criada.

```java
package externo;

import domain.ItemGlobal;

import java.util.ArrayList;
import java.util.List;

public class GlobalMarketApi {

    public List<ItemGlobal> fetchAvailableItems() {
        List<ItemGlobal> items = new ArrayList<>();

        items.add(new ItemGlobal("G900", "Mouse Gamer", 40.0, 5));
        items.add(new ItemGlobal("G901", "Webcam Full HD", 35.0, 0));
        items.add(new ItemGlobal("G902", "Headset USB", 28.0, 10));

        return items;
    }
}
```

Essa API retorna:

```java
List<ItemGlobal>
```

O preço vem em dólar e deve ser convertido para reais dentro do adapter.

---

# 11. Classe `Main`

Crie a classe `Main`.

Ela deve demonstrar que a aplicação consegue usar os dois fornecedores através da mesma interface.

```java
import adapter.AtacadoBrasilAdapter;
import adapter.GlobalMarketAdapter;
import domain.Produto;
import externo.AtacadoBrasilApi;
import externo.GlobalMarketApi;
import repository.CatalogoProdutos;

public class Main {
    public static void main(String[] args) {
        CatalogoProdutos catalogoAtacadoBrasil =
                new AtacadoBrasilAdapter(new AtacadoBrasilApi());

        CatalogoProdutos catalogoGlobalMarket =
                new GlobalMarketAdapter(new GlobalMarketApi());

        System.out.println("Produtos do Atacado Brasil:");
        for (Produto produto : catalogoAtacadoBrasil.listarProdutos()) {
            System.out.println(produto);
        }

        System.out.println();

        System.out.println("Produtos do Global Market:");
        for (Produto produto : catalogoGlobalMarket.listarProdutos()) {
            System.out.println(produto);
        }
    }
}
```

Observe que a classe `Main` usa os dois fornecedores por meio do mesmo tipo:

```java
CatalogoProdutos
```

Isso significa que a aplicação não precisa conhecer os detalhes internos de cada API externa.

---

# 12. Resultado esperado

A saída deve ser semelhante a:

```text
Produtos do Atacado Brasil:
Produto{codigo='A100', nome='Teclado Mecânico', precoFinal=250.0, quantidadeEstoque=12, fornecedor='Atacado Brasil', disponivel=true}
Produto{codigo='A200', nome='Monitor 24 Polegadas', precoFinal=899.9, quantidadeEstoque=4, fornecedor='Atacado Brasil', disponivel=true}
Produto{codigo='A300', nome='Cadeira Gamer', precoFinal=1200.0, quantidadeEstoque=0, fornecedor='Atacado Brasil', disponivel=false}

Produtos do Global Market:
Produto{codigo='G900', nome='Mouse Gamer', precoFinal=200.0, quantidadeEstoque=5, fornecedor='Global Market', disponivel=true}
Produto{codigo='G901', nome='Webcam Full HD', precoFinal=175.0, quantidadeEstoque=0, fornecedor='Global Market', disponivel=false}
Produto{codigo='G902', nome='Headset USB', precoFinal=140.0, quantidadeEstoque=10, fornecedor='Global Market', disponivel=true}
```

---
