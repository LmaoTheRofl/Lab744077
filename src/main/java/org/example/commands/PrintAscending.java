package org.example.commands;
import org.example.organization.Organization;
import org.example.storage.Collection;
import org.example.storage.Database;

import java.util.stream.Collectors;

public class PrintAscending implements Command{
    /**
     * вывести элементы коллекции в порядке возрастания
     * @return collection
     */
    @Override
    public String execute() {
       Collection.getInstance().sortAscending();
        return Collection.getInstance()
                .getAll()
                .stream()
                .map(Organization::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String getCommandName() {
        return "print_ascending";
    }
}
