
package view;

import model.Product;
import java.util.List;

public class ShoppingCartView {
    public void displayCart(List<Product> items, double total){
        System.out.println("  --- Carrito de Compras ---");
        if(items.isEmpty()){
            System.out.println("El carrito está vacío.");
        }else{
            for(Product item : items){
                System.out.println("Producto: " + item.getName() +
                        " | Precio: $" + item.getPrice());
            }
            System.out.println("Total: $" + String.format("%.2f", total));
        }
        System.out.println("  --------------------------");
    }
    
}
