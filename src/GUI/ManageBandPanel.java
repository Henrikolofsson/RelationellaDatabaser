package GUI;

import Controllers.MainController;
import Entities.Band;
import Entities.ComboBoxItem;
import Entities.Worker;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    The panel for managing bands.
 */
public class ManageBandPanel extends JPanel {
    private MainController controller;
    private ArrayList<Worker> listOfWorkers;
    private ArrayList<Band> listOfBands;

    private JPanel pnlBookBand;
    ArrayList<String> allLanguages;
    private JLabel lblBandName;
    private JTextField txtBandName;
    private JLabel lblBandCountry;
    private JComboBox<String> comboBoxCountry;
    private JLabel lblAssignedWorker;
    private JComboBox<ComboBoxItem> comboBoxWorker;
    private JLabel lblBandInfo;
    private JTextArea txtCountryInfo;
    private JButton btnBookBand;

    private JPanel pnlChangeBand;
    private JLabel lblChangeBand;
    private JComboBox<ComboBoxItem> comboBoxBands;
    private JLabel lblChangeBandName;
    private JTextField txtChangeBandName;
    private JLabel lblChangeBandCountry;
    private JComboBox<String> comboBoxChangeCountry;
    private JLabel lblChangeWorker;
    private JComboBox<ComboBoxItem> comboBoxChangeWorker;
    private JLabel lblChangeBandInfo;
    private JTextArea txtChangeBandInfo;
    private JButton btnChangeBand;

    private JPanel pnlDeleteBand;
    private JLabel lblDeleteBand;
    private JComboBox<ComboBoxItem> comboBoxDeleteBand;
    private JButton btnDeleteBand;

    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");

    public ManageBandPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        //Initializing components for booking band
        pnlBookBand = new JPanel(new GridBagLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Book band");
        titledBorder.setTitleColor(Color.WHITE);
        pnlBookBand.setBorder(titledBorder);
        pnlBookBand.setBackground(GRAY_BACKGROUND_COLOR);
        listOfWorkers = new ArrayList<>();
        listOfWorkers = controller.getAllWorkers();
        lblBandName = new JLabel("Band name: ");
        lblBandName.setForeground(Color.WHITE);
        txtBandName = new JTextField();
        lblBandCountry = new JLabel("Band country: ");
        lblBandCountry.setForeground(Color.WHITE);
        allLanguages = new ArrayList<String>();
        String[] languages = Locale.getISOLanguages();
        for (int i = 0; i < languages.length; i++){
            Locale loc = new Locale(languages[i]);
            allLanguages.add(loc.getDisplayLanguage());
        }
        comboBoxCountry = new JComboBox<String>();
        comboBoxCountry.setModel(new DefaultComboBoxModel<>(allLanguages.toArray(new String[0])));
        comboBoxCountry.setVisible(true);
        comboBoxCountry.setSize(new Dimension(50, 50));
        comboBoxCountry.setMaximumSize(new Dimension(50,50));
        comboBoxCountry.setMinimumSize(new Dimension(50,50));
        lblAssignedWorker = new JLabel("Assigned worker: ");
        lblAssignedWorker.setForeground(Color.WHITE);
        comboBoxWorker = new JComboBox<ComboBoxItem>();
        comboBoxWorker.setVisible(true);
        comboBoxWorker.setSize(new Dimension(50, 50));
        lblBandInfo = new JLabel("Band info: ");
        lblBandInfo.setForeground(Color.WHITE);
        txtCountryInfo = new JTextArea(2,50);
        btnBookBand = new JButton("Book band");

        //Initializing components for changing booked bands
        pnlChangeBand = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Change band");
        titledBorder.setTitleColor(Color.WHITE);
        pnlChangeBand.setBorder(titledBorder);
        pnlChangeBand.setBackground(GRAY_BACKGROUND_COLOR);
        listOfBands = new ArrayList<>();
        listOfBands = controller.getAllBands();
        lblChangeBand = new JLabel("Band to change:");
        lblChangeBand.setForeground(Color.WHITE);
        comboBoxBands = new JComboBox<ComboBoxItem>();
        lblChangeBandName = new JLabel("New band name:");
        lblChangeBandName.setForeground(Color.WHITE);
        txtChangeBandName = new JTextField();
        lblChangeBandCountry = new JLabel("New band country:");
        lblChangeBandCountry.setForeground(Color.WHITE);
        comboBoxChangeCountry = new JComboBox<>();
        comboBoxChangeCountry.setModel(new DefaultComboBoxModel<>(allLanguages.toArray(new String[0])));
        comboBoxChangeCountry.setVisible(true);
        lblChangeWorker = new JLabel("New assigned worker:");
        lblChangeWorker.setForeground(Color.WHITE);
        comboBoxChangeWorker = new JComboBox<ComboBoxItem>();
        lblChangeBandInfo = new JLabel("New band info:");
        lblChangeBandInfo.setForeground(Color.WHITE);
        txtChangeBandInfo = new JTextArea();
        btnChangeBand = new JButton("Change band");

        //Initializing components for deleting booked bands
        pnlDeleteBand = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Delete band");
        titledBorder.setTitleColor(Color.WHITE);
        pnlDeleteBand.setBorder(titledBorder);
        pnlDeleteBand.setBackground(GRAY_BACKGROUND_COLOR);
        lblDeleteBand = new JLabel("Delete band:");
        lblDeleteBand.setForeground(Color.WHITE);
        comboBoxDeleteBand = new JComboBox<ComboBoxItem>();
        btnDeleteBand = new JButton("Delete band");

    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setSize(new Dimension(580, 380));
        setBackground(GRAY_BACKGROUND_COLOR);

        //Adding book band panel
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 50, 5, 80);
        pnlBookBand.add(lblBandName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlBookBand.add(txtBandName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pnlBookBand.add(lblBandCountry, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        pnlBookBand.add(comboBoxCountry, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        pnlBookBand.add(lblAssignedWorker, c);

        //TODO: Fetch the available workers from the database
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pnlBookBand.add(comboBoxWorker, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        pnlBookBand.add(lblBandInfo, c);

        //TODO: set size of text area
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        pnlBookBand.add(txtCountryInfo, c);

        //TODO: Add buttons for INSERTING information to database
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        pnlBookBand.add(btnBookBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(pnlBookBand, c);

        //Adding change band panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 50, 5, 80);
        pnlChangeBand.add(lblChangeBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlChangeBand.add(comboBoxBands, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pnlChangeBand.add(lblChangeBandName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        pnlChangeBand.add(txtChangeBandName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        pnlChangeBand.add(lblChangeBandCountry, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pnlChangeBand.add(comboBoxChangeCountry, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        pnlChangeBand.add(lblChangeWorker, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        pnlChangeBand.add(comboBoxChangeWorker, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        pnlChangeBand.add(lblChangeBandInfo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        pnlChangeBand.add(txtChangeBandInfo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 5;
        pnlChangeBand.add(btnChangeBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(pnlChangeBand, c);

        //Adding delete band panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 50, 5, 80);
        pnlDeleteBand.add(lblDeleteBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlDeleteBand.add(comboBoxDeleteBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        pnlDeleteBand.add(btnDeleteBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        add(pnlDeleteBand, c);

        populateComboBox();
    }

    /*
        Fills up the ComboBoxes
     */
    private void populateComboBox() {
        listOfBands = controller.getAllBands();
        listOfWorkers = controller.getAllWorkers();

        comboBoxWorker.removeAllItems();
        comboBoxWorker.addItem(new ComboBoxItem("-1", "No worker selected"));
        for(Worker w : listOfWorkers) {
            comboBoxWorker.addItem(new ComboBoxItem(w.getPerson_number(),w.getName()));
        }
        comboBoxWorker.setRenderer(new ListCellRenderer());
        for(int i = 0; i < comboBoxWorker.getItemCount(); i++) {
            System.out.println(comboBoxWorker.getItemAt(i));
        }

        comboBoxBands.removeAllItems();
        comboBoxBands.addItem(new ComboBoxItem("-1", "No band selected"));
        for(Band b : listOfBands) {
            comboBoxBands.addItem(new ComboBoxItem(String.valueOf(b.getBand_id()), b.getBand_name()));
        }
        comboBoxBands.setRenderer(new ListCellRenderer());
        for(int i = 0; i < comboBoxBands.getItemCount(); i++) {
            System.out.println(comboBoxBands.getItemAt(i));
        }

        comboBoxChangeWorker.removeAllItems();
        comboBoxChangeWorker.addItem(new ComboBoxItem("-1", "No worker selected"));
        for(Worker w : listOfWorkers) {
            comboBoxChangeWorker.addItem(new ComboBoxItem(w.getPerson_number(), w.getName()));
        }
        for(int i = 0; i < comboBoxChangeWorker.getItemCount(); i++) {
            System.out.println(comboBoxChangeWorker.getItemAt(i));
        }

        comboBoxDeleteBand.removeAllItems();
        comboBoxDeleteBand.addItem(new ComboBoxItem("-1", "No band selected"));
        for(Band b : listOfBands) {
            comboBoxDeleteBand.addItem(new ComboBoxItem(String.valueOf(b.getBand_id()), b.getBand_name()));
        }
        for(int i = 0; i < comboBoxDeleteBand.getItemCount(); i++) {
            System.out.println(comboBoxDeleteBand.getItemAt(i));
        }

    }

    private void registerListeners() {
        btnBookBand.addActionListener(new BookBandListener());
        btnChangeBand.addActionListener(new ChangeBandListener());
        btnDeleteBand.addActionListener(new DeleteBandListener());
    }

    /*
        Updates the lists when clicking on the tab corresponding to this panel.
     */
    public void setLists(ArrayList<Worker> workers, ArrayList<Band> bands) {
        this.listOfWorkers = workers;
        this.listOfBands = bands;
        populateComboBox();
    }

    /*
        ActionListener for booking a band.
     */
    public class BookBandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
                if(!txtBandName.getText().isEmpty() && comboBoxWorker.getSelectedIndex() != 0 && !txtCountryInfo.getText().isEmpty()) {

                    Band band = new Band(-1, txtBandName.getText(), comboBoxCountry.getSelectedItem().toString(),
                            txtCountryInfo.getText(), comboBoxWorker.getItemAt(comboBoxWorker.getSelectedIndex()).getDbId());
                    if(controller.addBand(band)) {
                        populateComboBox();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You need to fill the form!");
                }
        }
    }

    /*
       ActionListener for changing a band.
    */
    public class ChangeBandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(comboBoxBands.getSelectedIndex() != 0) {
                if(comboBoxChangeWorker.getSelectedIndex() != 0) {
                    if(!txtChangeBandName.getText().isEmpty() && !txtChangeBandInfo.getText().isEmpty()) {

                        Band band = new Band(Integer.parseInt(comboBoxBands.getItemAt(comboBoxBands.getSelectedIndex()).getDbId()),txtChangeBandName.getText(),comboBoxChangeCountry.getSelectedItem().toString() ,txtChangeBandInfo.getText() , comboBoxChangeWorker.getItemAt(comboBoxChangeWorker.getSelectedIndex()).getDbId());
                        if(controller.changeBand(band)) {
                            populateComboBox();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You need to select a worker!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to select what band you want to edit!");
            }
        }
    }

    /*
       ActionListener for deleting a band.
    */
    public class DeleteBandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(comboBoxDeleteBand.getSelectedIndex() != 0) {
                if(controller.deleteBand(comboBoxDeleteBand.getItemAt(comboBoxDeleteBand.getSelectedIndex()).getDbId())) {
                    populateComboBox();
                }
            }
        }
    }
}
