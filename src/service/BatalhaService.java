package service;

import model.Criatura;
import model.Item;
import model.Habilidade;
import java.util.Scanner;

public class BatalhaService {

	public void lutar(Criatura a, Criatura b) {
        System.out.println("=== INÍCIO DA BATALHA ===");
        System.out.println(a.getNome() + " (" + a.getHp() + " HP) vs " + 
                          b.getNome() + " (" + b.getHp() + " HP)");
        
        Scanner scanner = new Scanner(System.in);
        int turno = 1;
        
        while (a.estaVivo() && b.estaVivo()) {
            System.out.println("\n------- Turno " + turno + " -------");

            Criatura atacante, defensor;
            if (a.getVelocidade() >= b.getVelocidade()) {
                atacante = a;
                defensor = b;
            } else {
                atacante = b;
                defensor = a;
            }

            System.out.println("\nVez de " + atacante.getNome());
            realizarAcao(atacante, defensor, scanner);

            if (!defensor.estaVivo()) {
                break;
            }

            System.out.println("\nVez de " + defensor.getNome());
            realizarAcao(defensor, atacante, scanner);
            
            turno++;

            if (turno > 50) {
                System.out.println("\n*** BATALHA INTERROMPIDA - MÁXIMO DE TURNOS ATINGIDO ***");
                break;
            }
        }

        scanner.close();

        if (a.estaVivo()) {
            System.out.println("\n=== " + a.getNome() + " VENCEU A BATALHA! ===");
        } else if (b.estaVivo()) {
            System.out.println("\n=== " + b.getNome() + " VENCEU A BATALHA! ===");
        } else {
            System.out.println("\n===== EMPATE! =====");
        }
    }

    public void realizarAcao(Criatura atacante, Criatura defensor, Scanner scanner) {
        if (!atacante.estaVivo()) {
            return;
        }

        System.out.println("Escolha sua ação:");
        System.out.println("1. Atacar");
        if (atacante.temItens()) {
            System.out.println("2. Usar item (" + atacante.getPrimeiroItem().getNome() + ")");
        }
        if (atacante.temHabilidade()) {
            System.out.println("3. Usar Habilidade (" + atacante.getHabilidade().getNome() + ")");
        }

        int escolha;

        escolha = scanner.nextInt();
       
        if (escolha == 2 && atacante.temItens()) {
            Item itemParaUsar = atacante.getPrimeiroItem();
            usarItem(atacante, atacante, itemParaUsar);
            atacante.removerItem(itemParaUsar);
        } else if (escolha == 3 && atacante.temHabilidade()) {
            Habilidade habilidadeParaUsar = atacante.getHabilidade();
            int hpAntes = defensor.getHp();
            usarHabilidade(atacante, defensor, habilidadeParaUsar);
            int dano = hpAntes - defensor.getHp();
            atacante.removerHabilidade(habilidadeParaUsar);
            
            System.out.println(atacante.getNome() + " usou " + habilidadeParaUsar.getNome() + "!");
            System.out.println(" e causou " + dano + " de dano! " +
                               "(HP restante: " + defensor.getHp() + ")");

            CalculadoraElemental calc = new CalculadoraElemental();
            double multiplicador = calc.calcularVantagem(habilidadeParaUsar.getTipo(), defensor.getTipo());
            
            if (multiplicador > 1.0) {
                System.out.println("  >> VANTAGEM ELEMENTAL! <<");
            } else if (multiplicador < 1.0) {
                System.out.println("  << DESVANTAGEM ELEMENTAL! >>");
            }
        } else {
            executarAtaque(atacante, defensor);
        }
    }

    public void executarAtaque(Criatura atacante, Criatura defensor) {
        if (!atacante.estaVivo() || !defensor.estaVivo()) {
            return;
        }

        int danoBruto = atacante.getAtk();

        CalculadoraElemental calc = new CalculadoraElemental();
        double multiplicador = calc.calcularVantagem(atacante.getTipo(), defensor.getTipo());
        int danoFinal = (int) (danoBruto * multiplicador);
        
        int hpAntes = defensor.getHp();
        defensor.receberDano(danoFinal);
        int dano = hpAntes - defensor.getHp();
        
	    System.out.println(atacante.getNome() + " atacou " + defensor.getNome() + 
                   " e causou " + dano + " de dano! " +
                   "(HP restante: " + defensor.getHp() + ")");

        if (multiplicador > 1.0) {
            System.out.println("  >> VANTAGEM ELEMENTAL! <<");
        } else if (multiplicador < 1.0) {
            System.out.println("  << DESVANTAGEM ELEMENTAL! >>");
        }
    }
    
    public void usarItem(Criatura usuario, Criatura alvo, Item item) {
        if (item != null && !item.foiConsumido()) {
            item.usar(usuario, alvo);
        }
    }
    
    public void usarHabilidade(Criatura usuario, Criatura alvo, Habilidade habilidade) {
    	if (habilidade != null && !habilidade.foiConsumido()) {
    		habilidade.aplicar(usuario, alvo);
    	}
    }
}