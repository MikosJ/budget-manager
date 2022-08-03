package budget;

public class Product {
    private final String name;
    private final double price;
    private String type;


    public Product(String userInput) {
        this.name = userInput.substring(0, userInput.indexOf(" $"));
        this.price = Double.parseDouble(userInput.substring(userInput.indexOf("$") + 1));
    }

    public void setType(String providedType) {
        this.type = providedType;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}