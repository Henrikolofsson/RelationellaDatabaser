package GUI;

import Controllers.MainController;
import Entities.Band;
import Entities.ComboBoxItem;
import Entities.Concert;
import Entities.Worker;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    The panel for managing concerts.
 */
public class ManageConcertsPanel extends JPanel {
    private MainController controller;
    private ArrayList<Band> listOfBands;

    private JPanel pnlBand;
    private JLabel lblBandToBook;
    private JComboBox<ComboBoxItem> comboBoxBands;

    private JPanel pnlDays;
    private JLabel lblDay;
    private JComboBox<ComboBoxItem> comboBoxDays;

    private JPanel pnlTime;
    private JLabel lblTime;
    private JComboBox<ComboBoxItem> comboBoxTime;

    private JPanel pnlScene;
    private JLabel lblScene;
    private JComboBox<ComboBoxItem> comboBoxScene;

    private ButtonGroup buttonGroup;
    private JRadioButton rdBtnAdd;
    private JRadioButton rdBtnChange;
    private JRadioButton rdBtnDelete;

    private JButton btnManage;

    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");

    
    public ManageConcertsPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        listOfBands = controller.getAllBands();

        setLayout(new GridBagLayout());
        setSize(new Dimension(580, 780));
        setBackground(GRAY_BACKGROUND_COLOR);

        buttonGroup = new ButtonGroup();
        rdBtnAdd = new JRadioButton("Add");
        rdBtnChange = new JRadioButton("Change");
        rdBtnDelete = new JRadioButton("Delete");
        buttonGroup.add(rdBtnAdd);
        buttonGroup.add(rdBtnChange);
        buttonGroup.add(rdBtnDelete);
        rdBtnAdd.setSelected(true);
        rdBtnAdd.setBackground(GRAY_BACKGROUND_COLOR);
        rdBtnAdd.setForeground(Color.WHITE);
        rdBtnChange.setBackground(GRAY_BACKGROUND_COLOR);
        rdBtnChange.setForeground(Color.WHITE);
        rdBtnDelete.setBackground(GRAY_BACKGROUND_COLOR);
        rdBtnDelete.setForeground(Color.WHITE);

        pnlBand = new JPanel(new GridBagLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Band to book");
        titledBorder.setTitleColor(Color.WHITE);
        pnlBand.setBorder(titledBorder);
        pnlBand.setBackground(GRAY_BACKGROUND_COLOR);
        lblBandToBook = new JLabel("Band to book:");
        lblBandToBook.setForeground(Color.WHITE);
        comboBoxBands = new JComboBox<>();

        pnlDays = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Day of concert");
        titledBorder.setTitleColor(Color.WHITE);
        pnlDays.setBorder(titledBorder);
        pnlDays.setBackground(GRAY_BACKGROUND_COLOR);
        lblDay = new JLabel("Day of concert:");
        lblDay.setForeground(Color.WHITE);
        comboBoxDays = new JComboBox<>();

        pnlTime = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Time of concert");
        titledBorder.setTitleColor(Color.WHITE);
        pnlTime.setBorder(titledBorder);
        pnlTime.setBackground(GRAY_BACKGROUND_COLOR);
        lblTime = new JLabel("Time of concert:");
        lblTime.setForeground(Color.WHITE);
        comboBoxTime = new JComboBox<>();

        pnlScene = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Time of concert");
        titledBorder.setTitleColor(Color.WHITE);
        pnlScene.setBorder(titledBorder);
        pnlScene.setBackground(GRAY_BACKGROUND_COLOR);
        lblScene = new JLabel("Scene:");
        lblScene.setForeground(Color.WHITE);
        comboBoxScene = new JComboBox<>();

        btnManage = new JButton("Add concert");
    }

    private void initializeGUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 50, 5, 80);
        pnlBand.add(lblBandToBook, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlBand.add(comboBoxBands, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(pnlBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        add(rdBtnAdd, c);
        //
        //
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        pnlDays.add(lblDay, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlDays.add(comboBoxDays, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(pnlDays, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        add(rdBtnChange, c);
        //
        //
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        pnlTime.add(lblTime, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlTime.add(comboBoxTime, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        add(pnlTime, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        add(rdBtnDelete, c);
        //
        //
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        pnlScene.add(lblScene, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlScene.add(comboBoxScene, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        add(pnlScene, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        add(btnManage, c);

        populateComboBoxes();
    }

    /*
        Fills up the ComboBoxes
     */
    private void populateComboBoxes() {
        comboBoxBands.removeAllItems();
        comboBoxBands.addItem(new ComboBoxItem("-1", "No band selected"));
        for(Band b : listOfBands) comboBoxBands.addItem(new ComboBoxItem(String.valueOf(b.getBand_id()), b.getBand_name()));

        comboBoxDays.removeAllItems();
        comboBoxDays.addItem(new ComboBoxItem("-1", "No day selected"));
        comboBoxDays.addItem(new ComboBoxItem("1","Thursday"));
        comboBoxDays.addItem(new ComboBoxItem("2","Friday"));
        comboBoxDays.addItem(new ComboBoxItem("3","Saturday"));
        comboBoxDays.addItem(new ComboBoxItem("4","Sunday"));

        comboBoxTime.removeAllItems();
        comboBoxTime.addItem(new ComboBoxItem("-1", "No time selected"));
        for(int i = 14; i <= 24; i+=2) {
            int j = 1;
            comboBoxTime.addItem(new ComboBoxItem(String.valueOf(j), String.valueOf(i)));
        }

        comboBoxScene.removeAllItems();
        comboBoxScene.addItem(new ComboBoxItem("-1", "No scene selected"));
        comboBoxScene.addItem(new ComboBoxItem("1", "Avalon"));
        comboBoxScene.addItem(new ComboBoxItem("2", "Euphoria"));
        comboBoxScene.addItem(new ComboBoxItem("3", "Orange"));
    }

    private void registerListeners() {
        btnManage.addActionListener(new ManageConcertsListener());
        rdBtnAdd.addItemListener(new RadioButtonListener());
        rdBtnChange.addItemListener(new RadioButtonListener());
        rdBtnDelete.addItemListener(new RadioButtonListener());
        comboBoxBands.addItemListener(new ComboBoxListener());
    }

    /*
        Updates the lists when clicking on the tab corresponding to this panel.
     */
    public void setBands(ArrayList<Band> allBands) {
        this.listOfBands = allBands;
        populateComboBoxes();
    }

    /*
        An ItemListener that is given to the ComboBoxBands to listen on item changed.
     */
    public class ComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                Concert concert = controller.getConcertByBand(comboBoxBands.getItemAt(comboBoxBands.getSelectedIndex()).getDbId());
                System.out.println(comboBoxBands.getSelectedIndex());
                System.out.println(concert);
                if (concert != null) {
                    comboBoxDays.setSelectedIndex(Integer.parseInt(concert.getDay()));

                    switch (concert.getTime()) {
                        case "14":
                            comboBoxTime.setSelectedIndex(1);
                            break;

                        case "16":
                            comboBoxTime.setSelectedIndex(2);
                            break;

                        case "18":
                            comboBoxTime.setSelectedIndex(3);
                            break;

                        case "20":
                            comboBoxTime.setSelectedIndex(4);
                            break;

                        case "22":
                            comboBoxTime.setSelectedIndex(5);
                            break;

                        case "24":
                            comboBoxTime.setSelectedIndex(6);
                            break;
                    }

                    switch (concert.getScene()) {
                        case "Avalon":
                            comboBoxScene.setSelectedIndex(1);
                            break;

                        case "Euphoria":
                            comboBoxScene.setSelectedIndex(2);
                            break;

                        case "Orange":
                            comboBoxScene.setSelectedIndex(3);
                            break;
                    }

                    if(rdBtnDelete.isSelected()) {
                        comboBoxDays.setEnabled(false);
                        comboBoxTime.setEnabled(false);
                        comboBoxScene.setEnabled(false);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No concert available from this band");
                    comboBoxDays.setSelectedIndex(0);
                    comboBoxDays.setEnabled(true);
                    comboBoxTime.setSelectedIndex(0);
                    comboBoxTime.setEnabled(true);
                    comboBoxScene.setSelectedIndex(0);
                    comboBoxScene.setEnabled(true);
                }
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                System.out.println("Item deselected");
            }
        }
    }

    /*
        Listens on changes in radio buttons.
     */
    public class RadioButtonListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED && e.getItem() == rdBtnAdd) {
                System.out.println("rd add");
                btnManage.setText("Add concert");
                comboBoxBands.setSelectedIndex(0);
                comboBoxBands.setEnabled(true);
                comboBoxDays.setSelectedIndex(0);
                comboBoxDays.setEnabled(true);
                comboBoxTime.setSelectedIndex(0);
                comboBoxTime.setEnabled(true);
                comboBoxScene.setSelectedIndex(0);
                comboBoxScene.setEnabled(true);
            } else if(e.getStateChange() == ItemEvent.SELECTED && e.getItem() == rdBtnChange) {
                System.out.println("rd change");
                btnManage.setText("Change concert");
                comboBoxBands.setSelectedIndex(0);
                comboBoxBands.setEnabled(true);
                comboBoxDays.setSelectedIndex(0);
                comboBoxDays.setEnabled(true);
                comboBoxTime.setSelectedIndex(0);
                comboBoxTime.setEnabled(true);
                comboBoxScene.setSelectedIndex(0);
                comboBoxScene.setEnabled(true);
            } else if(e.getStateChange() == ItemEvent.SELECTED && e.getItem() == rdBtnDelete){
                System.out.println("rd delete");
                btnManage.setText("Delete concert");
                comboBoxBands.setSelectedIndex(0);
                comboBoxDays.setSelectedIndex(0);
                comboBoxTime.setSelectedIndex(0);
                comboBoxScene.setSelectedIndex(0);
            }
        }
    }

    /*
        The action listener, listens on the button pressed, and depending on the radio button chosen it will do what either Adding, Changing or Deleting a concert.
        //TODO: Update the ComboBoxes
     */
    public class ManageConcertsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(rdBtnAdd.isSelected()) {
                if(comboBoxBands.getSelectedIndex() != 0 && comboBoxDays.getSelectedIndex() != 0 && comboBoxTime.getSelectedIndex() != 0 && comboBoxScene.getSelectedIndex() != 0) {
                    Concert concert = new Concert("-1", Integer.parseInt(comboBoxBands.getItemAt(comboBoxBands.getSelectedIndex()).getDbId()), comboBoxDays.getItemAt(comboBoxDays.getSelectedIndex()).getDbId(),
                            comboBoxTime.getItemAt(comboBoxTime.getSelectedIndex()).getText(), comboBoxScene.getItemAt(comboBoxScene.getSelectedIndex()).getText());
                    Concert concertByBand = controller.getConcertByBand(comboBoxBands.getItemAt(comboBoxBands.getSelectedIndex()).getDbId());
                    if(concertByBand == null) {
                        if(controller.addConcert(concert)) {
                            JOptionPane.showMessageDialog(null, "Concert added");
                        } else {
                            JOptionPane.showMessageDialog(null, "Concert could not be added");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Band already got a booked concert!");
                    }
                }
            } else if(rdBtnChange.isSelected()) {
                if(comboBoxBands.getSelectedIndex() != 0 && comboBoxDays.getSelectedIndex() != 0 && comboBoxTime.getSelectedIndex() != 0 && comboBoxScene.getSelectedIndex() != 0) {
                    Concert concert = new Concert("-1", Integer.parseInt(comboBoxBands.getItemAt(comboBoxBands.getSelectedIndex()).getDbId()), comboBoxDays.getItemAt(comboBoxDays.getSelectedIndex()).getDbId(),
                            comboBoxTime.getItemAt(comboBoxTime.getSelectedIndex()).getText(), comboBoxScene.getItemAt(comboBoxScene.getSelectedIndex()).getText());
                    Concert concertByBand = controller.getConcertByBand(comboBoxBands.getItemAt(comboBoxBands.getSelectedIndex()).getDbId());
                    if(concertByBand != null) {
                        if(controller.changeConcert(Integer.parseInt(concertByBand.getConcert_id()),concert)) {
                            JOptionPane.showMessageDialog(null, "Concert changed");
                        } else {
                            JOptionPane.showMessageDialog(null, "Concert could not be changed");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "This concert does not exist");
                    }
                }

            } else {
                Concert concertByBand = controller.getConcertByBand(comboBoxBands.getItemAt(comboBoxBands.getSelectedIndex()).getDbId());
                if(concertByBand != null) {
                    System.out.println(concertByBand.getConcert_id());
                    if(!controller.deleteConcert(concertByBand.getConcert_id())) {
                        JOptionPane.showMessageDialog(null, "Concert could not be deleted");
                    }
                }
            }
        }
    }



}
