package util;
import com.sun.source.util.SourcePositions;

import java.util.Scanner;

public class Scan {

    public static String nextLine(){
        Scanner scan = new Scanner(System.in);
        String out = scan.nextLine();
        scan.close();
        return out;
    }

    public static int nextInt(){
        System.out.print("> ");
        Scanner scan = new Scanner(System.in);
        int out = scan.nextInt();
//        scan.close();
        return out;
    }
}
