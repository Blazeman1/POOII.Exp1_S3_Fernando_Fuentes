
package decorator;

public abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }
    
    @Override
    public double getPrice(){
        return component.getPrice();
    }
    
    @Override
    public String getDescription(){
        return component.getDescription();
    }
}
