package GUI;

import Controllers.MainController;
import Entities.Band;
import Entities.BandMember;
import Entities.ComboBoxItem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    The panel for managing band members.
 */
public class ManageBandMemberPanel extends JPanel {
    private MainController controller;
    private ArrayList<Band> listOfBands;
    private ArrayList<BandMember> listOfBandMembers;

    private JPanel pnlAddMember;
    private JLabel lblAddBandMemberName;
    private JTextField txtAddBandMemberName;
    private JLabel lblAddBandMemberInfo;
    private JTextField txtAddBandMemberInfo;
    private JButton btnAddBandMember;

    private JPanel pnlChangeMember;
    private JLabel lblBandMembers;
    private JComboBox<ComboBoxItem> comboBoxBandMembers;
    private JLabel lblChangeBandMemberName;
    private JTextField txtChangeBandMemberName;
    private JLabel lblChangeBandMemberInfo;
    private JTextField txtChangeBandMemberInfo;
    private JButton btnChangeBandMember;

    private JPanel pnlDeleteMember;
    private JLabel lblDeleteBandMember;
    private JComboBox<ComboBoxItem> comboBoxBandMembersToDelete;
    private JButton btnDeleteBandMember;

    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");


    public ManageBandMemberPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    /*
        Initializes all the components in the class.
     */
    private void initializeComponents() {
        listOfBands = controller.getAllBands();
        listOfBandMembers = controller.getAllBandMembers();

        pnlAddMember = new JPanel(new GridBagLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Add band member");
        titledBorder.setTitleColor(Color.WHITE);
        pnlAddMember.setBorder(titledBorder);
        pnlAddMember.setBackground(GRAY_BACKGROUND_COLOR);
        //TODO: Fill combobox bands with all the bands available
        lblAddBandMemberName = new JLabel("Band member name:");
        lblAddBandMemberName.setForeground(Color.WHITE);
        txtAddBandMemberName = new JTextField();
        lblAddBandMemberInfo = new JLabel("Band member info:");
        lblAddBandMemberInfo.setForeground(Color.WHITE);
        txtAddBandMemberInfo = new JTextField();
        btnAddBandMember = new JButton("Add band member");

        pnlChangeMember = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Change band member");
        titledBorder.setTitleColor(Color.WHITE);
        pnlChangeMember.setBorder(titledBorder);
        pnlChangeMember.setBackground(GRAY_BACKGROUND_COLOR);
        listOfBandMembers = controller.getAllBandMembers();
        lblBandMembers = new JLabel("All band members:");
        lblBandMembers.setForeground(Color.WHITE);
        //TODO: Fill combobox bands with all the band members available
        comboBoxBandMembers = new JComboBox<ComboBoxItem>();
        comboBoxBandMembers.addItem(new ComboBoxItem("-1", "No band member selected"));
        lblChangeBandMemberName = new JLabel("Band member name to change:");
        lblChangeBandMemberName.setForeground(Color.WHITE);
        txtChangeBandMemberName = new JTextField();
        lblChangeBandMemberInfo = new JLabel("Band member info to change:");
        lblChangeBandMemberInfo.setForeground(Color.WHITE);
        txtChangeBandMemberInfo = new JTextField();
        btnChangeBandMember = new JButton("Change band member");

        pnlDeleteMember = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Delete band member");
        titledBorder.setTitleColor(Color.WHITE);
        pnlDeleteMember.setBorder(titledBorder);
        pnlDeleteMember.setBackground(GRAY_BACKGROUND_COLOR);
        lblDeleteBandMember = new JLabel("Choose member to delete:");
        lblDeleteBandMember.setForeground(Color.WHITE);
        //TODO: Fill combobox bands with all the band members available to delete
        comboBoxBandMembersToDelete = new JComboBox<ComboBoxItem>();
        comboBoxBandMembersToDelete.addItem(new ComboBoxItem("-1", "No band member selected"));
        btnDeleteBandMember = new JButton("Delete band member");


        populateComboBoxes();
    }

    /*
        Places all the components in this panel and renders it.
     */
    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setSize(new Dimension(580, 780));
        setBackground(GRAY_BACKGROUND_COLOR);

        //Adding components for ADD band member
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 50, 5, 80);
        pnlAddMember.add(lblAddBandMemberName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlAddMember.add(txtAddBandMemberName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pnlAddMember.add(lblAddBandMemberInfo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        pnlAddMember.add(txtAddBandMemberInfo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pnlAddMember.add(btnAddBandMember, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(pnlAddMember, c);

        //Adding components for CHANGE band member
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        pnlChangeMember.add(lblBandMembers, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlChangeMember.add(comboBoxBandMembers, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pnlChangeMember.add(lblChangeBandMemberName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        pnlChangeMember.add(txtChangeBandMemberName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        pnlChangeMember.add(lblChangeBandMemberInfo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pnlChangeMember.add(txtChangeBandMemberInfo, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        pnlChangeMember.add(btnChangeBandMember, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(pnlChangeMember, c);

        //Adding components for DELETE band member
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        pnlDeleteMember.add(lblDeleteBandMember, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlDeleteMember.add(comboBoxBandMembersToDelete, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pnlDeleteMember.add(btnDeleteBandMember, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        add(pnlDeleteMember, c);
    }

    private void registerListeners() {
        btnAddBandMember.addActionListener(new AddBandMemberListener());
       // btnAddMemberToBand.addActionListener(new AddBandMemberToBandListener());
        btnChangeBandMember.addActionListener(new ChangeBandMemberListener());
        btnDeleteBandMember.addActionListener(new DeleteBandMemberListener());
    }

    /*
        Filling up the ComboBoxes
     */
    private void populateComboBoxes() {
        listOfBands = controller.getAllBands();
        listOfBandMembers = controller.getAllBandMembers();

        comboBoxBandMembers.removeAllItems();
        comboBoxBandMembers.addItem(new ComboBoxItem("-1", "No band member selected"));
        for(BandMember bm : listOfBandMembers) comboBoxBandMembers.addItem(new ComboBoxItem(String.valueOf(bm.getBand_member_id()), bm.getBand_member_name()));

        comboBoxBandMembersToDelete.removeAllItems();
        comboBoxBandMembersToDelete.addItem(new ComboBoxItem("-1", "No band member selected"));
        for(BandMember bm : listOfBandMembers) comboBoxBandMembersToDelete.addItem(new ComboBoxItem(String.valueOf(bm.getBand_member_id()), bm.getBand_member_name()));
    }

    public void setBandMembers(ArrayList<BandMember> allBandMembers) {
        this.listOfBandMembers = allBandMembers;
        populateComboBoxes();
    }

    /*
        ActionListener for adding a band member.
     */
    public class AddBandMemberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(!txtAddBandMemberName.getText().isEmpty() && !txtAddBandMemberInfo.getText().isEmpty()) {
                BandMember bandMember = new BandMember(-1, txtAddBandMemberName.getText(), txtAddBandMemberInfo.getText());
                if(controller.addBandMember(bandMember)) {
                    //TODO: Update fields etc
                    populateComboBoxes();
                } else {
                    JOptionPane.showMessageDialog(null, "Could not add the bandmember");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to fill the form correctly");
            }
        }
    }

    /*
        ActionListener for changing a band member.
     */
    public class ChangeBandMemberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(comboBoxBandMembers.getSelectedIndex() != 0 && !txtChangeBandMemberName.getText().isEmpty() && !txtChangeBandMemberInfo.getText().isEmpty()) {
                BandMember bandMember = new BandMember(Integer.parseInt(comboBoxBandMembers.getItemAt(comboBoxBandMembers.getSelectedIndex()).getDbId()), txtChangeBandMemberName.getText(), txtChangeBandMemberInfo.getText());
                if(controller.changeBandMember(bandMember)) {
                    populateComboBoxes();
                } else {
                    JOptionPane.showMessageDialog(null, "Could not edit the band member");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to select a band member and fill the form");
            }
        }
    }

    /*
        ActionListener for adding a deleting member.
     */
    public class DeleteBandMemberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(comboBoxBandMembersToDelete.getSelectedIndex() != 0) {
                if(controller.deleteBandMember(Integer.parseInt(comboBoxBandMembersToDelete.getItemAt(comboBoxBandMembersToDelete.getSelectedIndex()).getDbId()))) {
                    populateComboBoxes();
                } else {
                    JOptionPane.showMessageDialog(null, "Could not delete band member and its associations");
                }
            }
        }
    }
}
