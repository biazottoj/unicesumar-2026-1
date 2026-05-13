# Atividade prática — Sistema de Estacionamento

Este projeto contém um pequeno sistema de estacionamento criado para praticar técnicas de refatoração relacionadas à organização de responsabilidades entre classes.

O projeto é único e coeso. Todos os exercícios fazem parte do mesmo domínio, e os alunos devem navegar pelas classes para identificar onde cada refatoração deve ser aplicada.

## Técnicas trabalhadas

1. Introduce Local Extension
2. Move Method
3. Move Field
4. Extract Class

## Como executar

Na pasta do projeto, execute:

```bash
mvn compile exec:java
```

Também é possível abrir o projeto em uma IDE e executar a classe:

```text
br.edu.refactoring.estacionamento.Main
```

## Estrutura

```text
src/main/java/br/edu/refactoring/estacionamento/
├── Main.java
├── Estacionamento.java
├── Cliente.java
├── Veiculo.java
├── Vaga.java
├── TicketEstacionamento.java
├── ReservaVaga.java
├── RegistroPagamento.java
├── OperacaoEstacionamento.java
├── CentralClientes.java
└── PainelVagas.java
```

## Objetivo

Refatorar o sistema mantendo o comportamento geral da aplicação.

O código foi escrito de forma propositalmente simples, mas com responsabilidades mal posicionadas. A atividade consiste em reorganizar essas responsabilidades usando as técnicas indicadas.
