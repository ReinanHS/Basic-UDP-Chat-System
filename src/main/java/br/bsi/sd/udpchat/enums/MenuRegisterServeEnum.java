package br.bsi.sd.udpchat.enums;

public enum MenuRegisterServeEnum {
    ENVIAR_MENSAGENS {
        public String toString() {
            return "Enviar mensagens";
        }
    },
    ESPERAR_MENSAGENS {
        public String toString() {
            return "Esperar mensagens";
        }
    },
    SAIR {
        public String toString() {
            return "Sair";
        }
    };

    public static boolean hasItem(int item) {
        return item >= 0 && item < MenuRegisterServeEnum.values().length;
    }
}
