# Lista de Revisão — Manutenção de Software

Esta lista reúne **30 atividades de revisão** baseadas nos conteúdos trabalhados nas atividades anteriores da disciplina: código limpo, legibilidade, comentários, formatação, funções, refatoração, Strategy, Singleton e Adapter.

---

## Parte 1 — Código Limpo, Legibilidade e Comentários

### 1. Conceito de código limpo

Explique, com suas palavras, o que é **código limpo** e por que ele é importante para a manutenção de software.

Sua resposta deve abordar:

- facilidade de leitura;
- redução de erros;
- facilidade de evolução;
- colaboração entre desenvolvedores.

---

### 2. Avaliação de nomes de variáveis

Analise os nomes abaixo:

```java
int x;
int dias;
int diasDesdeUltimoLogin;
double v;
double valorTotalPedido;
```

Responda:

a) Quais nomes são mais legíveis?  
b) Quais nomes são vagos?  
c) O que um bom nome deve comunicar ao leitor do código?

---

### 3. Melhorando nomes de parâmetros

Analise o método abaixo:

```java
public void copiar(String a, String b) {
    System.out.println("Copiando de " + a + " para " + b);
}
```

Faça o que se pede:

a) Identifique o problema de legibilidade.  
b) Renomeie os parâmetros.  
c) Explique por que a nova versão ficou mais clara.

---

### 4. Comentário redundante

Analise o código:

```java
// calcula o total do pedido
public double calcularTotalPedido(double subtotal, double frete) {
    return subtotal + frete;
}
```

Faça o que se pede:

a) Explique por que o comentário pode ser considerado redundante.  
b) Reescreva o código sem o comentário.  
c) Explique quando um comentário realmente seria útil.

---

### 5. Comentário desatualizado

Analise o código:

```java
// aplica desconto de 10% para compras acima de R$ 100
public double calcularDesconto(double valorCompra) {
    if (valorCompra > 200) {
        return valorCompra * 0.15;
    }
    return 0;
}
```

Faça o que se pede:

a) Identifique a inconsistência entre comentário e código.  
b) Explique por que isso prejudica a manutenção.  
c) Reescreva o código de forma mais confiável e legível.

---

### 6. Código comentado

Analise o trecho abaixo:

```java
public void finalizarVenda(Venda venda) {
    vendaRepository.salvar(venda);
    // emailService.enviarConfirmacao(venda);
    notaFiscalService.emitir(venda);
}
```

Responda:

a) Por que código comentado pode confundir outro desenvolvedor?  
b) O que deveria ser usado no lugar de manter código antigo comentado?  
c) Reescreva o método da forma que você considera mais adequada.

---

## Parte 2 — Formatação de Código

### 7. Formatação vertical

Reorganize a classe abaixo para que a leitura siga do método mais geral para os métodos auxiliares.

```java
public class PedidoService {
    private void imprimirResumo(String cliente, double total) {
        System.out.println(cliente + ": " + total);
    }

    private double calcularTotal(double subtotal, double frete) {
        return subtotal + frete;
    }

    public void fecharPedido(String cliente, double subtotal, double frete) {
        double total = calcularTotal(subtotal, frete);
        imprimirResumo(cliente, total);
    }
}
```

Ao final, explique o que foi melhorado.

---

### 8. Separação de conceitos

Reescreva o método abaixo usando linhas em branco para separar etapas diferentes do raciocínio.

```java
public void cadastrarProduto(String nome, double preco) {
    String nomeFormatado = nome.trim().toUpperCase();
    boolean precoValido = preco > 0;
    if (!precoValido) {
        System.out.println("Preço inválido");
        return;
    }
    System.out.println("Produto: " + nomeFormatado);
    System.out.println("Preço: " + preco);
    System.out.println("Cadastro finalizado");
}
```

---

### 9. Formatação horizontal

Reescreva o código abaixo para reduzir linhas longas.

```java
public void gerarRelatorio(String cliente, String email, double total, boolean premium, boolean pagamentoEmDia) {
    if (premium && pagamentoEmDia && total > 1000) {
        System.out.println("Relatório especial para " + cliente + " enviado para " + email + " com total de " + total);
    }
}
```

---

### 10. Identação

Corrija a identação do código abaixo.

```java
public void verificarAcesso(boolean ativo, boolean admin) {
if (ativo) {
if (admin) {
System.out.println("Acesso administrativo");
} else {
System.out.println("Acesso comum");
}
} else {
System.out.println("Usuário inativo");
}
}
```

---

### 11. Afinidade conceitual

Reorganize os métodos da classe abaixo agrupando responsabilidades semelhantes.

```java
public class UsuarioUtils {
    public void enviarEmail(String email) {
        System.out.println("Enviando e-mail para " + email);
    }

    public String formatarCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public void enviarNotificacao(String mensagem) {
        System.out.println(mensagem);
    }

    public String formatarNome(String nome) {
        return nome.trim().toUpperCase();
    }
}
```

Explique qual critério foi usado para reorganizar a classe.

---

## Parte 3 — Código Limpo: Funções

### 12. Função com muitas responsabilidades

Analise a função abaixo:

```java
public void finalizarPedido(Cliente cliente, Carrinho carrinho) {
    double total = 0;

    for (Item item : carrinho.getItens()) {
        total += item.getPreco() * item.getQuantidade();
    }

    Pedido pedido = new Pedido(cliente, carrinho.getItens(), total);
    pedidoRepository.salvar(pedido);

    emailService.enviar(cliente.getEmail(), "Pedido confirmado");
}
```

Faça o que se pede:

a) Identifique as responsabilidades da função.  
b) Explique por que isso prejudica a manutenção.  
c) Reescreva dividindo a lógica em funções menores.

---

### 13. Nome pouco descritivo

Analise o método:

```java
public boolean verificar(Usuario usuario) {
    return usuario.getIdade() >= 18 && usuario.isAtivo();
}
```

Faça o que se pede:

a) Explique o problema do nome `verificar`.  
b) Proponha um nome mais descritivo.  
c) Reescreva o método com o novo nome.

---

### 14. Função com muitos argumentos

Analise a função:

```java
public void cadastrarAluno(
    String nome,
    String cpf,
    String email,
    String telefone,
    String curso,
    int periodo
) {
    Aluno aluno = new Aluno();
    aluno.setNome(nome);
    aluno.setCpf(cpf);
    aluno.setEmail(email);
    aluno.setTelefone(telefone);
    aluno.setCurso(curso);
    aluno.setPeriodo(periodo);

    alunoRepository.salvar(aluno);
}
```

Faça o que se pede:

a) Explique por que muitos argumentos dificultam o uso da função.  
b) Crie uma classe ou objeto para agrupar os dados de cadastro.  
c) Reescreva a função recebendo esse objeto como parâmetro.

---

### 15. Efeito colateral escondido

Analise a função:

```java
public boolean usuarioExiste(String email) {
    Usuario usuario = usuarioRepository.buscarPorEmail(email);

    if (usuario == null) {
        usuarioRepository.salvar(new Usuario(email));
        return false;
    }

    return true;
}
```

Responda:

a) O que o nome da função sugere que ela faz?  
b) O que ela faz além disso?  
c) Por que isso é um efeito colateral?  
d) Reescreva separando verificação e criação do usuário.

---

### 16. Condicionais aninhadas

Reescreva a função abaixo usando retornos antecipados.

```java
public boolean podeAcessarSistema(Usuario usuario) {
    if (usuario != null) {
        if (usuario.isAtivo()) {
            if (!usuario.isBloqueado()) {
                if (usuario.temPermissao("SISTEMA")) {
                    return true;
                }
            }
        }
    }

    return false;
}
```

Depois, crie uma segunda versão usando funções auxiliares com nomes descritivos.

---

### 17. Argumento booleano

Analise o método:

```java
public void processarPedido(Pedido pedido, boolean enviarEmail) {
    pedidoRepository.salvar(pedido);

    if (enviarEmail) {
        emailService.enviarConfirmacao(pedido);
    }
}
```

Responda:

a) O argumento `enviarEmail` deixa a função mais clara ou mais confusa?  
b) Que comportamento diferente ele controla?  
c) Proponha uma alternativa sem argumento booleano.

---

## Parte 4 — Refatoração Básica

### 18. Extract Method

Refatore o método abaixo usando **Extract Method**.

```java
public void emitirRecibo(String cliente, int quantidade, double precoUnitario) {
    double subtotal = quantidade * precoUnitario;
    double imposto = subtotal * 0.10;
    double total = subtotal + imposto;

    System.out.println("Cliente: " + cliente);
    System.out.println("Quantidade: " + quantidade);
    System.out.println("Subtotal: " + subtotal);
    System.out.println("Imposto: " + imposto);
    System.out.println("Total: " + total);

    if (total > 500) {
        System.out.println("Compra de alto valor");
    }
}
```

Extraia métodos para:

- calcular subtotal;
- calcular imposto;
- imprimir recibo;
- verificar compra de alto valor.

---

### 19. Extract Variable

Refatore o código abaixo usando **Extract Variable**.

```java
public void verificarAprovacao(double notaFinal, int frequencia, boolean fezRecuperacao) {
    if ((notaFinal >= 6.0 && frequencia >= 75) || (fezRecuperacao && notaFinal >= 5.0 && frequencia >= 80)) {
        System.out.println("Aluno aprovado");
    } else {
        System.out.println("Aluno reprovado");
    }
}
```

Crie variáveis com nomes que expliquem cada condição importante.

---

### 20. Inline Method

Analise o código:

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

Faça o que se pede:

a) Avalie se o método auxiliar realmente melhora a leitura.  
b) Aplique **Inline Method**, caso considere adequado.  
c) Justifique sua decisão.

---

### 21. Inline Temp

Refatore o código usando **Inline Temp**, se considerar adequado.

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

Explique se a variável temporária ajudava ou atrapalhava a leitura.

---

## Parte 5 — Refatoração Orientada a Objetos

### 22. Move Method

Analise o cenário:

A classe `PedidoService` possui o método abaixo:

```java
public double calcularTotalPedido(Pedido pedido) {
    return pedido.getSubtotal() + pedido.getFrete() - pedido.getDesconto();
}
```

O método usa apenas dados da classe `Pedido`.

Faça o que se pede:

a) Explique por que esse método pode estar na classe errada.  
b) Mova o método para a classe `Pedido`.  
c) Ajuste a chamada em `PedidoService`.

---

### 23. Move Field

Analise o cenário:

A classe `Produto` possui os campos:

```java
private LocalDate dataCompra;
private LocalDate dataValidadeGarantia;
```

Esses dados não descrevem o produto em geral, mas sim uma compra específica.

Faça o que se pede:

a) Explique por que esses campos podem estar na classe errada.  
b) Crie uma classe `ItemPedido` ou `CompraProduto`.  
c) Mova os campos para a nova classe.

---

### 24. Extract Class

Analise a classe abaixo:

```java
public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private String banco;
    private String agencia;
    private String conta;
    private String metodoPagamentoPreferencial;
}
```

Faça o que se pede:

a) Identifique o grupo de atributos que forma outro conceito.  
b) Crie uma classe `DadosCobranca`.  
c) Atualize `Cliente` para usar essa nova classe.

---

### 25. Introduce Local Extension

Um sistema financeiro usa diretamente `BigDecimal` para representar dinheiro.

Crie uma classe `ValorMonetario` que encapsule um `BigDecimal` e ofereça os métodos:

```java
formatarEmReais()
ehAltoValor()
getValor()
```

Depois, explique por que essa classe melhora a expressividade do domínio.

---

## Parte 6 — Padrões de Projeto

### 26. Strategy para cálculo de desconto

Refatore o código abaixo usando **Strategy**.

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

Sua solução deve conter:

- uma interface `EstrategiaDesconto`;
- uma estratégia para cliente comum;
- uma estratégia para cliente VIP;
- uma estratégia para funcionário;
- uma classe que aplique a estratégia escolhida.

---

### 27. Strategy para pagamento

Projete uma solução usando **Strategy** para processar diferentes formas de pagamento:

- cartão de crédito;
- Pix;
- boleto;
- vale-presente.

Cada forma de pagamento deve possuir uma lógica própria de processamento.

Crie também uma classe `ProcessadorPagamento` que receba a estratégia escolhida.

---

### 28. Singleton para configurações globais

Implemente uma classe `ConfiguracaoSistema` usando **Singleton**.

A classe deve armazenar:

- nome da aplicação;
- ambiente de execução;
- URL do banco de dados;
- chave de API.

Depois, demonstre o uso da classe em um método `main`.

---

### 29. Strategy + Singleton

Crie um sistema de descontos em que cada estratégia consulte uma configuração global.

O sistema deve conter:

- estratégias de desconto;
- uma classe Singleton `ConfiguracaoDesconto`;
- um desconto máximo permitido;
- validação para impedir que uma estratégia ultrapasse o desconto máximo.

Explique por que essa solução combina Strategy e Singleton.

---

### 30. Adapter para integração de fornecedores

Uma loja virtual precisa integrar dois fornecedores externos.

O sistema interno espera produtos no formato:

```java
Produto
```

Com os campos:

```java
codigo;
nome;
precoFinal;
quantidadeEstoque;
fornecedor;
disponivel;
```

O Fornecedor A retorna:

```java
String[] {codigo, nome, precoBase, estoque}
```

O Fornecedor B retorna objetos `ItemGlobal` com:

```java
sku;
description;
priceInDollars;
availableUnits;
```

Faça o que se pede:

a) Crie uma interface `CatalogoProdutos`.  
b) Crie os adapters `FornecedorAAdapter` e `FornecedorBAdapter`.  
c) Converta dólar para real no adapter do Fornecedor B.  
d) Calcule disponibilidade com base no estoque.  
e) Demonstre no `main` que a aplicação usa os dois fornecedores pela mesma interface.
