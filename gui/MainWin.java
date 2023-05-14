package gui;

import javax.swing.JFrame;           // for main window
import javax.swing.JOptionPane;      // for standard dialogs
import javax.swing.JTextField;
// import javax.swing.JDialog;          // for custom dialogs (for alternate About dialog)
import javax.swing.JMenuBar;         // row of menu selections
import javax.swing.JMenu;            // menu selection that offers another menu
import javax.swing.JMenuItem;        // menu selection that does something
import javax.swing.JToolBar;         // row of buttons under the menu
import javax.swing.JButton;          // regular button
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
// import javax.swing.JToggleButton;    // 2-state button
// import javax.swing.BorderFactory;    // manufacturers Border objects around buttons
import javax.swing.Box;              // to create toolbar spacer
// import javax.swing.UIManager;        // to access default icons
import javax.swing.filechooser.FileNameExtensionFilter;

import store.Store;                 // to access the store from last sprint
import store.Customer;
import store.Option;
import store.Computer;
import store.Order;

import javax.swing.JLabel;           // text or image holder
import javax.swing.ImageIcon;        // holds a custom icon
import javax.swing.SwingConstants;   // useful values for Swing method calls

import javax.imageio.ImageIO;        // loads an image from a file

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;                 // opens a file
// import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;          // reports an error reading from a file
import java.util.Arrays;
// import java.text.NumberFormat;
import java.awt.BorderLayout;        // layout manager for main window
// import java.awt.FlowLayout;          // layout manager for About dialog

import java.awt.Color;               // the color of widgets, text, or borders
import java.awt.Font;                // rich text in a JLabel or similar widget
import java.awt.image.BufferedImage; // holds an image loaded from a file

public class MainWin extends JFrame
{
    //oh boy
    public MainWin(String title)
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem anew       = new JMenuItem("New Store");
        JMenuItem quit       = new JMenuItem("Quit");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem saveas = new JMenuItem("Save As");
        // JMenuItem rules      = new JMenuItem("Rules");
        JMenu insert = new JMenu("Insert");
        JMenuItem cust = new JMenuItem("Customer");
        JMenuItem opt = new JMenuItem("Option");
        JMenuItem comp = new JMenuItem("Computer");
        JMenuItem order = new JMenuItem("Order");
        JMenu view = new JMenu("View");
        JMenuItem custs = new JMenuItem("Customers");
        JMenuItem opts = new JMenuItem("Options");
        JMenuItem comps = new JMenuItem("Computers");
        JMenuItem orders = new JMenu("Orders");
        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");

        anew .addActionListener(event -> onNewClick(title));
        save.addActionListener(event -> onSaveClick());
        open.addActionListener(event -> onOpenClick());
        saveas.addActionListener(event -> onSaveAsClick());
        quit .addActionListener(event -> onQuitClick());
        cust.addActionListener(event -> onInsertCustomerClick());
        opt.addActionListener(event -> onInsertOptionClick());
        comp.addActionListener(event -> onInsertComputerClick());
        custs.addActionListener(event -> onViewClick(Record.CUSTOMER));
        opts.addActionListener(event -> onViewClick(Record.OPTION));
        comps.addActionListener(event -> onViewClick(Record.COMPUTER));
        about.addActionListener(event -> onAboutClick());
        orders.addActionListener(event -> onViewClick(null));
        order.addActionListener(event -> onInsertOrderClick());
        

        file.add(anew);
        file.add(save);
        file.add(quit);
        file.add(open);
        file.add(saveas);
        insert.add(cust);
        insert.add(opt);
        insert.add(comp);
        view.add(custs);
        view.add(opts);
        view.add(comps);
        view.add(orders);
        help.add(about);
        
        menubar.add(file);
        menubar.add(insert);
        menubar.add(view);
        menubar.add(help);
        setJMenuBar(menubar);
        
        // // // ///////////// //////////////////////////////////////////////////////////
        // // // T O O L B A R
        // // // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("ELSA Controls");

        // // Create the 3 buttons using the icons provided
        JButton bAddCustomer = new JButton(new ImageIcon("gui/resources/add_customer.png"));
          bAddCustomer.setActionCommand("Insert Customer");
          bAddCustomer.setToolTipText("Insert Customer");
          toolbar.add(bAddCustomer);
          bAddCustomer.addActionListener(event ->onInsertCustomerClick());

        JButton bAddOption = new JButton(new ImageIcon("gui/resources/add_option.png"));
          bAddOption.setActionCommand("Insert Option");
          bAddOption.setToolTipText("Insert Option");
          toolbar.add(bAddOption);
          bAddOption.addActionListener(event -> onInsertOptionClick());

        JButton bAddComputer = new JButton(new ImageIcon("gui/resources/add_computer.png"));
          bAddComputer.setActionCommand("Insert Computer");
          bAddComputer.setToolTipText("Insert Computer");
          toolbar.add(bAddComputer);
          bAddComputer.addActionListener(event -> onInsertComputerClick());
        
        toolbar.add(Box.createHorizontalStrut(25));

        // // Create the 3 buttons using the icons provided
        JButton bViewCustomers = new JButton(new ImageIcon("gui/resources/view_customers.png"));
          bViewCustomers.setActionCommand("View Customer");
          bViewCustomers.setToolTipText("View Customers");
          toolbar.add(bViewCustomers);
          bViewCustomers.addActionListener(event ->onViewClick(Record.CUSTOMER));

        JButton bViewOptions = new JButton(new ImageIcon("gui/resources/view_options.png"));
          bViewOptions.setActionCommand("View Options");
          bViewOptions.setToolTipText("View Options");
          toolbar.add(bViewOptions);
          bViewOptions.addActionListener(event -> onViewClick(Record.OPTION));

        JButton bViewComputers = new JButton(new ImageIcon("gui/resources/view_computers.png"));
          bViewComputers.setActionCommand("View Computers");
          bViewComputers.setToolTipText("View Computers");
          toolbar.add(bViewComputers);
          bViewComputers.addActionListener(event -> onViewClick(Record.COMPUTER));
        
        getContentPane().add(toolbar, BorderLayout.PAGE_START);

        
        // /////////////////////////// ////////////////////////////////////////////
        // E L S A   D I S P L A Y
        // Provide a text entry box to show the remaining sticks
        display = new JLabel();
        display.setFont(new Font("SansSerif", Font.BOLD, 18));
        display.setVerticalAlignment(JLabel.TOP);
        add(display, BorderLayout.CENTER);

        // S T A T U S   B A R   D I S P L A Y ////////////////////////////////////
        // Provide a status bar for game messages
        msg = new JLabel();
        add(msg, BorderLayout.PAGE_END);
        
        // Make everything in the JFrame visible
        setVisible(true);
        
        // Start a new game
        // onNewGameClick();
        store = new Store("S  T  O  R  E");
    }
    
    // Listeners

    protected void onInsertOrderClick()
    {
        Customer selectedCust = null;
        Object[] customers = store.customers();
        if(customers.length == 0)
        {
            onInsertCustomerClick();
            msg.setFont(new JLabel().getFont());    // Reset to default font
            try
            {
                store.add(new Customer(
                    JOptionPane.showInputDialog(this, "Customer name", "New Customer", JOptionPane.QUESTION_MESSAGE),                // System.out.print(name);
                    JOptionPane.showInputDialog(null, null, null)
                    ));
                    onViewClick(Record.CUSTOMER);
                }
                catch(NullPointerException bad){}
                catch(Exception bad)
                {
                    JOptionPane.showMessageDialog(this, bad, 
                        "Customer Not Created!", JOptionPane.ERROR_MESSAGE);
                }        
        }

        else if(customers.length == 1)
        {
            //instance order
            selectedCust = (Customer) customers[0];

        }

        else
        //more than one customer
        {
            JComboBox < Customer > custList = new JComboBox<>(Arrays.copyOf(
                customers, customers.length, Customer[].class));
            int yes = JOptionPane.showConfirmDialog(this, custList, "Order for which customer?", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(yes == JOptionPane.OK_OPTION)
            {
                selectedCust = (Customer) custList.getSelectedItem();
            }
            else
            {
                return;
            }
        }

        Order newOrder = new Order(selectedCust);
        while(true)
        {
            JComboBox<Computer> computerComboBox = new JComboBox<Computer>();
            computerComboBox.insertItemAt(null, 0); // Allow user to select "no computer"
            int result = JOptionPane.showConfirmDialog(this, computerComboBox, "Add computer to order:", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                Computer selectedComputer = (Computer) computerComboBox.getSelectedItem();
                if (selectedComputer != null) {
                    newOrder.addComputer(selectedComputer);
                } else if (newOrder.computers.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select a computer to add to the order.");
                } else {
                    // Add order to store and exit loop
                    store.add(newOrder);
                    break;
                }
            }

            else
            {
                break;
            }
        }
    }

    protected void onSaveClick() { // Create a new game
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
        bw.write("COMPUTERS GALORE\n");
        bw.write("VERSION 1.0\n");
        store.save(bw); //don't know why this doesn't work
        } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Unable to open " + filename + '\n' + e, "Failed",
        JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void onOpenClick()
    {
        JFileChooser fc = new JFileChooser(filename); // Create a file chooser dialog
        FileNameExtensionFilter ElsaFiles = new FileNameExtensionFilter(" ELSA files", "ELSA");
        fc.addChoosableFileFilter(ElsaFiles); // Add "Elsa file" filter
        fc.setFileFilter(ElsaFiles); // Show ELSA files only by default
        int result = fc.showOpenDialog(this); // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile(); // Obtain the selected File object
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                store = new Store(br); // Open a new game
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open! " + filename + '\n' + e, "Failed",
            JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected void onNewClick(String name)
    {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename))) {
            br.write("New Store\n");
            } catch (Exception bad) {
            System.err.println("Failed to write: " + bad);
            }  
            if(name.isEmpty()) {
                name = JOptionPane.showInputDialog(this, "Store Name","NEW ELSA");
                if(name.isEmpty()) name = "ELSA STORE";
            }
            store = new Store(name);
            onViewClick(Record.CUSTOMER);
    }

    protected void onSaveAsClick()
    {
        final JFileChooser fc = new JFileChooser(filename); // Create a file chooser dialog
        FileNameExtensionFilter ElsaFiles = new FileNameExtensionFilter("ELSA files", "elsa");
        fc.addChoosableFileFilter(ElsaFiles); // Add "Elsa file" filter
        fc.setFileFilter(ElsaFiles); // Show ELSA files only by default
        int result = fc.showSaveDialog(this); // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
        filename = fc.getSelectedFile(); // Obtain the selected File object
        if(!filename.getAbsolutePath().endsWith(".elsa")) // Ensure it ends with ".elsa"
        filename = new File(filename.getAbsolutePath() + ".elsa");
        onSaveClick();
        }
    }

    // very efficient
    protected String[] UnifiedDialog(String[] fields, String title, String iconFilename) {
        // Returning null indicates Cancel or X was clicked
        String[] result = null;
        
        // Load the icon if available
        ImageIcon icon = null;
        try {
            if(iconFilename != null) 
                icon = new ImageIcon(iconFilename);
        } catch(Exception e) {
        }

        // Widgets will include a label and JTextField for each field
        Object[] widgets = new Object[2*fields.length];
            
        // Create the widget pairs           
        for(int i=0; i<fields.length; ++i) {
            widgets[2*i] = new JLabel("<html><br>" + fields[i] + "</html>");
            widgets[2*i+1] = new JTextField();
        }
        
        // Show the dialog
        int button = JOptionPane.showConfirmDialog(this, widgets, title,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
            
        // If the OK button was pressed, extract result from widgets
        if(button == JOptionPane.OK_OPTION) {
            result = new String[fields.length];
            for(int i=0; i<fields.length; ++i) {
                JTextField textField = (JTextField) widgets[2*i+1];
                result[i] = textField.getText();
            }
        } 
        return result;
    }
    
    protected void onInsertCustomerClick() {
        msg.setFont(new JLabel().getFont());    // Reset to default font
        try
        {
            String[] prompt = UnifiedDialog(new String[]{"Name", "Email"},
            "New Customer", "gui/resources/add_customer.png");
        
        if(prompt != null) {
            store.add(new Customer(prompt[0], prompt[1]));
            onViewClick(Record.CUSTOMER);
        }
            // store.add(new Customer(
            //     JOptionPane.showInputDialog(this, "Customer name", "New Customer", JOptionPane.QUESTION_MESSAGE),                // System.out.print(name);
            //     JOptionPane.showInputDialog(this, "Customer email", "New Customer", JOptionPane.QUESTION_MESSAGE)
                // int atIndex = email.indexOf('@', 0);
                // int atChecker = (atIndex >= 0) ? email.indexOf('.', atIndex) : -1;
                // if(atChecker == -1)
                // {
                //     throw new IllegalArgumentException("Invalid email address: " + email);
                // }
                // ));
            onViewClick(Record.CUSTOMER);
        }
        catch(NullPointerException bad){}
        catch(Exception bad)
        {
            JOptionPane.showMessageDialog(this, bad, 
                "Customer Not Created!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected void onInsertOptionClick()
    {
        try
        {
        //     store.add(new Option(
        //     JOptionPane.showInputDialog(this, "Option name", 
        //     "New Option", JOptionPane.QUESTION_MESSAGE),
        //     (long) (100.0 * Double.parseDouble(
        //     JOptionPane.showInputDialog(this, "Option cost", 
        //     "New Option", JOptionPane.QUESTION_MESSAGE)))
        // ));
        // double notString = Double.parseDouble(cost);
        // long realCost = (long) (notString*100);
        String[] prompt = UnifiedDialog(new String[]{"Name", "Cost"},
        "New Option", "gui/resources/add_option.png");
    
    if(prompt != null) {
        store.add(new Option(prompt[0], (long) (100.0 * Double.parseDouble(prompt[1]))));
        onViewClick(Record.OPTION);
    }
        }
        catch(NullPointerException bad) {}
        catch(Exception bad)
        {
            JOptionPane.showMessageDialog(this, bad, 
            "Customer Not Created!", JOptionPane.ERROR_MESSAGE);
        }
    }
            
    protected void onInsertComputerClick() {   // Enable / disable computer player
       
        // Java Swing requires action to visually indicate enabled / disabled button
        // try { 
            // Computer c = new Computer(
            //     JOptionPane.showInputDialog(this, "Computer name", 
            //     "New Computer", JOptionPane.QUESTION_MESSAGE),
            //     JOptionPane.showInputDialog(this, "Computer model", 
            //     "New Computer", JOptionPane.QUESTION_MESSAGE)
            // );
            ImageIcon icon = null;
            try 
            {
                icon = new ImageIcon("gui/resources/add_computer.png");
            } 
            catch(Exception e) {}
            try 
            {
                String[] result = UnifiedDialog(new String[]{"Computer Name", "Computer Model"},
                "New Computer", "gui/resources/add_computer.png");
                if(result == null) return;

            Computer c = new Computer(result[0], result[1]);
            JComboBox<Object> moreOpt = new JComboBox<>(store.options());
            int optionsAdded = 0; // Don't add computers with no options
            while(true) {
                int button = JOptionPane.showConfirmDialog(this, moreOpt, 
                    "Another Option?", JOptionPane.YES_NO_OPTION);
                if(button != JOptionPane.YES_OPTION)
                {
                    break;
                }
                    c.addOption((Option) moreOpt.getSelectedItem());
                    ++optionsAdded;
            if(optionsAdded > 0) {
                store.add(c);
                onViewClick(Record.COMPUTER);
            }
            }
        } catch(NullPointerException e) {
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e, 
                "Computer Not Created!", JOptionPane.ERROR_MESSAGE);
        }    
    }
    protected void onViewClick(Record record) {  // in view -> click
        String subtitle = null;
        Object[] list = store.customers();
        if(record == Record.CUSTOMER)
        {
            subtitle = "Our Trusted Customers";
            list = store.customers();
        }

        if(record == Record.OPTION) {
            subtitle = "Options for Computers";
            list = store.options();
         }
         if(record == Record.COMPUTER) {
             subtitle = "Quality Computers for Sale";
             list = store.computers();
         }
         if(record == Record.ORDER) {
             subtitle = "Orders Placed";
             list = store.orders();
         }

        //taken from rice bc this is more efficient
       StringBuilder viewer = new StringBuilder("<html>"
        + "<p><font size=+2>" + subtitle + "</font></p>"
        + "</br>"
        + "<ol>\n");
        // + "<li>First item in the numbered list</li>"
        // + "<li>Second item in the numbered list</li>"

        for(Object i : list) viewer.append("<li>" + i.toString().replaceAll("<","&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll("\n", "<br/>") + "</li>\n");
        viewer.append("</ol></html>");
        display.setText(viewer.toString());

        // JOptionPane.showMessageDialog(this, s, "The Rules of ELSA", JOptionPane.PLAIN_MESSAGE);
    }
    protected void onAboutClick() {                 // Display About dialog
        Canvas logo = new Canvas("compstore.png");
        // try {
        //     BufferedImage myPicture = ImageIO.read(new File("128px-Pyramidal_matches.png"));
        //     logo = new JLabel(new ImageIcon(myPicture));
        // } catch(IOException e) {
        // }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>ELSA</font></p>"
          + "<p>Exceptional Laptops and Supercomputers Always</p>"
          + "<p>Version 0.4</p>"
           + "</html>",
          SwingConstants.CENTER);

        JLabel artists = new JLabel("<html>"
          + "<br/><p>Copyright 2017-2023 by Fawaz Asif</p>"
          + "<p>Licensed under Gnu GPL 3.0</p><br/>"
          + "<p>Logo is a public domain image</p>"
        //   + "<p><font size=-2>https://commons.wikimedia.org/wiki/File:Pyramidal_matches.svg</font></p>"
        //   + "<p>Robot by FreePik.com, licensed for personal</p><p>and commercial purposes with attribution</p>"
        //   + "<p><font size=-2>https://www.freepik.com/free-vector/grey-robot-silhouettes_714902.htm</font></p>"
          + "</html>",
          SwingConstants.CENTER);
          
         JOptionPane.showMessageDialog(this, 
             new Object[]{logo, title, artists},
             "ELSA",
             JOptionPane.PLAIN_MESSAGE
         );
     }

/*
    // This is an alternate About dialog using JDialog instead of JOptionPane
    
    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog();
        about.setLayout(new FlowLayout());
        about.setTitle("The Game of ELSA");
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("128px-Pyramidal_matches.png"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>ELSA</font></p>"
          + "</html>");
        about.add(title);
        JLabel artists = new JLabel("<html>"
          + "<p>Version 1.4J</p>"
          + "<p>Copyright 2017-2023 by George F. Rice</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<p>Logo by M0tty, licensed under CC BY-SA 3.0</p>"
          + "<p><font size=-2>https://commons.wikimedia.org/wiki/File:Pyramidal_matches.svg</font></p>"
          + "<p>Robot by FreePik.com, licensed for personal</p><p>and commercial purposes with attribution</p>"
          + "<p><font size=-2>https://www.freepik.com/free-vector/grey-robot-silhouettes_714902.htm</font></p>"
          + "</html>");
        about.add(artists);
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        about.add(ok);
        
        about.setSize(450,400);
        about.setVisible(true);
     }
*/
    protected void onQuitClick() {System.exit(0);}   // Exit the game

    // private void setSticks() {              // Update display, robot move
    //     // s collects the status message
    //     String s = "";
        // 
        // If the robot is enabled and it's their turn, move the robot
    //     if(ELSA.sticksLeft() > 0) {
    //         if(computerPlayer.isSelected() && ELSA.currentPlayer() == 2) {
    //             int move = 1;
    //             try {
    //                 move = ELSA.optimalMove();
    //             } catch(Exception e) {
    //                 System.err.println("Invalid optimal move: " + e.getMessage());
    //             }
    //             s += "Robot plays " + move + ", ";
    //             ELSA.takeSticks(move);
    //         }
    //     }
        
    //     // Report who's turn it is, or (if all sticks gone) who won
        
    //     if (ELSA.sticksLeft() > 0) {
    //         s += "Player " + ELSA.currentPlayer() + "'s turn";
    //     } else {
    //         s += "Player " + ELSA.currentPlayer() +  " wins!";
    //         msg.setFont(new Font("SansSerif", Font.BOLD, 18));
    //     }
        
    //     // Display the collected status on the status bar
    //     msg.setText(s);

    //     // Update the visual display of sticks
    //     s = "";
    //     for(int i=0; i<ELSA.sticksLeft(); ++i) s += ("| ");
    //     s += "  (" + (ELSA.sticksLeft()) + " sticks)";
    //     sticks.setText(s);

    //     // Set sensitivity of the human stick selectors so user can't make an illegal move
    //     button1.setEnabled(ELSA.sticksLeft() > 0);
    //     button2.setEnabled(ELSA.sticksLeft() > 1);
    //     button3.setEnabled(ELSA.sticksLeft() > 2);
    // }
    
    private Store store;
    private File filename;
    private JLabel display;                  // Display of computer store
    private JLabel msg;                     // Status message display
}
