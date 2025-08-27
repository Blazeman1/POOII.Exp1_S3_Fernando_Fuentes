
package decorator;

/**
 * Clase CategoryDiscount que extiende de Decorator
 * Esta clase aplica un descuento específico a una categoría de productos.
 * @author ffuen
 */
public class CategoryDiscount extends Decorator{
    // Atributos
    private String targetCategory;
    private double discountPercent;
    
        /**
     * Constructor que recibe un componente(producto) para aplicar un porcentaje de descuento
     * en base a una categoría concreta
     * @param component El componente a decorar.
     * @param targetCategory La categoría objetivo para el descuento.
     * @param discountPercent El porcentaje de descuento a aplicar.
     */
    public CategoryDiscount(Component component, String targetCategory, double discountPercent) {
        super(component);
        this.targetCategory = targetCategory;
        this.discountPercent = discountPercent;
    }
    
    // Métodos de la interfaz Component
    @Override
    public double getPrice(){
        double originalPrice = component.getPrice();
        
        // Verifica si el componente es un Product y aplicar descuento si coincide la categoría
        if(component instanceof Product){
            Product product = (Product) component;
            if(product.getCategory().equalsIgnoreCase(targetCategory)){
                return originalPrice * (1 - discountPercent / 100);
            }
        }
        // Si no es un Product o no coincide la categoría, devuelve el precio original.
        return originalPrice;
    }
    
    @Override
    public String getDescription(){
        String baseDescription = component.getDescription();
        
        // Verificar si el componente es un Product
        if(component instanceof Product){
            Product product = (Product) component;
            if(product.getCategory().equalsIgnoreCase(targetCategory)){
                return baseDescription + " - " + discountPercent + "% descuento categoría (" + targetCategory + ")";
            }
        }
        return baseDescription;
    }
    
    // Método para obtener información del descuento
    public String getDiscountInfo(){
        return "Descuento: " + discountPercent + "% para categoría: " + targetCategory;
    }
}
