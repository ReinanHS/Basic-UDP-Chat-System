package br.bsi.sd.udpchat.console;

import java.util.List;

public class Kernel {
    /**
     * Get the application Name.
     */
    public String getAppName() {
        return "app";
    }

    /**
     * Get the application version
     *
     * @return version of app
     */
    public String getVersion() {
        return "0.0.1.windows";
    }

    /**
     * Runs the application.
     *
     * @param args an array of String arguments to be parsed.
     */
    public void run(String[] args) {

        if (args.length > 0 && args[0] != null) {

            switch (args[0]) {
                case "--version" -> {
                    System.out.println(this.getAppName() + " version " + this.getVersion());
                    return;
                }
                case "--help" -> {
                    this.usageHelp();
                    return;
                }
                default -> {
                    this.findActions(args);
                    return;
                }
            }
        }

        this.usageHelp();
    }

    /**
     * Method for displaying basic application information on the screen.
     */
    protected void usageHelp() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("usage: ").append(this.getAppName()).append(" [--version] [--help]\n");
        stringBuilder.append("\t\t\t\t<command> [<args>]");
        stringBuilder.append("\n\nThese are common ").append(this.getAppName()).append(" commands used in various situations:\n");

        for (CommandLine commandLine : this.commands()) {
            stringBuilder.append("\t").append(commandLine.getCommand()).append("\t\t\t").append(commandLine.getDescription());
        }

        System.out.println(stringBuilder);
    }

    /**
     * Method to check if a command is available.
     *
     * @param args Command parameters
     */
    private void findActions(String[] args) {
        boolean isFindAction = false;

        for (CommandLine commandLine : this.commands()) {
            if (commandLine.command.equals(args[0]) && commandLine.hasController()) {
                commandLine.callAction(args);
                isFindAction = true;
                break;
            }
        }

        if (!isFindAction) {
            System.out.println(this.getAppName() + ": '" + args[0] + "' is not a " + this.getAppName() + " command. See '" + this.getAppName() + " --help'.");
        }
    }

    /**
     * Method for adding commands that are available for use
     */
    protected List<CommandLine> commands() {
        return null;
    }
}
