
package model;

import decorator.CategoryDiscount;
import decorator.Component;
import decorator.TenPercentDiscount;

public class DiscountManager {
    private static DiscountManager instance;
    
    private DiscountManager(){
        // Constructor privado para prevenir instanciación
    }
    
    public static synchronized DiscountManager getInstance(){
        if(instance == null){
            instance = new DiscountManager();
        }
        return instance;
    }
    
    // Métodos para componentes decorator
    public Component applyGeneralDiscount(Component product){
        return new TenPercentDiscount(product);
    }
    
    public Component applyCategoryDiscountToComponent(Component product, String category, double discount){
        return new CategoryDiscount(product, category,discount);
    }
    
    // Métodos para productos del modelo (sin decorator)
    public double applyDiscountToProduct(Product product, double discountPercentage){
        double discountedPrice = product.getPrice() * (1 - discountPercentage / 100);
        return Math.round(discountedPrice * 100.0) / 100.0;
    }
    
    public double applyCategoryDiscountToProduct(Product product, String category, double discountPercentage){
        if(product.getCategory().equalsIgnoreCase(category)){
            return applyDiscountToProduct(product, discountPercentage);
        }
        return product.getPrice();
    }
}
