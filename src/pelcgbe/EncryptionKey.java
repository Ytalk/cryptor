import java.util.Scanner;

public class EncryptionKey{
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int key = 0;
        String encrypted_message = "";

        int op;
        new EncryptionInterface();

        do{
            System.out.println("1. criptografar\n2. desincriptografar\n0. sair");
            op = input.nextInt();

            if(op == 1){
                input.nextLine();
                System.out.println("informar mensagem: ");
                String original_message = input.nextLine();
                System.out.println("informar chave: ");
                key = input.nextInt();

                encrypted_message = CaesarCipher.encrypt(original_message, key);
                System.out.println("mensagem cifrada: " + encrypted_message);
            }

            if(op == 2){
                String decrypted_message = CaesarCipher.decrypt(encrypted_message, key);
                System.out.println("mensagem decifrada: " + decrypted_message);
            }

        }while(op != 0);
        System.exit(0);
    }
}