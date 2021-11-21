package br.bsi.sd.udpchat;

import br.bsi.sd.udpchat.actions.LicenseController;
import br.bsi.sd.udpchat.console.CommandLine;
import br.bsi.sd.udpchat.console.Kernel;

import java.util.ArrayList;
import java.util.List;

public class UdpChatApp extends Kernel {
    @Override
    public String getAppName() {
        return "udpChat";
    }

    /**
     * Method for adding commands that are available for use
     */
    @Override
    protected List<CommandLine> commands() {
        ArrayList<CommandLine> commandLines = new ArrayList<>();

        try {
            commandLines.add(new CommandLine("license", "See some information about who developed the app", LicenseController.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commandLines;
    }
}
