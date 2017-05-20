package com.javarush.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lollipop on 12.08.2016.
 */
public class AuthorisationFrame extends JFrame
{
    public void init() {
        setSize(900, 500);
        setTitle("Autorisation");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(new GridBagLayout());

        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel label = new JLabel();

        JButton loginButton = new JButton("Login in");
        loginButton.addActionListener(new LoginButonListener(label));
        JButton registrationButton = new JButton("Registration");
        registrationButton.addActionListener(new RegistrationButtonListener(label));

        JTextField loginTextField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);


       add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
         GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
         new Insets(2, 2, 2, 2), 0, 0));
       add(loginTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
         GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
         new Insets(2, 2, 2, 2), 0, 0));

       add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
         GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
         new Insets(2, 2, 2, 2), 0, 0));

       add(passwordField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
         GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
         new Insets(2, 2, 2, 2), 0, 0));
       add(loginButton, new GridBagConstraints(0, 2, 2, 1, 1, 1,
         GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
         new Insets(2, 2, 2, 2), 0, 0));
       add(registrationButton, new GridBagConstraints(0, 3, 2, 1, 1, 1,
         GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
         new Insets(2, 2, 2, 2), 0, 0));
       add(label, new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.NORTH,
         GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        setVisible(true);
        pack();
    }
    public class LoginButonListener implements ActionListener {
        private JLabel label;

        public LoginButonListener(JLabel label)
        {
            this.label = label;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            label.setText("You have been logged");
        }
    }
    public class RegistrationButtonListener implements ActionListener {
        private JLabel label;

        public RegistrationButtonListener(JLabel label)
        {
            this.label = label;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            label.setText("You have been registered");
        }
    }
}
