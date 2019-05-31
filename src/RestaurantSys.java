/**
 * Created by De Jong Yeong on 15/11/2016.
 * @author De Jong Yeong
 */

import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.border.TitledBorder;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class RestaurantSys extends JFrame implements ActionListener {

    //declaring attributes
    JMenu fileMenu;
    JMenu staffMenu;
    JMenu dishMenu;
    JMenu salesMenu;
    JLabel imgLabel;
    JLabel dateLabel;
    JLabel welcomeLabel;
    JPanel buttonC;
    JPanel wlcPanel;
    JButton staffButton;
    JButton dishButton;
    JButton salesButton;
    BufferedImage img; //from package java.awt.image
    TitledBorder titledBorder;
    ArrayList<Staff> staffs = new ArrayList<Staff>();
    LinkedList<Cuisine> cuisines = new LinkedList<Cuisine>();
    ArrayList<Sale> sales = new ArrayList<Sale>();
    private Staff staff;
    private Cuisine cuisine;
    private Sale sale;

    //constructor
    public RestaurantSys() {

        setTitle("Restaurant System");
        setSize(400, 310);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //WindowConstants is an interface (javax.swing)
        setLocationRelativeTo(null);
        setResizable(false);

        setIconImage(new ImageIcon("C:\\Users\\User\\Desktop\\OOP2\\RestaurantSys\\src\\logo.png").getImage());

        Container cPane = getContentPane();
        cPane.setLayout(new FlowLayout());

        createFileMenu();
        createStaffMenu();
        createDishMenu();
        createSalesMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.LIGHT_GRAY);
        menuBar.add(fileMenu);
        menuBar.add(staffMenu);
        menuBar.add(dishMenu);
        menuBar.add(salesMenu);

        wlcPanel = new JPanel();
        wlcPanel.add(Box.createVerticalStrut(10)); //creates an invisible, fixed-height component belongs to javax.swing.Box
        wlcPanel.setLayout(new BoxLayout(wlcPanel, BoxLayout.Y_AXIS)); //allows multiple components to laid out vertically from top to bottom

        welcomeLabel = new JLabel("Welcome to the System");
        welcomeLabel.setFont(new Font("serif",3,20));
        welcomeLabel.setForeground(Color.BLUE);

        //sets welcomeLabel vertical alignment by using the static variable inherits from Component class th at belongs to java.awt package.
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        wlcPanel.add(welcomeLabel);
        wlcPanel.add(Box.createVerticalStrut(35));

        //add images
        /*******************************************************************
         *Title: How to center a JFrame on screen
         *Author: MadProgrammer
         *Site owner/sponsor: stackoverflow.com
         *Date: Mar 4 2014
         *Code Version: Jul 13 2015
         *Availability: http://stackoverflow.com/questions/22162398/how-to-set-a-background-picture-in-jpanel (Accessed: 16/11/2016)
         *Modified: Code refactored
         *******************************************************************/

        //File Handling exceptions
        try {
            //ImageIO.read() returns a BufferedImage
            img = ImageIO.read(new File("C:\\Users\\User\\Desktop\\OOP2\\RestaurantSys\\src\\panda.png")); //end of [refactored or non-original] code
            imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            wlcPanel.add(imgLabel);
        } catch(IOException ex) {
            //display error message when file is not found
            JOptionPane.showMessageDialog(null,"Invalid Image File in Main Screen");
        }

        wlcPanel.add(Box.createVerticalStrut(30));

        //set border
        /*******************************************************************
         *Title: How to use Borders
         *Author: Oracle
         *Site owner/sponsor: docs.oracle.com
         *Date: -
         *Code Version: -
         *Availability: https://docs.oracle.com/javase/tutorial/uiswing/components/border.html (Accessed: 16/11/2016)
         *Modified: References
         *******************************************************************/

        buttonC = new JPanel();

        //belongs to javax.swing.border package that create a TitleBorder instance
        titledBorder = new TitledBorder("Shortcut Button");
        titledBorder.setTitleColor(Color.RED);

        //createLineBorder() is a static method that belongs to BorderFactory class from javax.swing.package
        buttonC.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        buttonC.setPreferredSize(new Dimension(390, 50));
        buttonC.setBorder(titledBorder);
        buttonC.setLayout(new GridLayout(1,3)); //1 row 3 column

        staffButton = new JButton("Register Staff");
        staffButton.addActionListener(this); //refers to the current class
        buttonC.add(staffButton);

        dishButton = new JButton("Add Cuisine");
        dishButton.addActionListener(this);
        buttonC.add(dishButton);

        salesButton = new JButton("Add Sales");
        salesButton.addActionListener(this);
        buttonC.add(salesButton);

        cPane.add(wlcPanel);
        cPane.add(buttonC);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("monospaced",Font.PLAIN,11));
        sysClock();
        cPane.add(dateLabel);
    }

    /**
     * Creates the file menu and add menu items
     * @return nothing
     */
    public void createFileMenu() {
        JMenuItem item;

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        item = new JMenuItem("Open");
        item.setMnemonic(KeyEvent.VK_O);
        item.addActionListener(this);
        fileMenu.add(item);

        item = new JMenuItem("Save");
        item.setMnemonic(KeyEvent.VK_S);
        item.addActionListener(this);
        fileMenu.add(item);

        item = new JMenuItem("Exit");
        item.setMnemonic(KeyEvent.VK_E);
        //anonymous inner class
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirmation",JOptionPane.YES_NO_OPTION);

                if(option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                }
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(item);
    }

    /**
     * Creates staff menu and add menu items
     * @return nothing
     */
    public void createStaffMenu() {
        JMenuItem item;

        staffMenu = new JMenu("Staff");
        staffMenu.setMnemonic(KeyEvent.VK_S);

        item = new JMenuItem("Register Staff");
        item.setMnemonic(KeyEvent.VK_R);
        //anonymous inner class
        item.addActionListener(this);
        staffMenu.add(item);

        item = new JMenuItem("Delete Staff");
        item.setMnemonic(KeyEvent.VK_D);
        item.addActionListener(this);
        staffMenu.add(item);

        item = new JMenuItem("View Staff");
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(this);
        staffMenu.add(item);
    }

    /**
     * Creates dish menu and add menu items
     * @return nothing
     */
    public void createDishMenu() {
        JMenuItem item;

        dishMenu = new JMenu("Cuisine");
        dishMenu.setMnemonic(KeyEvent.VK_C);

        item = new JMenuItem("Add Cuisine");
        item.setMnemonic(KeyEvent.VK_A);
        item.addActionListener(this);
        dishMenu.add(item);

        item = new JMenuItem("Delete Cuisine");
        item.setMnemonic(KeyEvent.VK_D);
        item.addActionListener(this);
        dishMenu.add(item);

        item = new JMenuItem("View Cuisine");
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(this);
        dishMenu.add(item);
    }

    /**
     * Creates sales menu and add menu items
     * @return nothing
     */
    public void createSalesMenu() {
        JMenuItem item;

        salesMenu = new JMenu("Sales");
        salesMenu.setMnemonic(KeyEvent.VK_S);

        item = new JMenuItem("Add Sales");
        item.setMnemonic(KeyEvent.VK_S);
        item.addActionListener(this);
        salesMenu.add(item);

        item = new JMenuItem("View Sales");
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(this);
        salesMenu.add(item);
    } //end of createSalesMenu()

    /*******************************************************************
     *Title: Java Eclipse GUI Tutorial 23 # Show System Date and Time in JFrame ( Dynamic Clock )
     *Author: ProgrammingKnowledge
     *Site owner/sponsor: youtube.com
     *Date: Aug 1, 2014
     *Code Version: -
     *Availability: https://www.youtube.com/watch?v=tpQAslXjNKU (Accessed: 16/11/2016)
     *Modified: Code Refactored
     *******************************************************************/

    /**
     * This method creates a dynamically clock
     * @return nothing
     */
    public void sysClock() {

        //Thread is a small process which can run differently from your process so that both programs run together
        //Thread class is belongs to java.lang packages
        //Thread class (subclass) extends Object class (superclass) implements Runnable
        //anonymous inner class
        Thread clock = new Thread() { //allocated new Thread Object

            //execute the same thread and is an entry point for this thread
            //override run() in Runnable interfaces
            @Override
            public void run() {
                try {
                    //infinite for loop to run the time
                    while(true) {
                        Calendar cld = new GregorianCalendar();

                        //date
                        int day = cld.get(Calendar.DAY_OF_MONTH);
                        int month = cld.get(Calendar.MONTH) + 1; //month start from 0 to 11
                        int year = cld.get(Calendar.YEAR);

                        //time
                        int sec = cld.get(Calendar.SECOND);
                        int min = cld.get(Calendar.MINUTE);
                        int hour = cld.get(Calendar.HOUR_OF_DAY);

                        dateLabel.setText(day + "/" + month + "/" + year + "  " + hour + ":" + min + ":" + sec);

                        //causes the current thread to suspend execution for a specific period of time (in milliseconds)
                        //throws Interrupted Exception
                        //sleep 1000 seconds. 1 sec = 1000 millisecond
                        sleep(1000);
                    } //end of while loop
                    //when thread is waiting, sleeping or otherwise occupied and the thread is interrupted, either before or during the activity
                } catch (InterruptedException e) {
                    //indicates tasks to perform
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } //end of try catch statement
            } //end of run method
        }; //end of anonymous inner class

        //causes the clock object to begin execute
        clock.start(); //end of [refactored or non-original] code
    } //end of sysClock method

    //save() method
    /**
     * This method used to save data
     * @return nothing
     * @throws IOException
     */
    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("staffs.dat"));
        oos.writeObject(staffs);
        oos.close();

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("cuisines.dat"));
        os.writeObject(cuisines);
        os.close();

        ObjectOutputStream sos = new ObjectOutputStream(new FileOutputStream("sales.dat"));
        sos.writeObject(sales);
        sos.close();
    } //end save method

    //open() method
    /**
     * This method used to open data
     * @return nothing
     */
    public void open() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("staffs.dat"));
            staffs = (ArrayList<Staff>) ois.readObject();
            ois.close();

            ObjectInputStream is = new ObjectInputStream(new FileInputStream("cuisines.dat"));
            cuisines = (LinkedList<Cuisine>) is.readObject();
            is.close();

            ObjectInputStream sois = new ObjectInputStream(new FileInputStream("sales.dat"));
            sales = (ArrayList<Sale>) sois.readObject();
            sois.close();

            JOptionPane.showMessageDialog(null,"File loaded to the system. Please 'View'","Open",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } //end open() method

    //add staff
    /**
     * This method is used to add staff into the system.
     * @return nothing
     */
    public void addStaff() {

        final String list [] = {"Manager","Waiter/Waitress","Sous-Chef","Chef","Cleaner"};
        final String genlist [] = {"Male","Female"};
        int i;
        int year, month, day;
        String pps;
        String name;
        String gender;
        String dob;
        String address;
        String phone;
        String position;
        char gen;

        pps = JOptionPane.showInputDialog("Enter Staff's PPS Number");

        //validation
        boolean valid = false;

        while(!valid) {

            try {

                if(pps.length() == 9) {
                    for(i = 0; i < 7; i++) {
                        if(!Character.isDigit(pps.charAt(i))) {
                            break;
                        }
                    }

                    if(i == 7 && Character.isLetter(pps.charAt(7)) && Character.isLetter(pps.charAt(8))) {

                        name = JOptionPane.showInputDialog("Enter Staff's Name");

                        for(i = 0; i < name.length(); i++) {
                            if(!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ' && name.charAt(i) != '\'') {
                                break;
                            }
                        }

                        if(i == name.length()) {

                            /*******************************************************************
                             *Title: To displays a dialog with a list of choices in a drop-down list box : JOptionPane Dialog « Swing « Java Tutorial
                             *Author: java2s.com
                             *Site owner/sponsor: java2s.com
                             *Date: -
                             *Code Version: -
                             *Availability: http://www.java2s.com/Tutorial/Java/0240__Swing/Todisplaysadialogwithalistofchoicesinadropdownlistbox.htm (Accessed: 22/11/2016)
                             *Modified: Code Refactored
                             *******************************************************************/

                            gender = (String)JOptionPane.showInputDialog(null,"Gender","Gender",JOptionPane.QUESTION_MESSAGE,null,genlist,genlist[0]); //end of [refactored or non-original] code

                            gen = gender.charAt(0);

                            dob = JOptionPane.showInputDialog("Enter Staff's DOB in format dd/mm/yyyy");

                            if(dob.length() == 10) {

                                if(Character.isDigit(dob.charAt(0)) && Character.isDigit(dob.charAt(1)) && dob.charAt(2) == '/' && Character.isDigit(dob.charAt(3))
                                        && Character.isDigit(dob.charAt(4)) && dob.charAt(5) == '/' && Character.isDigit(dob.charAt(6)) && Character.isDigit(dob.charAt(7))
                                        && Character.isDigit(dob.charAt(8)) && Character.isDigit(dob.charAt(9))) {

                                    year = Integer.parseInt(dob.substring(6,10));
                                    month = Integer.parseInt(dob.substring(3,5));
                                    day = Integer.parseInt(dob.substring(0,2));

                                    Calendar cld = new GregorianCalendar();

                                    if(year < cld.get(Calendar.YEAR)) {

                                        if(month >= 1 && month <= 12) {
                                            if(day >= 1 && day <= 31) {

                                                address = JOptionPane.showInputDialog("Enter Staff's Address");
                                                phone = JOptionPane.showInputDialog("Enter Staff's Phone");

                                                /*******************************************************************
                                                 *Title: To displays a dialog with a list of choices in a drop-down list box : JOptionPane Dialog « Swing « Java Tutorial
                                                 *Author: java2s.com
                                                 *Site owner/sponsor: java2s.com
                                                 *Date: -
                                                 *Code Version: -
                                                 *Availability: http://www.java2s.com/Tutorial/Java/0240__Swing/Todisplaysadialogwithalistofchoicesinadropdownlistbox.htm (Accessed: 22/11/2016)
                                                 *Modified: Code Refactored
                                                 *******************************************************************/

                                                position = (String)JOptionPane.showInputDialog(null,"Choose Staff's Position","Position",JOptionPane.QUESTION_MESSAGE,null,list,list[0]); //end of [refactored / non-original code]

                                                staff = new Staff(pps, name, gen, dob, address, phone, position);

                                                JOptionPane.showMessageDialog(null,"Staff '" + name + "' added to the system");

                                                valid = true;

                                            } else {
                                                dob = JOptionPane.showInputDialog("Invalid day, please re-enter Staff's DOB in format dd/mm/yyyy");
                                            } //end of day validation

                                        } else {
                                            dob = JOptionPane.showInputDialog("Invalid month, please re-enter Staff's DOB in format dd/mm/yyyy");
                                        } //end of month validation

                                    } else {
                                        dob = JOptionPane.showInputDialog("Invalid year, please re-enter Staff's DOB in format dd/mm/yyyy");
                                    } //end of year validation

                                } else {
                                    dob = JOptionPane.showInputDialog("Invalid DOB format, please re-enter Staff's DOB in format dd/mm/yyyy");
                                }
                            } else {
                                dob = JOptionPane.showInputDialog("Invalid DOB length. Please re-enter DOB in format dd/mm/yyyy");
                            } //end of dob validation
                        } else {
                            name = JOptionPane.showInputDialog("Invalid name, please re-enter");
                        } // end of name validation
                    } else {
                        pps = JOptionPane.showInputDialog("Invalid PPS Format, Please re-enter according to this format: <1234567IR>");
                    } //end of pps validation

                } else {
                    pps = JOptionPane.showInputDialog("Invalid PPS");
                } //end of pps validation
            } catch (NullPointerException npex) {

                int choose = JOptionPane.showConfirmDialog(null,"Field must not be empty. Do you want to continue?","Confirmation",JOptionPane.YES_NO_OPTION);

                if(choose == JOptionPane.YES_OPTION) {
                    pps = JOptionPane.showInputDialog("Enter Staff's PPS Number");
                } else {
                    break;
                }

            } //end catch
        } //end of validation

        staffs.add(staff); //add staff object to staffs array list

    } //end of add staff

    /**
     * This method is used to display a list of the staff.
     * @return nothing
     */
    public void displayStaff() {
        JComboBox staffCombo = new JComboBox();
        JTextArea output = new JTextArea();
        output.setText("Staff Details:\n\n");

        //exception handling
        try {
            if(staffs.size() < 1) {
                JOptionPane.showMessageDialog(null,"No staffs is added in the system. Please 'Open' file.","Error",JOptionPane.ERROR_MESSAGE);
            } else {
                //Iterator class belongs to java.util package
                Iterator<Staff> iterator = staffs.iterator();
                while (iterator.hasNext()) {
                    staffCombo.addItem(iterator.next().getName() + "\n"); //add staffs name to combo box
                }

                JOptionPane.showMessageDialog(null, staffCombo, "Select staff to view details", JOptionPane.PLAIN_MESSAGE);

                //get selected index and append staffs details to JTextArea and display
                int selected = staffCombo.getSelectedIndex();
                output.append(staffs.get(selected).toString());

                JOptionPane.showMessageDialog(null, output, "Staff Details", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (NullPointerException npx) {
            JOptionPane.showMessageDialog(null,"Error due to null pointer","Error",JOptionPane.ERROR_MESSAGE);
            npx.printStackTrace();
        }
    } //end of displayStaff method

    /**
     * This method is used to delete staff from list
     * @return nothing
     */
    public void deleteStaff() {
        JComboBox staffList = new JComboBox();

        //enhanced for loop
        //for every Staff s in staffs array list, get the object name and add into JComboBox
        for(Staff s : staffs) {
            staffList.addItem(s.getName());
        }

        JOptionPane.showMessageDialog(null,"Select staff to be removed","Remove Staff",JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null,staffList,"Remove Staff",JOptionPane.INFORMATION_MESSAGE);

        int selected = staffList.getSelectedIndex();

        staffs.remove(selected); //removed 'selected' from staffs array list

        JOptionPane.showMessageDialog(null,"Staff Removed","Removed",JOptionPane.INFORMATION_MESSAGE);
    }

    //add cuisine
    /**
     * This method is used to add cuisine into the system
     * @return nothing
     */
    public void addCuisine() {

        final String [] cuisineList = {"Irish","America","Malaysia","Thailand","Korea","Nyonya","Vietnam"}; //constant
        String category;
        String dish;
        String description;
        double price;
        boolean valid = false;

        category = (String)JOptionPane.showInputDialog(null,"Cuisine","Cuisine",JOptionPane.QUESTION_MESSAGE,null,cuisineList,cuisineList[0]);

        //validation
        while(!valid) {

            try {

                dish = JOptionPane.showInputDialog("Enter Dish's Name");
                description = JOptionPane.showInputDialog("Enter Dish's Description");

                try {

                    price = Double.parseDouble(JOptionPane.showInputDialog("Enter Dish's Price"));

                    cuisine = new Cuisine(category,dish,description,price);

                    JOptionPane.showMessageDialog(null,"Cuisine '" + dish + "' added to the system");

                    valid = true;

                } catch (NumberFormatException nfx) {
                    JOptionPane.showMessageDialog(null,"The price you entered is invalid, please enter valid price");
                } //end of number format exception

            } catch(NullPointerException npx) {

                int choose = JOptionPane.showConfirmDialog(null,"Field must not be empty. Do you want to continue?","Confirmation",JOptionPane.YES_NO_OPTION);

                if(choose == JOptionPane.YES_OPTION) {
                    category = (String)JOptionPane.showInputDialog(null,"Cuisine","Cuisine",JOptionPane.QUESTION_MESSAGE,null,cuisineList,cuisineList[0]);
                } else {
                    break;
                }
            }
        } //end of validation
        cuisines.add(cuisine); //add cuisine object to cuisines LinkedList
    } //end of add cuisine method

    //display cuisine method
    /**
     * This method is used to display a list of cuisine
     * @return nothing
     */
    public void displayCuisine() {
        JComboBox cuisineCombo = new JComboBox();
        JTextArea output = new JTextArea();

        output.setText("Cuisine Details:\n\n");

        if(cuisines.size() < 1) {
            JOptionPane.showMessageDialog(null,"No cuisines is added in the system. Please 'Open' the file.","Error",JOptionPane.ERROR_MESSAGE);
        } else {
            Iterator<Cuisine> iterator = cuisines.iterator();
            while(iterator.hasNext()) {
                cuisineCombo.addItem(iterator.next().getDish() + "\n");
            }
            JOptionPane.showMessageDialog(null,cuisineCombo,"Select cuisine to view details",JOptionPane.PLAIN_MESSAGE);

            int selected = cuisineCombo.getSelectedIndex();
            output.append(cuisines.get(selected).toString());

            JOptionPane.showMessageDialog(null,output,"Cuisine Details",JOptionPane.PLAIN_MESSAGE);
        }
    } //end of display cuisine method

    /**
     * This method is used to delete cuisines from the system
     * @return nothing
     */
    public void deleteCuisine() {

        JComboBox cuisineList = new JComboBox();

        for(Cuisine c : cuisines) {
            cuisineList.addItem(c.getDish());
        }

        JOptionPane.showMessageDialog(null,"Select cuisine to be removed","Remove Cuisine",JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null,cuisineList,"Remove Cuisine",JOptionPane.INFORMATION_MESSAGE);

        int selected = cuisineList.getSelectedIndex();

        cuisines.remove(selected);

        JOptionPane.showMessageDialog(null,"Cuisine Removed","Removed",JOptionPane.INFORMATION_MESSAGE);
    } //end of deleteCuisine method

    //add sales method
    /**
     * This method is used to add sales to the system
     * @return nothing
     */
    public void addSales() {
        String costAsString;
        double cost = 0;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        boolean valid = false;

        do {
            //catch NumberFormat exception
            try {
                costAsString = JOptionPane.showInputDialog("Enter " + simpleDateFormat.format(date) + " sales value");
                cost = Double.parseDouble(costAsString);
                sale = new Sale(cost);

                JOptionPane.showMessageDialog(null,"Today sales " + simpleDateFormat.format(date) + " value: EUR " + cost + " added","Sales added",JOptionPane.INFORMATION_MESSAGE);

                sales.add(sale);

                valid = true;
            } catch (NumberFormatException nfex) {
                JOptionPane.showMessageDialog(null,"Sales value entered is invalid, please re-enter","Error",JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException npx) {
                int choose = JOptionPane.showConfirmDialog(null,"Field must not be empty. Do you want to continue?","Confirmation",JOptionPane.YES_NO_OPTION);

                if(choose == JOptionPane.YES_OPTION) {
                    costAsString = JOptionPane.showInputDialog("Please re-enter " + simpleDateFormat.format(date) + " sales value");
                } else {
                    break;
                }
            }
        } while (!valid); //end of while loop
    } //end of add sales method

    public void viewSales() {
        double total = 0;

        JTextArea msg = new JTextArea();

        if(sales.size() < 1) {
            JOptionPane.showMessageDialog(null,"No sales added/loaded to the system","Error",JOptionPane.ERROR_MESSAGE);
        } else {
            Iterator<Sale> iterator = sales.iterator();
            while(iterator.hasNext()) {
                sale = iterator.next();
                total += sale.getCost(); //get sale value from sales LinkedList and add together
                msg.append(sale + "\n");
            } //end while loop

            msg.append("\nTotal Sales: " + String.format("%.2f",total));

            JOptionPane.showMessageDialog(null,msg,"Sales Report",JOptionPane.PLAIN_MESSAGE);
        } //end else statement
    } //end of viewSales method

    @Override
    public void actionPerformed(ActionEvent e) {
        String menuName = e.getActionCommand();
        if(menuName == "Register Staff" || e.getSource() == staffButton) {
            addStaff();
        } else if (menuName == "View Staff") {
            displayStaff();
        } else if (menuName == "Delete Staff") {
            deleteStaff();
        } else if(menuName == "Open") {
            open();
        } else if(menuName == "Save") {
            try {
                save();
                JOptionPane.showMessageDialog(null,"Data saved successfully","Saved",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null,"Not able to save the file");
                e1.printStackTrace();
            }
        } else if (menuName == "Add Cuisine" || e.getSource() == dishButton) {
            addCuisine();
        } else if(menuName == "View Cuisine") {
            displayCuisine();
        } else if(menuName == "Delete Cuisine") {
            deleteCuisine();
        } else if(menuName == "Add Sales" || e.getSource() == salesButton) {
            addSales();
        } else if(menuName == "View Sales") {
            viewSales();
        }
    } //end of actionPerformed method
}
