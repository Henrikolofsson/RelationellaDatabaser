package GUI;

import Controllers.MainController;

import javax.swing.*;
import java.awt.*;

public class MainAdminPanel extends JPanel {
    private MainController controller;
    private JTabbedPane tabbedPane;
    private BookBandPanel bookBandPanel;

    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");

    public MainAdminPanel(MainController controller, int width, int height) {
        this.controller = controller;
        /*
        tabbedPane = new JTabbedPane();
        bandManagePanel = new BandManagePanel();

        setLayout(new GridBagLayout());
        setSize(new Dimension(width, height));
        setBackground(GRAY_BACKGROUND_COLOR);
        tabbedPane.addTab("Manage/Book band", bandManagePanel);
        add(tabbedPane);

         */
    }
}
