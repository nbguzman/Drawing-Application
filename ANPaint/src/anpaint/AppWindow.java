package anpaint;

import anpaint.Commands.Command;
import anpaint.Commands.Invokers.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;

//singleton pattern
//this class will handle the application window and the button events
public class AppWindow extends JFrame {
    //single instance, used globally
    private static AppWindow _instance;
    //data used within the class
    private DrawPanel _drawPanel;
    private JComboBox _shapesDDL;
    private JComboBox _colourDDL;
    private JComboBox _lineWeightDDL;
    private JComboBox _lineStyleDDL;
    private String[] _shapes = { "Line", "Rectangle", "Circle", "Triangle" };
    private String[] _colours = { "Black", "Red", "Green", "Blue" };
    private String[] _weight = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    private String[] _style = { "Solid", "Dashed" };
    //a "history" of commands
    public Stack<Command> _cmds;
    public Stack<Command> _cmdsBackup;

    //singleton related methods
    private AppWindow() {
        _cmds = new Stack<>();
        _cmdsBackup = new Stack<>();
        _instance = this;
        buildFrame();
        buildMenu();
        buildToolbar();
        //pack();
    }

    // only 1 instance of window
    public static AppWindow getInstance() {
        if (_instance == null)
            _instance = new AppWindow();
        return _instance;
    }

    //clear backup (cannot redo when something new is done)
    public void clearBackup() {
        _cmdsBackup.clear();
    }

    public void addCommand(Command cmd) {
        _cmds.add(cmd);
    }

    //getters
    public String getShapeType() {
        return _shapesDDL.getSelectedItem().toString();
    }

    public String getColour() {
        return _colourDDL.getSelectedItem().toString();
    }

    public boolean getLineType() {
        return _lineStyleDDL.getSelectedItem().toString().equals("Solid") ? true : false;
    }

    public int getWeight() {
        int returnValue = 0;

        switch (_lineWeightDDL.getSelectedItem().toString()) {
            case "1":
                returnValue = 1;
                break;
            case "2":
                returnValue = 2;
                break;
            case "3":
                returnValue = 3;
                break;
            case "4":
                returnValue = 4;
                break;
            case "5":
                returnValue = 5;
                break;
            case "6":
                returnValue = 6;
                break;
            case "7":
                returnValue = 7;
                break;
            case "8":
                returnValue = 8;
                break;
            case "9":
                returnValue = 9;
                break;
            case "10":
                returnValue = 10;
                break;
        }

        return returnValue;
    }

    //window constructing methods
    private void buildFrame() {
        //the window positioning values
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)(dim.width * 0.8);
        int width = (int)(dim.height * 0.8);

        //instantiate a panel to draw shapes on
        _drawPanel = new DrawPanel(_instance);
        _drawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        //set all the frame properties
        this.setTitle("AN Paint");
        this.setSize(height, width);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocation((dim.width-(this.getSize().width))/2, (dim.height-(this.getSize().height))/2);
        this.add(_drawPanel);
    }

    private void buildMenu() {
        JMenuBar _menuBar;
        JMenu _file, _edit;
        JMenuItem _save, _load, _exit, _copy, _paste, _undo, _redo;
        //creating the bar at the top
        _menuBar = new JMenuBar();
        _file = new JMenu("File");
        _file.setMnemonic(KeyEvent.VK_F);
        _edit = new JMenu("Edit");
        _edit.setMnemonic(KeyEvent.VK_E);
        _menuBar.add(_file);
        _menuBar.add(_edit);

        //creating and adding the items to the file menu
        _save = new JMenuItem("Save");
        _save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        _load = new JMenuItem("Open");
        _load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        _exit = new JMenuItem("Exit");
        _exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        _save.addActionListener(new InvokeSave(_drawPanel));
        _load.addActionListener(new InvokeLoad(_drawPanel));
        _exit.addActionListener(new InvokeExit(_drawPanel));

        //add menuitems to menu
        _file.add(_save);
        _file.add(_load);
        _file.add(_exit);

        //creating and adding the items to the edit menu
        _copy = new JMenuItem("Copy");
        _copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        _paste = new JMenuItem("Paste");
        _paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        _undo = new JMenuItem("Undo");
        _undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        _redo = new JMenuItem("Redo");
        _redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

        //undo the last commands
        //have to use anonymous inner class to access _cmds
        _undo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //place undos into a command history to be able to
                //redo the commands within that history
                if (!_cmds.isEmpty())
                {
                    _cmdsBackup.add(_cmds.pop());
                    int i = _cmdsBackup.size() - 1;
                    _cmdsBackup.get(i).undo();
                }
            }
        });

        //redo the last commands
        //have to use anonymous inner class to access _cmds
        _redo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //execute the redo of the latest command stored in the history
                if (!_cmdsBackup.isEmpty())
                {
                    _cmds.add(_cmdsBackup.pop());
                    int i = _cmds.size() - 1;
                    _cmds.get(i).redo();
                }
            }
        });

        _edit.add(_copy);
        _edit.add(_paste);
        _edit.add(_undo);
        _edit.add(_redo);

        //add menu to the frame
        this.setJMenuBar(_menuBar);
    }

    private void buildToolbar() {
        JToolBar _toolBar;
        JButton _shapeTool;
        JButton _moveTool;
        JButton _resizeTool;
        JButton _group;
        JButton _ungroup;
        JButton _selectionTool;

        //creating the toolbar
        _toolBar = new JToolBar("Tool Palette");
        _toolBar.setLayout(new BoxLayout(_toolBar, BoxLayout.Y_AXIS));
        _toolBar.setFloatable(false);
        _toolBar.setRollover(true);

        //creating and adding the objects the the toolbar
        _toolBar.add(Box.createRigidArea(new Dimension(0,20)));

        //shape tool
        JLabel tempLbl = new JLabel("Shape Options");
        tempLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        _toolBar.add(tempLbl);
        _toolBar.add(Box.createRigidArea(new Dimension(0,20)));

        //shape options
        _shapesDDL = new JComboBox(_shapes);
        _shapesDDL.setSelectedIndex(0);
        setMaxSize(_shapesDDL);
        _toolBar.add(_shapesDDL);
        _toolBar.add(Box.createRigidArea(new Dimension(0,5)));

        _colourDDL = new JComboBox(_colours);
        _colourDDL.setSelectedIndex(0);
        setMaxSize(_colourDDL);
        _toolBar.add(_colourDDL);
        _toolBar.add(Box.createRigidArea(new Dimension(0,5)));

        _lineWeightDDL = new JComboBox(_weight);
        _lineWeightDDL.setSelectedIndex(0);
        setMaxSize(_lineWeightDDL);
        _toolBar.add(_lineWeightDDL);
        _toolBar.add(Box.createRigidArea(new Dimension(0,5)));

        _lineStyleDDL = new JComboBox(_style);
        _lineStyleDDL.setSelectedIndex(0);
        setMaxSize(_lineStyleDDL);
        _toolBar.add(_lineStyleDDL);
        _toolBar.add(Box.createRigidArea(new Dimension(0,20)));

        _shapeTool = new JButton("Shape Tool");
        _shapeTool.setAlignmentX(Component.CENTER_ALIGNMENT);
        _shapeTool.setMnemonic(KeyEvent.VK_D);

        //adding the action listener to know the shape tool is active
        _shapeTool.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                _drawPanel.changeState(PanelState.DRAW);
                _drawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        });

        _toolBar.add(_shapeTool);
        _toolBar.add(Box.createRigidArea(new Dimension(0,5)));

        _moveTool = new JButton("Move Tool");
        _moveTool.setAlignmentX(Component.CENTER_ALIGNMENT);
        _moveTool.setMnemonic(KeyEvent.VK_M);

        _moveTool.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                _drawPanel.changeState(PanelState.MOVE);
                _drawPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });

        _toolBar.add(_moveTool);
        _toolBar.add(Box.createRigidArea(new Dimension(0,5)));

        _resizeTool = new JButton("Resize Tool");
        _resizeTool.setAlignmentX(Component.CENTER_ALIGNMENT);
        _resizeTool.setMnemonic(KeyEvent.VK_R);

        _resizeTool.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                _drawPanel.changeState(PanelState.RESIZE);
                _drawPanel.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
            }
        });

        _toolBar.add(_resizeTool);
        _toolBar.add(Box.createRigidArea(new Dimension(0,5)));

        //selection Tool
        _selectionTool = new JButton("Selection Tool");
        _selectionTool.setAlignmentX(Component.CENTER_ALIGNMENT);
        _selectionTool.setMnemonic(KeyEvent.VK_S);

        //adding the action listener to know the selection tool is active
        _selectionTool.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                _drawPanel.changeState(PanelState.SELECT);
                _drawPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        _toolBar.add(_selectionTool);
        _toolBar.add(Box.createRigidArea(new Dimension(0,30)));

        //grouping options
        tempLbl = new JLabel("Gouping Options");
        tempLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        _toolBar.add(tempLbl);
        _toolBar.add(Box.createRigidArea(new Dimension(0,20)));

        _group = new JButton("Group");
        _group.setAlignmentX(Component.CENTER_ALIGNMENT);
        _group.setMnemonic(KeyEvent.VK_G);

        _group.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                _drawPanel.groupShapes();
            }
        });

        _toolBar.add(_group);
        _toolBar.add(Box.createRigidArea(new Dimension(0,5)));

        _ungroup = new JButton("Ungroup");
        _ungroup.setAlignmentX(Component.CENTER_ALIGNMENT);
        _ungroup.setMnemonic(KeyEvent.VK_U);

        _ungroup.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                _drawPanel.unGroupShapes();
            }
        });

        _toolBar.add(_ungroup);

        //add the toolbar to the panel within the frame
        this.add(_toolBar, BorderLayout.WEST);
    }

    protected void setCommands(Stack<Command> cmds)
    {
        _cmds = cmds;
    }

    protected void setCommandsBackup(Stack<Command> bu)
    {
        _cmdsBackup = bu;
    }

    protected void clearCommandsBackup()
    {
        _cmds.clear();
        _cmdsBackup.clear();
    }

    //changes the maximum size of a java component, so it doesn't stretch to fill the layout
    private void setMaxSize(JComponent jc) {
        Dimension max = jc.getMaximumSize();
        Dimension pref = jc.getPreferredSize();
        max.height = pref.height;
        jc.setMaximumSize(max);
    }
}