package GUI;

import Controllers.MainController;
import Entities.Band;
import Entities.Concert;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    The panel for visitors to see the festivals schedule.
 */
public class VisitorPanel extends JPanel {
    private MainController controller;
    private ArrayList<Concert> listOfConcerts;
    private ArrayList<Band> listOfBands;

    private JPanel pnlThursday;
    private JPanel pnlFriday;
    private JPanel pnlSaturday;
    private JPanel pnlSunday;

    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");

    public VisitorPanel(MainController controller, int width, int height) {
        this.controller = controller;
        setLayout(new GridBagLayout());
        setSize(new Dimension(width, height));
        setBackground(GRAY_BACKGROUND_COLOR);
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        listOfConcerts = controller.getAllConcerts();
        listOfBands = controller.getAllBands();
        pnlThursday = new JPanel(new GridBagLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Thursday");
        titledBorder.setTitleColor(Color.WHITE);
        pnlThursday.setBorder(titledBorder);
        pnlThursday.setBackground(GRAY_BACKGROUND_COLOR);

        pnlFriday = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Friday");
        titledBorder.setTitleColor(Color.WHITE);
        pnlFriday.setBorder(titledBorder);
        pnlFriday.setBackground(GRAY_BACKGROUND_COLOR);

        pnlSaturday = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Saturday");
        titledBorder.setTitleColor(Color.WHITE);
        pnlSaturday.setBorder(titledBorder);
        pnlSaturday.setBackground(GRAY_BACKGROUND_COLOR);

        pnlSunday = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Sunday");
        titledBorder.setTitleColor(Color.WHITE);
        pnlSunday.setBorder(titledBorder);
        pnlSunday.setBackground(GRAY_BACKGROUND_COLOR);
    }

    private void initializeGUI() {
        int gridYthursday = 0;
        int gridYfriday = 0;
        int gridYsaturday = 0;
        int gridYsunday = 0;
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 50, 5, 80);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(pnlThursday, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(pnlFriday, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        add(pnlSaturday, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        add(pnlSunday, c);

        for (Concert concert : listOfConcerts) {
            System.out.println(concert);
            switch (concert.getDay()) {
                case "1":
                    JLabel lblToAdd = new JLabel();
                    gridYthursday++;
                    c.gridy = gridYthursday;
                    StringBuilder sb = new StringBuilder();
                    for (Band b : listOfBands) {
                        if (concert.getBand_id() == b.getBand_id()) {
                            sb.append(b.getBand_name());
                            sb.append("         ");
                            sb.append(concert.getScene());
                            sb.append("         ").append(concert.getTime());
                        }
                    }
                    lblToAdd.setText(sb.toString());
                    lblToAdd.setForeground(Color.WHITE);
                    pnlThursday.add(lblToAdd, c); //thursday;
                    break;

                case "2":
                    lblToAdd = new JLabel();
                    gridYfriday++;
                    c.gridy = gridYfriday;
                    sb = new StringBuilder();
                    for (Band b : listOfBands) {
                        if (concert.getBand_id() == b.getBand_id()) {
                            sb.append(b.getBand_name());
                            sb.append("         ");
                            sb.append(concert.getScene());
                            sb.append("         ").append(concert.getTime());
                        }
                    }
                    lblToAdd.setText(sb.toString());
                    lblToAdd.setForeground(Color.WHITE);
                    pnlFriday.add(lblToAdd, c);//Friday
                    break;

                case "3":
                    lblToAdd = new JLabel();
                    gridYsaturday++;
                    c.gridy = gridYsaturday;
                    sb = new StringBuilder();
                    for (Band b : listOfBands) {
                        if (concert.getBand_id() == b.getBand_id()) {
                            sb.append(b.getBand_name());
                            sb.append("         ");
                            sb.append(concert.getScene());
                            sb.append("         ").append(concert.getTime());
                        }
                    }
                    lblToAdd.setText(sb.toString());
                    lblToAdd.setForeground(Color.WHITE);
                    pnlSaturday.add(lblToAdd, c);//Saturday
                    break;

                case "4":
                    lblToAdd = new JLabel();
                    gridYsunday++;
                    c.gridy = gridYsunday;
                    sb = new StringBuilder();
                    for (Band b : listOfBands) {
                        if (concert.getBand_id() == b.getBand_id()) {
                            sb.append(b.getBand_name());
                            sb.append("         ");
                            sb.append(concert.getScene());
                            sb.append("         ").append(concert.getTime());
                        }
                    }
                    lblToAdd.setText(sb.toString());
                    lblToAdd.setForeground(Color.WHITE);
                    pnlSunday.add(lblToAdd, c);//Sunday
                    break;

            }
        }

    }
}
