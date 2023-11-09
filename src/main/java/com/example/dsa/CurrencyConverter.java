package com.example.dsa;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CurrencyConverter {
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("USD", 92.2);
        exchangeRates.put("EUR", 110.2);
        exchangeRates.put("GBP", 113.3);
        exchangeRates.put("JPY", 0.61);
        exchangeRates.put("CAD", 67.1);
    }

    private static double getRandomExchangeRate() {
        return 0.1 + new Random().nextDouble() * (3 - 0.0);
    }

    private static double convertCurrency(double rubles, double exchangeRate) {
        return rubles / exchangeRate * getRandomExchangeRate();
    }
    static class CurrencyConverterUI {
        private final JFrame frame;
        private final JPanel panel;
        private final JTextField inputField;
        private final JTextField outputField;

        public CurrencyConverterUI() {
            frame = new JFrame("Конвертер валют");
            panel = new JPanel();
            JLabel inputLabel = new JLabel("Введите сумму в рублях:");
            inputField = new JTextField(10);
            JLabel outputLabel = new JLabel("Конвертированная сумма:");
            outputField = new JTextField(10);
            JButton convertButton = new JButton("Конвертировать");

            panel.add(inputLabel);
            panel.add(inputField);
            panel.add(outputLabel);
            panel.add(outputField);
            panel.add(convertButton);

            convertButton.addActionListener(new ConvertButtonListener());

            frame.add(panel);
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void createAndShowGUI() {
            frame.setVisible(true);
        }

        private class ConvertButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double rubles = Double.parseDouble(inputField.getText());
                    String selectedCurrency = JOptionPane.showInputDialog("Выбери валюту (USD, EUR, GBP(Франки), " +
                            "JPY(ЙЕНЫ)" + ", CAD(Канадские доллары):");

                    if (exchangeRates.containsKey(selectedCurrency)) {
                        double exchangeRate = exchangeRates.get(selectedCurrency);
                        double convertedAmount = convertCurrency(rubles, exchangeRate);
                        DecimalFormat df = new DecimalFormat("#.##");
                        outputField.setText(df.format(convertedAmount));
                    } else {
                        JOptionPane.showMessageDialog(null, "Недействительная валюта.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Неверный ввод. Пожалуйста, введите действительный номер.");
                }
            }
        }
    }
}