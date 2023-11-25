import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.*;
import javax.swing.ImageIcon;

public class EncryptionInterface{
    private JTextField campoTextoOriginal;
    private JTextField campoTextoResultado;

    public EncryptionInterface(){
        JFrame frame = new JFrame("Cryptor");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Container principal
        Container main_container = frame.getContentPane();
        main_container.setLayout(new BorderLayout());

        // Container para os botões
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(1, 3));

        // JPanel para entrada de texto
        JPanel panelEntrada = new JPanel(new GridLayout(2, 2));

        JLabel labelOriginal = new JLabel("Mensagem Original:", SwingConstants.CENTER);
        campoTextoOriginal = new JTextField();
        panelEntrada.add(labelOriginal);
        panelEntrada.add(campoTextoOriginal);

        JLabel labelResultado = new JLabel("Resultado:");
        campoTextoResultado = new JTextField();
        panelEntrada.add(labelResultado);
        panelEntrada.add(campoTextoResultado);

        campoTextoOriginal.setPreferredSize(new Dimension(400, 200));
        main_container.add(panelEntrada, BorderLayout.CENTER);
        //frame.getContentPane().add(campoTextoOriginal, BorderLayout.CENTER);

        // Carregando a imagem do arquivo PNG
            ImageIcon imagemIcon = new ImageIcon("key_icon.png");
            Image imagem = imagemIcon.getImage();

            // Redimensionando a imagem se necessário
            Image novaImagem = imagem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            // Criando um JLabel para exibir a imagem
            JLabel labelImagem = new JLabel(new ImageIcon(novaImagem));

            frame. setLayout(new FlowLayout());
            labelImagem.setBounds(100, 100, 300, 200);

            // Adicionando o JLabel ao JFrame
            main_container.add(labelImagem);

        JButton open_file_button = new JButton("open file");
        open_file_button.setBounds(0, 0, 90, 30);
        open_file_button.setFont(new Font("Arial", Font.PLAIN, 10));
        button_panel.add(open_file_button);

        JButton encrypt_button = new JButton("encrypt file");
        encrypt_button.setBounds(0, 0, 90, 30);
        encrypt_button.setFont(new Font("Arial", Font.PLAIN, 10));
        button_panel.add(encrypt_button);

        JButton decrypt_button = new JButton("decrypt file");
        decrypt_button.setBounds(0, 0, 90, 30);
        decrypt_button.setFont(new Font("Arial", Font.PLAIN, 10));
        button_panel.add(decrypt_button);

        main_container.add(button_panel);


        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}