import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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
    private JTextArea original_text_box;
    private JTextArea result_text_box;
    private int valor = 0;
    CaesarCipher CaesarCipher;

    public EncryptionInterface(){
        JFrame frame = new JFrame("Cryptor");
        frame.setSize(1280, 720);//tamanho setado em HD
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(100, 50, 100));

        Container main_container = frame.getContentPane();
        main_container.setLayout(null);

        //painel para entrada de texto
        JPanel input_panel = new JPanel(new BorderLayout());

        JLabel original_label = new JLabel("MENSAGEM");
        original_label.setPreferredSize(new Dimension(190, 100));
        original_label.setHorizontalAlignment(SwingConstants.CENTER);
        original_label.setVerticalAlignment(SwingConstants.CENTER);

        original_text_box = new JTextArea();
        original_text_box.setPreferredSize(new Dimension(300, 100));
        JScrollPane scrr = new JScrollPane(original_text_box);
        scrr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        input_panel.add(original_label, BorderLayout.WEST);
        input_panel.add(scrr, BorderLayout.CENTER);
        input_panel.setBounds(200, 150, 1100, 100);///////////////////////////////
        main_container.add(input_panel);//painel entrada
        main_container.add(Box.createVerticalStrut(100));
        input_panel.setAlignmentX(Component.TOP_ALIGNMENT);

        //painel para SAIDA DE TEXTO
        JPanel output_panel = new JPanel(new BorderLayout());

        JLabel result_label = new JLabel("MENSAGEM (DE)CODIFICADA");
        result_label.setPreferredSize(new Dimension(190, 100));
        result_label.setHorizontalAlignment(SwingConstants.CENTER);
        result_label.setVerticalAlignment(SwingConstants.CENTER);

        result_text_box = new JTextArea(10, 20);
        result_text_box.setPreferredSize(new Dimension(400, 100));
        JScrollPane scrrl = new JScrollPane(result_text_box);

        output_panel.add(result_label, BorderLayout.WEST);
        output_panel.add(scrrl, BorderLayout.CENTER);
        main_container.add(output_panel);//painel saida
        output_panel.setBounds(200, 580, 1100, 100);///////////////////////////////

        main_container.add(Box.createVerticalStrut(100));
        input_panel.setAlignmentX(Component.BOTTOM_ALIGNMENT);

        //carregando a imagem do arquivo PNG
            JPanel key_panel = new JPanel(new GridLayout(1, 2));

            ImageIcon icon = new ImageIcon("key_icon.png");
            Image imagem = icon.getImage();
            Image new_image = imagem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            
            JLabel label_image = new JLabel(new ImageIcon(new_image));
            label_image.setBounds(100, 100, 300, 300);

            JTextField txt_key = new JTextField();
            txt_key.setEditable(false);
            txt_key.setText(String.valueOf(valor));

            // adiciona grid key
            key_panel.add(label_image, BorderLayout.WEST);
            key_panel.add(txt_key, BorderLayout.EAST);
            key_panel.setBounds(200, 380, 100, 50);///////////////////
            main_container.add(key_panel);//painel key


            JPanel upDown_panel = new JPanel(new GridLayout(2, 1));

            JButton up = new JButton("▲");
            up.setFont(new Font("Arial", Font.PLAIN, 10));
            up.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    valor++;
                    txt_key.setText(String.valueOf(valor));
                }
            });

            JButton down = new JButton("▼");
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

            upDown_panel.add(up, BorderLayout.NORTH);
            upDown_panel.add(down, BorderLayout.SOUTH);
            upDown_panel.setBounds(320, 380, 44, 50);/////////////////
            main_container.add(upDown_panel);

        // painel para os botões
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(1, 2));

        JButton encrypt_button = new JButton("encrypt");
        encrypt_button.setFont(new Font("Arial", Font.PLAIN, 10));
        button_panel.add(encrypt_button);
        encrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = original_text_box.getText();
                int key = Integer.parseInt(txt_key.getText());
                String resultado = CaesarCipher.encrypt(text, key);
                result_text_box.setText(resultado);
            }
        });

        JButton decrypt_button = new JButton("decrypt");
        decrypt_button.setFont(new Font("Arial", Font.PLAIN, 10));
        button_panel.add(decrypt_button);
        decrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String text = original_text_box.getText();
                int key = Integer.parseInt(txt_key.getText());
                String resultado = CaesarCipher.decrypt(text, key);
                result_text_box.setText(resultado);
            }
        });

        button_panel.setBounds(1100, 400, 200, 30);////////////////////////
        main_container.add(button_panel);//painel botao cifras


        JButton open_file_button = new JButton("open file");
        open_file_button.setBounds(1210, 100, 90, 30);
        open_file_button.setFont(new Font("Arial", Font.PLAIN, 10));
        main_container.add(open_file_button);
        open_file_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                original_text_box.setText(CaesarCipher.readFile("escreva_aqui.txt"));//metodo
            }
        });

        JButton save_button = new JButton("to save");
        save_button.setBounds(1210, 700, 90, 30);
        save_button.setFont(new Font("Arial", Font.PLAIN, 10));
        main_container.add(save_button);
        save_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String texto = result_text_box.getText();
                CaesarCipher.writeFile("cifrado.txt", texto);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}