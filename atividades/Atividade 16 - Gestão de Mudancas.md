# Atividade — Gestão de Mudança em Software (cenários inéditos)

## Objetivo geral
Aplicar, em cenários inéditos, o ciclo completo de gestão de mudança em software, incluindo:

- entendimento e refinamento da solicitação;
- análise de impacto;
- identificação de riscos;
- priorização;
- controle da mudança;
- rastreabilidade;
- planejamento da implementação;
- validação da mudança.

## Objetivos específicos
Ao final da atividade, espera-se que os alunos consigam:

- transformar uma solicitação genérica em uma mudança mais clara e operacional;
- identificar módulos, classes, serviços e testes potencialmente impactados;
- analisar riscos técnicos e de negócio;
- comparar prioridades entre demandas concorrentes;
- construir registros de controle e rastreamento;
- propor um plano inicial de implementação e validação;
- enxergar a manutenção como um processo completo, e não apenas como alteração de código.

---

# Instruções gerais

A atividade deve ser realizada em **grupos de 4 alunos**.

Cada grupo deve escolher **apenas um** dos dois cenários abaixo e conduzir a mudança **do começo ao fim**, aplicando todas as etapas da gestão de mudança.

O foco da atividade **não é implementar código**, mas **gerenciar tecnicamente a mudança**.

---

# Cenário 1 — Plataforma de clínica médica

## Contexto do sistema
Uma clínica utiliza um sistema já em produção com os seguintes módulos:

- cadastro de pacientes;
- agenda de consultas;
- prontuário;
- faturamento;
- notificações por SMS/e-mail;
- relatórios administrativos.

Hoje, o sistema agenda consultas e envia lembretes simples aos pacientes. No entanto, ele **não possui lista de espera automática**.

## Mudança solicitada
A gerência da clínica enviou a seguinte solicitação:

> “Precisamos que, quando um paciente cancelar uma consulta, o sistema ofereça automaticamente esse horário para pacientes que estejam em lista de espera.”

## Outras demandas abertas no sistema
Além dessa mudança, a equipe técnica também possui estas demandas:

1. corrigir um erro em que alguns lembretes de consulta são enviados em duplicidade;
2. incluir lista de espera automática para consultas canceladas;
3. melhorar a exportação de relatórios financeiros em PDF;
4. corrigir um problema de lentidão na busca de pacientes pelo nome.

---

# Cenário 2 — Sistema acadêmico universitário

## Contexto do sistema
Uma universidade utiliza um sistema acadêmico com os seguintes módulos:

- cadastro de alunos;
- disciplinas e turmas;
- matrícula;
- lançamento de notas;
- financeiro;
- portal do aluno;
- relatórios de secretaria.

Hoje, o sistema permite matrícula em disciplinas dentro do período regular, mas **não possui mecanismo de solicitação de quebra de pré-requisito**.

## Mudança solicitada
A coordenação de curso enviou a seguinte solicitação:

> “Precisamos permitir que alunos solicitem quebra de pré-requisito para uma disciplina, e que a coordenação possa aprovar ou reprovar esse pedido.”

## Outras demandas abertas no sistema
Além dessa mudança, a equipe técnica também possui estas demandas:

1. corrigir erro no cálculo de média final em uma regra específica;
2. permitir solicitação de quebra de pré-requisito com aprovação da coordenação;
3. melhorar o layout do boletim no portal do aluno;
4. corrigir falha no envio de comprovantes de matrícula por e-mail.

---

# Tarefa do grupo

O grupo deverá atuar como uma **equipe de manutenção de software** e conduzir a mudança escolhida ao longo de todo o ciclo de gestão.

---

# Etapas obrigatórias da atividade

## Etapa 1 — Compreensão e refinamento da solicitação

### Objetivo
Transformar a solicitação inicial em uma descrição mais clara, técnica e operacional.

### O grupo deve produzir
- uma versão inicial interpretada da mudança;
- pelo menos **6 perguntas de esclarecimento** ao solicitante;
- uma versão refinada e mais completa da mudança.

### Perguntas orientadoras
- Em que momento do fluxo a mudança acontece?
- Quais regras de negócio precisam ser respeitadas?
- Quem pode executar essa ação?
- Há restrições de prazo, histórico ou aprovação?
- O que deve acontecer em caso de erro?
- Quais usuários ou módulos serão afetados?

### Resultado esperado
Uma descrição mais precisa da mudança, pronta para ser analisada tecnicamente.

---

## Etapa 2 — Registro inicial da mudança

### Objetivo
Formalizar a mudança antes da análise técnica.

### O grupo deve produzir
Um registro inicial contendo:

- ID da mudança;
- título;
- origem da solicitação;
- descrição resumida;
- motivação;
- status inicial.

### Exemplo de estrutura
- **ID:** CHG-0XX  
- **Título:** ...  
- **Origem:** ...  
- **Descrição:** ...  
- **Motivação:** ...  
- **Status:** em análise  

---

## Etapa 3 — Análise de impacto

### Objetivo
Identificar quais partes do sistema podem ser afetadas pela mudança.

### O grupo deve produzir
- uma lista de módulos impactados;
- uma lista de possíveis classes, serviços, telas ou processos afetados;
- uma tabela de impacto.

### Tabela sugerida

| Elemento afetado | Tipo de impacto | Nível de risco |
|---|---|---|
| Módulo X | alteração de regra | médio |
| Serviço Y | inclusão de validação | alto |
| Relatório Z | ajuste de informação exibida | baixo |

### O que se espera
Que o grupo perceba impactos em:
- interface;
- regra de negócio;
- persistência;
- comunicação;
- relatórios;
- testes;
- integrações.

---

## Etapa 4 — Identificação de riscos

### Objetivo
Mapear possíveis problemas que podem surgir ao implementar a mudança.

### O grupo deve produzir
Pelo menos **5 riscos**, cada um com:
- descrição;
- impacto;
- probabilidade ou criticidade;
- estratégia de mitigação.

### Tabela sugerida

| Risco | Impacto | Mitigação |
|---|---|---|
| Regra aplicada incorretamente | comportamento inconsistente | centralizar regra e criar testes |
| Notificação enviada de forma errada | falha de comunicação com usuário | revisar fluxo e testar cenários |

---

## Etapa 5 — Priorização da mudança

### Objetivo
Decidir a prioridade da mudança em relação às demais demandas abertas.

### O grupo deve produzir
Uma tabela de priorização comparando:
- a mudança principal escolhida;
- as outras demandas abertas do cenário.

### Critérios mínimos
- valor de negócio;
- urgência;
- risco;
- esforço;
- prioridade final.

### Tabela sugerida

| Demanda | Valor | Urgência | Risco | Esforço | Prioridade |
|---|---|---|---|---|---|
| Mudança principal | alto | média | médio | médio | 2 |
| Bug crítico | muito alto | alta | alto | médio | 1 |

### O que se espera
Que o grupo justifique tecnicamente a ordem definida.

---

## Etapa 6 — Controle da mudança

### Objetivo
Evoluir o registro inicial e transformá-lo em um item de controle mais completo.

### O grupo deve produzir
Um registro consolidado da mudança contendo:

- ID;
- título;
- descrição refinada;
- prioridade;
- status;
- dependências;
- módulos afetados;
- responsáveis;
- versão prevista;
- observações relevantes.

---

## Etapa 7 — Rastreabilidade

### Objetivo
Conectar a mudança aos elementos do sistema que serão alterados e validados.

### O grupo deve produzir
Um quadro de rastreabilidade conectando:

- mudança;
- regras de negócio;
- módulos ou classes;
- testes;
- artefatos/documentos, se necessário.

### Tabela sugerida

| Mudança | Regra | Código/artefato afetado | Teste relacionado |
|---|---|---|---|
| CHG-0XX | regra 1 | serviço X | teste A |
| CHG-0XX | regra 2 | módulo Y | teste B |

### O que se espera
Que o grupo mostre que a mudança está conectada a:
- requisito;
- implementação;
- validação.

---

## Etapa 8 — Planejamento inicial da implementação

### Objetivo
Organizar uma sequência lógica para executar a mudança com menor risco.

### O grupo deve produzir
- um plano resumido com **5 a 8 passos**;
- justificativa da ordem;
- indicação de pontos críticos de teste ou revisão.

### Exemplo de estrutura
1. ajustar modelo de dados  
2. implementar regra central  
3. atualizar serviço principal  
4. atualizar interface  
5. ajustar notificações  
6. revisar relatórios  
7. criar e executar testes  

---

## Etapa 9 — Estratégia de validação da mudança

### Objetivo
Definir como a equipe verificará se a mudança foi implementada corretamente.

### O grupo deve produzir
Uma proposta de validação contendo:

- testes funcionais necessários;
- testes de regressão necessários;
- cenários principais;
- critérios de aceite.

### Exemplo do que pode aparecer
- cenário válido;
- cenário inválido;
- cenário com exceção;
- cenário de integração com outro módulo;
- cenário que verifica se uma funcionalidade anterior não foi quebrada.

---

## Etapa 10 — Reflexão final

### Objetivo
Consolidar a visão da mudança como processo completo.

### O grupo deve responder
1. Qual foi a parte mais difícil do ciclo de gestão de mudança?
2. Em qual etapa o risco técnico ficou mais evidente?
3. O que poderia dar errado se a equipe pulasse a análise de impacto?
4. O que poderia dar errado se a mudança não fosse rastreada adequadamente?
5. Por que gestão de mudança não deve ser confundida apenas com implementação?

---

# Entregáveis

Cada grupo deve entregar um documento contendo:

1. cenário escolhido;
2. refinamento da solicitação;
3. perguntas de esclarecimento;
4. registro inicial da mudança;
5. análise de impacto;
6. identificação de riscos;
7. tabela de priorização;
8. controle consolidado da mudança;
9. quadro de rastreabilidade;
10. plano inicial de implementação;
11. estratégia de validação;
12. reflexão final.


# Entrega
Utilize o link a seguir para a entrega: [https://forms.gle/DxqPJX5izkBzKkYZ9](https://forms.gle/DxqPJX5izkBzKkYZ9)