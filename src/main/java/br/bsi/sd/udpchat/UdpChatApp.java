package br.bsi.sd.udpchat;

import br.bsi.sd.udpchat.actions.HelperController;
import br.bsi.sd.udpchat.console.CommandLine;
import br.bsi.sd.udpchat.console.Kernel;

import java.util.ArrayList;
import java.util.List;

public class UdpChatApp extends Kernel {
    @Override
    protected List<CommandLine> commands() {
        ArrayList<CommandLine> commandLines = new ArrayList<>();

        try {
            commandLines.add(new CommandLine("help", "list available subcommands", HelperController.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commandLines;
    }
}
