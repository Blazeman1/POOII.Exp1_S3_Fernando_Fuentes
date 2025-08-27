
package command.cart;

import command.Command;
import decorator.Component;
import model.ShoppingCart;

public class DeleteProductCommand implements Command {
    private final Component producto;
    private final ShoppingCart carrito;
    private boolean wasRemoved;
    
    public DeleteProductCommand(Component producto){
        this.producto = producto;
        this.carrito = ShoppingCart.getInstance();
    }
    
    @Override
    public void ejecutar(){
        wasRemoved = false;
        //Lógica para eliminar producto del carrito
        System.out.println("Producto eliminado: " + producto.getDescription());
    }
    
    @Override
    public void deshacer(){
        if(wasRemoved){
            System.out.println("Producto restaurado: " + producto.getDescription());
        }else{
            System.out.println("No se puede restaurar el producto yua que no fue eliminado: " + producto.getDescription());
        }
    }
}
