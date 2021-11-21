package br.bsi.sd.udpchat.console;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandLine {
    protected String command;
    protected String description;
    private final Object controller;
    private final Class<?> classController;

    public CommandLine(String command, String description, Class<?> classLoad) {
        this.command = command;
        this.description = description;
        this.classController = classLoad;
        this.controller = this.buildController();
    }

    public CommandLine(String command, Class<?> classLoad) {
        this(command, "", classLoad);
    }

    private Object buildController() {
        try{
            Constructor<?> cons = this.classController.getConstructor();
            return cons.newInstance();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean hasAction(String actionName) {
        try {

            this.classController.getDeclaredMethod(actionName);

            return true;

        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
