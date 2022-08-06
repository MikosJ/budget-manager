package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
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
        this.productList.get(productList.size() - 1).setType(type);


    }

    public void addIncome(Scanner sc) {
        System.out.println("Enter income:");
        this.balance += sc.nextDouble();
        System.out.println("Income was added!");
    }

    public void getBalance() {
        System.out.println(String.format("Balance $%.2f", this.balance));
    }

    public void menu(Scanner sc) {
        boolean execute = true;
        String type = null;
        while (execute) {
            System.out.println("\nChoose your action:\n1) Add income\n2) Add purchase\n3) Show list of purchases\n4) Balance\n5) Save\n6) Load\n0) Exit");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println();
                    this.addIncome(sc);
                    break;
                case 2:
                    System.out.println();
                    setTypeSubMenu();
                    break;
                case 3:
                    System.out.println();
                    this.getTypeSubMenu();
                    break;
                case 4:
                    System.out.println();
                    this.getBalance();
                    break;
                case 5:
                    System.out.println();
                    this.saveToFile();
                    break;
                case 6:
                    System.out.println();
                    loadPurchases();
                    break;
                case 0:
                    System.out.println("\nBye!");
                    execute = false;
                    break;

            }
        }
    }

    public void setTypeSubMenu() {
        boolean execute = true;
        String type;
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

    public void getTypeSubMenu() {
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
                if (Objects.equals(type, product.getType())) {
                    System.out.println(product.getName() + " $" + String.format("%.2f", product.getPrice()));
                }
                if (Objects.equals(type, "All")) {
                    System.out.println(product.getName() + " $" + String.format("%.2f", product.getPrice()));
                }
            }
            System.out.println(String.format("Total sum: $%.2f", getTotal(type)));
        } else {
            System.out.println("The purchase list is empty");
        }

    }

    private double getTotal(String type) {
        double sum = 0.0;
        for (Product product : this.productList) {
            if (Objects.equals(type, product.getType())) {
                double cost = product.getPrice();
                sum += cost;
            }
            if (Objects.equals(type, "All")) {
                double cost = product.getPrice();
                sum += cost;
            }
        }
        return sum;
    }

    private void saveToFile() {
        double balance = this.balance;
        try {
            FileWriter file = new FileWriter("purchases.txt");
            file.write(String.format("Balance $%.2f", balance));
            for (Product product :
                    this.productList) {
                file.write(String.format("\nName: %s, Price: $%.2f, Category: %s", product.getName(), product.getPrice(), product.getType()));
            }
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void loadPurchases() {
        this.productList.clear();
        File file = new File("purchases.txt");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String purchase = reader.nextLine();
                System.out.println(purchase);
                if (purchase.startsWith("Balance")) {
                    this.balance = Double.parseDouble(purchase.substring(purchase.indexOf("$") + 1));
                }
                if (purchase.startsWith("Name")) {

                    String productName = purchase.substring(purchase.indexOf(" ") + 1, purchase.indexOf(","));
                    String productPrice = purchase.substring(purchase.lastIndexOf("$") + 1, purchase.indexOf(", C"));
                    String productType = purchase.substring(purchase.lastIndexOf(" ") + 1);
                    this.productList.add(new Product(productName + " $" + productPrice));
                    this.productList.get(productList.size() - 1).setType(productType);

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
