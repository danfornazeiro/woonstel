## Como desenvolver o projeto na sua máquina:

1. Baixar JDK 21 e configurar na sua máquina.
2. Você deve usar o comando deixado abaixo na pasta onde você deseja salvar o projeto
```bash
git clone https://github.com/danfornazeiro/woonstel.git
```
3. Criar um banco de dados PostgreSQL com:
   - nome: woonstel
   - usuário: postgres
   - senha: 12345678
4. Abra sua IDE:
   - Eclipse
   - **IntelliJ Idea (Recomendado)**
   - VsCode
6. Rodar o projeto e testar as rotas.

---

# Sistema de Gestão Técnica de Condomínios

## Sobre o Projeto

O **Sistema de Gestão Técnica de Condomínios** é uma plataforma desenvolvida para organizar e otimizar o gerenciamento de problemas técnicos, manutenções e operações dentro de condomínios.

Atualmente, muitos condomínios utilizam grupos de mensagens para relatar problemas como lâmpadas queimadas, vazamentos, defeitos em portões ou elevadores. Esse modelo gera desorganização, pois as informações se perdem entre mensagens e não existe um registro estruturado das demandas.

Este sistema foi projetado para centralizar essas informações em um único ambiente, permitindo registrar ocorrências, acompanhar tarefas de manutenção e manter um histórico organizado das atividades realizadas no condomínio.

É importante destacar que o sistema **não é uma rede social para moradores** e **não tem como objetivo promover discussões ou comunicação social entre condôminos**.
O foco da plataforma é **exclusivamente a gestão técnica e operacional do condomínio**.

---

## Objetivo

O objetivo do sistema é melhorar a organização da administração condominial, oferecendo ferramentas que permitam:

* Registrar problemas técnicos de forma estruturada
* Acompanhar demandas de manutenção
* Definir prioridades e responsáveis
* Manter histórico de manutenções realizadas
* Melhorar a transparência da gestão para os moradores

Com isso, o síndico pode ter uma visão mais clara da situação da infraestrutura do condomínio e tomar decisões com base em informações organizadas.

---

## Principais Funcionalidades

### Registro de Problemas

Os moradores podem registrar problemas técnicos encontrados nas áreas comuns do condomínio.

Cada registro pode incluir:

* descrição do problema
* local onde ocorre
* categoria do problema
* foto opcional
* data de registro

Essas informações ficam armazenadas no sistema, evitando que se percam em conversas informais.

---

### Gestão de Demandas pelo Síndico

O síndico possui acesso a um painel administrativo onde pode:

* visualizar todos os problemas registrados
* criar tarefas de manutenção
* definir prioridades
* definir prazos
* estimar custos
* atribuir tarefas a funcionários ou empresas
* acompanhar o andamento das atividades

---

### Gestão de Tarefas

Cada ocorrência registrada pode se transformar em uma tarefa de manutenção.

As tarefas permitem controlar:

* responsável pela execução
* nível de prioridade
* prazo de resolução
* custo estimado
* histórico de atualizações

---

### Gestão de Funcionários

Funcionários do condomínio podem acessar o sistema para visualizar tarefas atribuídas.

Eles podem:

* visualizar tarefas pendentes
* marcar tarefas como em andamento
* marcar tarefas como concluídas
* registrar observações ou fotos do serviço realizado

---

### Cadastro de Empresas Terceirizadas

O sistema permite cadastrar empresas que prestam serviços ao condomínio, como:

* manutenção de elevadores
* serviços de limpeza
* manutenção elétrica
* jardinagem

Também é possível registrar histórico de serviços realizados e custos associados.

---

### Agenda de Serviços

O sistema possui uma agenda para organizar serviços programados, como:

* manutenção preventiva de elevadores
* limpeza de caixa d’água
* revisões elétricas
* serviços de jardinagem

Cada serviço pode registrar data, horário, empresa responsável e custo.

---

### Transparência para Moradores

Moradores podem visualizar informações relacionadas à gestão técnica do condomínio, como:

* problemas reportados
* tarefas em andamento
* tarefas concluídas
* histórico de manutenção
* agenda de serviços

Isso aumenta a transparência na administração.

---

### Reserva de Espaços

O sistema também permite a reserva de áreas comuns do condomínio.

Entre os espaços que podem ser reservados estão:

* churrasqueira
* salão de festas
* quadra esportiva
* espaço gourmet

Os moradores podem visualizar a disponibilidade e realizar reservas diretamente pelo sistema.

---

## Público-Alvo

O sistema foi projetado para atender principalmente:

* condomínios residenciais
* condomínios de pequeno, médio e grande porte
* condomínios horizontais (condomínios de casas)
* síndicos profissionais que administram múltiplos condomínios

---

## Benefícios do Sistema

Entre os principais benefícios da utilização da plataforma estão:

* organização das demandas de manutenção
* redução da perda de informações
* criação de histórico técnico do condomínio
* melhor planejamento de manutenção
* maior transparência para moradores
* apoio à tomada de decisões do síndico

---

## Diferencial do Projeto

Diferente de muitas plataformas existentes, este sistema não tem como foco principal a comunicação entre moradores ou a gestão financeira do condomínio.

O diferencial da proposta é o **foco específico na gestão técnica e manutenção da infraestrutura do condomínio**, permitindo um controle mais eficiente das demandas operacionais.

---

## Conclusão

O **Sistema de Gestão Técnica de Condomínios** busca resolver um problema comum na administração condominial: a falta de organização no registro e acompanhamento de problemas técnicos.

Ao substituir métodos informais por uma plataforma estruturada, o sistema permite uma gestão mais eficiente, transparente e profissional das atividades de manutenção.

Reforça-se que o sistema **não é uma plataforma de comunicação social entre moradores**, sendo voltado exclusivamente para **gestão técnica e operacional do condomínio**.
