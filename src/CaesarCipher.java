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
}
