package br.bsi.sd.udpchat.actions;

public abstract class ControllerBase implements Controller {
    /**
     * Method for calling the command to be executed.
     */
    public void command() {
        this.command(new String[0]);
    }
}
