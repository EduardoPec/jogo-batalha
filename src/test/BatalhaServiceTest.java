package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Criatura;
import model.Habilidade;
import model.Item;
import model.TipoElemental;
import service.BatalhaService;

public class BatalhaServiceTest {
	
    private BatalhaService svc;

    @BeforeEach
    void setUp() {
    	svc = new BatalhaService();
    }

	private Criatura criatura(String nome, int hp, int atk, int def, int vel, TipoElemental tipo) {
        return new Criatura(nome, hp, atk, def, vel, tipo);
    }

    @Test
    void testeAplicarDanoNeutro() {
        Criatura atacante = criatura("Monstro", 100, 20, 10, 15, TipoElemental.FOGO);
        Criatura defensor = criatura("Gorila", 80, 15, 5, 10, TipoElemental.FOGO); 

        svc.executarAtaque(atacante, defensor);
        assertEquals(65, defensor.getHp());
    }

    @Test
    void testeAplicarDanoComVantagem() {
        Criatura atacante = criatura("Monstro", 100, 20, 10, 15, TipoElemental.FOGO);
        Criatura defensor = criatura("Gorila", 80, 15, 5, 10, TipoElemental.TERRA);

        svc.executarAtaque(atacante, defensor);
        assertEquals(55, defensor.getHp());
    }

    @Test
    void testeAplicarDanoComDesvantagem() {
        Criatura atacante = criatura("Monstro", 100, 20, 10, 15, TipoElemental.FOGO);
        Criatura defensor = criatura("Gorila", 80, 15, 5, 10, TipoElemental.AGUA);

        svc.executarAtaque(atacante, defensor);
        assertEquals(70, defensor.getHp());
    }

    @Test
    void testeNaoDeveAtacarSeAtacanteMorto() {
        Criatura atacante = criatura("Monstro", 0, 999, 0, 1, TipoElemental.FOGO);
        Criatura defensor = criatura("Gorila", 100, 10, 5, 10, TipoElemental.AGUA);

        svc.executarAtaque(atacante, defensor);
        assertEquals(100, defensor.getHp());
    }

    @Test
    void testeNaoDeveAtacarSeDefensorMorto() {
        Criatura atacante = criatura("Monstro", 100, 20, 0, 1, TipoElemental.FOGO);
        Criatura defensor = criatura("Gorila", 0, 10, 5, 10, TipoElemental.AGUA);

        svc.executarAtaque(atacante, defensor);
        assertEquals(0, defensor.getHp());
    }

    @Test
    void testeAplicaDanoMinimo5() {
        Criatura atacante = criatura("Monstro", 100, 10, 0, 1, TipoElemental.FOGO);
        Criatura defensor = criatura("Gorila", 100, 10, 20, 10, TipoElemental.FOGO);
       
        svc.executarAtaque(atacante, defensor);
        assertEquals(95, defensor.getHp());
    }

    @Test
    void testeAplicaDanoMinimo1() {
        Criatura atacante = criatura("Monstro", 100, 10, 0, 1, TipoElemental.FOGO);
        Criatura defensor = criatura("Gorila", 49, 10, 20, 10, TipoElemental.FOGO);
   
        svc.executarAtaque(atacante, defensor);
        assertEquals(48, defensor.getHp());
    }

    @Test
    void testeDeveChamarItemUsar() {
        Criatura usuario = criatura("A", 60, 10, 2, 5, TipoElemental.FOGO);
        Criatura alvo = criatura("B", 100, 7, 5, 3, TipoElemental.AGUA);

        Item item = mock(Item.class);
        when(item.foiConsumido()).thenReturn(false);

        svc.usarItem(usuario, alvo, item);

        verify(item, times(1)).usar(usuario, alvo);
        verify(item, times(1)).foiConsumido();
    }

    @Test
    void testeNaoUsaItemConsumido() {
        Criatura usuario = criatura("A", 60, 10, 2, 5, TipoElemental.FOGO);
        Criatura alvo = criatura("B", 100, 7, 5, 3, TipoElemental.AGUA);

        Item item = mock(Item.class);
        when(item.foiConsumido()).thenReturn(true);

        svc.usarItem(usuario, alvo, item);

        verify(item, times(1)).foiConsumido();
        verify(item, never()).usar(usuario, alvo);
    }
    
    @Test
    void testeItemNuloNaoGeraExcecao() {
        Criatura usuario = criatura("A", 60, 10, 2, 5, TipoElemental.FOGO);
        Criatura alvo = criatura("B", 100, 7, 5, 3, TipoElemental.AGUA);

        svc.usarItem(usuario, alvo, null);
        assertTrue(true);
    }

    @Test
    void testeNaoAplicaHabilidadeConsumida() {
        Criatura usuario = criatura("A", 60, 10, 2, 5, TipoElemental.FOGO);
        Criatura alvo = criatura("B", 100, 7, 5, 3, TipoElemental.AGUA);

        Habilidade hab = mock(Habilidade.class);
        when(hab.foiConsumido()).thenReturn(true);

        svc.usarHabilidade(usuario, alvo, hab);

        verify(hab, times(1)).foiConsumido();
        verify(hab, never()).aplicar(usuario, alvo);
    }
    
    @Test
    void testeAplicaHabilidadeQuandoNaoConsumida() {
        Criatura usuario = criatura("A", 60, 10, 2, 5, TipoElemental.FOGO);
        Criatura alvo = criatura("B", 100, 7, 5, 3, TipoElemental.AGUA);

        Habilidade hab = mock(Habilidade.class);
        when(hab.foiConsumido()).thenReturn(false);

        svc.usarHabilidade(usuario, alvo, hab);

        verify(hab, times(1)).aplicar(usuario, alvo);
        verify(hab, times(1)).foiConsumido();
    }

    @Test
    void testeHabilidadeNulaNaoGeraExcecao() {
        Criatura usuario = criatura("A", 60, 10, 2, 5, TipoElemental.FOGO);
        Criatura alvo = criatura("B", 100, 7, 5, 3, TipoElemental.AGUA);

        svc.usarHabilidade(usuario, alvo, null);
        assertTrue(true);
    }
}

