
package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static ShoppingCart instance;
    private List<Product> items;
    
    private ShoppingCart(){
        this.items = new ArrayList<>();
    }
    
    public static synchronized ShoppingCart getInstance(){
        if(instance == null){
            instance = new ShoppingCart();
        }
        return instance;
    }
    
    public void addItem(Product product){
        items.add(product);
    }
    
    public boolean removeItem(Product product){
        return items.remove(product);
    }
    
    public List<Product> getItems(){
        return items;
    }
    
    public double calculateTotal(){
        return items.stream().mapToDouble(Product::getPrice).sum();
    }
    
    public void clear(){
        items.clear();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Carrito de compras:\n");
        if(items.isEmpty()){
            sb.append("El carrito está vacío\n");
        }else{
            for(Product item : items){
                sb.append("- ").append(item.toString()).append("\n");
            }
            sb.append("Total: $").append(String.format("%.2f", calculateTotal()));
        }
        return sb.toString();
    }
}
