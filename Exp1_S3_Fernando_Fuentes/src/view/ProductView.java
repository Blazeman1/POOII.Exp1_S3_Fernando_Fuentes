
package view;

import model.Product;
import java.util.List;

public class ProductView {
    public void displayProducts(List<Product> products){
        System.out.println("  --- Productos Disponibles ---");
        for(Product product : products){
            System.out.println("Id: " + product.getId() +
                    " | Nombre: " + product.getName() +
                    " | Precio: $" + product.getPrice() +
                    " | Categoría: " + product.getCategory());
        }
        System.out.println("  -----------------------------");
    }
    
    public void displayProductDetails(Product product){
        System.out.println("Detalles del producto:");
        System.out.println("Id: " + product.getId());
        System.out.println("Nombre: " + product.getName());
        System.out.println("Precio: $" + product.getPrice());
        System.out.println("Categoría: " + product.getCategory());
    }
}
