package com.javaexercices.probabilidade;

import java.util.List;
import java.util.Set;

public class Analizador<T> {
    public double getProbabilidadeSimples(Amostra<T> amostra, T evento){
        if (amostra.size()==0){
            return 0;
        }
        return (double) amostra.getCount(evento)/amostra.size();
    }

    public double getProbabilidadeComposta(Amostra<T> amostra, Set<T> evento){
        if (amostra.size()==0){
            return 0;
        }
        double P = 0;
        for (T E:evento){
            P+=(double)amostra.getCount(E)/amostra.size();
        }
        return P;
    }

    public Chance getChance(double probabilidade){
        return Chance.getChance(probabilidade);
    }

    public void printAnalize(){}
}
