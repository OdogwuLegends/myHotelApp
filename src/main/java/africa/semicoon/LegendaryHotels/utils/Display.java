package africa.semicoon.LegendaryHotels.utils;

import java.util.Scanner;

public class Display {
    public static void message(String message) { System.out.println(message); }
    public static void errorMessage(String message) { System.err.println(message); }

    public static int intInput(String prompt){
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String StringInput(String prompt){
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
