
package decorator;

public class TenPercentDiscount extends Decorator {
    public TenPercentDiscount(Component component){
        super(component);
    }
    
    @Override
    public double getPrice(){
        return component.getPrice() * 0.9;
    }
    
    @Override
    public String getDescription(){
        return component.getDescription() + " - 10% descuento general";
    }
}
