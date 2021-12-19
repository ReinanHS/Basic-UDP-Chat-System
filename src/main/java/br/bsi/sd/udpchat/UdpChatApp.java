package br.bsi.sd.udpchat;



import br.bsi.sd.udpchat.actions.server.ConnectionByRegisterController;
import br.bsi.sd.udpchat.actions.ConnectionController;
import br.bsi.sd.udpchat.actions.LicenseController;
import br.bsi.sd.udpchat.actions.server.ServerByRegisterController;
import br.bsi.sd.udpchat.console.CommandLine;
import br.bsi.sd.udpchat.console.Kernel;

import java.util.ArrayList;
import java.util.List;

public class UdpChatApp extends Kernel {
    public static final String APP_NAME = "udpChat";

    @Override
    public String getAppName() {
        return APP_NAME;
    }

    /**
     * Method for adding commands that are available for use
     */
    @Override
    protected List<CommandLine> commands() {
        ArrayList<CommandLine> commandLines = new ArrayList<>();

        try {
            commandLines.add(new CommandLine("license", "See some information about who developed the app", LicenseController.class));
            commandLines.add(new CommandLine("connect", "Command to connect to a chat", ConnectionController.class));
            commandLines.add(new CommandLine("connect-server-register", "Command to connect to the logging server", ConnectionByRegisterController.class));
            commandLines.add(new CommandLine("server-register", "Command to start the logging server", ServerByRegisterController.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commandLines;
    }
}
