
package controller;

import model.Product;
import model.ShoppingCart;
import view.ShoppingCartView;

public class ShoppingCartController {
    private ShoppingCart cart;
    private ShoppingCartView view;
    
    public ShoppingCartController(ShoppingCart cart, ShoppingCartView view){
        this.cart = cart;
        this.view = view;
    }
    
    public void addToCart(Product product){
        cart.addItem(product);
        System.out.println("Producto agregado al carrito: " + product.getName());
    }
    
    public void removeFromCart(Product product){
        cart.removeItem(product);
        System.out.println("Producto removido del carrito: " + product.getName());
    }
    
    public void displayCart(){
        view.displayCart(cart.getItems(), cart.calculateTotal());
    }
    
    public double getCartTotal(){
        return cart.calculateTotal();
    }
}
