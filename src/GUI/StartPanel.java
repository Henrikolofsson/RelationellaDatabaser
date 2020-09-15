package GUI;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    private FestivalPanel adminPanel;
    private FestivalPanel visitorPanel;

    private static int HALF_WINDOW_SIZE = (580/2);

    public StartPanel() {
        initializeGUI();
    }

    private void initializeGUI() {
        setLayout(new GridLayout(1,2, 0, 20));
        setSize(new Dimension(580, 380));
        setBackground(Color.WHITE);

        adminPanel = new FestivalPanel(HALF_WINDOW_SIZE, 380, "...");
        visitorPanel = new FestivalPanel(HALF_WINDOW_SIZE, 380, ",,,");
        add(adminPanel);
        add(visitorPanel);
    }
}
