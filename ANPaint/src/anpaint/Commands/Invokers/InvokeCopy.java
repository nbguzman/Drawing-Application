package anpaint.Commands.Invokers;

import anpaint.Commands.Command;
import anpaint.Commands.CopyCommand;
import anpaint.Commands.LoadCommand;
import anpaint.DrawPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The InvokeCopy class acts as an invoker that creates and executes the
 * CopyCommand when an item has been clicked on the application
 */
public class InvokeCopy implements ActionListener {
    Command cmd;

    public InvokeCopy(DrawPanel dp) {
       cmd = new CopyCommand(dp);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
           cmd.execute();
        }
        catch(Exception ex)
        {
            System.out.println("InvokeCopy error, StackTrace:");
            ex.printStackTrace();
        }
    }
    
}
