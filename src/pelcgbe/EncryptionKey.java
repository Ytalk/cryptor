import java.util.Scanner;

public class EncryptionKey{
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int chave = 0;
        String mensagemCifrada = "ola mundo!";

        int op;
        new EncryptionInterface();

        do{
            System.err.println("1. criptografar\n2. desincriptografar\n0. sair");
            op = input.nextInt();

            if(op == 1){
                input.nextLine();
                System.err.println("informar mensagem: ");
                String mensagemOriginal = input.nextLine();
                System.err.println("informar chave: ");
                chave = input.nextInt();

                mensagemCifrada = CaesarCipher.encrypt(mensagemOriginal, chave);
                System.out.println("Mensagem Cifrada: " + mensagemCifrada);
            }

            if(op == 2){
                String mensagemDecifrada = CaesarCipher.decrypt(mensagemCifrada, chave);
                System.out.println("Mensagem Decifrada: " + mensagemDecifrada);
            }

        }while(op != 0);
        
    }
}