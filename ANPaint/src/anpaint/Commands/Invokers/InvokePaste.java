package anpaint.Commands.Invokers;

import anpaint.Commands.Command;
import anpaint.Commands.CopyCommand;
import anpaint.Commands.PasteCommand;
import anpaint.DrawPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The InvokePaste class acts as an invoker that creates and executes the
 * PasteCommand when an item has been clicked on the application
 */
public class InvokePaste implements ActionListener {
    Command cmd;

    public InvokePaste(DrawPanel dp) {
       cmd = new PasteCommand(dp);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
           cmd.execute();
        }
        catch(Exception ex)
        {
            System.out.println("InvokePaste error, StackTrace:");
            ex.printStackTrace();
        }
    }
}
