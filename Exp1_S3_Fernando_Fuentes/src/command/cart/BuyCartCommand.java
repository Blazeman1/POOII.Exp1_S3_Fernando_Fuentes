
package command.cart;

import command.Command;
import model.ShoppingCart;

public class BuyCartCommand implements Command {
    private final ShoppingCart carrito;
    
    public BuyCartCommand(){
        this.carrito = ShoppingCart.getInstance();
    }
    
    @Override
    public void ejecutar(){
        if(carrito.getItems().isEmpty()){
            System.out.println("No hay productos en el carrito para comprar.");
            return;
        }
        
        double total = carrito.calculateTotal();
        System.out.println("Compra realizada.");
        System.out.println(carrito.toString());
        System.out.println("Total pagado: $" + String.format("%.2f", total));
        
        carrito.clear();
    }
    
    @Override
    public void deshacer(){
        System.out.println("La compra ha sido cancelada.");
    }
}
