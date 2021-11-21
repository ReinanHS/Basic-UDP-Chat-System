package br.bsi.sd.udpchat.console;

import java.util.List;

public class Kernel {
    protected String appName = "app";

    /**
     * Runs the application
     *
     * @param args an array of String arguments to be parsed
     */
    public void run(String[] args) {

        if (args.length > 0 && args[0] != null) {
            this.findActions(args);
            return;
        }

        this.usageHelp();
    }

    protected void usageHelp() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("usage: " + this.appName + " [--version] [--help]\n");
        stringBuilder.append("\t\t\t<command> [<args>]");
        stringBuilder.append("\n\nThese are common " + this.appName + " commands used in various situations:\n");

        for (CommandLine commandLine : this.commands()) {
            stringBuilder.append("\t" + commandLine.getCommand() + "\t" + commandLine.getDescription());
        }

        System.out.println(stringBuilder);
    }

    private void findActions(String[] args) {
        boolean isFindAction = false;

        for (CommandLine commandLine : this.commands()) {
            if (commandLine.hasAction(args[0])) {
                System.out.println("Tem");
                isFindAction = true;
                break;
            }
        }

        if (!isFindAction) {
            System.out.println("404");
        }
    }

    protected List<CommandLine> commands() {
        return null;
    }
}
