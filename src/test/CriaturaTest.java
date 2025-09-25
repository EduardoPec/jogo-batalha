package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Criatura;
import model.Habilidade;
import model.Item;
import model.TipoElemental;

public class CriaturaTest {	
	private Criatura criatura;
	
	@Test
	void testarDanoMaiorQueDefesa() {	
		criatura = new Criatura("Monstro", 100, 20, 10, 8, TipoElemental.FOGO);
		
		int dano = 25;
		int hpEsperado = 100 - (25-10);
		
		criatura.receberDano(dano);
		assertEquals(hpEsperado, criatura.getHp());
	}
	
	@Test
	void testarDanoMenorQueDefesaHpAcima50() {
		criatura = new Criatura("Monstro", 100, 20, 20, 8, TipoElemental.FOGO);
		
		criatura.receberDano(10);
		assertEquals(95, criatura.getHp());
	}
	
	@Test
	void testarDanoMenorQueDefesaHpAbaixo50() {
		criatura = new Criatura("Monstro", 40, 20, 20, 8, TipoElemental.FOGO);
		
		criatura.receberDano(10);
		assertEquals(39, criatura.getHp());
	}
	
	@Test
	void testarDanoMaiorQueHp() {
		criatura = new Criatura("Monstro", 50, 20, 20, 8, TipoElemental.FOGO);
		
		criatura.receberDano(100);
		assertEquals(0, criatura.getHp());
		assertFalse(criatura.estaVivo());
	}
	
	@Test
	void testarCurarAumentaHp() {
		criatura = new Criatura("Monstro", 100, 20, 10, 8, TipoElemental.FOGO);
		
		criatura.receberDano(40);
		criatura.curar(25);
		
		assertEquals(95, criatura.getHp());
	}
	
	@Test
	void testarGerenciarItens() {
		criatura = new Criatura("Monstro", 100, 20, 10, 8, TipoElemental.FOGO);
		Item item = new Item("Poção", "cura", 25);
		
		assertFalse(criatura.temItens());
		
		criatura.adicionarItem(item);
		assertTrue(criatura.temItens());
		assertEquals(item, criatura.getPrimeiroItem());
		
		criatura.removerItem(item);
		assertFalse(criatura.temItens());
	}
	
	@Test
	void testarGerenciarHabilidades() {
		criatura = new Criatura("Monstro", 100, 20, 10, 8, TipoElemental.FOGO);
		Habilidade habilidade = new Habilidade("Bola de Fogo", 15, TipoElemental.FOGO);
		
		assertFalse(criatura.temHabilidade());
		
		criatura.adicionarHabilidade(habilidade);
        assertTrue(criatura.temHabilidade());
        assertEquals(habilidade, criatura.getHabilidade());
        
        criatura.removerHabilidade(habilidade);
        assertFalse(criatura.temHabilidade());
	}
	
	@Test
	void testarEstaVivo() {
		criatura = new Criatura("Monstro", 100, 20, 10, 8, TipoElemental.FOGO);
		
		assertTrue(criatura.estaVivo());
		
		criatura.receberDano(200);
		assertFalse(criatura.estaVivo());
	}
}
