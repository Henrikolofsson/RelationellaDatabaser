package GUI;

import Controllers.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;

public class BookBandPanel extends JPanel {
    private MainController controller;
    private JLabel lblBandName;
    private JTextField txtBandName;
    private JLabel lblBandCountry;
    private JComboBox<String> comboBoxCountry;
    private JLabel lblAssignedWorker;
    private JComboBox<String> comboBoxWorker;
    private JLabel lblBandInfo;
    private JTextArea txtCountryInfo;
    private JButton btnBookBand;

    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");

    public BookBandPanel(MainController controller) {
        this.controller = controller;
        initializeGUI();
        registerListeners();
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setSize(new Dimension(580, 380));
        setBackground(GRAY_BACKGROUND_COLOR);

        lblBandName = new JLabel("Band name: ");
        lblBandName.setForeground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 50, 5, 80);
        add(lblBandName, c);

        txtBandName = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        add(txtBandName, c);

        lblBandCountry = new JLabel("Band country: ");
        lblBandCountry.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(lblBandCountry, c);

        ArrayList<String> allLanguages = new ArrayList<String>();
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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        add(comboBoxCountry, c);

        lblAssignedWorker = new JLabel("Assigned worker: ");
        lblAssignedWorker.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        add(lblAssignedWorker, c);

        //TODO: Fetch the available workers from the database
        comboBoxWorker = new JComboBox<>();
        comboBoxWorker.setVisible(true);
        comboBoxWorker.setSize(new Dimension(50, 50));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        add(comboBoxWorker, c);

        lblBandInfo = new JLabel("Band info: ");
        lblBandInfo.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        add(lblBandInfo, c);

        txtCountryInfo = new JTextArea(2,50);
        //TODO: set size of text area
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        add(txtCountryInfo, c);

        //TODO: Add buttons for INSERTING information to database
        btnBookBand = new JButton("Book band");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        add(btnBookBand, c);
    }

    private void registerListeners() {
        btnBookBand.addActionListener(new BookBandListener());
    }

    public class BookBandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}
