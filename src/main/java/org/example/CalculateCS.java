package org.example;

import javax.swing.*;
import java.awt.event.*;

public class CalculateCS extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField locks;
    private JTextField barrels;
    private JTextField stocks;
    private JTextField result;
    private JButton closeButton;

    public CalculateCS() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClear();
            }
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onClear();
            }
        });
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClear();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    private void onOK() {
        long lock ;
        long stock ;
        long barrel ;

        try {

            lock = Math.round(Double.parseDouble(locks.getText()));
            if (lock < 1 || lock > 70) {
                JOptionPane.showMessageDialog(this, "Out of length");
            }

            stock = Math.round(Double.parseDouble(stocks.getText()));
            if (stock < 1 || stock > 80) {
                JOptionPane.showMessageDialog(this, "Out of length");
            }
            barrel =Math.round(Double.parseDouble(barrels.getText()));
            if (barrel < 1 || barrel > 90) {
                JOptionPane.showMessageDialog(this, "Out of length");
            }

            long sales =  (45 * lock + 30 * stock + 25 * barrel);
            double commission;

            if (sales <= 1000) {
                commission = sales * 0.1;
            } else if (sales <= 1800) {
                commission = 1000 * 0.1 + (sales - 1000) * 0.15;
            } else {
                commission = 1000 * 0.1 + 800 * 0.15 + (sales - 1800) * 0.2;
            }

            result.setText(String.valueOf(Long.valueOf((long) commission)));

        } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error");
        }

    }

    private void onClear() {
        // add your code here if necessary
        stocks.setText("");
        locks.setText("");
        barrels.setText("");
        result.setText("");
    }

    public static void main(String[] args) {
        CalculateCS dialog = new CalculateCS();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
