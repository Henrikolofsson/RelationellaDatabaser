package GUI;

import Controllers.MainController;
import Entities.ComboBoxItem;
import Entities.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    The panel for managing workers.
 */
public class ManageWorkersPanel extends JPanel {
    private MainController controller;
    private ArrayList<Worker> listOfWorkers;
    private JLabel lblWorkers;
    private JComboBox<ComboBoxItem> comboBoxWorkers;
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

        comboBoxWorkers = new JComboBox<ComboBoxItem>();
        populateComboBox();
        comboBoxWorkers.setEnabled(false);

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


        lblWorkerName = new JLabel("Workers name:");
        txtWorkerName = new JTextField();

        lblWorkerPersonNumber = new JLabel("Workers person number:");
        txtWorkerPersonNumber = new JTextField();

        lblWorkerAddress = new JLabel("Workers address:");
        txtWorkerAddress = new JTextField();

        btnManageWorker = new JButton("Add worker");



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
        comboBoxWorkers.addItem(new ComboBoxItem("-1", "No worker selected"));

        listOfWorkers = controller.getAllWorkers();
        for(Worker w : listOfWorkers) {
            comboBoxWorkers.addItem(new ComboBoxItem(w.getPerson_number(), w.getName()));
        }
        comboBoxWorkers.setRenderer(new ListCellRenderer());
    }

    private void clearFields() {
        txtWorkerName.setText("");
        txtWorkerPersonNumber.setText("");
        txtWorkerAddress.setText("");
        txtWorkerPersonNumber.setEnabled(true);
    }

    private void registerListeners() {
        btnManageWorker.addActionListener(new ManageWorkerListener());
        rdBtnAdd.addItemListener(new RadioButtonListener());
        rdBtnChange.addItemListener(new RadioButtonListener());
        rdBtnDelete.addItemListener(new RadioButtonListener());
        comboBoxWorkers.addItemListener(new ComboBoxListener());
    }

    public void setWorkers(ArrayList<Worker> allWorkers) {
        this.listOfWorkers = allWorkers;
        populateComboBox();
    }

    /*
        Listens on item change event in ComboBoxes.
     */
    public class ComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            int index = comboBoxWorkers.getSelectedIndex();
            if(index > 0) {
                txtWorkerName.setText(comboBoxWorkers.getItemAt(comboBoxWorkers.getSelectedIndex()).getText());
                txtWorkerPersonNumber.setText(comboBoxWorkers.getItemAt(comboBoxWorkers.getSelectedIndex()).getDbId());
                //TODO: Fix this quickfix for address. Unnecessary loop
                for(Worker w : listOfWorkers) if(w.getPerson_number().equals(comboBoxWorkers.getItemAt(comboBoxWorkers.getSelectedIndex()).getDbId())) txtWorkerAddress.setText(w.getAddress());
                txtWorkerPersonNumber.setEnabled(false);
            } else if(index == 0) {
                clearFields();
            }
        }
    }

    public class RadioButtonListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED && e.getItem() == rdBtnAdd) {
                System.out.println("rd add");
                comboBoxWorkers.setSelectedIndex(0);
                comboBoxWorkers.setEnabled(false);
                btnManageWorker.setText("Add worker");
                txtWorkerPersonNumber.setEnabled(true);
            } else if(e.getStateChange() == ItemEvent.SELECTED && e.getItem() == rdBtnChange) {
                comboBoxWorkers.setEnabled(true);
                System.out.println("rd change");
                btnManageWorker.setText("Change worker");
            } else if(e.getStateChange() == ItemEvent.SELECTED && e.getItem() == rdBtnDelete){
                comboBoxWorkers.setEnabled(true);
                btnManageWorker.setText("Delete worker");
                System.out.println("rd delete");
            }
        }
    }

    /*
        The action listener, listens on the button pressed, and depending on the radio button chosen it will do what either Adding, Changing or Deleting a worker.
     */
    public class ManageWorkerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(rdBtnAdd.isSelected()) {
                if(!txtWorkerName.getText().isEmpty() && !txtWorkerPersonNumber.getText().isEmpty() && !txtWorkerAddress.getText().isEmpty()) {
                    if(controller.addWorker(txtWorkerPersonNumber.getText(), txtWorkerName.getText(), txtWorkerAddress.getText())) {
                        System.out.println("Worker added!");
                    } else {
                        System.out.println("Could not add the worker!");
                    }

                } else {
                    JOptionPane.showConfirmDialog(null, "All fields is not filled correctly.");
                }
            } else if(rdBtnChange.isSelected()) {
                if(!txtWorkerName.getText().isEmpty() && !txtWorkerPersonNumber.getText().isEmpty() && !txtWorkerAddress.getText().isEmpty()) {
                    if(comboBoxWorkers.getSelectedIndex() != 0) {
                        Worker worker = new Worker(txtWorkerPersonNumber.getText(), txtWorkerName.getText(), txtWorkerAddress.getText());

                        if(controller.changeWorker(worker)) {
                            JOptionPane.showMessageDialog(null, "Worker is now edited!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Could not edit the worker!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You need to select a worker to edit!");
                    System.out.println("You need to select a worker");
                }
            } else {
                if(comboBoxWorkers.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "You need to select a worker to delete!");
                } else {
                    if(!txtWorkerName.getText().isEmpty() && !txtWorkerAddress.getText().isEmpty()) {
                        if(controller.deleteWorker(listOfWorkers.get(comboBoxWorkers.getSelectedIndex()-1))) {
                            JOptionPane.showMessageDialog(null, "Worker is now deleted!");
                        }
                    }
                }
            }
            populateComboBox();
        }
    }
}
