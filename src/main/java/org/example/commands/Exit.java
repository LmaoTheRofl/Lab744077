package org.example.commands;

public class Exit implements Command{
    /**
     *  завершить программу (без сохранения в файл)
     * @return finish
     */
    @Override
    public String execute() {
        System.exit(1);
        return "finish";
    }

    @Override
    public String getCommandName() {
        return "exit";
    }
}
