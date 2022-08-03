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

    public void addPurchase(String type) {
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
        this.productList.get(productList.size()-1).setType(type);



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
        String type = null;
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
                    setTypeSubMenu();
                    break;
                case 3:
                    System.out.println("");
                    this.getTypeSubMenu();
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
    public void setTypeSubMenu(){
        boolean execute = true;
        String type = null;
        while (execute) {
            System.out.println("\nChoose the type of purchase\n1) Food\n2) Clothes\n3) Entertainment\n4) Other\n5) Back");
            int option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (option) {
                case 1:
                    type = "Food";
                    addPurchase(type);
                    break;
                case 2:
                    type = "Clothes";
                    addPurchase(type);
                    break;
                case 3:
                    type = "Entertainment";
                    addPurchase(type);
                    break;
                case 4:
                    type = "Other";
                    addPurchase(type);
                    break;
                case 5:
                    execute = false;
                    break;
            }
        }
    }
    public void getTypeSubMenu(){
        boolean execute = true;
        while (execute) {
            System.out.println("\nChoose the type of purchase\n1) Food\n2) Clothes\n3) Entertainment\n4) Other\n5) All\n6) Back");
            int option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (option) {
                case 1:
                    System.out.println("Food:");
                    printPurchases("Food");
                    break;
                case 2:
                    System.out.println("Clothes:");
                    printPurchases("Clothes");
                    break;
                case 3:
                    System.out.println("Entertainment:");
                    printPurchases("Entertainment");
                    break;
                case 4:
                    System.out.println("Other:");
                    printPurchases("Other");
                    break;
                case 5:
                    System.out.println("All:");
                    printPurchases("All");
                    break;
                case 6:
                    execute = false;
                    break;
            }

        }
    }

    public void printPurchases(String type) {
        if (!this.productList.isEmpty()) {
            for (Product product : this.productList) {
                if (type == product.getType()){
                    System.out.println(product.getName() + " $" + String.format("%.2f", product.getPrice()));
                }
                if (type == "All") {
                    System.out.println(product.getName() + " $" + String.format("%.2f", product.getPrice()));
                }
            }
            System.out.println("Total sum: $" + getTotal(type));
        } else {
            System.out.println("The purchase list is empty");
        }

    }

    private double getTotal(String type) {
        double sum = 0.0;
        for (Product product : this.productList) {
            if (type == product.getType()) {
                double cost = product.getPrice();
                sum += cost;
            }
            if (type == "All"){
                double cost = product.getPrice();
                sum += cost;
            }
        }
        return sum;
    }


}
