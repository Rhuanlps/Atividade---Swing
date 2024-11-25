import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadasroDeContato {
    private JFrame frame;
    private JTextField nomeInput;
    private JTextField telefoneInput;
    private JTextField emailInput;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaContatos;
    private JButton adicionarContato;
    private JButton removerContato;

    public CadasroDeContato() {

        frame = new JFrame("Cadastro de Contatos");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        nomeInput = new JTextField(20);
        telefoneInput = new JTextField(15);
        emailInput = new JTextField(20);

        adicionarContato = new JButton("Adicionar Contato");
        removerContato = new JButton("Remover Contato");

        modeloLista = new DefaultListModel<>();
        listaContatos = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaContatos);
        scroll.setPreferredSize(new Dimension(350, 150));

        frame.add(new JLabel("Nome:"));
        frame.add(nomeInput);
        frame.add(new JLabel("Telefone:"));
        frame.add(telefoneInput);
        frame.add(new JLabel("E-mail:"));
        frame.add(emailInput);
        frame.add(adicionarContato);
        frame.add(removerContato);
        frame.add(scroll);

        adicionarContato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeInput.getText().trim();
                String telefone = telefoneInput.getText().trim();
                String email = emailInput.getText().trim();

                if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String contato = String.format("Nome: %s | Telefone: %s | E-mail: %s", nome, telefone, email);
                modeloLista.addElement(contato);

                nomeInput.setText("");
                telefoneInput.setText("");
                emailInput.setText("");
            }
        });

        removerContato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaContatos.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    modeloLista.remove(indiceSelecionado);
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione um contato para remover!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CadasroDeContato::new);
    }
}
