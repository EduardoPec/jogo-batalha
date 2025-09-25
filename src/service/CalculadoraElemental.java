package service;

import model.TipoElemental;

public class CalculadoraElemental {
    
    public double calcularVantagem(TipoElemental atacante, TipoElemental defensor) {
        double danoPadrao = 1.0;
        
        if (atacante == TipoElemental.FOGO && defensor == TipoElemental.TERRA) return 1.5;
        if (atacante == TipoElemental.FOGO && (defensor == TipoElemental.AR || defensor == TipoElemental.AGUA)) return 0.75;
        
        if (atacante == TipoElemental.AR && defensor == TipoElemental.FOGO) return 1.5;
        if (atacante == TipoElemental.AR && defensor == TipoElemental.AGUA) return 0.75;

        if (atacante == TipoElemental.TERRA && defensor == TipoElemental.AGUA) return 1.5;
        if (atacante == TipoElemental.TERRA && defensor == TipoElemental.FOGO) return 0.75;

        if (atacante == TipoElemental.AGUA && (defensor == TipoElemental.AR || defensor == TipoElemental.FOGO)) return 1.5;
        if (atacante == TipoElemental.AGUA && defensor == TipoElemental.TERRA) return 0.75;

        if (atacante == TipoElemental.LUZ && defensor == TipoElemental.TREVA) return 1.5;
        if (atacante == TipoElemental.TREVA && defensor == TipoElemental.LUZ) return 1.5;
        
        return danoPadrao;
    }
}
