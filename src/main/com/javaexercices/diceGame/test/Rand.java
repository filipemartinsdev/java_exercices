package test;
import java.lang.Math;
import java.util.Arrays;

public class Rand {
    public static void main(String...args){
        int[]foo = {1, 2, 2, 3, 4, 2, 2};
        Arrays.sort(foo);

        for(int i:foo){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
