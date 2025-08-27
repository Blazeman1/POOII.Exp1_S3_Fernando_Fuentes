
package command.discounts;

import command.Command;
import decorator.CategoryDiscount;
import decorator.Component;

/**
 * Comando para aplicar un descuento por categoría a un producto
 * Implementa la interfaz Command
 * @author ffuen
 */

public class ApplyCategoryDiscountCommand implements Command {
    // Atributos
    private Component product;
    private Component originalProduct;
    private final String category;
    private final double discount;
    private boolean executed = false;
    
    /**
     * Constructor del comando
     * @param product Producto al que se le aplicará el descuento.
     * @param category Categoría a la que se le aplicará el descuento.
     * @param discount Porcentaje de descuento a aplicar (0.0 - 100.0).
     */

    public ApplyCategoryDiscountCommand(Component product, String category, double discount){
        this.originalProduct = product;
        this.product = product;
        this.category = category;
        this.discount = discount;
    }
    
    /**
     * Getters
     * Obtiene el producto con el descuento aplicado
     * @return Producto con el descuento aplicado
     */
    
    public Component getProduct(){
        return product;
    }
    
    // Método de la interfaz Command
    @Override
    public void ejecutar(){
        if(!executed){
            // Aplicar el decorador de categoria
            product = new CategoryDiscount(originalProduct, category, discount);
            System.out.println("Descuento de categoría aplicado: " + product.getDescription());
            System.out.println("Precio original: $" + originalProduct.getPrice() +
                    " | Precio con descuento: $" + product.getPrice());
            executed = true;
        }else{
            System.out.println("El descuento ya fue aplicado anteriormente.");
        }
    }
    
    @Override
    public void deshacer(){
        if(executed){
            product = originalProduct;
            System.out.println("Descuento de categoría removido: " + product.getDescription());
            System.out.println("Precio restaurado: $" + product.getPrice());
            executed = false;
        }else{
            System.out.println("No hay descuento aplicado para deshacer.");
        }
    }
}
