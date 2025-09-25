package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Criatura;
import model.Habilidade;
import model.Item;
import model.TipoElemental;
import service.BatalhaService;

public class Program {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("========= BATALHA DE CRIATURAS MÍSTICAS =========");
        System.out.println();
    
        System.out.println("=== CRIAÇÃO DA PRIMEIRA CRIATURA ===");
        Criatura criatura1 = criarCriatura(sc, 1);
        
        System.out.println();
        
        System.out.println("=== CRIAÇÃO DA SEGUNDA CRIATURA ===");
        Criatura criatura2 = criarCriatura(sc, 2);
        
        System.out.println();
        
        System.out.println("=== ADICIONAR HABILIDADES ===");
        adicionarHabilidades(sc, criatura1);
        adicionarHabilidades(sc, criatura2);
        
        Item item1 = new Item("Poção de Cura", "cura", 25);
        Item item2 = new Item("Poção de Cura", "cura", 25);
        criatura1.adicionarItem(item1);
        criatura2.adicionarItem(item2);
        
        System.out.println();
        
        BatalhaService batalha = new BatalhaService();
        batalha.lutar(criatura1, criatura2);
        
        sc.close();
    }
    
    private static Criatura criarCriatura(Scanner sc, int numero) {
        System.out.println("Criatura " + numero + ":");
        
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        
        System.out.println("Pontos de Vida (HP): ");
        int hp = sc.nextInt();
        
        System.out.println("Pontos de Ataque (ATK): ");
        int atk = sc.nextInt();
        
        System.out.println("Pontos de Defesa (DEF): ");
        int def = sc.nextInt();
        
        System.out.println("Velocidade: ");
        int velocidade = sc.nextInt();    
        sc.nextLine();
        
        TipoElemental tipo = escolherTipoElemental(sc);
        
        return new Criatura(nome, hp, atk, def, velocidade, tipo);
    }
    
    private static TipoElemental escolherTipoElemental(Scanner sc) {
        int escolha = 0;
        while (escolha < 1 || escolha > 6) {
            System.out.println("\nTipos Elementais Disponíveis:");
            System.out.println("1 - FOGO (Vantagem: Terra, Desvantagem: Água e Ar)");
            System.out.println("2 - ÁGUA (Vantagem: Fogo e Ar, Desvantagem: Terra)");
            System.out.println("3 - TERRA (Vantagem: Água, Desvantagem: Fogo)");
            System.out.println("4 - AR (Vantagem: Fogo, Desvantagem: Água)");
            System.out.println("5 - LUZ (Vantagem: Trevas, Desvantagem: Luz)");
            System.out.println("6 - TREVA (Vantagem: Luz, Desvantagem: Trevas)");
            
            System.out.println("Escolha o tipo elemental (1-6): ");
            escolha = sc.nextInt();
            sc.nextLine();
            
            if (escolha < 1 || escolha > 6) {
                System.out.println("Opção invalida! Tente novamente");
            }
        }
        
        switch (escolha) {
            case 1: return TipoElemental.FOGO;
            case 2: return TipoElemental.AGUA;
            case 3: return TipoElemental.TERRA;
            case 4: return TipoElemental.AR;
            case 5: return TipoElemental.LUZ;
            case 6: return TipoElemental.TREVA;
            default: return null;
        }
    }
    
    private static void adicionarHabilidades(Scanner sc, Criatura criatura) {
        System.out.println("Adicionando habilidades para " + criatura.getNome() + " (" + criatura.getTipo() + ")");
        
        List<Habilidade> habilidadesDisponiveis = criarHabilidadePorTipo(criatura.getTipo());
        
        System.out.println("=== Habilidades Disponiveis ===");
        for (int i=0; i<habilidadesDisponiveis.size(); i++) {
            Habilidade h = habilidadesDisponiveis.get(i);
            System.out.println((i + 1) + " - " + h.getNome() + " | Dano: " + h.getDano());
        }
        
        int totalEscolhas = Math.min(1, habilidadesDisponiveis.size());
        System.out.println("Escolha " + totalEscolhas + " habilidade(s) pelo numero: ");
        
        for (int i=0; i<totalEscolhas; i++) {
            System.out.println("Habilidade #" + (i+1) + ":");
            
            
            int opcao;
            while (true) {
                System.out.println("Digite o número da habilidade: ");
                opcao = sc.nextInt();
                
                if (opcao < 1 || opcao > habilidadesDisponiveis.size()) {
                    System.out.println("Número inválido. Escolha entre 1 e " + habilidadesDisponiveis.size());
                    continue;
                }
                
                Habilidade escolhida = habilidadesDisponiveis.get(opcao - 1);
                criatura.adicionarHabilidade(escolhida);
                
                System.out.println("Habilidade adicionada: " + escolhida.getNome());
                break;
            }
        }
    }
    
    private static List<Habilidade> criarHabilidadePorTipo(TipoElemental tipo) {
        List<Habilidade> list = new ArrayList<>();
        switch (tipo) {
        case FOGO:
            list.add(new Habilidade("Bola de Fogo", 12, TipoElemental.FOGO));
            list.add(new Habilidade("Explosão Ígnea", 18, TipoElemental.FOGO));
            list.add(new Habilidade("Lança-Chamas", 15, TipoElemental.FOGO));
            break;
        case AGUA:
            list.add(new Habilidade("Jato d'Água", 12, TipoElemental.AGUA));
            list.add(new Habilidade("Onda", 16, TipoElemental.AGUA));
            list.add(new Habilidade("Maremoto", 18, TipoElemental.AGUA));
            break;
        case TERRA:
            list.add(new Habilidade("Pedrada", 11, TipoElemental.TERRA));
            list.add(new Habilidade("Tremor", 17, TipoElemental.TERRA));
            list.add(new Habilidade("Areia Movediça", 8, TipoElemental.TERRA));
            break;
        case AR:
            list.add(new Habilidade("Corte de Vento", 12, TipoElemental.AR));
            list.add(new Habilidade("Tornado", 18, TipoElemental.AR));
            list.add(new Habilidade("Lâmina de Ar", 14, TipoElemental.AR));
            break;
        case LUZ:
            list.add(new Habilidade("Raio Sagrado", 16, TipoElemental.LUZ));
            list.add(new Habilidade("Clarão", 10, TipoElemental.LUZ));
            list.add(new Habilidade("Lamina Iluminada", 17, TipoElemental.TREVA));
            break;
        case TREVA:
            list.add(new Habilidade("Sombra Cortante", 13, TipoElemental.TREVA));
            list.add(new Habilidade("Dreno Sombrio", 15, TipoElemental.TREVA));
            list.add(new Habilidade("Cajado da Escuridão", 18, TipoElemental.TREVA));
            break;
        }
        return list;
    }
}