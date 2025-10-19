package com.javaexercices.term;

import java.io.IOException;
import java.util.*;

public class Play {
    private final String targetWord;
    private int points = 100;

    public Play() throws IOException, InterruptedException {
        this.targetWord = WordApiClient.getRandomWord(6).toUpperCase();
    }

    public void init(){
        Scanner scan = new Scanner(System.in);
        System.out.println("+---------+");
        System.out.println("|  TERMO  |   6 Letras");
        System.out.println("+---------+\n");
//        System.out.println(targetWord);
        System.out.println("Start!\n>> 100 points");

        boolean isCorrect = false;
        while(!isCorrect){
            System.out.print("\n> ");
            String current = scan.next().toUpperCase();
            if(current.length()>this.targetWord.length()){
                System.out.println("too long");
                continue;
            } else if(current.length()<this.targetWord.length()){
                System.out.println("too small");
                continue;
            }

            for (int i=0; i<this.targetWord.length()*2;i++){
                System.out.print("-");
            }
            System.out.println();
            printWordCompare(this.targetWord, current);
            for (int i=0; i<this.targetWord.length()*2;i++){
                System.out.print("-");
            }
            System.out.println();
            if (targetWord.equals(current)) {
                isCorrect = true;
                System.out.println("Congratulations, you earned "+this.points+" points!");
            }
            else{
                this.points -= 5;
                System.out.println(">> "+this.points+" points ");
            }
        }
        scan.close();
    }

    private static void printWordCompare(String target, String current){
        char[] targetToArray = target.toCharArray();
        char[] currentToArray = current.toCharArray();
        char[] sortedTargetArray;
        sortedTargetArray = targetToArray.clone();

        Arrays.sort(sortedTargetArray);

        for(char c:currentToArray){
            System.out.print(c+" ");
        }
        System.out.print("\n");

        int count = 0;
        for(char c:currentToArray){
            if(c == targetToArray[count]){
                System.out.print("* ");
            }
            else if(Arrays.binarySearch(sortedTargetArray, c) < 0){
                System.out.print("x ");
            }
            else {
                System.out.print("^ ");
            }
            count++;
        }
        System.out.print("\n");

    }
}
