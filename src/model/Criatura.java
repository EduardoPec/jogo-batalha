package model;

import java.util.ArrayList;
import java.util.List;

public class Criatura {
	private String nome;
    private Integer hp;
    private Integer atk;
    private Integer def;
    private Integer velocidade;
    private TipoElemental tipo;
    
    private final List<Item> itens = new ArrayList<>();
    private final List<Habilidade> habilidades = new ArrayList<>();
    
	public Criatura() {
	}

	public Criatura(String nome, int hp, int atk, int def, int velocidade, TipoElemental tipo) {
		this.nome = nome;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.velocidade = velocidade;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public int getHp() {
		return hp;
	}
	
	public int getAtk() {
		return atk;
	}

	public int getDef() {
		return def;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public TipoElemental getTipo() {
		return tipo;
	}
	
	public void receberDano(int dano) {
		int danoFinal = dano - def;
		
		if (danoFinal <= 0) {        
	        if (hp >= 50) {
	        	danoFinal = 5;
	        }
	        else {
	        	danoFinal = 1;
	        }
	    }

		hp -= danoFinal;
		
	    if (hp < 0) {
	        hp = 0; 
	    }
	}
	
	public void curar(int quantidade) {
		hp += quantidade;
	}
	
	public boolean estaVivo() {
		return hp > 0;
	}
	
	public void adicionarItem(Item item) {
		itens.add(item);
	}
	
	public Item getPrimeiroItem() {
	    if (temItens()) {
	        return itens.get(0);
	    }
	    return null;
	}

	public void removerItem(Item item) {
	    itens.remove(item);
	}
	
	public boolean temItens() { 
		return !itens.isEmpty(); 
	}

	public void adicionarHabilidade(Habilidade habilidade) {
	    habilidades.add(habilidade);
	}
	
	public Habilidade getHabilidade() {
		if (temHabilidade()) {
			return habilidades.get(0);
		}
		return null;
	}
	
	public void removerHabilidade(Habilidade habilidade) {
		habilidades.remove(habilidade);
	}
	
	public boolean temHabilidade() {
	    return !habilidades.isEmpty();
	}
}
