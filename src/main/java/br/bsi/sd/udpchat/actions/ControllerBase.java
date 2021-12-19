package br.bsi.sd.udpchat.actions;

import java.util.Scanner;

public abstract class ControllerBase implements Controller {
    public Scanner scanner = new Scanner(System.in);
    /**
     * Method for calling the command to be executed.
     */
    public void command() {
        try {
            this.command(new String[0]);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
