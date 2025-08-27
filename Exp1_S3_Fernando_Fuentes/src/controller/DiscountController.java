
package controller;

import model.DiscountManager;
import model.Product;
import view.DiscountView;

public class DiscountController {
    private DiscountManager discountManager;
    private DiscountView view;
    
    public DiscountController(DiscountView view){
        this.discountManager = DiscountManager.getInstance();
        this.view = view;
    }
    
    public double applyPercentageDiscount(Product product, double percentage){
        double originalPrice = product.getPrice();
        double discountedPrice = discountManager.applyDiscountToProduct(product, percentage);
        view.displayDiscountApplied(product, originalPrice, discountedPrice);
        
        // Actualiza el precio del producto
        product.setPrice(discountedPrice);
        
        return discountedPrice;
    }
    
    public double applyCategoryDiscount(Product product, String category, double percentage){
        double originalPrice = product.getPrice();
        double discountedPrice = discountManager.applyCategoryDiscountToProduct(product, category, percentage);
        
        if(discountedPrice != originalPrice){
            view.displayDiscountApplied(product, originalPrice, discountedPrice);
            // Actualiza el precio del producto
            product.setPrice(discountedPrice);
        }else{
            view.displayNoDiscountApplied(product);
        }
        
        return discountedPrice;
    }
    
    // Métodos para trabajar con componentes decorator si es necesario
    public decorator.Component applyGeneralDiscountToComponent(decorator.Component component){
        return discountManager.applyGeneralDiscount(component);
    }
    
    public decorator.Component applyCategoryDiscountToComponent(decorator.Component component, String category, double discount){
        return discountManager.applyCategoryDiscountToComponent(component, category, discount);
    }
}
