package br.bsi.sd.udpchat.utils.input;

import br.bsi.sd.udpchat.entity.Client;
import br.bsi.sd.udpchat.utils.helps.ClientHelp;

public class InputClient {
    public static String readName(){
        try {
            System.out.print("Digite seu primeiro nome: ");

            return InputBase.scanner.next();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao ler o nome. Tente novamente!");
            return readName();
        }
    }

    public static String readListeningPort() {
        try {
            System.out.print("Informe a porta na qual o socket vai ouvir: ");

            return InputBase.scanner.next();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao ler a porta. Tente novamente!");
            return readListeningPort();
        }
    }

    public static Client read() {
        System.out.println("Por favor informe os dados do cliente");

        return new Client(readListeningPort(), readName(), ClientHelp.getLocalHost());
    }
}
