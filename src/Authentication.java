import jdk.nashorn.internal.scripts.JO;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.io.IOException;

/**
 * This is the main class for RestaurantSys that first prompt for admin username and password
 * @author De Jong Yeong
 * @version 1.0
 */

public class Authentication extends JDialog implements ActionListener {

    //attributes
    JLabel user;
    JLabel pass;
    JTextField userField;
    JPasswordField passField;
    JButton loginButton;
    JButton cancelButton;
    private String username;
    private String password;

    /**
     * No-argument constructor
     */
    public Authentication() {

        setTitle("Login");
        setVisible(true);
        setResizable(false);

        /*******************************************************************
         *Title: How to center a JFrame on screen
         *Author: Alvin Alexander
         *Site owner/sponsor: alvinalexander.com
         *Date: 28 June 2016
         *Code Version: -
         *Availability: http://alvinalexander.com/blog/post/jfc-swing/how-center-jframe-java-swing (Accessed: 15/11/2016)
         *Modified: Code refactored
         *******************************************************************/

        setLocationRelativeTo(null); //end of [non-original or refactored]

        /*******************************************************************
         *Title: 3 Ways to Set Icon Image for JFrame
         *Author: Gowtham Gutha
         *Site owner/sponsor: java-demos.blogspot.ie
         *Date: 22 October 2013
         *Code Version: -
         *Availability: http://java-demos.blogspot.ie/2013/10/3-ways-to-set-icon-image-for-jframe.html (Accessed: 15/11/2016)
         *Modified: Code refactored (image renamed)
         *******************************************************************/

        //setIconImage sets the image to be displayed as the icon for this JDialog (return type: void)
        //ImageIcon(String filename) creates an image icon from the specified file.
        //.getImage() returns this ImageIcon's image.
        setIconImage(new ImageIcon("C:\\Users\\User\\Desktop\\OOP2\\RestaurantSys\\src\\logo.png").getImage()); //end of [non-original or refactored] code

        /*******************************************************************
         *Title: How to position components with GridBagLayout?
         *Author: MadProgrammer
         *Site owner/sponsor: stackoverflow.com
         *Date: Oct 23 2012
         *Code Version: -
         *Availability: http://stackoverflow.com/questions/13028917/how-to-position-components-with-gridbaglayout (Accessed: 15/11/2016)
         *Modified: Code refactored
         *******************************************************************/

        /*******************************************************************
         *Title: Class GridBagConstraints
         *Author: Java API
         *Site owner/sponsor: docs.oracle.com
         *Date: -
         *Code Version: -
         *Availability: https://docs.oracle.com/javase/7/docs/api/java/awt/GridBagConstraints.html#fill (Accessed: 01/12/2016)
         *Modified: Java API lookup
         *******************************************************************/

        JPanel prompt = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints(); //specifies constraints for components that are laid out using GridBagLayout

        //make the component wide enough to fill its display area horizontally but do not change its height.
        cs.fill = GridBagConstraints.HORIZONTAL;

        user = new JLabel("Username: ");
        cs.gridx = 0; //specifies the cell containing the leading edge of the component's display area (first cell in a row)
        cs.gridy = 0; //specifies the cell at the top of the component's display area (topmost cell)
        cs.gridheight = 1; //specifies the number of cells in a column for component's display area (default value)
        prompt.add(user, cs);

        userField = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        prompt.add(userField, cs); //inherited from Container class.
        userField.requestFocus();
        userField.setToolTipText("Key in username that provided during installation");

        prompt.add(Box.createVerticalStrut(30));

        pass = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridheight = 1;
        prompt.add(pass, cs);

        passField = new JPasswordField(15);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        passField.setToolTipText("Key in password that provided during installation");
        prompt.add(passField, cs); //end of [non-original or refactored]

        JPanel bPanel = new JPanel(new FlowLayout());

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setMnemonic(KeyEvent.VK_L);

        //anonymous inner class
        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic(KeyEvent.VK_C);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        bPanel.add(loginButton);
        bPanel.add(cancelButton);

        getContentPane().add(prompt, BorderLayout.CENTER);
        getContentPane().add(bPanel, BorderLayout.PAGE_END);

        /*******************************************************************
         *Title: What does .pack() do? [closed]
         *Author: sidgate
         *Site owner/sponsor: stackoverflow.com
         *Date: Apr 10 2014
         *Code Version: -
         *Availability: http://stackoverflow.com/questions/22982295/what-does-pack-do (Accessed: 15/11/2016)
         *Modified: References
         *******************************************************************/

        //size the frame so that all its contents are at or above their preferred sizes.
        pack(); //end of [refactored or non-original] code

        setResizable(false);
    } //end of constructor

    //event handler
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton || e.getSource() == userField || e.getSource() == passField) {
            username = userField.getText();
            password = new String(passField.getPassword()); //convert char[] to string because getPassword() returns char[]

            if(!username.equals("") && !password.equals("")) {
                JOptionPane.showMessageDialog(null,"Welcome " + username);
                RestaurantSys m = new RestaurantSys();
                m.setVisible(true);
                this.dispose(); //dispose Authentication class JDialog
            } else {
                JOptionPane.showMessageDialog(null,"Invalid username/password","Error",JOptionPane.ERROR_MESSAGE);
                userField.setText("");
                passField.setText("");
                userField.requestFocus();
            }
        }
    } //end of actionPerformed() method
}
