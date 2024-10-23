package com.papiricoh.pokegame.util;

public class PrintUtils {

    public static void printInfo(String msg) {
        System.out.println(ConsoleColors.BLUE_BRIGHT + "(Info) " + ConsoleColors.RESET + msg);
    }

    public static void printWarning(String msg) {
        System.out.println(ConsoleColors.YELLOW + "(Warning) " + ConsoleColors.RESET + msg);
    }

    public static void printError(String msg) {
        System.out.println(ConsoleColors.RED + "(Error) " + ConsoleColors.RESET + msg);
    }

    public static void printSuccess(String msg) {
        System.out.println(ConsoleColors.GREEN + "(Success) " + ConsoleColors.RESET + msg);
    }

    public static void printComment(String msg) {
        System.out.println(ConsoleColors.PURPLE + "(Comment) " + ConsoleColors.RESET + msg);
    }
}
