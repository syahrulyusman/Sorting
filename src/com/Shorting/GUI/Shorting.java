package com.Shorting.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shorting {
    private JPanel rootPanel;
    private JButton buttonSave;
    private JTable tableData;
    private JTextField textData;
    private JTextField textJumlah;
    private DefaultTableModel tableModel;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public Shorting() {
        this.initComponents();
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tableData.getModel();
                String data = textData.getText();
                String jumlah = textJumlah.getText();
                if(data.isBlank() || jumlah.isBlank()){
                    JOptionPane.showMessageDialog(rootPanel,
                            "Data Belum Lengkap",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;

                }

                int a = Integer.parseInt(textJumlah.getText());
                String[] tmp = data.split(",");

                for (int i = 0; i < a; i++) {
                    model.addRow(new Object[]{});
                }
                int sss = 0;
                for (int i : Database.getas(data, a)) {
                    model.setValueAt(i, sss, 0);
                    sss++;
                }
                int yyy = 0;
                for (int i : Database.getdes(data, a)) {
                    model.setValueAt(i, yyy, 1);
                    yyy++;
                }
            }
        });
    }

    private void initComponents() {
        Object[] tableColom = {
                "Ascending",
                "Descending"
        };
        Object[][] initData = {

        };
        tableModel = new DefaultTableModel(initData, tableColom);
        //set table model
        tableData.setModel(tableModel);
        //menampilkan sorting di setiap colom
        tableData.setAutoCreateRowSorter(true);
        // enable single selection
        tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}