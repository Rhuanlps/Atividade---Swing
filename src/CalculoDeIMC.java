
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculoDeIMC {
    private JFrame frame;
    private JTextField campoPeso;
    private JTextField campoAltura;
    private JLabel resultadoLabel;

    public CalculoDeIMC() {
        // Configuração do JFrame
        frame = new JFrame("Calculadora de IMC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1, 10, 10));

        // Campo de entrada para peso
        JPanel painelPeso = new JPanel(new FlowLayout());
        painelPeso.add(new JLabel("Peso (kg):"));
        campoPeso = new JTextField(10);
        painelPeso.add(campoPeso);
        frame.add(painelPeso);

        // Campo de entrada para altura
        JPanel painelAltura = new JPanel(new FlowLayout());
        painelAltura.add(new JLabel("Altura (m):"));
        campoAltura = new JTextField(10);
        painelAltura.add(campoAltura);
        frame.add(painelAltura);

        // Botão para calcular o IMC
        JButton botaoCalcular = new JButton("Calcular IMC");
        frame.add(botaoCalcular);

        // Label para exibir o resultado
        resultadoLabel = new JLabel("", SwingConstants.CENTER);
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(resultadoLabel);

        // Adicionar ação ao botão
        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });

        frame.setVisible(true);
    }

    private void calcularIMC() {
        try {
            // Validação e conversão dos campos de entrada
            double peso = Double.parseDouble(campoPeso.getText());
            double altura = Double.parseDouble(campoAltura.getText());

            if (peso <= 0 || altura <= 0) {
                throw new NumberFormatException();
            }

            // Cálculo do IMC
            double imc = peso / (altura * altura);
            String categoria = determinarCategoria(imc);

            // Exibir o resultado
            resultadoLabel.setText(String.format("IMC: %.2f (%s)", imc, categoria));
        } catch (NumberFormatException ex) {
            // Exibir mensagem de erro em caso de entrada inválida
            resultadoLabel.setText("Por favor, insira valores válidos.");
        }
    }

    private String determinarCategoria(double imc) {
        if (imc < 18.5) {
            return "Baixo peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }

    public static void main(String[] args) {
        new CalculoDeIMC();
    }
}
