package org.bilgeadam.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
    static Scanner sc = new Scanner(System.in);

    public static String getStringInput(String explaination) {
        System.out.println(explaination);
        return sc.nextLine();
    }

    public static int getIntegerInput(String explaination) {
        System.out.println(explaination);
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                OutputHelper.errorMessage("Hatalı giriş! Bir tamsayı girin:");
                sc.nextLine();
            }
        }
        return input;
    }

    public static Double getDoubleInput(String explaination) {
        System.out.println(explaination);
        double input;
        while (true) {
            try {
                input = sc.nextDouble();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                OutputHelper.errorMessage("Hatalı giriş! Tekrar girin:");
                sc.nextLine();
            }
        }
        return input;
    }

    public static Long getLongInput(String explaination) {
        System.out.println(explaination);
        long input;
        while (true) {
            try {
                input = sc.nextLong();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                OutputHelper.errorMessage("Hatalı giriş! Tekrar girin:");
                sc.nextLine();
            }
        }
        return input;
    }

}
