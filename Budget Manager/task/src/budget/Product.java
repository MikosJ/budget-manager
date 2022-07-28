package budget;

public class Product {
    private String name;
    private double price;

    public Product(String userInput) {
        this.name = userInput.substring(0, userInput.indexOf(" $"));
        this.price = Double.parseDouble(userInput.substring(userInput.indexOf("$")+1));
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}