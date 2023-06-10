package org.example.commands;

import org.example.storage.Collection;
import org.example.storage.Database;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ShowInfo implements Command{
    /**
     * @return в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     */
    @Override
    public String execute() {
        return "класс коллекции: "+ Collection.getInstance().getAll().getClass()+"\nсоздано: " + LocalDateTime.ofEpochSecond(
                Collection.getInstance().getCreationTime() / 1000, 0, ZoneOffset.UTC)+"\nэлементов внутри: "+ Collection.getInstance().getAll().size();
    }

    @Override
    public String getCommandName() {
        return "info";
    }
}
