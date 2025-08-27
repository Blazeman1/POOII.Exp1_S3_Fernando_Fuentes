
package decorator;

public class Product implements Component {
    private String name;
    private double basePrice;
    private String category;

    public Product(String name, double basePrice, String category) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
    }
    
    public String getCategory(){
        return category;
    }
    
    @Override
    public double getPrice(){
        return basePrice;
    }
    
    @Override
    public String getDescription(){
        return name + " : $" + String.format("%.2f", basePrice) + " (" + category + ")";
    }
}
