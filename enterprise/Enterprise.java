package Exercices;
import java.util.ArrayList;
import java.util.Arrays;

public class Enterprise {
    public static void main(String...args){
        displaySalesTable();
    }

    private static double[] sales = {
            1500,
            200,
            5000,
            12500.50,
            99999.90
    };

    public static void displaySalesTable(){
        System.out.printf("%s\n", "ENTERPRISE--------------------");
        System.out.printf("%s\n", "SALES-TABLE-------------------");
        System.out.printf("%-11s%-16s%-16s%-16s\n", "Seller", "Sales", "Bonus", "Total");

        int count = 1;
        for(double sale:sales){
            System.out.printf("#%-10dR$%-14.2fR$%-14.2fR$%-14.2f\n", count, sale, sale*0.09, 200+sale*0.09);
            count++;
        }
    }

}

