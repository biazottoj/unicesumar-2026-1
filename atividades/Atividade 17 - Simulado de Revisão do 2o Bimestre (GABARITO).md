# Gabarito — Atividade 17: Simulado de Revisão do 2º Bimestre

## Parte 1 — Código Limpo e Legibilidade

## 1. Conceito de código limpo

Código limpo é um código escrito de forma clara, organizada e fácil de compreender. Ele não se preocupa apenas em funcionar no momento em que foi criado, mas também em permitir que outras pessoas — ou o próprio autor no futuro — consigam entendê-lo, corrigir erros e alterá-lo com segurança.

A legibilidade é importante porque desenvolvedores passam muito tempo lendo código antes de modificá-lo. Quando nomes, funções e classes são claros, é mais fácil identificar regras do sistema e entender o objetivo de cada trecho.

Além disso, código limpo reduz a possibilidade de erros, pois diminui ambiguidades e torna problemas mais visíveis. Ele também facilita a evolução do sistema, já que mudanças podem ser feitas em partes menores e mais bem definidas. Por fim, melhora a colaboração entre desenvolvedores, porque o código se torna uma forma de comunicação compreensível para toda a equipe.

---

## 2. Avaliação de nomes de variáveis

```java
int x;
int dias;
int diasDesdeUltimoLogin;
double v;
double valorTotalPedido;
```

### a) Nomes mais legíveis

Os nomes mais legíveis são:

```java
int diasDesdeUltimoLogin;
double valorTotalPedido;
```

Eles deixam claro o significado da informação armazenada.

O nome `dias` também pode ser aceitável quando o contexto deixa evidente de quais dias se está falando. Por exemplo, dentro de um método chamado `calcularDiasDeAtraso`, o nome pode ser suficiente.

### b) Nomes vagos

Os nomes vagos são:

```java
int x;
double v;
```

Esses nomes não comunicam o que os valores representam. Para entendê-los, seria necessário analisar o restante do código.

### c) O que um bom nome deve comunicar

Um bom nome deve comunicar:

- o que a variável, método ou classe representa;
- qual é seu objetivo no contexto do sistema;
- qual unidade ou regra está associada ao valor, quando isso for relevante;
- informação suficiente para reduzir a necessidade de comentários explicativos.

Por exemplo, `valorTotalPedido` é melhor que `v` porque informa claramente que o valor corresponde ao total de um pedido.

---

## 3. Melhorando nomes de parâmetros

### a) Problema de legibilidade

No método abaixo, os parâmetros `a` e `b` não deixam claro o que representa a origem e o destino da cópia.

```java
public void copiar(String a, String b) {
    System.out.println("Copiando de " + a + " para " + b);
}
```

### b) Parâmetros renomeados

```java
public void copiar(String origem, String destino) {
    System.out.println("Copiando de " + origem + " para " + destino);
}
```

### c) Explicação

A nova versão é mais clara porque os nomes `origem` e `destino` mostram o papel de cada argumento. Assim, a leitura da chamada do método também se torna mais compreensível.

---

## Parte 2 — Formatação de Código

## 7. Formatação vertical

```java
public class PedidoService {

    public void fecharPedido(String cliente, double subtotal, double frete) {
        double total = calcularTotal(subtotal, frete);
        imprimirResumo(cliente, total);
    }

    private double calcularTotal(double subtotal, double frete) {
        return subtotal + frete;
    }

    private void imprimirResumo(String cliente, double total) {
        System.out.println(cliente + ": " + total);
    }
}
```

A organização foi melhorada porque o método público, que representa a principal operação da classe, aparece primeiro. Logo abaixo estão os métodos auxiliares usados por ele, na ordem em que são chamados.

Essa organização favorece uma leitura de cima para baixo: primeiro entende-se o que a classe faz e, depois, os detalhes de como ela realiza cada etapa.

---

## 8. Separação de conceitos

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

As linhas em branco separam três etapas diferentes:

1. preparação e validação dos dados;
2. exibição das informações do produto;
3. confirmação do encerramento do cadastro.

---

## 9. Formatação horizontal

```java
public void gerarRelatorio(
    String cliente,
    String email,
    double total,
    boolean premium,
    boolean pagamentoEmDia
) {
    if (premium
            && pagamentoEmDia
            && total > 1000) {

        String mensagem = "Relatório especial para " + cliente
                + " enviado para " + email
                + " com total de " + total;

        System.out.println(mensagem);
    }
}
```

A quebra de linhas evita que condições e mensagens muito extensas prejudiquem a leitura horizontal do código.

---

## 10. Identação

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

A identação mostra visualmente quais instruções pertencem a cada bloco condicional, facilitando a compreensão do fluxo de execução.

---

## 11. Afinidade conceitual

```java
public class UsuarioUtils {

    public String formatarCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public String formatarNome(String nome) {
        return nome.trim().toUpperCase();
    }

    public void enviarEmail(String email) {
        System.out.println("Enviando e-mail para " + email);
    }

    public void enviarNotificacao(String mensagem) {
        System.out.println(mensagem);
    }
}
```

O critério utilizado foi a **afinidade conceitual**. Os métodos de formatação foram posicionados juntos, pois ambos transformam dados textuais. Em seguida, foram agrupados os métodos relacionados ao envio de mensagens e notificações.

Em um sistema maior, uma melhoria adicional seria separar essas responsabilidades em classes diferentes, como `FormatadorUsuario` e `ServicoNotificacao`.

---

## Parte 3 — Código Limpo: Funções

## 12. Função com muitas responsabilidades

### a) Responsabilidades identificadas

A função original possui várias responsabilidades:

1. calcular o total dos itens do carrinho;
2. criar o objeto `Pedido`;
3. salvar o pedido no repositório;
4. enviar um e-mail de confirmação ao cliente.

### b) Problema para manutenção

Quando uma função acumula muitas responsabilidades, ela fica maior, mais difícil de testar e mais difícil de modificar. Por exemplo, uma alteração na regra de cálculo do total pode afetar uma função que também trata persistência e envio de e-mail.

### c) Versão refatorada

```java
public void finalizarPedido(Cliente cliente, Carrinho carrinho) {
    double total = calcularTotalCarrinho(carrinho);
    Pedido pedido = criarPedido(cliente, carrinho, total);

    salvarPedido(pedido);
    enviarConfirmacao(cliente);
}

private double calcularTotalCarrinho(Carrinho carrinho) {
    double total = 0;

    for (Item item : carrinho.getItens()) {
        total += item.getPreco() * item.getQuantidade();
    }

    return total;
}

private Pedido criarPedido(Cliente cliente, Carrinho carrinho, double total) {
    return new Pedido(cliente, carrinho.getItens(), total);
}

private void salvarPedido(Pedido pedido) {
    pedidoRepository.salvar(pedido);
}

private void enviarConfirmacao(Cliente cliente) {
    emailService.enviar(cliente.getEmail(), "Pedido confirmado");
}
```

Agora cada método possui uma intenção mais específica e um nome que explica sua responsabilidade.

---

## 13. Nome pouco descritivo

### a) Problema do nome `verificar`

O nome `verificar` é genérico. Ele não explica qual condição está sendo verificada.

### b) Nome mais descritivo

Um nome adequado é:

```java
isMaiorDeIdade
```

### c) Método reescrito

```java
public boolean isMaiorDeIdade(Usuario usuario) {
    return usuario.getIdade() >= 18 && usuario.isAtivo();
}
```

O nome permite entender a regra sem precisar analisar a implementação.

---

## 14. Função com muitos argumentos

### a) Problema de muitos argumentos

Uma função com muitos argumentos é mais difícil de usar porque:

- aumenta a chance de trocar a ordem dos valores;
- torna a chamada longa;
- indica que vários dados pertencem a um mesmo conceito;
- dificulta futuras alterações, como a inclusão de um novo campo.

### b) Classe para agrupar os dados

```java
public class DadosCadastroAluno {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String curso;
    private int periodo;

    public DadosCadastroAluno(
        String nome,
        String cpf,
        String email,
        String telefone,
        String curso,
        int periodo
    ) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.curso = curso;
        this.periodo = periodo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCurso() {
        return curso;
    }

    public int getPeriodo() {
        return periodo;
    }
}
```

### c) Função reescrita

```java
public void cadastrarAluno(DadosCadastroAluno dadosCadastro) {
    Aluno aluno = new Aluno();

    aluno.setNome(dadosCadastro.getNome());
    aluno.setCpf(dadosCadastro.getCpf());
    aluno.setEmail(dadosCadastro.getEmail());
    aluno.setTelefone(dadosCadastro.getTelefone());
    aluno.setCurso(dadosCadastro.getCurso());
    aluno.setPeriodo(dadosCadastro.getPeriodo());

    alunoRepository.salvar(aluno);
}
```

---

## 15. Efeito colateral escondido

### a) O que o nome sugere

O nome `usuarioExiste` sugere que a função apenas consulta se existe um usuário com determinado e-mail.

### b) O que a função faz além disso

Além de consultar, ela cria e salva um novo usuário quando não encontra o e-mail.

### c) Por que isso é um efeito colateral

É um efeito colateral porque uma operação que aparenta ser apenas uma consulta também modifica o estado do sistema, gravando um novo usuário no repositório. Esse comportamento pode surpreender quem chama o método.

### d) Separação entre verificação e criação

```java
public boolean usuarioExiste(String email) {
    return usuarioRepository.buscarPorEmail(email) != null;
}

public Usuario criarUsuario(String email) {
    Usuario usuario = new Usuario(email);
    usuarioRepository.salvar(usuario);

    return usuario;
}
```

Uma possível utilização seria:

```java
if (!usuarioExiste(email)) {
    criarUsuario(email);
}
```

---

## 16. Condicionais aninhadas

### Primeira versão: retornos antecipados

```java
public boolean podeAcessarSistema(Usuario usuario) {
    if (usuario == null) {
        return false;
    }

    if (!usuario.isAtivo()) {
        return false;
    }

    if (usuario.isBloqueado()) {
        return false;
    }

    return usuario.temPermissao("SISTEMA");
}
```

Essa versão reduz os níveis de identação e torna as condições de bloqueio mais visíveis.

### Segunda versão: funções auxiliares descritivas

```java
public boolean podeAcessarSistema(Usuario usuario) {
    return usuarioExiste(usuario)
            && usuarioEstaAtivo(usuario)
            && usuarioNaoEstaBloqueado(usuario)
            && usuarioPossuiPermissaoDeSistema(usuario);
}

private boolean usuarioExiste(Usuario usuario) {
    return usuario != null;
}

private boolean usuarioEstaAtivo(Usuario usuario) {
    return usuario.isAtivo();
}

private boolean usuarioNaoEstaBloqueado(Usuario usuario) {
    return !usuario.isBloqueado();
}

private boolean usuarioPossuiPermissaoDeSistema(Usuario usuario) {
    return usuario.temPermissao("SISTEMA");
}
```

---

## 17. Argumento booleano

### a) Clareza do argumento

O argumento `enviarEmail` pode tornar a função menos clara porque indica que ela possui dois comportamentos diferentes.

### b) Comportamento controlado

Ele controla se, após salvar o pedido, o sistema também envia ou não uma confirmação por e-mail.

### c) Alternativa sem argumento booleano

```java
public void processarPedido(Pedido pedido) {
    pedidoRepository.salvar(pedido);
}

public void processarPedidoEEnviarConfirmacao(Pedido pedido) {
    processarPedido(pedido);
    emailService.enviarConfirmacao(pedido);
}
```

Os dois métodos possuem nomes explícitos e deixam claro qual comportamento será executado.

---

## Parte 4 — Refatoração Básica

## 18. Extract Method

```java
public void emitirRecibo(String cliente, int quantidade, double precoUnitario) {
    double subtotal = calcularSubtotal(quantidade, precoUnitario);
    double imposto = calcularImposto(subtotal);
    double total = subtotal + imposto;

    imprimirRecibo(cliente, quantidade, subtotal, imposto, total);
    verificarCompraDeAltoValor(total);
}

private double calcularSubtotal(int quantidade, double precoUnitario) {
    return quantidade * precoUnitario;
}

private double calcularImposto(double subtotal) {
    return subtotal * 0.10;
}

private void imprimirRecibo(
    String cliente,
    int quantidade,
    double subtotal,
    double imposto,
    double total
) {
    System.out.println("Cliente: " + cliente);
    System.out.println("Quantidade: " + quantidade);
    System.out.println("Subtotal: " + subtotal);
    System.out.println("Imposto: " + imposto);
    System.out.println("Total: " + total);
}

private void verificarCompraDeAltoValor(double total) {
    if (total > 500) {
        System.out.println("Compra de alto valor");
    }
}
```

A refatoração separa cálculo, apresentação e regra de negócio em métodos diferentes.

---

## 19. Extract Variable

```java
public void verificarAprovacao(
    double notaFinal,
    int frequencia,
    boolean fezRecuperacao
) {
    boolean aprovadoDiretamente =
            notaFinal >= 6.0
            && frequencia >= 75;

    boolean aprovadoAposRecuperacao =
            fezRecuperacao
            && notaFinal >= 5.0
            && frequencia >= 80;

    if (aprovadoDiretamente || aprovadoAposRecuperacao) {
        System.out.println("Aluno aprovado");
    } else {
        System.out.println("Aluno reprovado");
    }
}
```

As variáveis extraídas deixam explícitas as duas regras de aprovação.

---

## 20. Inline Method

O método `atingiuValorMinimo` possui um nome claro e expressa uma regra de negócio. Por isso, neste caso, **não é recomendado aplicar Inline Method**.

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

Manter o método auxiliar melhora a leitura do `if`, pois permite interpretar a condição como uma pergunta de domínio: “o pedido atingiu o valor mínimo?”.

A refatoração Inline Method seria mais adequada se o método auxiliar não adicionasse significado, por exemplo, se tivesse um nome genérico como `verificar`.

---

## 21. Inline Temp

A variável `estoqueBaixo` melhora a leitura porque dá um nome à condição `quantidade < 10`. Portanto, neste caso, também é adequado **manter a variável temporária**.

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

Caso fosse aplicado Inline Temp, a solução seria:

```java
public void verificarEstoque(int quantidade) {
    if (quantidade < 10) {
        System.out.println("Estoque baixo");
    } else {
        System.out.println("Estoque suficiente");
    }
}
```

Embora a segunda versão seja menor, a primeira tende a ser mais legível porque `estoqueBaixo` explica a intenção da condição.

---

## Parte 5 — Refatoração Orientada a Objetos

## 22. Move Method

### a) Por que o método pode estar na classe errada

O método usa apenas dados do objeto `Pedido`: subtotal, frete e desconto. Portanto, a responsabilidade de calcular seu próprio total pertence naturalmente à classe `Pedido`, e não à classe `PedidoService`.

### b) Método movido para `Pedido`

```java
public class Pedido {
    private double subtotal;
    private double frete;
    private double desconto;

    public double calcularTotal() {
        return subtotal + frete - desconto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getFrete() {
        return frete;
    }

    public double getDesconto() {
        return desconto;
    }
}
```

### c) Chamada ajustada em `PedidoService`

```java
public class PedidoService {

    public void exibirTotal(Pedido pedido) {
        double total = pedido.calcularTotal();

        System.out.println("Total do pedido: " + total);
    }
}
```

---

## 23. Move Field

### a) Por que os campos estão na classe errada

`dataCompra` e `dataValidadeGarantia` não representam características gerais do produto. Elas representam informações de uma compra específica de um produto.

Por exemplo, dois clientes podem comprar o mesmo produto em datas diferentes e, consequentemente, possuir garantias com datas de validade diferentes.

### b) e c) Nova classe com os campos movidos

```java
import java.time.LocalDate;

public class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
```

```java
import java.time.LocalDate;

public class CompraProduto {
    private Produto produto;
    private LocalDate dataCompra;
    private LocalDate dataValidadeGarantia;

    public CompraProduto(
        Produto produto,
        LocalDate dataCompra,
        LocalDate dataValidadeGarantia
    ) {
        this.produto = produto;
        this.dataCompra = dataCompra;
        this.dataValidadeGarantia = dataValidadeGarantia;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public LocalDate getDataValidadeGarantia() {
        return dataValidadeGarantia;
    }
}
```

---

## 24. Extract Class

### a) Grupo de atributos que forma outro conceito

Os campos abaixo estão relacionados a informações de cobrança e pagamento:

```java
private String banco;
private String agencia;
private String conta;
private String metodoPagamentoPreferencial;
```

Eles formam o conceito de `DadosCobranca`.

### b) Classe `DadosCobranca`

```java
public class DadosCobranca {
    private String banco;
    private String agencia;
    private String conta;
    private String metodoPagamentoPreferencial;

    public DadosCobranca(
        String banco,
        String agencia,
        String conta,
        String metodoPagamentoPreferencial
    ) {
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.metodoPagamentoPreferencial = metodoPagamentoPreferencial;
    }

    public String getBanco() {
        return banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getConta() {
        return conta;
    }

    public String getMetodoPagamentoPreferencial() {
        return metodoPagamentoPreferencial;
    }
}
```

### c) Classe `Cliente` atualizada

```java
public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private DadosCobranca dadosCobranca;

    public Cliente(
        String nome,
        String cpf,
        String email,
        DadosCobranca dadosCobranca
    ) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dadosCobranca = dadosCobranca;
    }

    public DadosCobranca getDadosCobranca() {
        return dadosCobranca;
    }
}
```

---

## 25. Introduce Local Extension

```java
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class ValorMonetario {
    private static final BigDecimal LIMITE_ALTO_VALOR =
            new BigDecimal("1000.00");

    private final BigDecimal valor;

    public ValorMonetario(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor não pode ser nulo.");
        }

        this.valor = valor;
    }

    public String formatarEmReais() {
        NumberFormat formatador =
                NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        return formatador.format(valor);
    }

    public boolean ehAltoValor() {
        return valor.compareTo(LIMITE_ALTO_VALOR) > 0;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
```

Exemplo de uso:

```java
ValorMonetario totalPedido =
        new ValorMonetario(new BigDecimal("1500.00"));

System.out.println(totalPedido.formatarEmReais());

if (totalPedido.ehAltoValor()) {
    System.out.println("Pedido de alto valor.");
}
```

A classe melhora a expressividade do domínio porque o código deixa de manipular apenas um `BigDecimal` genérico e passa a trabalhar com um conceito explícito de valor monetário. Além disso, regras relacionadas a dinheiro, como formatação e identificação de alto valor, ficam centralizadas em um único local.

---

## Parte 6 — Padrões de Projeto

## 26. Strategy para cálculo de desconto

### Interface da estratégia

```java
public interface EstrategiaDesconto {
    double calcularDesconto(double valorCompra);
}
```

### Estratégia para cliente comum

```java
public class DescontoClienteComum implements EstrategiaDesconto {

    @Override
    public double calcularDesconto(double valorCompra) {
        return valorCompra * 0.05;
    }
}
```

### Estratégia para cliente VIP

```java
public class DescontoClienteVip implements EstrategiaDesconto {

    @Override
    public double calcularDesconto(double valorCompra) {
        return valorCompra * 0.10;
    }
}
```

### Estratégia para funcionário

```java
public class DescontoFuncionario implements EstrategiaDesconto {

    @Override
    public double calcularDesconto(double valorCompra) {
        return valorCompra * 0.20;
    }
}
```

### Classe que aplica a estratégia

```java
public class CalculadoraDesconto {
    private EstrategiaDesconto estrategiaDesconto;

    public CalculadoraDesconto(EstrategiaDesconto estrategiaDesconto) {
        this.estrategiaDesconto = estrategiaDesconto;
    }

    public double calcular(double valorCompra) {
        return estrategiaDesconto.calcularDesconto(valorCompra);
    }

    public void setEstrategiaDesconto(EstrategiaDesconto estrategiaDesconto) {
        this.estrategiaDesconto = estrategiaDesconto;
    }
}
```

### Exemplo de uso

```java
public class Main {

    public static void main(String[] args) {
        CalculadoraDesconto calculadora =
                new CalculadoraDesconto(new DescontoClienteVip());

        double desconto = calculadora.calcular(500.00);

        System.out.println("Desconto: " + desconto);
    }
}
```

O padrão Strategy permite incluir novas regras de desconto sem modificar uma sequência grande de `if` ou `else if`.

---

## 27. Strategy para pagamento

### Interface

```java
public interface EstrategiaPagamento {
    void processar(double valor);
}
```

### Estratégias concretas

```java
public class PagamentoCartaoCredito
        implements EstrategiaPagamento {

    @Override
    public void processar(double valor) {
        System.out.println(
                "Pagamento de R$ " + valor
                + " processado no cartão de crédito."
        );
    }
}
```

```java
public class PagamentoPix implements EstrategiaPagamento {

    @Override
    public void processar(double valor) {
        System.out.println(
                "Gerando cobrança Pix de R$ " + valor + "."
        );
    }
}
```

```java
public class PagamentoBoleto implements EstrategiaPagamento {

    @Override
    public void processar(double valor) {
        System.out.println(
                "Boleto gerado no valor de R$ " + valor + "."
        );
    }
}
```

```java
public class PagamentoValePresente
        implements EstrategiaPagamento {

    @Override
    public void processar(double valor) {
        System.out.println(
                "Aplicando vale-presente ao pagamento de R$ "
                + valor + "."
        );
    }
}
```

### Classe `ProcessadorPagamento`

```java
public class ProcessadorPagamento {
    private EstrategiaPagamento estrategiaPagamento;

    public ProcessadorPagamento(
        EstrategiaPagamento estrategiaPagamento
    ) {
        this.estrategiaPagamento = estrategiaPagamento;
    }

    public void processar(double valor) {
        estrategiaPagamento.processar(valor);
    }

    public void setEstrategiaPagamento(
        EstrategiaPagamento estrategiaPagamento
    ) {
        this.estrategiaPagamento = estrategiaPagamento;
    }
}
```

### Exemplo de uso

```java
public class Main {

    public static void main(String[] args) {
        ProcessadorPagamento processador =
                new ProcessadorPagamento(new PagamentoPix());

        processador.processar(250.00);
    }
}
```

---

## 28. Singleton para configurações globais

```java
public class ConfiguracaoSistema {
    private static final ConfiguracaoSistema INSTANCIA =
            new ConfiguracaoSistema();

    private String nomeAplicacao;
    private String ambienteExecucao;
    private String urlBancoDados;
    private String chaveApi;

    private ConfiguracaoSistema() {
        nomeAplicacao = "Sistema de Pedidos";
        ambienteExecucao = "desenvolvimento";
        urlBancoDados = "jdbc:mysql://localhost:3306/pedidos";
        chaveApi = "chave-inicial";
    }

    public static ConfiguracaoSistema getInstancia() {
        return INSTANCIA;
    }

    public String getNomeAplicacao() {
        return nomeAplicacao;
    }

    public void setNomeAplicacao(String nomeAplicacao) {
        this.nomeAplicacao = nomeAplicacao;
    }

    public String getAmbienteExecucao() {
        return ambienteExecucao;
    }

    public void setAmbienteExecucao(String ambienteExecucao) {
        this.ambienteExecucao = ambienteExecucao;
    }

    public String getUrlBancoDados() {
        return urlBancoDados;
    }

    public void setUrlBancoDados(String urlBancoDados) {
        this.urlBancoDados = urlBancoDados;
    }

    public String getChaveApi() {
        return chaveApi;
    }

    public void setChaveApi(String chaveApi) {
        this.chaveApi = chaveApi;
    }
}
```

Exemplo de uso:

```java
public class Main {

    public static void main(String[] args) {
        ConfiguracaoSistema configuracao =
                ConfiguracaoSistema.getInstancia();

        configuracao.setAmbienteExecucao("produção");

        System.out.println(
                "Aplicação: " + configuracao.getNomeAplicacao()
        );

        System.out.println(
                "Ambiente: " + configuracao.getAmbienteExecucao()
        );
    }
}
```

O construtor é privado para impedir a criação de objetos com `new`. O método `getInstancia()` devolve sempre o mesmo objeto de configuração.

---

## 29. Strategy + Singleton

Neste exemplo, cada estratégia calcula um desconto solicitado. A classe `CalculadoraDesconto` consulta o Singleton `ConfiguracaoDesconto` para garantir que o desconto aplicado não ultrapasse o máximo permitido.

### Singleton de configuração

```java
public class ConfiguracaoDesconto {
    private static final ConfiguracaoDesconto INSTANCIA =
            new ConfiguracaoDesconto();

    private double percentualMaximoDesconto = 0.15;

    private ConfiguracaoDesconto() {
    }

    public static ConfiguracaoDesconto getInstancia() {
        return INSTANCIA;
    }

    public double getPercentualMaximoDesconto() {
        return percentualMaximoDesconto;
    }

    public void setPercentualMaximoDesconto(
        double percentualMaximoDesconto
    ) {
        this.percentualMaximoDesconto = percentualMaximoDesconto;
    }
}
```

### Interface Strategy

```java
public interface EstrategiaDesconto {
    double calcularPercentualDesconto(double valorCompra);
}
```

### Estratégias

```java
public class DescontoClienteComum
        implements EstrategiaDesconto {

    @Override
    public double calcularPercentualDesconto(double valorCompra) {
        return 0.05;
    }
}
```

```java
public class DescontoClienteVip
        implements EstrategiaDesconto {

    @Override
    public double calcularPercentualDesconto(double valorCompra) {
        return 0.10;
    }
}
```

```java
public class DescontoFuncionario
        implements EstrategiaDesconto {

    @Override
    public double calcularPercentualDesconto(double valorCompra) {
        return 0.20;
    }
}
```

### Classe que aplica a regra global

```java
public class CalculadoraDesconto {
    private EstrategiaDesconto estrategiaDesconto;

    public CalculadoraDesconto(
        EstrategiaDesconto estrategiaDesconto
    ) {
        this.estrategiaDesconto = estrategiaDesconto;
    }

    public double calcularDesconto(double valorCompra) {
        double percentualSolicitado =
                estrategiaDesconto.calcularPercentualDesconto(valorCompra);

        double percentualMaximo =
                ConfiguracaoDesconto.getInstancia()
                        .getPercentualMaximoDesconto();

        double percentualAplicado =
                Math.min(percentualSolicitado, percentualMaximo);

        return valorCompra * percentualAplicado;
    }
}
```

### Exemplo de uso

```java
public class Main {

    public static void main(String[] args) {
        ConfiguracaoDesconto.getInstancia()
                .setPercentualMaximoDesconto(0.15);

        CalculadoraDesconto calculadora =
                new CalculadoraDesconto(
                        new DescontoFuncionario()
                );

        double desconto = calculadora.calcularDesconto(1000.00);

        System.out.println("Desconto aplicado: " + desconto);
    }
}
```

A solução combina os dois padrões porque:

- **Strategy** encapsula regras alternativas de desconto;
- **Singleton** oferece uma única configuração global do limite máximo;
- a classe principal aplica a estratégia escolhida sem permitir que ela ultrapasse a regra global do sistema.

No exemplo, a estratégia de funcionário solicita 20%, mas o sistema limita o desconto a 15%.

---

## 30. Adapter para integração de fornecedores

A solução abaixo permite que a aplicação trabalhe com dois fornecedores externos usando uma única interface interna.

### Classe interna `Produto`

```java
public class Produto {
    private String codigo;
    private String nome;
    private double precoFinal;
    private int quantidadeEstoque;
    private String fornecedor;
    private boolean disponivel;

    public Produto(
        String codigo,
        String nome,
        double precoFinal,
        int quantidadeEstoque,
        String fornecedor,
        boolean disponivel
    ) {
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

    public String getNome() {
        return nome;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Produto{"
                + "codigo='" + codigo + '\''
                + ", nome='" + nome + '\''
                + ", precoFinal=" + precoFinal
                + ", quantidadeEstoque=" + quantidadeEstoque
                + ", fornecedor='" + fornecedor + '\''
                + ", disponivel=" + disponivel
                + '}';
    }
}
```

### a) Interface `CatalogoProdutos`

```java
import java.util.List;

public interface CatalogoProdutos {
    List<Produto> listarProdutos();
}
```

### Classes que representam os dados externos

```java
import java.util.List;

public class FornecedorA {
    private List<String[]> produtos;

    public FornecedorA(List<String[]> produtos) {
        this.produtos = produtos;
    }

    public List<String[]> buscarProdutos() {
        return produtos;
    }
}
```

```java
public class ItemGlobal {
    private String sku;
    private String description;
    private double priceInDollars;
    private int availableUnits;

    public ItemGlobal(
        String sku,
        String description,
        double priceInDollars,
        int availableUnits
    ) {
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

```java
import java.util.List;

public class FornecedorB {
    private List<ItemGlobal> produtos;

    public FornecedorB(List<ItemGlobal> produtos) {
        this.produtos = produtos;
    }

    public List<ItemGlobal> buscarProdutos() {
        return produtos;
    }
}
```

### b) Adapter do Fornecedor A

```java
import java.util.ArrayList;
import java.util.List;

public class FornecedorAAdapter implements CatalogoProdutos {
    private FornecedorA fornecedorA;

    public FornecedorAAdapter(FornecedorA fornecedorA) {
        this.fornecedorA = fornecedorA;
    }

    @Override
    public List<Produto> listarProdutos() {
        List<Produto> produtosAdaptados = new ArrayList<>();

        for (String[] item : fornecedorA.buscarProdutos()) {
            String codigo = item[0];
            String nome = item[1];
            double precoBase = Double.parseDouble(item[2]);
            int estoque = Integer.parseInt(item[3]);

            boolean disponivel = estoque > 0;

            Produto produto = new Produto(
                    codigo,
                    nome,
                    precoBase,
                    estoque,
                    "Fornecedor A",
                    disponivel
            );

            produtosAdaptados.add(produto);
        }

        return produtosAdaptados;
    }
}
```

### c) e d) Adapter do Fornecedor B com conversão e disponibilidade

```java
import java.util.ArrayList;
import java.util.List;

public class FornecedorBAdapter implements CatalogoProdutos {
    private static final double COTACAO_DOLAR = 5.20;

    private FornecedorB fornecedorB;

    public FornecedorBAdapter(FornecedorB fornecedorB) {
        this.fornecedorB = fornecedorB;
    }

    @Override
    public List<Produto> listarProdutos() {
        List<Produto> produtosAdaptados = new ArrayList<>();

        for (ItemGlobal item : fornecedorB.buscarProdutos()) {
            double precoEmReais =
                    item.getPriceInDollars() * COTACAO_DOLAR;

            boolean disponivel = item.getAvailableUnits() > 0;

            Produto produto = new Produto(
                    item.getSku(),
                    item.getDescription(),
                    precoEmReais,
                    item.getAvailableUnits(),
                    "Fornecedor B",
                    disponivel
            );

            produtosAdaptados.add(produto);
        }

        return produtosAdaptados;
    }
}
```

> A cotação de `5.20` foi usada apenas para demonstrar a conversão. Em um sistema real, a cotação deveria ser obtida de uma fonte apropriada e atualizada.

### e) Demonstração no `main`

```java
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String[]> produtosFornecedorA = Arrays.asList(
                new String[] {
                        "A01", "Teclado", "150.00", "10"
                },
                new String[] {
                        "A02", "Mouse", "80.00", "0"
                }
        );

        FornecedorA fornecedorA =
                new FornecedorA(produtosFornecedorA);

        List<ItemGlobal> produtosFornecedorB = Arrays.asList(
                new ItemGlobal(
                        "B01", "Monitor", 200.00, 4
                ),
                new ItemGlobal(
                        "B02", "Webcam", 50.00, 0
                )
        );

        FornecedorB fornecedorB =
                new FornecedorB(produtosFornecedorB);

        CatalogoProdutos catalogoA =
                new FornecedorAAdapter(fornecedorA);

        CatalogoProdutos catalogoB =
                new FornecedorBAdapter(fornecedorB);

        exibirProdutos(catalogoA);
        exibirProdutos(catalogoB);
    }

    private static void exibirProdutos(CatalogoProdutos catalogo) {
        for (Produto produto : catalogo.listarProdutos()) {
            System.out.println(produto);
        }
    }
}
```

Os dois adapters implementam a mesma interface, `CatalogoProdutos`. Assim, o restante da aplicação não precisa conhecer os formatos específicos usados pelos fornecedores externos.
