
package view;

import model.Product;

public class DiscountView {
    public void displayDiscountApplied(Product product, double originalPrice, double discountedPrice){
        System.out.println("  --- Descuento Aplicado ---");
        System.out.println("Producto: " + product.getName());
        System.out.println("Precio original: $" + originalPrice);
        System.out.println("Precio con descuento: $" + discountedPrice);
        System.out.println("Ahorro: $" + (originalPrice - discountedPrice));
        System.out.println("  --------------------------");
    }
    
    public void displayNoDiscountApplied(Product product){
        System.out.println("No se aplicó descuento al producto: " + product.getName());
    }
}
