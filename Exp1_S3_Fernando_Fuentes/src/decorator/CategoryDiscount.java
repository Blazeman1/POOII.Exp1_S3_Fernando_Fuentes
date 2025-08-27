
package decorator;

/**
 * Clase CategoryDiscount que extiende de Decorator
 * Esta clase aplica un descuento espec�fico a una categor�a de productos.
 * @author ffuen
 */
public class CategoryDiscount extends Decorator{
    // Atributos
    private String targetCategory;
    private double discountPercent;
    
        /**
     * Constructor que recibe un componente(producto) para aplicar un porcentaje de descuento
     * en base a una categor�a concreta
     * @param component El componente a decorar.
     * @param targetCategory La categor�a objetivo para el descuento.
     * @param discountPercent El porcentaje de descuento a aplicar.
     */
    public CategoryDiscount(Component component, String targetCategory, double discountPercent) {
        super(component);
        this.targetCategory = targetCategory;
        this.discountPercent = discountPercent;
    }
    
    // M�todos de la interfaz Component
    @Override
    public double getPrice(){
        double originalPrice = component.getPrice();
        
        // Verifica si el componente es un Product y aplicar descuento si coincide la categor�a
        if(component instanceof Product){
            Product product = (Product) component;
            if(product.getCategory().equalsIgnoreCase(targetCategory)){
                return originalPrice * (1 - discountPercent / 100);
            }
        }
        // Si no es un Product o no coincide la categor�a, devuelve el precio original.
        return originalPrice;
    }
    
    @Override
    public String getDescription(){
        String baseDescription = component.getDescription();
        
        // Verificar si el componente es un Product
        if(component instanceof Product){
            Product product = (Product) component;
            if(product.getCategory().equalsIgnoreCase(targetCategory)){
                return baseDescription + " - " + discountPercent + "% descuento categor�a (" + targetCategory + ")";
            }
        }
        return baseDescription;
    }
    
    // M�todo para obtener informaci�n del descuento
    public String getDiscountInfo(){
        return "Descuento: " + discountPercent + "% para categor�a: " + targetCategory;
    }
}
