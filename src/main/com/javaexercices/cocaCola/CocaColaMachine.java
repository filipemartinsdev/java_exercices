package main.com.javaexercices.cocaCola;
import java.util.Scanner;

public class CocaColaMachine {
    public static void main(String[] args) {
        CocaColaMachine.start();
    }

    private static int wallet;

    private static final String[] PRODUCT_LIST = {
            "Coca-Cola 250ml",
            "Coca-Cola 350ml",
            "Coca-Cola 1L",
            "Coca-Cola 2L",
            "Coca-Cola 5L",
    };
    private static final int[] PRODUCT_PRICES = {
            10, 15, 30, 50, 100
    };
    private static final int[] COINS = {
            5, 10, 25, 50, 100
    };


//    SCRIPT INIT
    public static void start(){
        wallet = 0;
        System.out.println("------------------------------------------");
        System.out.println("COCA COLA MACHINE");
        printItems();
        System.out.println("------------------------------------------");
        System.out.print("How many cents do you have? ");
        wallet = getWallet();
        System.out.println("------------------------------------------");
        System.out.print("YOUR ORDER: ");
        int order = getOrder();
        paymentManager(order);
    }

    private static int getWallet(){
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }


//    GET AND AUTHENTICATE INPUT ( ORDER )
    private static int getOrder(){
        Scanner scan = new Scanner(System.in);

        int order = -1;
        boolean isOK = false;
        while(!isOK){
            order = scan.nextInt();
            if(order >= 1 && order <= 5){
                isOK = true;
            } else {
                System.out.print ("INVALID ORDER! TRY AGAIN: ");
            }
        }
        return order;
    }

//    GET AND AUTHENTICATE INPUT ( INSERTED COINS )
    private static int getCoin(){
        Scanner scan = new Scanner(System.in);

        boolean isOK = false;
        int out;

        do {
            out = scan.nextInt();
            if(1 <= out && out <= 5){
                out = COINS[out -1];
                isOK = true;
            } else {
                System.out.print("INVALID OPTION! TRY AGAIN: ");
            }
        } while (!isOK);

        return out;
    }


//    PAYMENT METHOD
    private static void paymentManager(int orderID){
        int productPrice = PRODUCT_PRICES[orderID-1];
        int devolution = 0;

        boolean haveMoney = true;

        if(wallet < productPrice) {
            System.out.println("YOU DON'T HAVE ENOUGH COINS.");
            haveMoney = false;
        }

        if(haveMoney) {
            System.out.println("------------------------------------------");
            System.out.printf(
                    "PRODUCT: %s\nPRICE: %d cents\n", PRODUCT_LIST[orderID-1], productPrice
            );
            System.out.println("------------------------------------------");

            int insertedCoins = 0;
            System.out.println("INSERT COINS!\n");
            do {
                System.out.println("INSERTED COINS: " + insertedCoins);
                printCoins();
                System.out.print("> ");
                insertedCoins += getCoin();
            } while(insertedCoins < productPrice);

            System.out.println("INSERTED COINS: " + insertedCoins);
            System.out.println("------------------------------------------");

            if(insertedCoins > productPrice) {
                devolution = insertedCoins - productPrice;

                System.out.println("DEVOLUTION: " + devolution + " cents");
            }
        }

        System.out.println("GOOD BYE!");
        System.out.println("------------------------------------------");
    }

//    PRINT PRODUCT LIST
    private static void printItems(){
        System.out.println("-------------------------------------------");
        for(int count = 0; count < PRODUCT_LIST.length; count++){
            System.out.printf("[%d] %-20s | %d cents\n", count+1, PRODUCT_LIST[count], PRODUCT_PRICES[count]);
        }
    }

//    PRINT COIN OPTIONS
    private static void printCoins(){
        for(int count = 0; count < COINS.length; count++){
            System.out.printf("[%d] %d cents\n", count+1, COINS[count]);
        }
    }
}
