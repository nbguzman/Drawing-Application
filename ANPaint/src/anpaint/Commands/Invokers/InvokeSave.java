package anpaint.Commands.Invokers;

import anpaint.Commands.Command;
import anpaint.Commands.ExitCommand;
import anpaint.Commands.SaveCommand;
import anpaint.DrawPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The InvokeSave class acts as an invoker that creates and executes the
 * SaveCommand when an item has been clicked on the application
 */
public class InvokeSave implements ActionListener {
    Command cmd;

    public InvokeSave(DrawPanel dp) {
       cmd = new SaveCommand(dp);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
           cmd.execute();
        }
        catch(Exception ex)
        {
            System.out.println("InvokeExit error, StackTrace:");
            ex.printStackTrace();
        }
    }
    
}
