package com.javaexercices.probabilidade;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Amostra<Integer> S1 = new Amostra<>(List.of(1,2,3,4,5,6,7,8,9,10)); // Espaço Amostral (S)
        Integer E1 = 10; // Evento 1 (Simples)
        Set<Integer> E2 = Set.of(1,2,3,4, 5); // Evento 2 (Composto)

        var analizador = new Analizador<Integer>();
        double P1 = analizador.getProbabilidadeSimples(S1, E1); // Probabilidade 1
        double P2 = analizador.getProbabilidadeComposta(S1, E2); // Probabilidade 2

        System.out.println("Espaço Amostral S1:\n"+S1.getList()+"\n");
        System.out.printf(
                "P(%s) = %.2f%%\nPortanto, %s\n", E1, P1*100, analizador.getChance(P1));
        System.out.printf(
                "P(%s) = %.2f%%\nPortanto, %s\n", E2, P2*100, analizador.getChance(P2));

//        << Probabilidade com os mesmos Eventos em outra Amostra >>
        S1.filter(x->x%2==0);
        System.out.println("\nEspaço Amostral S2:\n"+S1.getList()+"\n");

        double P3 = analizador.getProbabilidadeSimples(S1, E1);
        double P4 = analizador.getProbabilidadeComposta(S1, E2);
        System.out.printf(
                "P(%s) = %.2f%%\nPortanto, %s\n", E1, P3*100, analizador.getChance(P1));
        System.out.printf(
                "P(%s) = %.2f%%\nPortanto, %s\n", E2, P4*100, analizador.getChance(P2));
    }
}
