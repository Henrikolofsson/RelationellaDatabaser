package GUI;

import Controllers.MainController;
import Entities.Band;
import Entities.BandMember;
import Entities.BandMembersAssociation;
import Entities.ComboBoxItem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.*;

public class ViewBandMembersAssociationPanel extends JPanel {
    private MainController controller;
    private ArrayList<Band> listOfBands;
    private ArrayList<BandMember> listOfBandMembers;

    private JPanel pnlViewAssociations;
    private JLabel lblViewBands;
    private JComboBox comboBoxViewBandAssociations;
    private Vector vectorComboBoxViewBandAssociations;
    private JLabel lblViewBandMembers;
    private DefaultListModel<String> model;
    private JList<String> jlistOfBandMembers;

    private JPanel pnlAddMemberToBand;
    private JLabel lblBands;
    private JComboBox<String> comboBoxBands;
    private Vector vectorComboBoxBands;
    private JLabel lblAddBandMembersToBand;
    private JComboBox<String> comboBoxBandMembersToAdd;
    private Vector vectorComboBoxBandMembersToAdd;
    private JButton btnAddMemberToBand;

    private JPanel pnlRemoveBandMemberAssociation;
    private JLabel lblRemoveFromBand;
    private JComboBox<String> comboBoxRemoveFromBand;
    private Vector vectorComboBoxRemoveFromBand;
    private JLabel lblRemoveBandMember;
    private JComboBox<String> comboBoxRemoveBandMember;
    private Vector vectorComboBoxRemoveBandMember;
    private JButton btnRemoveMemberFromBand;


    private Color GRAY_BACKGROUND_COLOR = Color.decode("#808285");

    public ViewBandMembersAssociationPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        listOfBands = controller.getAllBands();
        listOfBandMembers = controller.getAllBandMembers();

        pnlViewAssociations = new JPanel(new GridBagLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "View band members associations");
        titledBorder.setTitleColor(Color.WHITE);
        pnlViewAssociations.setBorder(titledBorder);
        pnlViewAssociations.setBackground(GRAY_BACKGROUND_COLOR);
        lblViewBands = new JLabel("Band to show:");
        lblViewBands.setForeground(Color.WHITE);
        comboBoxViewBandAssociations = new JComboBox<>();
        lblViewBandMembers = new JLabel("Band members:");
        lblViewBandMembers.setForeground(Color.WHITE);
        model = new DefaultListModel<>();
        model.add(0, "No band member selected");
        jlistOfBandMembers = new JList<>(model);

        pnlAddMemberToBand = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Add band member to band");
        titledBorder.setTitleColor(Color.WHITE);
        pnlAddMemberToBand.setBorder(titledBorder);
        pnlAddMemberToBand.setBackground(GRAY_BACKGROUND_COLOR);
        lblBands = new JLabel("Bands:");
        lblBands.setForeground(Color.WHITE);
        comboBoxBands = new JComboBox<String>();
        lblAddBandMembersToBand = new JLabel("Band member to add:");
        lblAddBandMembersToBand.setForeground(Color.WHITE);
        comboBoxBandMembersToAdd = new JComboBox<>();
        comboBoxBandMembersToAdd.addItem("No band member selected");
        btnAddMemberToBand = new JButton("Add member to band");

        pnlRemoveBandMemberAssociation = new JPanel(new GridBagLayout());
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Remove band members associations");
        titledBorder.setTitleColor(Color.WHITE);
        pnlRemoveBandMemberAssociation.setBorder(titledBorder);
        pnlRemoveBandMemberAssociation.setBackground(GRAY_BACKGROUND_COLOR);
        lblRemoveFromBand = new JLabel("Remove associations from band:");
        lblRemoveFromBand.setForeground(Color.WHITE);
        comboBoxRemoveFromBand = new JComboBox<>();
        lblRemoveBandMember = new JLabel("Remove selected band member:");
        lblRemoveBandMember.setForeground(Color.WHITE);
        comboBoxRemoveBandMember = new JComboBox<>();
        btnRemoveMemberFromBand = new JButton("Delete association");
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setSize(new Dimension(580, 780));
        setBackground(GRAY_BACKGROUND_COLOR);

        //Adding VIEW panel
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 50, 5, 80);
        pnlViewAssociations.add(lblViewBands, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlViewAssociations.add(comboBoxViewBandAssociations, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pnlViewAssociations.add(lblViewBandMembers, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        pnlViewAssociations.add(jlistOfBandMembers, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(pnlViewAssociations, c);

        //Adding ADD panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        pnlAddMemberToBand.add(lblBands, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlAddMemberToBand.add(comboBoxBands, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pnlAddMemberToBand.add(lblAddBandMembersToBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        pnlAddMemberToBand.add(comboBoxBandMembersToAdd, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pnlAddMemberToBand.add(btnAddMemberToBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(pnlAddMemberToBand, c);

        //Adding DELETE panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        pnlRemoveBandMemberAssociation.add(lblRemoveFromBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pnlRemoveBandMemberAssociation.add(comboBoxRemoveFromBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pnlRemoveBandMemberAssociation.add(lblRemoveBandMember, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        pnlRemoveBandMemberAssociation.add(comboBoxRemoveBandMember, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pnlRemoveBandMemberAssociation.add(btnRemoveMemberFromBand, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        add(pnlRemoveBandMemberAssociation, c);

        populateComboBoxes();
    }

    private void registerListeners() {
        btnAddMemberToBand.addActionListener(new AddBandMemberToBandListener());
        btnRemoveMemberFromBand.addActionListener(new RemoveBandMemberAssociationListener());
        comboBoxViewBandAssociations.addItemListener(new BandSelectedListener());
        comboBoxRemoveFromBand.addItemListener(new BandSelectedRemoveListener());
    }

    private void populateComboBoxes() {
        System.out.println("PopulateComboboxes");
        listOfBands = controller.getAllBands();
        listOfBandMembers = controller.getAllBandMembers();
        populateViewComboBox();
        populateAddComboBox();
        populateRemoveComboBox();
    }

    private void populateViewComboBox() {
        comboBoxViewBandAssociations.removeAllItems();
//        vectorComboBoxViewBandAssociations.clear();
      //  vectorComboBoxViewBandAssociations.addElement(new ComboBoxItem(0, "No band selected"));
        int i = 0;
        for(Band b : listOfBands) {
        //    vectorComboBoxViewBandAssociations.addElement(new ComboBoxItem(b.getBand_id(), b.getBand_name()));
        }
       // comboBoxViewBandAssociations.setModel(vectorComboBoxViewBandAssociations);
    }

    private void populateAddComboBox() {
        comboBoxBands.removeAllItems();
        comboBoxBands.addItem("No band selected");
        for(Band b : listOfBands) comboBoxBands.addItem(b.getBand_name());

        comboBoxBandMembersToAdd.removeAllItems();
        comboBoxBandMembersToAdd.addItem("No band member selected");
        for(BandMember bm : listOfBandMembers) comboBoxBandMembersToAdd.addItem(bm.getBand_member_name());
    }

    private void populateRemoveComboBox() {
        comboBoxRemoveFromBand.removeAllItems();
        comboBoxRemoveFromBand.addItem("No band selected");
        for(Band b : listOfBands) comboBoxRemoveFromBand.addItem(b.getBand_name());

        comboBoxRemoveBandMember.removeAllItems();
        comboBoxRemoveBandMember.addItem("No band member selected");
    }

    public class BandSelectedListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            System.out.println("BandSelectedListener");
            if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
                ArrayList<BandMember> bandMembers = controller.getAllBandMembersInBand(listOfBands.get(comboBoxViewBandAssociations.getSelectedIndex()-1).getBand_id());
                if(bandMembers != null) {
                    model.clear();
                    int i = 0;
                    for(BandMember bm : bandMembers) {
                        model.add(i, bm.getBand_member_name());
                        i++;
                    }
                    jlistOfBandMembers.setModel(model);
                }
            }
        }
    }

    public class BandSelectedRemoveListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            System.out.println("BandSelectedRemoveListener");
            if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
                ArrayList<BandMember> bandMembers = controller.getAllBandMembersInBand(listOfBands.get(comboBoxRemoveFromBand.getSelectedIndex()-1).getBand_id());
                comboBoxRemoveBandMember.removeAllItems();
                comboBoxRemoveBandMember.addItem("No band member selected");
                for(BandMember bm : bandMembers) comboBoxRemoveBandMember.addItem(bm.getBand_member_name());
            }
        }
    }

     public class AddBandMemberToBandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(comboBoxBands.getSelectedIndex() != 0 && comboBoxBandMembersToAdd.getSelectedIndex() != 0) {
                BandMembersAssociation bandMembersAssociation = new BandMembersAssociation(listOfBands.get(comboBoxBands.getSelectedIndex()-1).getBand_id(),
                        listOfBandMembers.get(comboBoxBandMembersToAdd.getSelectedIndex()-1).getBand_member_id());
                if(controller.addBandMembersAssociation(bandMembersAssociation)) {
                    //TODO: present the added member
                } else {
                    JOptionPane.showMessageDialog(null, "Could not add the band member to the band");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to select a band and band member");
            }
        }
    }

    public class RemoveBandMemberAssociationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(comboBoxRemoveFromBand.getSelectedIndex() != 0 && comboBoxRemoveFromBand.getSelectedIndex() != 0) {
                System.out.println("Band id : " + listOfBands.get(comboBoxRemoveFromBand.getSelectedIndex()-1).getBand_id());
                System.out.println("Band member id: " + listOfBandMembers.get(comboBoxRemoveBandMember.getSelectedIndex()-1).getBand_member_id());
                listOfBandMembers = controller.getAllBandMembersInBand(comboBoxRemoveFromBand.getSelectedIndex()-1);
                if(controller.removeBandMemberFromBand(listOfBands.get(comboBoxRemoveFromBand.getSelectedIndex()-1).getBand_id(),
                        listOfBandMembers.get(comboBoxRemoveBandMember.getSelectedIndex()-1).getBand_member_id())) {

                } else {
                    JOptionPane.showMessageDialog(null, "Could not remove band member from band");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to select a band and a member, to remove an association");
            }
        }
    }

}
