import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppdeNota {
    private JFrame frame;
    private JTextField notaInput;
    private JTextArea listaNotas;
    private JLabel resultadoMedia;
    private ArrayList<Double> notas;

    public AppdeNota() {
        notas = new ArrayList<>();

        frame = new JFrame("Aplicativo de Notas");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        notaInput = new JTextField(10);
        JButton adicionarNota = new JButton("Adicionar Nota");

        listaNotas = new JTextArea(10, 20);
        listaNotas.setEditable(false);
        JScrollPane scroll = new JScrollPane(listaNotas);

        JButton calcularMedia = new JButton("Calcular Média");
        resultadoMedia = new JLabel("Média: --");

        frame.add(new JLabel("Nota:"));
        frame.add(notaInput);
        frame.add(adicionarNota);
        frame.add(scroll);
        frame.add(calcularMedia);
        frame.add(resultadoMedia);

        adicionarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double nota = Double.parseDouble(notaInput.getText());
                    if (nota < 0 || nota > 10) {
                        JOptionPane.showMessageDialog(frame, "Insira uma nota entre 0 e 10.");
                        return;
                    }
                    notas.add(nota);
                    listaNotas.append(nota + "\n");
                    notaInput.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número válido.");
                }
            }
        });

        calcularMedia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (notas.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nenhuma nota adicionada.");
                    return;
                }
                double soma = 0;
                for (double nota : notas) {
                    soma += nota;
                }
                double media = soma / notas.size();
                resultadoMedia.setText(String.format("Média: %.2f", media));
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppdeNota::new);
    }
}
