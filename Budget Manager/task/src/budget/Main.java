package budget;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Purchases manager = new Purchases();

        manager.menu(sc);

    }
}