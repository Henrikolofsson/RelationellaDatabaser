package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FestivalWindow extends JFrame {
    private StartPanel startPanel;
    private FestivalPanel adminPanel;
    private FestivalPanel visitorPanel;

    private static int HALF_WINDOW_SIZE = (580/2);
    private Color GRAY_BACKGROUND_COLOR_HOVER = Color.decode("#414042");


    public FestivalWindow() {
        initializeGUI();
        registerListeners();
    }

    public void initializeGUI() {
        //Default JFrame initializations
        setTitle("Ruski Festival");
        setSize(new Dimension(600,400));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        //Adding the StartPanel class in this FestivalWindow-Frame
        String admin = "Admin";
        String visitor = "Visitor";

        adminPanel = new FestivalPanel(HALF_WINDOW_SIZE, 380, admin);
        add(adminPanel);

        visitorPanel = new FestivalPanel(HALF_WINDOW_SIZE, 380, visitor);
        add(visitorPanel);

    }

    private void registerListeners() {
        adminPanel.addMouseListener(new FestivalWindowListener());
        visitorPanel.addMouseListener(new FestivalWindowListener());
    }

    public class FestivalWindowListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() == adminPanel) {
                System.out.println("Adminpanel");
                adminPanel.setForeground(GRAY_BACKGROUND_COLOR_HOVER);
            } else if(e.getSource() == visitorPanel) {
                System.out.println("Visitorpanel");
                visitorPanel.setForeground(GRAY_BACKGROUND_COLOR_HOVER);
            }
            //System.out.println(e.getSource());
           // parent = (JPanel)mouseEvent.getSource();
           // parent.setForeground(Color.BLUE);
            //parent.setForeground(GRAY_BACKGROUND_COLOR_HOVER);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if(e.getSource() == adminPanel) {
                //adminPanel.setForeground();
            }
        }
    }

}
