package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TipoElemental;
import service.CalculadoraElemental;

public class CalculadoraElementalTest {
	
	private CalculadoraElemental calculadora;

	@BeforeEach
	void setUp() {
		calculadora = new CalculadoraElemental();
	}

	@Test
    void testarVantagem() {
        assertEquals(1.5, calculadora.calcularVantagem(TipoElemental.FOGO, TipoElemental.TERRA));
        assertEquals(1.5, calculadora.calcularVantagem(TipoElemental.AR, TipoElemental.FOGO));
        assertEquals(1.5, calculadora.calcularVantagem(TipoElemental.TERRA, TipoElemental.AGUA));
        assertEquals(1.5, calculadora.calcularVantagem(TipoElemental.AGUA, TipoElemental.AR));
        assertEquals(1.5, calculadora.calcularVantagem(TipoElemental.AGUA, TipoElemental.FOGO));
        assertEquals(1.5, calculadora.calcularVantagem(TipoElemental.LUZ, TipoElemental.TREVA));
        assertEquals(1.5, calculadora.calcularVantagem(TipoElemental.TREVA, TipoElemental.LUZ));
	}
	
    @Test
    void testarDesvantagem() {
        assertEquals(0.75, calculadora.calcularVantagem(TipoElemental.FOGO, TipoElemental.AGUA));
        assertEquals(0.75, calculadora.calcularVantagem(TipoElemental.FOGO, TipoElemental.AR));
        assertEquals(0.75, calculadora.calcularVantagem(TipoElemental.AR, TipoElemental.AGUA));
        assertEquals(0.75, calculadora.calcularVantagem(TipoElemental.TERRA, TipoElemental.FOGO));
        assertEquals(0.75, calculadora.calcularVantagem(TipoElemental.AGUA, TipoElemental.TERRA));
    }

    @Test
    void testarCasosNeutros() {
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.FOGO, TipoElemental.FOGO));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.FOGO, TipoElemental.LUZ));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.FOGO, TipoElemental.TREVA));
        
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.AGUA, TipoElemental.AGUA));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.AGUA, TipoElemental.LUZ));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.AGUA, TipoElemental.TREVA));
        
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.TERRA, TipoElemental.TERRA));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.TERRA, TipoElemental.AR));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.TERRA, TipoElemental.LUZ));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.TERRA, TipoElemental.TREVA));
        
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.AR, TipoElemental.AR));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.AR, TipoElemental.TERRA));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.AR, TipoElemental.LUZ));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.AR, TipoElemental.TREVA));
        
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.LUZ, TipoElemental.LUZ));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.LUZ, TipoElemental.FOGO));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.LUZ, TipoElemental.AGUA));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.LUZ, TipoElemental.TERRA));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.LUZ, TipoElemental.AR));
        
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.TREVA, TipoElemental.TREVA));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.TREVA, TipoElemental.FOGO));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.TREVA, TipoElemental.AGUA));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.TREVA, TipoElemental.TERRA));
        assertEquals(1.0, calculadora.calcularVantagem(TipoElemental.TREVA, TipoElemental.AR));
    }
}
