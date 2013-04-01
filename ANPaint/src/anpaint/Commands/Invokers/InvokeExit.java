package anpaint.Commands.Invokers;

import anpaint.Commands.*;
import anpaint.DrawPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The InvokeExit class acts as an invoker that creates and executes the
 * ExitCommand when an item has been clicked on the application
 */
public class InvokeExit implements ActionListener {
    Command cmd;

    public InvokeExit(DrawPanel dp) {
       cmd = new ExitCommand(dp);
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
