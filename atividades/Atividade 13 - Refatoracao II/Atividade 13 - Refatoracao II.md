# Atividade — Refatoração em um Sistema de Estacionamento

## Contexto

O projeto representa um pequeno sistema de estacionamento. Ele possui classes para representar clientes, veículos, vagas, tickets, reservas e pagamentos.

O sistema compila e executa, mas algumas responsabilidades estão mal distribuídas. A tarefa é melhorar a organização do código por meio de refatorações.

## Orientacões para a conducão da atividade

1. Execute o projeto antes de modificar o código.
2. Observe a saída produzida pela classe `Main`.
3. Aplique as refatorações indicadas nos exercícios.
4. Execute o projeto novamente.
5. O comportamento geral deve ser preservado.
6. Não altere regras de negócio sem necessidade.
7. Crie novas classes quando a técnica exigir.
8. Renomeie métodos e atributos quando isso ajudar a clareza.

---

# Exercício 1 — Introduce Local Extension para data e hora

## Classes relacionadas

- `TicketEstacionamento`

## Problema

A classe `TicketEstacionamento` manipula diretamente `LocalDateTime` para:

- formatar a data e hora de entrada;
- verificar se a entrada ocorreu em horário especial.

Essas regras podem aparecer em outras partes do sistema e não pertencem necessariamente ao ticket.

## Tarefa

Crie uma extensão local para representar uma data e hora usada no estacionamento.

Essa nova classe deve oferecer operações para:

- formatar data e hora;
- verificar se está em horário especial (você deve definir qual seria um horário especial);
- retornar o valor original, se necessário.

---

# Exercício 2 — Introduce Local Extension para valor monetário

## Classes relacionadas

- `RegistroPagamento`

## Problema

A classe `RegistroPagamento` manipula diretamente `BigDecimal` para:

- formatar valor em reais;
- comparar se o pagamento é de alto valor.

Essas regras são específicas do domínio financeiro do sistema.

## Tarefa

Crie uma extensão local para representar valores monetários do estacionamento.

Essa nova classe deve oferecer as operações:

- formatar em reais;
- verificar se o valor é alto;
- retornar o valor original, se necessário.

---

# Exercício 3 — Move Method: permanência do ticket

## Classes relacionadas

- `OperacaoEstacionamento`
- `TicketEstacionamento`

## Problema

O método `calcularMinutosPermanencia` está em `OperacaoEstacionamento`, mas usa dados do ticket.

## Tarefa

Avalie se o método deve ser movido para `TicketEstacionamento`.

Depois da refatoração, a classe que representa o ticket deve saber calcular sua própria permanência.

---

# Exercício 4 — Move Method: desconto do cliente

## Classes relacionadas

- `CentralClientes`
- `Cliente`

## Problema

O método `calcularDescontoCliente` está em `CentralClientes`, mas usa principalmente dados de `Cliente`.

## Tarefa

Mova a regra de desconto para a classe que possui os dados necessários.

---

# Exercício 5 — Move Method: status da vaga

## Classes relacionadas

- `PainelVagas`
- `Vaga`

## Problema

O método `descreverStatus` está em `PainelVagas`, mas usa apenas dados de `Vaga`.

## Tarefa

Mova a responsabilidade de descrever o status para `Vaga`.

---

# Exercício 6 — Move Field: datas de entrada e saída

## Classes relacionadas

- `Veiculo`
- `TicketEstacionamento`

## Problema

A classe `Veiculo` armazena `dataHoraEntrada` e `dataHoraSaidaPrevista`.

Essas datas não descrevem o veículo em si. Elas descrevem uma permanência específica no estacionamento.

## Tarefa

Mova esses campos para `TicketEstacionamento`.

Ajuste construtores, getters e chamadas afetadas.

---

# Exercício 7 — Move Field: dados da reserva

## Classes relacionadas

- `Vaga`
- `ReservaVaga`

## Problema

A classe `Vaga` possui `nomeClienteReserva` e `horarioReserva`.

Esses dados não descrevem a vaga de forma permanente. Eles descrevem uma reserva específica.

## Tarefa

Mova esses campos para `ReservaVaga`.

Ajuste construtores, getters e métodos de impressão.

---

# Exercício 8 — Extract Class: endereço do estacionamento

## Classe relacionada

- `Estacionamento`

## Problema

A classe `Estacionamento` mistura dados do estacionamento com dados de endereço.

Os campos `rua`, `numero`, `bairro`, `cidade`, `estado` e `cep` formam um conceito separado.

## Tarefa

Extraia uma classe para representar o endereço.

---

# Exercício 9 — Extract Class: dados de cobrança do cliente

## Classe relacionada

- `Cliente`

## Problema

A classe `Cliente` mistura dados pessoais, dados de fidelidade e dados de cobrança.

Os campos `banco`, `agencia`, `conta` e `metodoPagamentoPreferencial` formam um conceito separado.

## Tarefa

Extraia uma classe para representar os dados de cobrança.

---

# Exercício 10 — Extract Class: dados do seguro do veículo

## Classe relacionada

- `Veiculo`

## Problema

A classe `Veiculo` mistura dados do veículo com dados de seguro.

Os campos `seguradora`, `numeroApolice` e `telefoneAssistencia` formam um conceito separado.

## Tarefa

Extraia uma classe para representar os dados do seguro.

# Entrega
A atividade pode ser feita em grupos de até 4 alunos.

Utilize o link para entregar o projeto (ZIP): [https://forms.gle/CmaEHoNGSDw2Pvmd8](https://forms.gle/CmaEHoNGSDw2Pvmd8)
