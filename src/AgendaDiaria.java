import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaDiaria {
    private JFrame frame;
    private JTextField campoCompromisso;
    private JSpinner spinnerDataHora;
    private JTable tabelaCompromissos;
    private DefaultTableModel modeloTabela;

    public AgendaDiaria() {
        frame = new JFrame("Agenda Diária");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel painelEntrada = new JPanel(new FlowLayout());
        campoCompromisso = new JTextField(20);
        painelEntrada.add(new JLabel("Compromisso:"));
        painelEntrada.add(campoCompromisso);

        spinnerDataHora = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerDataHora, "dd/MM/yyyy HH:mm");
        spinnerDataHora.setEditor(editor);
        painelEntrada.add(new JLabel("Data e Hora:"));
        painelEntrada.add(spinnerDataHora);

        JButton botaoAdicionar = new JButton("Adicionar Compromisso");
        painelEntrada.add(botaoAdicionar);
        frame.add(painelEntrada, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(new String[]{"Data e Hora", "Descrição"}, 0);
        tabelaCompromissos = new JTable(modeloTabela);
        tabelaCompromissos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollTabela = new JScrollPane(tabelaCompromissos);
        frame.add(scrollTabela, BorderLayout.CENTER);

        JButton botaoRemover = new JButton("Remover Compromisso");
        frame.add(botaoRemover, BorderLayout.SOUTH);

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCompromisso();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerCompromisso();
            }
        });

        frame.setVisible(true);
    }

    private void adicionarCompromisso() {
        String compromisso = campoCompromisso.getText();
        Date dataHora = (Date) spinnerDataHora.getValue();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if (compromisso.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "O campo de compromisso não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        modeloTabela.addRow(new Object[]{formato.format(dataHora), compromisso});
        campoCompromisso.setText("");
    }

    private void removerCompromisso() {
        int linhaSelecionada = tabelaCompromissos.getSelectedRow();
        if (linhaSelecionada != -1) {
            modeloTabela.removeRow(linhaSelecionada);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um compromisso para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AgendaDiaria();
    }
}

