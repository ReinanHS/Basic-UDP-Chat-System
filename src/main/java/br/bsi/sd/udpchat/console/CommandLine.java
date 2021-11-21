package br.bsi.sd.udpchat.console;

import java.lang.reflect.Constructor;

public class CommandLine {
    /**
     * Command name
     */
    protected String command;

    /**
     * Command description
     */
    protected String description;

    /**
     * Object that stores the controller instance
     */
    private final Object controller;

    /**
     * Controller Class Information
     */
    private final Class<?> classController;

    /**
     * Command builder
     * @param command Name of command
     * @param description Description of command
     * @param classLoad ClassLoad of controller
     */
    public CommandLine(String command, String description, Class<?> classLoad) {
        this.command = command;
        this.description = description;
        this.classController = classLoad;
        this.controller = this.buildController();
    }

    /**
     * Command builder
     * @param command Name of command
     * @param classLoad ClassLoad of controller
     */
    public CommandLine(String command, Class<?> classLoad) {
        this(command, "", classLoad);
    }

    /**
     * Method to create a controller instance
     * @return controller instance
     */
    private Object buildController() {
        try{
            Constructor<?> cons = this.classController.getConstructor();
            return cons.newInstance();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Method to check if there is an action available in the controller
     * @param actionName Name of action to check
     * @return if it exists it will return true
     */
    public boolean hasAction(String actionName) {
        try {

            this.classController.getDeclaredMethod(actionName);

            return true;

        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * Method to get the command name
     * @return command name
     */
    public String getCommand() {
        return command;
    }

    /**
     * Method to get the command description
     * @return command description
     */
    public String getDescription() {
        return description;
    }
}
