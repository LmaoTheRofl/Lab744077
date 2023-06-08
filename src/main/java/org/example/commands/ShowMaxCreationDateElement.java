package org.example.commands;

import org.example.organization.Organization;
import org.example.storage.Collection;
import org.example.storage.Database;

import java.util.Comparator;

public class ShowMaxCreationDateElement implements Command{
    /**
     * @return любой объект из коллекции, значение поля creationDate которого является максимальным
     */
    @Override
    public String execute() {
        return Collection.getInstance()
                .getAll()
                .stream().max(Comparator.comparing(Organization::getCreationDate)).toString();
    }

    @Override
    public String getCommandName() {
        return "max_by_creation_date";
    }
}
