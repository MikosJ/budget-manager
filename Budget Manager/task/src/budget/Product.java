package budget;

public class Product implements Comparable<Product> {
    private final String name;
    private final double price;
    private String type;


    public Product(String userInput) {
        this.name = userInput.substring(0, userInput.lastIndexOf(" $"));
        this.price = Double.parseDouble(userInput.substring(userInput.lastIndexOf("$") + 1));
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
        return this.name;
    }

    @Override
    public int compareTo(Product o) {
        if (this.getPrice() > o.getPrice()) return -1;
        else if (o.getPrice() > this.getPrice()) return 1;
        return 0;
    }
}