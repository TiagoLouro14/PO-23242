# 📊 XXL - Gestor de Folha de Cálculo (PO)

Este repositório contém o projeto "XXL", desenvolvido no âmbito da disciplina de **Programação com Objectos (2023/2024)**. O objetivo do projeto foi desenhar e implementar o núcleo (domain layer) e a interface de texto (UI layer) de uma aplicação de folhas de cálculo, aplicando os princípios fundamentais da Programação Orientada a Objetos (POO) e Padrões de Desenho (Design Patterns).

## 👥 Autor
* **Número de Aluno:** 104101
* **Grupo:** 9

## 🏆 Classificação Obtida
| Componente | Nota |
| :--- | :--- |
| **UML (3)** | 2.30 |
| **Avaliação Intermédia - EI (6)** | 3.42 (Man: 2.25 / Aut: 1.17) |
| **Avaliação Final - EF (11)** | 4.24 (Man: 3.30 / Aut: 0.94) |
| **Nota Final do Projeto (20)** | **9.96 / 20** |

## 📋 Resumo do Projeto

A aplicação **XXL** permite gerir uma folha de cálculo através de uma interface de texto. As principais características implementadas incluem:

*   **Manipulação de Células e Gamas:** Endereçamento e manipulação de blocos de células, permitindo definir conteúdos dinâmicos.
*   **Tipos de Conteúdo:** Suporte para literais (inteiros e cadeias de caracteres), referências a outras células, e funções aritméticas e de manipulação de texto (e.g., `ADD`, `SUB`, `AVERAGE`, `CONCAT`, `COALESCE`).
*   **Cut Buffer:** Área de transferência independente para a folha de cálculo, suportando as operações `Copiar`, `Cortar` e `Colar` mantendo a orientação da gama manipulada.
*   **Gestão de Estado e Persistência:** Serialização de objetos em Java para guardar e carregar o estado completo da sessão.
*   **Pesquisas Dinâmicas:** Consultas ao estado da folha por valores resultantes e por nomes de funções.
*   **Separação de Camadas:** Arquitetura estruturada dividindo a Lógica de Negócio (`xxl.core`) da Interface de Utilizador (`xxl.app`), permitindo que a camada de domínio ignore completamente a camada de interação.

## 🛠️ Stack Tecnológico e Padrões de Desenho
* **Linguagem:** Java
* **Design Patterns Aplicados:**
  * *Command* (estruturação dos menus da biblioteca `pt.tecnico.po.ui`).
  * *Visitor* (desacoplamento de operações sobre as células e os seus diferentes tipos de conteúdos).
  * *Strategy / Factory* (flexibilidade na representação em memória e instanciação de funções).
* **Gestão de Exceções:** Implementação robusta de exceções customizadas para representar estados anómalos tanto no núcleo como na comunicação com o utilizador.
