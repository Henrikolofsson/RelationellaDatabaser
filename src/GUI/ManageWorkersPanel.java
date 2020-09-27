package GUI;

import Controllers.MainController;
import Entities.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageWorkersPanel extends JPanel {
    private MainController controller;
    private ArrayList<Worker> listOfWorkers;
    private JLabel lblWorkers;
    private JComboBox<String> comboBoxWorkers;
    private JLabel lblWorkerName;
    private JTextField txtWorkerName;
    private JLabel lblWorkerPersonNumber;
    private JTextField txtWorkerPersonNumber;
    private JLabel lblWorkerAddress;
    private JTextField txtWorkerAddress;

    private ButtonGroup buttonGroup;
    private JRadioButton rdBtnAdd;
    private JRadioButton rdBtnChange;
    private JRadioButton rdBtnDelete;

    private JButton btnManageWorker;

    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");

    public ManageWorkersPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblWorkers = new JLabel("Available workers:");

        //TODO: fill combobox
        comboBoxWorkers = new JComboBox<>();
        populateComboBox();
       // comboBoxWorkers.addItem("No worker selected");

        buttonGroup = new ButtonGroup();
        rdBtnAdd = new JRadioButton("Add");
        rdBtnChange = new JRadioButton("Change");
        rdBtnDelete = new JRadioButton("Delete");
        buttonGroup.add(rdBtnAdd);
        buttonGroup.add(rdBtnChange);
        buttonGroup.add(rdBtnDelete);
        rdBtnAdd.setSelected(true);

        lblWorkerName = new JLabel("Workers name:");
        txtWorkerName = new JTextField();

        lblWorkerPersonNumber = new JLabel("Workers person number:");
        txtWorkerPersonNumber = new JTextField();

        lblWorkerAddress = new JLabel("Workers address:");
        txtWorkerAddress = new JTextField();

        btnManageWorker = new JButton("Manage worker");



    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setSize(new Dimension(580, 380));
        setBackground(GRAY_BACKGROUND_COLOR);

        lblWorkers.setForeground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 50, 5, 80);
        add(lblWorkers, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        add(comboBoxWorkers, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        add(rdBtnAdd, c);

        lblWorkerName.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        add(lblWorkerName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        add(txtWorkerName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        add(rdBtnChange, c);

        lblWorkerPersonNumber.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        add(lblWorkerPersonNumber, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        add(txtWorkerPersonNumber, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        add(rdBtnDelete, c);

        lblWorkerAddress.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        add(lblWorkerAddress, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        add(txtWorkerAddress, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        add(btnManageWorker, c);

    }

    private void populateComboBox() {
        comboBoxWorkers.removeAllItems();
        comboBoxWorkers.addItem("No worker selected");

        listOfWorkers = controller.getAllWorkers();
        for(Worker w : listOfWorkers) {
            comboBoxWorkers.addItem(w.toString());
        }
    }

    private void registerListeners() {
        btnManageWorker.addActionListener(new ManageWorkerListener());
    }

    public class ManageWorkerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(rdBtnAdd.isSelected()) {
                if(!txtWorkerName.getText().toString().isEmpty() && !txtWorkerPersonNumber.getText().toString().isEmpty() && !txtWorkerAddress.getText().toString().isEmpty()) {
                    if(controller.addWorker(txtWorkerPersonNumber.getText().toString(), txtWorkerName.getText().toString(), txtWorkerAddress.getText().toString())) {
                        System.out.println("Worker added!");
                    } else {
                        System.out.println("Could not add the worker!");
                    }

                } else {
                    JOptionPane.showConfirmDialog(null, "All fields is not filled correctly.");
                }
            } else if(rdBtnChange.isSelected()) {
                if(!txtWorkerName.getText().toString().isEmpty() && !txtWorkerPersonNumber.getText().toString().isEmpty() && !txtWorkerAddress.getText().toString().isEmpty()) {
                    if(comboBoxWorkers.getSelectedIndex() != 0) {
                        Worker worker = new Worker(txtWorkerPersonNumber.getText().toString(), txtWorkerName.getText().toString(), txtWorkerAddress.getText().toString());

                        if(controller.changeWorker(worker)) {
                            System.out.println("Combobox:" + comboBoxWorkers.getItemAt(comboBoxWorkers.getSelectedIndex()));
                            System.out.println("Arraylist:" + listOfWorkers.get(comboBoxWorkers.getSelectedIndex()-1).toString());


                        } else {

                        }

                    } else {
                        System.out.println("You need to select a worker");
                    }
                }
            }
        }
    }
}
