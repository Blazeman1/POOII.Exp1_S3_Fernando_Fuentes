
package model;

public class Product {
    private String id;
    private String name;
    private double price;
    private String category;
    
    public Product(String id, String name, double price, String category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category;}
    public void setPrice(double price) { this.price = price;}
    
    @Override
    public String toString() {
        return name + " [$" + price + ", " + category + "]";
    }
}
