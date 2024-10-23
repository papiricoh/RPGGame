package com.papiricoh.pokegame.util;

public class PrintUtils {

    public static void printInfo(String msg) {
        System.out.println(ConsoleColors.BLUE_BRIGHT + "(Info) " + ConsoleColors.RESET + msg);
    }

    public static void printWarning(String msg) {
        System.out.println(ConsoleColors.YELLOW + "(Warning) " + ConsoleColors.RESET + msg);
    }
}
