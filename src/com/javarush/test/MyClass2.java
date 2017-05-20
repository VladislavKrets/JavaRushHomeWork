package com.javarush.test;


public class MyClass2{

    public static void main(String[] args) throws Exception
    {
        AuthorisationFrame frame = new AuthorisationFrame();
        frame.init();
        /*JFrame frame = new JFrame();
        frame.setSize(900, 500);
        frame.setTitle("Autorisation");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setResizable(false);

        frame.setLayout(new GridBagLayout());

        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");

        JButton loginButton = new JButton("Login in");
        JButton registrationButton = new JButton("Registration");

        JTextField loginTextField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JLabel label = new JLabel();
*/
        /*c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.9;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(2, 2, 2, 2);
        c.ipadx = 0;
        c.ipady = 0;
*//*
        frame.add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        frame.add(loginTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        frame.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        frame.add(passwordField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        frame.add(loginButton, new GridBagConstraints(0, 2, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        frame.add(registrationButton, new GridBagConstraints(0, 3, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        frame.add(label, new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));



        frame.setVisible(true);
        frame.pack();*/
/*
        frame.setLayout(new BorderLayout());

        JPanel panelCenter = new JPanel(new GridLayout(3, 3));
        JPanel panelNorth = new JPanel(new BorderLayout());

        JButton button1 = new JButton("button1");
        JButton button2 = new JButton("button2");
        JButton button3 = new JButton("button3");
        JButton button4 = new JButton("button4");
        JButton button5 = new JButton("button5");
        JButton button6 = new JButton("button6");
        JButton button7 = new JButton("button7");
        JButton button8 = new JButton("button8");
        JButton button9 = new JButton("button9");
        JButton button0 = new JButton("button0");

        JTextField field = new JTextField(10);

        panelCenter.add(button1);
        panelCenter.add(button2);
        panelCenter.add(button3);
        panelCenter.add(button4);
        panelCenter.add(button5);
        panelCenter.add(button6);
        panelCenter.add(button7);
        panelCenter.add(button8);
        panelCenter.add(button9);
        panelCenter.add(button0);

        panelNorth.add(field, BorderLayout.CENTER);

        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);*/
        /*frame.setLayout(new BorderLayout());

        JButton button = new JButton("Button1");
        JTextField textField = new JTextField(10);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(600, 100));
        panel.setBackground(Color.YELLOW);
        panel.add(button);
        panel.add(textField);
        frame.setVisible(true);

        frame.add(panel, BorderLayout.SOUTH);
*/
        // frame.setLayout(new GridBagLayout());
        /*
        frame.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.BLACK);
        panel2.setBackground(Color.blue);
        panel3.setBackground(Color.CYAN);
        panel4.setBackground(Color.YELLOW);
        panel5.setBackground(Color.GREEN);

        JButton button = new JButton("Button");
        panel1.setLayout(new BorderLayout());
        panel5.setLayout(new BorderLayout());
        panel1.add(new JButton("Button2"));
        panel5.add(button, BorderLayout.PAGE_START);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.add(panel3, BorderLayout.WEST);
        frame.add(panel4, BorderLayout.EAST);
        frame.add(panel5, BorderLayout.CENTER);
        */

        /*JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel2.setBackground(Color.green);
        JButton button = new JButton("Button");
        panel.add(button);
        panel2.add(new JButton("button2"));
        panel.add(panel2);


        frame.add(panel);
        */
        /*JLabel label = new JLabel();
        JProgressBar bar = new JProgressBar();
        bar.setStringPainted(true);
        bar.setMinimum(0);
        bar.setMaximum(100);
        frame.add(bar);
        frame.add(label);
        bar.setIndeterminate(true);
        for (int i = bar.getMinimum(); i <= bar.getMaximum(); i++) {
            Thread.sleep(100);
            bar.setValue(i);
            label.setText(String.valueOf(bar.getValue()));
        }

*/
/*
        JLabel label = new JLabel("label");

        Font font = new Font("Verdana", Font.PLAIN, 25);
        label.setFont(font);
        frame.add(label);*/
/*
        JPasswordField field = new JPasswordField(30);
        field.setText("Текст");
        field.setBackground(Color.YELLOW);
        System.out.println(field.getPassword());

        JButton button = new JButton("Button");
        frame.add(field);
        frame.add(button);
*/

        /*Thread.sleep(3000);
        frame.setState(JFrame.ICONIFIED);

        Thread.sleep(3000);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Thread.sleep(3000);
        frame.setExtendedState(JFrame.NORMAL);
*/
    }

}