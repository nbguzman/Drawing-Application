package anpaint.Commands.Invokers;

import anpaint.Commands.Command;
import anpaint.Commands.LoadCommand;
import anpaint.Commands.SaveCommand;
import anpaint.DrawPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The InvokeLoad class acts as an invoker that creates and executes the
 * LoadCommand when an item has been clicked on the application
 */
public class InvokeLoad implements ActionListener {
    Command cmd;

    public InvokeLoad(DrawPanel dp) {
       cmd = new LoadCommand(dp);
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
