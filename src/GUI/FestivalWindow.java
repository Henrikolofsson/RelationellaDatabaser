package GUI;

import Controllers.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
  The main window for the application.
 */
public class FestivalWindow extends JFrame {
    private StartPanel startPanel;
    private FestivalPanel adminPanel;
    private FestivalPanel visitorPanel;
    private LogInAdminPanel loginAdminPanel;
    private MainController controller;
    private JTabbedPane tabbedPane;
    private MainAdminPanel mainAdminPanel;

    private ManageBandPanel manageBandPanel;
    private ManageWorkersPanel manageWorkersPanel;
    private ManageBandMemberPanel manageBandMemberPanel;
    private ViewBandMembersAssociationPanel viewBandMembersAssociationPanel;
    private ManageConcertsPanel manageConcertsPanel;


    private static int HALF_WINDOW_SIZE = (580/2);
    private Color GRAY_BACKGROUND_COLOR_HOVER = Color.decode("#414042");
    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");


    public FestivalWindow(MainController controller) {
        this.controller = controller;
        initializeGUI();
        registerListeners();
    }

    public void initializeGUI() {
        //Default JFrame initializations
        setTitle("Ruski Festival");
        setSize(new Dimension(600,800));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 780));
        setVisible(true);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        //Adding one panel for visitors, and one for administrators.
        String admin = "Admin";
        String visitor = "Visitor";
        adminPanel = new FestivalPanel(HALF_WINDOW_SIZE, 780, admin);
        add(adminPanel);
        visitorPanel = new FestivalPanel(HALF_WINDOW_SIZE, 580, visitor);
        add(visitorPanel);

        //Adding the login admin panel
        loginAdminPanel = new LogInAdminPanel(controller,580, 780);

    }

    /*
      Registers listeners to the panels in the window, to change color and respond on click.
     */
    private void registerListeners() {
        adminPanel.addMouseListener(new FestivalWindowListener());
        visitorPanel.addMouseListener(new FestivalWindowListener());
    }

    /*
      Removes the login panel, and adds the logged in panel for administrators
     */
    public void setAdminLoggedIn() {
        mainAdminPanel = new MainAdminPanel(controller, 580, 580);
        remove(loginAdminPanel);

        tabbedPane = new JTabbedPane();

        manageBandPanel = new ManageBandPanel(controller);
        tabbedPane.addTab("Manage bands", manageBandPanel);

        manageWorkersPanel = new ManageWorkersPanel(controller);
        tabbedPane.addTab("Manage workers", manageWorkersPanel);

        manageBandMemberPanel = new ManageBandMemberPanel(controller);
        tabbedPane.addTab("Manage band members", manageBandMemberPanel);

        viewBandMembersAssociationPanel = new ViewBandMembersAssociationPanel(controller);
        tabbedPane.addTab("View band members association", viewBandMembersAssociationPanel);

        manageConcertsPanel = new ManageConcertsPanel(controller);
        tabbedPane.addTab("Manage concerts", manageConcertsPanel);



        add(tabbedPane);

        pack();
        repaint();
    }

    /*
      A class that extends a mouse adapter that works as a listener, this is added to the panels to respond on interaction.
     */
    public class FestivalWindowListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() == adminPanel) {
                adminPanel.setBackground(GRAY_BACKGROUND_COLOR_HOVER);
            }
            else if(e.getSource() == visitorPanel) {
                visitorPanel.setBackground(GRAY_BACKGROUND_COLOR_HOVER);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if(e.getSource() == adminPanel) {
                System.out.println("Adminpanel exit");
                adminPanel.setBackground(GRAY_BACKGROUND_COLOR);
            } else if(e.getSource() == visitorPanel) {
                visitorPanel.setBackground(GRAY_BACKGROUND_COLOR);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(e.getSource() == adminPanel) {
                remove(adminPanel);
                remove(visitorPanel);
                add(loginAdminPanel);
                pack();
                repaint();
            } else if(e.getSource() == visitorPanel) {

            }
        }
    }

}
