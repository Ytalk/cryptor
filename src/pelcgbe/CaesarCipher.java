import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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


    public static String readFile(String input_path){
        StringBuilder original_txt = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(input_path));){
            String line;

            while ((line = reader.readLine()) != null){
                original_txt.append(line).append(System.lineSeparator());
            }
        }
        catch (IOException e){
            System.err.println("erro ao ler arquivo: " + e.getMessage());
        }

        return original_txt.toString();
    }


    public static void writeFile(String output_path, String message){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output_path))){

            writer.write(message);
        }catch(IOException e){
            System.err.println("erro ao escrever arquivo: " + e.getMessage());
        }
    }
    
}
