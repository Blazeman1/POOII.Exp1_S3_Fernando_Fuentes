
package command;

import java.util.ArrayList;
import java.util.List;

public class DiscountInvoker {
    private List<Command> commands = new ArrayList<>();
    
    public void addCommand(Command command){
        commands.add(command);
    }
    
    public void ejecutarComandos(){
        for(Command command : commands){
            command.ejecutar();
        }
    }
    
    public void deshacerComandos(){
        for(int i = commands.size() - 1; i >= 0; i--){
            commands.get(i).deshacer();
        }
    }
}
