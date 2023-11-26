import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CaesarCipher{
    private static final int TAMANHO_ALFABETO = 26;

    public static String encrypt(String txt, int key) {
        StringBuilder resultado = new StringBuilder();

        for (char caractere : txt.toCharArray()) {
            if (Character.isLetter(caractere)) {
                char inicioAlfabeto = Character.isUpperCase(caractere) ? 'A' : 'a';
                char novoCaractere = (char) ((caractere - inicioAlfabeto + key + TAMANHO_ALFABETO) % TAMANHO_ALFABETO + inicioAlfabeto);
                resultado.append(novoCaractere);
            } 
            else {
                resultado.append(caractere);
            }
        }

        return resultado.toString();
    }

    public static String decrypt(String txtCifrada, int key) {
        return encrypt(txtCifrada, -key);
    }


    public static String readFile(String caminhoEntrada) {
        StringBuilder textoOriginal = new StringBuilder();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoEntrada));){

            String linha;
            while ((linha = leitor.readLine()) != null) {
                textoOriginal.append(linha).append(System.lineSeparator()); // Usando System.lineSeparator() para obter a quebra de linha adequada
            }

        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }

        return textoOriginal.toString();
    }

    public static void writeFile(String output_path, String message){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output_path))){

            writer.write(message);
        }catch(IOException e){
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }
    
}
