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

/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    The panel for managing viewing the association between band members and bands.
 */
public class ViewBandMembersAssociationPanel extends JPanel {
    private MainController controller;
    private ArrayList<Band> listOfBands;
    private ArrayList<BandMember> listOfBandMembers;

    private JPanel pnlViewAssociations;
    private JLabel lblViewBands;
    private JComboBox<ComboBoxItem> comboBoxViewBandAssociations;
    private JLabel lblViewBandMembers;
    private DefaultListModel<String> model;
    private JList<String> jlistOfBandMembers;

    private JPanel pnlAddMemberToBand;
    private JLabel lblBands;
    private JComboBox<ComboBoxItem> comboBoxBands;
    private JLabel lblAddBandMembersToBand;
    private JComboBox<ComboBoxItem> comboBoxBandMembersToAdd;
    private JButton btnAddMemberToBand;

    private JPanel pnlRemoveBandMemberAssociation;
    private JLabel lblRemoveFromBand;
    private JComboBox<ComboBoxItem> comboBoxRemoveFromBand;
    private JLabel lblRemoveBandMember;
    private JComboBox<ComboBoxItem> comboBoxRemoveBandMember;
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
        comboBoxBands = new JComboBox<ComboBoxItem>();
        lblAddBandMembersToBand = new JLabel("Band member to add:");
        lblAddBandMembersToBand.setForeground(Color.WHITE);
        comboBoxBandMembersToAdd = new JComboBox<>();
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
        listOfBands = controller.getAllBands();
        listOfBandMembers = controller.getAllBandMembers();
        populateViewComboBox();
        populateAddComboBox();
        populateRemoveComboBox();
    }

    private void populateViewComboBox() {
        comboBoxViewBandAssociations.removeAllItems();
        comboBoxViewBandAssociations.addItem(new ComboBoxItem("-1", "No band selected"));
        for(Band b : listOfBands) {
            comboBoxViewBandAssociations.addItem(new ComboBoxItem(String.valueOf(b.getBand_id()), b.getBand_name()));
        }
    }

    private void populateAddComboBox() {
        comboBoxBands.removeAllItems();
        comboBoxBands.addItem(new ComboBoxItem("-1", "No band selected"));
        for(Band b : listOfBands) comboBoxBands.addItem(new ComboBoxItem(String.valueOf(b.getBand_id()), b.getBand_name()));

        comboBoxBandMembersToAdd.removeAllItems();
        comboBoxBandMembersToAdd.addItem(new ComboBoxItem("-1", "No band member selected"));
        for(BandMember bm : listOfBandMembers) comboBoxBandMembersToAdd.addItem(new ComboBoxItem(String.valueOf(bm.getBand_member_id()), bm.getBand_member_name()));
    }

    private void populateRemoveComboBox() {
        comboBoxRemoveFromBand.removeAllItems();
        comboBoxRemoveFromBand.addItem(new ComboBoxItem("-1", "No band selected"));
        for(Band b : listOfBands) comboBoxRemoveFromBand.addItem(new ComboBoxItem(String.valueOf(b.getBand_id()), b.getBand_name()));

        comboBoxRemoveBandMember.removeAllItems();
        comboBoxRemoveBandMember.addItem(new ComboBoxItem("-1", "No band member selected"));
        for(BandMember bm : listOfBandMembers) comboBoxRemoveBandMember.addItem(new ComboBoxItem(String.valueOf(bm.getBand_member_id()), bm.getBand_member_name()));
    }

    public void setLists(ArrayList<Band> allBands, ArrayList<BandMember> allBandMembers) {
        this.listOfBands = allBands;
        this.listOfBandMembers = allBandMembers;
        populateComboBoxes();
    }

    /*
        Listens on what band is selected in the ComboBox and fetches the band members in that band.
     */
    public class BandSelectedListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            System.out.println("BandSelectedListener");
            if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
                ArrayList<BandMember> bandMembers = controller.getAllBandMembersInBand(Integer.parseInt(comboBoxViewBandAssociations.getItemAt(comboBoxViewBandAssociations.getSelectedIndex()).getDbId()));
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
                ArrayList<BandMember> bandMembers = controller.getAllBandMembersInBand(Integer.parseInt(comboBoxRemoveFromBand.getItemAt(comboBoxRemoveFromBand.getSelectedIndex()).getDbId()));
                comboBoxRemoveBandMember.removeAllItems();
                for(BandMember bm : bandMembers) comboBoxRemoveBandMember.addItem(new ComboBoxItem(String.valueOf(bm.getBand_member_id()), bm.getBand_member_name()));
            }
        }
    }

     public class AddBandMemberToBandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(comboBoxBands.getSelectedIndex() != 0 && comboBoxBandMembersToAdd.getSelectedIndex() != 0) {
                BandMembersAssociation bandMembersAssociation = new BandMembersAssociation(Integer.parseInt(comboBoxBands.getItemAt(comboBoxBands.getSelectedIndex()).getDbId()), Integer.parseInt(comboBoxBandMembersToAdd.getItemAt(comboBoxBandMembersToAdd.getSelectedIndex()).getDbId()));
                if(controller.addBandMembersAssociation(bandMembersAssociation)) {
                    //TODO: present the added member
                    populateComboBoxes();
                } else {
                    JOptionPane.showMessageDialog(null, "Could not add the band member to the band");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to select a band and band member");
            }
        }
    }

    //TODO: fix this
    public class RemoveBandMemberAssociationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(comboBoxRemoveFromBand.getSelectedIndex() != 0 && comboBoxRemoveFromBand.getSelectedIndex() != 0) {
                ArrayList<BandMember> bandMembers = controller.getAllBandMembersInBand(Integer.parseInt(comboBoxRemoveFromBand.getItemAt(comboBoxRemoveFromBand.getSelectedIndex()).getDbId()));
                if(controller.removeBandMemberFromBand(Integer.parseInt(comboBoxRemoveFromBand.getItemAt(comboBoxRemoveFromBand.getSelectedIndex()).getDbId()),
                        Integer.parseInt(comboBoxRemoveBandMember.getItemAt(comboBoxRemoveBandMember.getSelectedIndex()).getDbId()))) {
                    populateComboBoxes();
                } else {
                    JOptionPane.showMessageDialog(null, "Could not remove band member from band");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to select a band and a member, to remove an association");
            }
        }
    }

}
