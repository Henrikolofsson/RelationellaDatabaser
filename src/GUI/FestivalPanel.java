package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    The panel that fills up the starting window, where you can choose either the admin or visitor interface.
 */
public class FestivalPanel extends JPanel {
    private JLabel lblAdmin;
    private JLabel lblVisitor;

    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");

    public FestivalPanel(int width, int height, String lblText) {
        initializeGUI(width, height, lblText);
    }

    private void initializeGUI(int width, int height, String lblText) {
        setLayout(new GridLayout(1,1, 0, 0));
        setSize(new Dimension(width, height));
        setBackground(GRAY_BACKGROUND_COLOR);

        if(lblText.equals("Admin")) {
            lblAdmin = new JLabel("Admin");
            lblAdmin.setBorder(new EmptyBorder(0, (((width/2)-10)), 0, 0));
            lblAdmin.setForeground(Color.WHITE);
            add(lblAdmin);
        } else {
            lblVisitor = new JLabel("Visitor");
            lblVisitor.setBorder(new EmptyBorder(0, ((width/2)-10), 0, 0));
            lblVisitor.setForeground(Color.WHITE);
            add(lblVisitor);
        }


    }




}
