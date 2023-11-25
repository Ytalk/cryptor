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


    public static void cifrarArquivo(String caminhoEntrada, String caminhoSaida, int shift, boolean decifrar) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoEntrada));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoSaida))) {

            StringBuilder textoOriginal = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                textoOriginal.append(linha).append(System.lineSeparator()); // Usando System.lineSeparator() para obter a quebra de linha adequada
            }

            String textoProcessado;
            if (decifrar) {
                textoProcessado = decrypt(textoOriginal.toString(), -shift);
            } else {
                textoProcessado = encrypt(textoOriginal.toString(), shift);
            }

            escritor.write(textoProcessado);

            if (decifrar) {
                System.out.println("Arquivo decifrado com sucesso!");
            } else {
                System.out.println("Arquivo cifrado com sucesso!");
            }


        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }
    
}
