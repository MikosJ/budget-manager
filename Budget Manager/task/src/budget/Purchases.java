package budget;

import java.util.ArrayList;
import java.util.Scanner;

public class Purchases {
    ArrayList<Product> productList;
    double balance;
    Scanner sc = new Scanner(System.in);

    public Purchases() {
        this.productList = new ArrayList<>();
    }

    public void addPurchase() {
        System.out.println("Enter purchase name:");
        String name = sc.nextLine();
        System.out.println("Enter its price:");
        double price = sc.nextDouble();
        sc.nextLine();
        if (this.balance - price < 0) {
            this.balance = 0;
        } else {
            this.balance = this.balance - price;
        }
        System.out.println(name + " $" + price);
        this.productList.add(new Product(name + " $" + price));
        System.out.println("Purchase was added!");


    }

    public void addIncome(Scanner sc) {
        System.out.println("Enter income:");
        this.balance += sc.nextDouble();
        System.out.println("Income was added!");
    }

    public void getBalance() {
        System.out.println("Balance: $" + this.balance);
    }

    public void menu(Scanner sc) {
        boolean execute = true;

        while (execute) {
            System.out.println("\nChoose your action:\n1) Add income\n2) Add purchase\n3) Show list of purchases\n4) Balance\n0) Exit");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("");
                    this.addIncome(sc);
                    break;
                case 2:
                    System.out.println("");
                    this.addPurchase();
                    break;
                case 3:
                    System.out.println("");
                    this.printPurchases();
                    break;
                case 4:
                    System.out.println("");
                    this.getBalance();
                    break;
                case 0:
                    System.out.println("\nBye!");
                    execute = false;
                    break;

            }
        }
    }

    public void purchaseSubMenu(Scanner sc) {
        boolean execute = true;

        while (execute) {
            System.out.println("\nChoose the type of purchase:\n1) Food\n2) Clothes\n3) Entertainment\n4) Other\n5) Back");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("");

                    break;
                case 2:
                    System.out.println("");

                    break;
                case 3:
                    System.out.println("");

                    break;
                case 4:
                    System.out.println("");

                    break;
                case 5:

                    menu(sc);
                    break;

            }
        }
    }

    public void printPurchases() {
        if (!this.productList.isEmpty()) {
            for (Product product : this.productList) {
                System.out.println(product.getName() + " $" + String.format("%.2f", product.getPrice()));
            }
            System.out.println("Total sum: $" + getTotal());
        } else {
            System.out.println("The purchase list is empty");
        }

    }

    private double getTotal() {
        double sum = 0.0;
        for (Product product : this.productList) {
            double cost = product.getPrice();
            sum += cost;
        }
        return sum;
    }


}
