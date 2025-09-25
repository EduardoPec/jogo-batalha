# 🐉 Jogo de Batalhas Místicas  

Um simulador de batalhas em Java, no estilo RPG, onde criaturas com atributos únicos, itens e habilidades especiais se enfrentam em duelos estratégicos.  

---

## ⚔️ Funcionalidades
- Criação de criaturas personalizadas (HP, ataque, defesa, velocidade e tipo elemental).  
- Sistema de **tipos elementais** com vantagens e desvantagens (Fogo, Água, Terra, Ar, Luz e Trevas).  
- Mecânica de **itens de batalha** (poções, bombas, frascos elementais, etc.) que aplicam cura, dano ou efeitos de status.  
- **Habilidades especiais** que adicionam mais profundidade à luta.  
- Regras de cálculo de dano com base em ataque, defesa e modificadores elementais.  
- Sistema de turnos definido pela velocidade das criaturas.  
- Tratamento de efeitos contínuos (ex.: queimadura, enraizamento, confusão).  

---

## 🛠️ Tecnologias Utilizadas
- **Java** (JDK 21+)  
- Programação orientada a objetos (POO)  
- Princípios de design inspirados no **SOLID**  
- **JUnit 5 + Mockito** para testes unitários  

---

## 📂 Estrutura do Projeto
- src/
- ├── application/ -> Classe principal (Program.java)
- ├── model/ -> Classes de domínio (Criatura, Item, Habilidade, TipoElemental, etc.)
- └── service/ -> Regras de negócio (BatalhaService, cálculos de dano, efeitos, etc.)
- test/
- └── java/ -> Testes unitários com JUnit e Mockito

---

## 🎮 Como Executar
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/JogoBatalhas.git
