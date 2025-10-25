package com.javaexercices.estatistica;

import java.util.Arrays;

public class Estatistica {
    public static double media(double[] amostra){
        return Arrays.stream(amostra)
                .sum()/amostra.length;
    }

    public static double varianciaP(double[] amostra, double media){
        return Arrays.stream(amostra)
                .map(x->Math.pow(x-media, 2))
                .sum()/amostra.length;

//        double result = 0;
//        for (double v : amostra) {
//            result += Math.pow(v - media, 2);
//        }
//        return result/amostra.length;
    }

    public static double varianciaA(double[] amostra, double media){
        return Arrays.stream(amostra)
                .map(x->Math.pow(x-media, 2))
                .sum()/(amostra.length-1);

//        double result = 0;
//        for (double v : amostra) {
//            result += Math.pow(v - media, 2);
//        }
//        return result/(amostra.length-1);
    }

    public static double desvioPadrao(double variancia){
        return Math.sqrt(variancia);
    }

    public static double coeficienteVariacao(double desvioPadrao, double media){
        return desvioPadrao/media*100;
    }

    public static void printAnlyze(double[] amostra){
        double media = Estatistica.media(amostra);
        double v = Estatistica.varianciaP(amostra, media);
        double dp = Estatistica.desvioPadrao(v);
        double cv = Estatistica.coeficienteVariacao(dp, media);

        System.out.printf("x = %.2f\n", media);
        System.out.printf("V²p = %.2f\n",v);
        System.out.printf("Dp = +-%.2f\n", dp);
        System.out.printf("Cv = %.2f%%\n", cv);
    }
}
