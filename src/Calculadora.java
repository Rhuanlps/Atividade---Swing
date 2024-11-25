import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora {
    private JFrame frame;
    private JTextField display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    public Calculadora() {
        // Configuração do JFrame
        frame = new JFrame("Calculadora Simples");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout(10, 10));

        // Configuração do display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        // Configuração dos botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (isNumber(command)) {
                if (isOperatorClicked) {
                    display.setText("");
                    isOperatorClicked = false;
                }
                display.setText(display.getText() + command);
            } else if (isOperator(command)) {
                if (!display.getText().isEmpty()) {
                    firstNumber = Double.parseDouble(display.getText());
                    operator = command;
                    isOperatorClicked = true;
                }
            } else if ("=".equals(command)) {
                calculateResult();
            } else if ("C".equals(command)) {
                resetCalculator();
            }
        }
    }

    private boolean isNumber(String command) {
        return "0123456789".contains(command);
    }

    private boolean isOperator(String command) {
        return "+-*/".contains(command);
    }

    private void calculateResult() {
        if (!display.getText().isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(display.getText());
            double result = performOperation(firstNumber, secondNumber, operator);
            display.setText(String.valueOf(result));
            operator = "";
            isOperatorClicked = true;
        }
    }

    private double performOperation(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(frame, "Erro: Divisão por zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
                return num1 / num2;
            default:
                return 0;
        }
    }

    private void resetCalculator() {
        display.setText("");
        firstNumber = 0;
        operator = "";
        isOperatorClicked = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculadora::new);
    }
}
