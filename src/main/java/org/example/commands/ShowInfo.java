package org.example.commands;

import org.example.storage.Collection;
import org.example.storage.Database;

public class ShowInfo implements Command{
    /**
     * @return в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     */
    @Override
    public String execute() {
        return "класс коллекции: "+ Collection.getInstance().getAll().getClass()+"\nсоздано: " + Collection.getInstance().getFileCreationDate("Input.json")+"\nэлементов внутри: "+ Collection.getInstance().getAll().size();
    }

    @Override
    public String getCommandName() {
        return "info";
    }
}
