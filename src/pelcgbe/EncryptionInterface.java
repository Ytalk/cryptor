import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptionInterface{
    private JTextField campoTextoOriginal;
    private JTextField campoTextoResultado;
    private int valor = 0;
    CaesarCipher CaesarCipher;

    public EncryptionInterface(){
        JFrame frame = new JFrame("Cryptor");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(100, 50, 100));

        Container main_container = frame.getContentPane();
        main_container.setLayout(null);

        //painel para entrada de texto
        JPanel input_panel = new JPanel(new GridLayout(1, 2));
        JLabel labelOriginal = new JLabel("MENSAGEM");
        campoTextoOriginal = new JTextField(100);
        input_panel.add(labelOriginal);
        input_panel.add(campoTextoOriginal);
        input_panel.setBounds(200, 150, 600, 100);///////////////////////////////
        main_container.add(input_panel);//painel entrada
        main_container.add(Box.createVerticalStrut(100));
        input_panel.setAlignmentX(Component.TOP_ALIGNMENT);

        //painel para SAIDA DE TEXTO
        JPanel output_panel = new JPanel(new GridLayout(1, 2));
        JLabel labelResultado = new JLabel("MENSAGEM (DE)CODIFICADA");
        campoTextoResultado = new JTextField();
        output_panel.add(labelResultado);
        output_panel.add(campoTextoResultado);
        main_container.add(output_panel);//painel saida
        output_panel.setBounds(600, 650, 600, 100);///////////////////////////////
        main_container.add(Box.createVerticalStrut(100));
        input_panel.setAlignmentX(Component.BOTTOM_ALIGNMENT);

        //carregando a imagem do arquivo PNG
            JPanel key_panel = new JPanel(new GridLayout(2, 2));

            ImageIcon imagemIcon = new ImageIcon("key_icon.png");
            Image imagem = imagemIcon.getImage();

            Image novaImagem = imagem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            
            JLabel labelImagem = new JLabel(new ImageIcon(novaImagem));
            labelImagem.setBounds(100, 100, 300, 200);

            JTextField txt_key = new JTextField(10);
            txt_key.setEditable(false);
            txt_key.setText(String.valueOf(valor));

            JButton up = new JButton("up");
            up.setBounds(0, 0, 10, 10);
            up.setFont(new Font("Arial", Font.PLAIN, 10));
            up.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    valor++;
                    txt_key.setText(String.valueOf(valor));
                }
            });
            

            JButton down = new JButton("down");
            down.setBounds(0, 0, 10, 10);
            down.setFont(new Font("Arial", Font.PLAIN, 10));
            down.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(valor > 0){
                        valor--;
                        txt_key.setText(String.valueOf(valor));
                    }
                }
            });

            // adiciona grid key
            key_panel.add(labelImagem);
            key_panel.add(up);
            key_panel.add(down);
            key_panel.add(txt_key);
            key_panel.setBounds(460, 500, 100, 200);///////////////////
            main_container.add(key_panel);//painel key

        // painel para os botões
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(1, 3));

        JButton open_file_button = new JButton("open file");
        open_file_button.setBounds(0, 0, 90, 30);
        open_file_button.setFont(new Font("Arial", Font.PLAIN, 10));
        button_panel.add(open_file_button);
        open_file_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int key = Integer.parseInt(txt_key.getText());
                CaesarCipher.cifrarArquivo("escreva_aqui.txt", "escreva_aqui.txt", key, false);//metodo
            }
        });

        JButton encrypt_button = new JButton("encrypt file");
        encrypt_button.setBounds(0, 0, 90, 30);
        encrypt_button.setFont(new Font("Arial", Font.PLAIN, 10));
        button_panel.add(encrypt_button);
        encrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoDigitado = campoTextoOriginal.getText();
                int key = Integer.parseInt(txt_key.getText());
                String resultado = CaesarCipher.encrypt(textoDigitado, key);
                campoTextoResultado.setText(resultado);
            }
        });

        JButton decrypt_button = new JButton("decrypt file");
        decrypt_button.setBounds(100, 200, 90, 30);
        decrypt_button.setFont(new Font("Arial", Font.PLAIN, 10));
        button_panel.add(decrypt_button);
        decrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String textoDigitado = campoTextoOriginal.getText();
                int key = Integer.parseInt(txt_key.getText());
                String resultado = CaesarCipher.decrypt(textoDigitado, key);
                campoTextoResultado.setText(resultado);
            }
        });

        button_panel.setBounds(400, 400, 300, 80);

        main_container.add(button_panel);//painel botao

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}