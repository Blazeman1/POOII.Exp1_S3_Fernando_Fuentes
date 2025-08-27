
package command.discounts;

import command.Command;
import decorator.Component;
import decorator.TenPercentDiscount;

/**
 * Comando para aplicar un descuento general del 10% a un producto.
 * Implementa la interfaz Command.
 * @author ffuen
 */

public class ApplyGeneralDiscountCommand implements Command {
    // Atributos
    private Component product;
    private Component originalProduct;
    private boolean executed = false;
    
    /**
     * Constructor que inicializa el comando con el producto al que se le aplicará el descuento.
     * @param product Producto al que se le aplicará el descuento.
     */
    
    public ApplyGeneralDiscountCommand(Component product){
        this.product = product;
        this.originalProduct = product;
    }
    
    /**
     * Getters
     * Obtiene el producto actual con el descuento aplicado.
     * @return Producto con el descuento aplicado
     */
    
    public Component getProduct(){
        return product;
    }
    // Implementación de los métodos de la interfaz Command
    @Override
    public void ejecutar(){
        if(!executed){
            // Aplicamos el decorator de 10%
            product = new TenPercentDiscount(originalProduct);
            System.out.println("Descuento general aplicado: " + product.getDescription());
            System.out.println("Precio original: $ " + originalProduct.getPrice() +
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
            System.out.println("Descuento general removido: " + product.getDescription());
            System.out.println("Precio restaurado: $" + product.getPrice());
            executed = false;
        }else{
            System.out.println("No hay descuento aplicado para deshacer.");
        }
    }
}
