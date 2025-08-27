
package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String userId;
    private List<Product> products;
    private double total;
    private String status;
    
    public Order(String orderId, String userId){
        this.orderId = orderId;
        this.userId = userId;
        this.products = new ArrayList<>();
        this.total = 0;
        this.status = "Pendiente";
    }
    
    public void addProduct(Product product){
        products.add(product);
        total += product.getPrice();
    }
    
    public void removeProduct(Product product){
        products.remove(product);
        total -= product.getPrice();
    }
    
    // Getters
    public String getOrderId() { return orderId; }
    public String getUser() { return userId; }
    public List<Product> getProducts() { return products; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
    
    public void setTotal(double total) { this.total = total; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString(){
        return "Order #" + orderId + " - Total: $" + total + " - Status: " + status;
    }
}
