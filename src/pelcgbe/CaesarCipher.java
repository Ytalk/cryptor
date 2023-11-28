import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;


public class CaesarCipher{
    private static final int alphabet_lenght = 26;

    public static String encrypt(String txt, int key){
        StringBuilder encrypted = new StringBuilder();

        for (char character : txt.toCharArray()){
            if (Character.isLetter(character)){
                char start_alphabet = Character.isUpperCase(character) ? 'A' : 'a';
                char new_character = (char) ((character - start_alphabet + key + alphabet_lenght) % alphabet_lenght + start_alphabet);
                encrypted.append(new_character);
            } 
            else {
                encrypted.append(character);
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String txt, int key) {
        return encrypt(txt, -key);
    }
    

    public void writeFile(String txt){
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.setDialogTitle("Specify a directory to save");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        file_chooser.setFileFilter(filter);

        int user_selection = file_chooser.showSaveDialog(null);

        if(user_selection == JFileChooser.APPROVE_OPTION){
            File save_file = file_chooser.getSelectedFile();

            String file_path = save_file.getAbsolutePath();
            if (!file_path.endsWith(".txt")){
                save_file = new File(file_path + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(save_file))){
                writer.write(txt);
                System.out.println("Arquivo salvo com sucesso.");
            } 
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void readFile(JTextArea text_area){
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.setDialogTitle("Specify a file to open");

        FileNameExtensionFilter default_filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        file_chooser.setFileFilter(default_filter);
        //file_chooser.setAcceptAllFileFilterUsed(false);
        //file_chooser.addChoosableFileFilter(new FileNameExtensionFilter("All Files", "*"));

        int user_selection = file_chooser.showOpenDialog(null);

        if(user_selection == JFileChooser.APPROVE_OPTION){
            File open_file = file_chooser.getSelectedFile();

            try(BufferedReader reader = new BufferedReader(new FileReader(open_file))){
                StringBuilder txt = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null){
                    txt.append(line).append("\n");
                }

                text_area.setText(txt.toString());
                System.out.println("Arquivo aberto com sucesso.");
            } 
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
