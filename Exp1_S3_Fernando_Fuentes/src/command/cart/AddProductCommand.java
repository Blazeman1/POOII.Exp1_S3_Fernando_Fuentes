
package command.cart;

import command.Command;
import decorator.Component;
import model.ShoppingCart;

public class AddProductCommand implements Command {
    private final Component producto;
    private final ShoppingCart carritoCompras;
    
    public AddProductCommand(Component producto){
        this.producto = producto;
        this.carritoCompras = ShoppingCart.getInstance();
    }
    
    @Override
    public void ejecutar(){
        // Convertir Component a model.Product para el carrito MVC
        if(producto instanceof decorator.Product){
            decorator.Product decoratorProduct = (decorator.Product) producto;
            model.Product modelProduct = new model.Product(
                "ID-" + System.currentTimeMillis(),
                decoratorProduct.getDescription(),
                decoratorProduct.getPrice(),
                decoratorProduct.getCategory());
            carritoCompras.addItem(modelProduct);
            System.out.println("Producto agregado: " + producto.getDescription());
        }
    }
    
    @Override
            public void deshacer(){
                // Implementar lógica para remover producto si es necesario
                System.out.println("Operación de agregar producto deshecha.");
            }
    
}
