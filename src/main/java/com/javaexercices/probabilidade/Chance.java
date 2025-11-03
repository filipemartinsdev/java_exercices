package com.javaexercices.probabilidade;

public enum Chance {
    CERTO, PROVAVEL, IMPROVAVEL, IMPOSSIVEL;

    public static Chance getChance(double probabilidade){
        if      (probabilidade == 1)  return CERTO;
        else if (probabilidade >= 0.5) return PROVAVEL;
        else if (probabilidade > 0)   return IMPROVAVEL;
        else return IMPOSSIVEL;
    }
}