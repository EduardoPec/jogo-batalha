package model;

public class Item {
	
	private String nome;
	private String tipo;
	private Integer valor;
	private boolean consumido;
	
	public Item(String nome, String tipo, Integer valor) {
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}
	
	public boolean foiConsumido() {
		return consumido;
	}

	public void usar(Criatura usuario, Criatura alvo) {
		if (consumido) {
			System.out.println("O item " + nome + " já foi usado!");
			return;
		}
		
		if ("cura".equals(tipo)) {
			usuario.curar(valor);
			System.out.println(usuario.getNome() + " recuperou " + valor + " de HP com " + nome + "!");
            consumido = true;
            return;
		}
	}
}
