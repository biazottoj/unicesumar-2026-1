# Walkthrough — Construindo um Projeto com o Padrão Adapter sem Pacotes

## Objetivo

Neste walkthrough, vamos construir passo a passo um projeto em Java que utiliza o padrão de projeto **Adapter**.

O projeto lê dados de pessoas a partir de um arquivo CSV e transforma esses dados em objetos da classe `Pessoa`.

A ideia central é adaptar a saída de um leitor de CSV, que retorna dados em formato bruto, para uma interface esperada pelo sistema, que trabalha com objetos do domínio da aplicação.

Nesta versão, todas as classes ficam sem declaração de `package`, para simplificar a organização e a execução do projeto.

---

# 1. Entendendo o problema

Imagine que temos um arquivo CSV com dados de pessoas:

```csv
nome,idade,email
Ana Silva,29,ana.silva@email.com
João Santos,34,joao.santos@email.com
Marina Souza,41,marina.souza@email.com
```

Esse arquivo contém três informações por pessoa:

- nome;
- idade;
- e-mail.

O sistema precisa listar essas pessoas como objetos Java da classe `Pessoa`.

No entanto, o leitor de CSV não retorna objetos `Pessoa`. Ele retorna dados em formato bruto:

```java
List<String[]>
```

Cada `String[]` representa uma linha do CSV.

Por exemplo:

```java
["Ana Silva", "29", "ana.silva@email.com"]
```

Mas o restante do sistema não deveria trabalhar diretamente com `String[]`.

O ideal é que o sistema receba uma lista de objetos:

```java
List<Pessoa>
```

O padrão **Adapter** será usado para resolver essa incompatibilidade.

---

# 2. Ideia geral da solução

A solução terá três partes principais:

1. Um leitor de CSV que sabe ler o arquivo e retornar os dados brutos.
2. Uma interface de repositório que define como o sistema deseja obter pessoas.
3. Um adapter que converte os dados brutos do CSV em objetos `Pessoa`.

A diferença principal é esta:

```text
LeitorCsvImpl
retorna List<String[]>
```

```text
RepositorioDePessoas
espera retornar List<Pessoa>
```

O adapter ficará entre essas duas partes.

---

# 3. Estrutura final do projeto

A estrutura final do projeto será:

```text
adapter_design_pattern/
├── arquivocsv/
│   └── arquivo.csv
└── src/
    ├── Main.java
    ├── Pessoa.java
    ├── LeitorCsv.java
    ├── LeitorCsvImpl.java
    ├── RepositorioDePessoas.java
    └── PessoaCsvAdapter.java
```

Como as classes não usam `package`, todos os arquivos `.java` ficam diretamente dentro da pasta `src`.

---

# 4. Criando o arquivo CSV

Crie uma pasta chamada `arquivocsv`.

Dentro dela, crie um arquivo chamado `arquivo.csv`.

O conteúdo do arquivo deve ser:

```csv
nome,idade,email
Ana Silva,29,ana.silva@email.com
João Santos,34,joao.santos@email.com
Marina Souza,41,marina.souza@email.com
```

Esse arquivo será usado como fonte de dados do sistema.

---

# 5. Criando a classe `Pessoa`

A classe `Pessoa` representa o objeto de domínio da aplicação.

Crie o arquivo:

```text
src/Pessoa.java
```

Código:

```java
public class Pessoa {
    private String nome;
    private int idade;
    private String email;

    public Pessoa() {
    }

    public Pessoa(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

Essa classe será usada para representar cada linha válida do CSV como um objeto Java.

Por exemplo, a linha:

```csv
Ana Silva,29,ana.silva@email.com
```

será transformada em:

```java
new Pessoa("Ana Silva", 29, "ana.silva@email.com")
```

---

# 6. Criando a interface `LeitorCsv`

Agora vamos criar uma interface que define o comportamento esperado de um leitor de CSV.

Crie o arquivo:

```text
src/LeitorCsv.java
```

Código:

```java
import java.util.List;

public interface LeitorCsv {
    List<String[]> lerArquivo(String caminhoArquivo);
}
```

Essa interface define um contrato simples:

```java
List<String[]> lerArquivo(String caminhoArquivo);
```

Isso significa que qualquer classe que implemente `LeitorCsv` deverá receber o caminho de um arquivo e retornar uma lista de linhas.

Cada linha será representada como um vetor de `String`.

---

# 7. Criando a implementação `LeitorCsvImpl`

Agora vamos criar uma classe concreta que lê o arquivo CSV.

Crie o arquivo:

```text
src/LeitorCsvImpl.java
```

Código:

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorCsvImpl implements LeitorCsv {

    @Override
    public List<String[]> lerArquivo(String caminhoArquivo) {
        List<String[]> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length == 3) {
                    linhas.add(dados);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linhas;
    }
}
```

Essa classe faz as seguintes ações:

1. Abre o arquivo CSV.
2. Ignora a primeira linha, que contém o cabeçalho.
3. Lê cada linha restante.
4. Divide a linha usando a vírgula como separador.
5. Adiciona a linha à lista somente se ela possuir três campos.
6. Retorna uma lista de `String[]`.

Por exemplo, a linha:

```csv
João Santos,34,joao.santos@email.com
```

é transformada em:

```java
String[] dados = {"João Santos", "34", "joao.santos@email.com"};
```

Neste momento, ainda não temos objetos `Pessoa`.

Temos apenas dados brutos lidos do CSV.

---

# 8. Criando a interface `RepositorioDePessoas`

Agora vamos definir como o restante do sistema deseja acessar pessoas.

Crie o arquivo:

```text
src/RepositorioDePessoas.java
```

Código:

```java
import java.util.List;

public interface RepositorioDePessoas {
    List<Pessoa> listarPessoas();
}
```

Essa interface representa a forma como a aplicação deseja obter os dados.

Ela não quer saber se os dados vêm de:

- arquivo CSV;
- banco de dados;
- API externa;
- arquivo JSON;
- memória.

A aplicação deseja apenas chamar:

```java
listarPessoas()
```

e receber:

```java
List<Pessoa>
```

Aqui aparece a incompatibilidade principal do projeto:

```text
LeitorCsv retorna List<String[]>
RepositorioDePessoas espera List<Pessoa>
```

O Adapter será responsável por fazer essa conversão.

---

# 9. Criando o Adapter `PessoaCsvAdapter`

Agora vamos criar a classe que aplica o padrão **Adapter**.

Crie o arquivo:

```text
src/PessoaCsvAdapter.java
```

Código:

```java
import java.util.ArrayList;
import java.util.List;

public class PessoaCsvAdapter implements RepositorioDePessoas {

    private LeitorCsv leitorCsv;
    private String caminhoArquivoCsv;

    public PessoaCsvAdapter(String caminhoArquivoCsv, LeitorCsv leitorCsv) {
        this.caminhoArquivoCsv = caminhoArquivoCsv;
        this.leitorCsv = leitorCsv;
    }

    @Override
    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        List<String[]> dadosCsv = leitorCsv.lerArquivo(caminhoArquivoCsv);

        for (String[] dados : dadosCsv) {
            Pessoa pessoa = new Pessoa(dados[0], Integer.parseInt(dados[1]), dados[2]);
            pessoas.add(pessoa);
        }

        return pessoas;
    }
}
```

Essa é a classe mais importante do projeto para entender o padrão Adapter.

Ela implementa a interface que o sistema espera:

```java
RepositorioDePessoas
```

Por isso, ela precisa ter o método:

```java
listarPessoas()
```

Ao mesmo tempo, ela usa internamente um objeto do tipo:

```java
LeitorCsv
```

O `LeitorCsv` retorna dados brutos:

```java
List<String[]>
```

Então o adapter percorre esses dados e cria objetos `Pessoa`.

A conversão acontece neste trecho:

```java
for (String[] dados : dadosCsv) {
    Pessoa pessoa = new Pessoa(dados[0], Integer.parseInt(dados[1]), dados[2]);
    pessoas.add(pessoa);
}
```

Cada posição do vetor representa uma coluna do CSV:

| Posição | Valor |
|---|---|
| `dados[0]` | nome |
| `dados[1]` | idade |
| `dados[2]` | e-mail |

Como a idade vem do CSV como texto, ela precisa ser convertida para inteiro:

```java
Integer.parseInt(dados[1])
```

Ao final, o método retorna:

```java
List<Pessoa>
```

Ou seja, o adapter transforma a interface disponível em uma interface esperada pelo sistema.

---

# 10. Criando a classe `Main`

Agora vamos criar a classe principal para executar o projeto.

Crie o arquivo:

```text
src/Main.java
```

Código:

```java
public class Main {
    public static void main(String[] args) {
        String caminhoCsv = ".\\arquivocsv\\arquivo.csv";

        LeitorCsv leitorCsv = new LeitorCsvImpl();

        PessoaCsvAdapter adaptador = new PessoaCsvAdapter(caminhoCsv, leitorCsv);

        System.out.println();

        for (Pessoa pessoa : adaptador.listarPessoas()) {
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Idade: " + pessoa.getIdade());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println();
        }
    }
}
```

A classe `Main` executa o fluxo completo:

1. Define o caminho do arquivo CSV.
2. Cria um leitor de CSV.
3. Cria o adapter.
4. Chama `listarPessoas()`.
5. Exibe os dados de cada pessoa.

A linha abaixo cria o leitor concreto:

```java
LeitorCsv leitorCsv = new LeitorCsvImpl();
```

Mesmo criando um `LeitorCsvImpl`, a variável foi declarada como `LeitorCsv`.

Isso significa que o código trabalha com a interface, e não diretamente com a implementação concreta.

---

# 11. Atenção ao caminho do arquivo CSV

No Windows, o caminho usado no projeto é:

```java
String caminhoCsv = ".\\arquivocsv\\arquivo.csv";
```

Em Linux ou macOS, pode ser necessário usar:

```java
String caminhoCsv = "./arquivocsv/arquivo.csv";
```

O caminho deve considerar o diretório a partir do qual o programa está sendo executado.

Se o arquivo não for encontrado, o programa pode exibir uma mensagem parecida com:

```text
java.io.FileNotFoundException
```

Nesse caso, confira se:

1. A pasta `arquivocsv` existe.
2. O arquivo se chama exatamente `arquivo.csv`.
3. O programa está sendo executado a partir da pasta raiz do projeto.
4. O caminho usado no código corresponde ao sistema operacional.

---

# 12. Executando o projeto

Ao executar a classe `Main`, a saída esperada será semelhante a:

```text
Nome: Ana Silva
Idade: 29
Email: ana.silva@email.com

Nome: João Santos
Idade: 34
Email: joao.santos@email.com

Nome: Marina Souza
Idade: 41
Email: marina.souza@email.com
```

Isso confirma que:

1. O arquivo CSV foi lido.
2. As linhas foram separadas corretamente.
3. Os dados brutos foram convertidos para objetos `Pessoa`.
4. O sistema recebeu os dados usando a interface `RepositorioDePessoas`.

---

# 13. Onde está o padrão Adapter?

No projeto, o padrão Adapter aparece na classe:

```java
PessoaCsvAdapter
```

Ela adapta o comportamento de:

```java
LeitorCsv
```

para a interface esperada:

```java
RepositorioDePessoas
```

A relação pode ser entendida assim:

```text
LeitorCsvImpl
lê CSV e retorna dados brutos
```

```text
PessoaCsvAdapter
usa LeitorCsvImpl e converte os dados
```

```text
RepositorioDePessoas
define o formato esperado pelo sistema
```

```text
Main
usa o adapter para obter objetos Pessoa
```

---

# 14. Papéis do padrão Adapter neste projeto

| Papel no padrão Adapter | Classe no projeto | Responsabilidade |
|---|---|---|
| Target | `RepositorioDePessoas` | Interface esperada pelo sistema |
| Adapter | `PessoaCsvAdapter` | Converte dados do CSV para objetos `Pessoa` |
| Adaptee | `LeitorCsv` / `LeitorCsvImpl` | Classe/interface existente com formato incompatível |
| Client | `Main` | Usa o adapter para obter a lista de pessoas |
| Objeto de domínio | `Pessoa` | Representa os dados da aplicação |

---

# 15. Por que o Adapter é útil neste cenário?

O padrão Adapter é útil porque o sistema possui duas formas diferentes de representar os dados.

O leitor de CSV trabalha assim:

```java
List<String[]>
```

Mas a aplicação deseja trabalhar assim:

```java
List<Pessoa>
```

Sem o Adapter, a classe `Main` ou outras partes do sistema precisariam conhecer os detalhes do CSV.

Por exemplo, o código de conversão poderia ficar espalhado pelo sistema:

```java
Pessoa pessoa = new Pessoa(dados[0], Integer.parseInt(dados[1]), dados[2]);
```

Isso não é ideal, porque várias classes passariam a depender da estrutura interna do arquivo CSV.

Com o Adapter, a conversão fica concentrada em uma classe específica:

```java
PessoaCsvAdapter
```

Assim, o restante do sistema pode trabalhar apenas com objetos `Pessoa`.

---

# 16. Fluxo completo da execução

O fluxo completo do projeto é:

```text
Main
  cria LeitorCsvImpl
  cria PessoaCsvAdapter
  chama listarPessoas()
```

```text
PessoaCsvAdapter
  chama leitorCsv.lerArquivo(caminhoArquivoCsv)
```

```text
LeitorCsvImpl
  abre o arquivo CSV
  ignora o cabeçalho
  lê cada linha
  separa os campos por vírgula
  retorna List<String[]>
```

```text
PessoaCsvAdapter
  recebe List<String[]>
  transforma cada String[] em Pessoa
  retorna List<Pessoa>
```

```text
Main
  percorre List<Pessoa>
  imprime nome, idade e e-mail
```
