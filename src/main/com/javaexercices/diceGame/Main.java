import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String...args){
        Scanner scan = new Scanner(System.in);

        Dice d6 = new Dice(6);

        System.out.print("How many times to roll? ");
        int x = scan.nextInt();

        d6.roll(x);
        d6.displayResults();
    }


    public static class Dice {
        private final int sides;
        private int[] results;
        private int[] frequence;

        public Dice(int sides){
            this.sides = sides;
            this.frequence = new int[sides*2];
        }

        public void roll(int rolls){
            this.results = new int[rolls];

            System.out.printf("\nRolling %d times\n", rolls);
            System.out.println("Generating results...");

            for(int i = 0; i < rolls; i++){
//                Calc sum of 2 dices rolled
                int result = ((int)(Math.random()*sides+1))+((int)(Math.random()*sides+1));
                this.results[i]= result;

                frequence[--result]++;
//                System.out.println(result);
            }

//            for(int i:results){
//                frequence[--i]++;
//            }
        }

        public void displayResults(){
            System.out.println("\nRESULT-SHOWCASE----------------");
            System.out.printf("%-10s%-10s\n", "Result", "Occurrences");

            int index=1;
            int count=2;
            while(index<this.frequence.length){
                System.out.printf("%-10d%-10d\n", count, frequence[index]);
                count++;
                index++;
            }

            System.out.println("--------------------------------");

            System.out.println("\nRANK----------------------------");
            Arrays.sort(frequence);

            count=0;
            index=0;

            System.out.printf("%-10s%-10s\n", "Result", "Occurrences");
            while(index<frequence.length){
                System.out.printf("%-10d%-10d\n", count, frequence[index]);
                index++;
            }
        }
    }

}
