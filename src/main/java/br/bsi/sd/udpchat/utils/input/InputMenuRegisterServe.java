package br.bsi.sd.udpchat.utils.input;

import br.bsi.sd.udpchat.enums.MenuRegisterServeEnum;

public class InputMenuRegisterServe {
    public static MenuRegisterServeEnum read() {
        try{
            System.out.println("Menu de opções:");

            for (MenuRegisterServeEnum item : MenuRegisterServeEnum.values()) {
                System.out.println("(" + item.ordinal() + ") - " + item.toString());
            }

            System.out.print("Digite sua opção: ");

            int actionId = InputBase.scanner.nextInt();

            if(MenuRegisterServeEnum.hasItem(actionId)) {
                return MenuRegisterServeEnum.values()[actionId];
            }

            throw new Exception("O valor inserido é inválido");

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao ler o menu: "+e.getMessage()+" \nTente novamente!");
            return read();
        }
    }
}
