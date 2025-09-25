package model;

import service.CalculadoraElemental;

public class Habilidade {
	
	private String nome;
	private Integer dano;
	private boolean consumido;
	private TipoElemental tipo;

	public Habilidade(String nome, Integer dano, TipoElemental tipo) {
		this.nome = nome;
		this.dano = dano;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}
	
	public Integer getDano() {
		return dano;
	}

	public boolean foiConsumido() {
		return consumido;
	}

	public TipoElemental getTipo() {
		return tipo;
	}

	public int aplicar(Criatura atacante, Criatura defensor) {
		CalculadoraElemental calc = new CalculadoraElemental();
        double multiplicador = calc.calcularVantagem(this.getTipo(), defensor.getTipo());
        
		int danoBruto = this.getDano() + atacante.getAtk();
        int danoFinal = (int) (danoBruto * multiplicador);
        
        defensor.receberDano(danoFinal);
        return danoFinal;
	}
}