package com.example.dsa;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter.CurrencyConverterUI ui = new CurrencyConverter.CurrencyConverterUI();
            ui.createAndShowGUI();
        });
    }
}
