package ik.rpg.main.visuals;

import ik.rpg.main.GameLogic;

import java.util.Scanner;

public class GraphicMotor {
    public static Scanner scanner = new Scanner(System.in);

    //get user input
    public static int readInt(String prompt, int userChoices) {
        int input;

        do {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Please enter an integer!");
            }

        } while (input < 1 || input > userChoices);

        return input;
    }
    //simulate the console clear
    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    //separator
    public static void printSeparator(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    //print heading
    public static void printHeading(String title) {
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    //stop the game and wait input
    public static void anythingToContinue() {
        System.out.println("\nEnter anything to continue...");
        scanner.next();
    }
}
