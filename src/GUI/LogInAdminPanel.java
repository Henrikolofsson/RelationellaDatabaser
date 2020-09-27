package GUI;

import Controllers.MainController;
import Database.DatabaseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInAdminPanel extends JPanel {
    private MainController controller;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");

    public LogInAdminPanel(MainController controller, int width, int height) {
        this.controller = controller;
        initializeGUI(width, height);
    }

    private void initializeGUI(int width, int height) {
        setLayout(new GridBagLayout());
        setSize(new Dimension(width, height));
        setBackground(GRAY_BACKGROUND_COLOR);

        lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 80, 5, 80);
        add(lblUsername, c);

        txtUsername = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(txtUsername, c);

        lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        add(lblPassword, c);

        txtPassword = new JPasswordField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        add(txtPassword, c);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new LoginListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(20, 250, 0, 250);
        add(btnLogin, c);
        repaint();
    }

    public class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            controller.login(txtUsername.getText(), txtPassword.getPassword());
        }
    }


}
