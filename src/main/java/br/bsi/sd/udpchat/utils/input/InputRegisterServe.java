package br.bsi.sd.udpchat.utils.input;

import br.bsi.sd.udpchat.entity.server.register.ConnectionByServerRegister;

import java.net.InetAddress;

public class InputRegisterServe {
    public static String readServePort() {
        try {
            System.out.print("Digite a porta do socket do servidor de registro: ");

            return InputBase.scanner.next();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao ler a porta. Tente novamente!");
            return InputRegisterServe.readServePort();
        }
    }

    public static InetAddress readServeIp() {
        try {
            System.out.print("Digite o nome ou ip do servidor de registro: ");

            return InetAddress.getByName(InputBase.scanner.next());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao ler o ip. Tente novamente!");
            return InputRegisterServe.readServeIp();
        }
    }

    public static ConnectionByServerRegister read()
    {
        System.out.println("Por favor informe os dados do servidor de registro!");
        return new ConnectionByServerRegister(readServePort(), readServeIp());
    }
}
