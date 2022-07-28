package budget;

import java.util.Scanner;

public class Menu {
    double balance = 0;

    Menu(Scanner sc) {
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter income:");
                this.balance += sc.nextDouble();
                System.out.printf("Income was added!");
            case 2:


            case 3:

        }
    }

}
