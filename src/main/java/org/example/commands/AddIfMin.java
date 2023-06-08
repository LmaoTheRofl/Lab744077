package org.example.commands;

import org.example.organization.Organization;
import org.example.storage.Collection;

import java.util.Comparator;
import java.util.Optional;

import static org.example.utils.Utils.readOrganization;

public class AddIfMin implements Command{
    /**
     * добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     * @return element was added
     */
    @Override
    public String execute()  {
        Organization organization = readOrganization();
        Optional<Organization> minElement = Collection.getInstance().getAll()
                .stream()
                .min(Comparator.comparing(Organization::getAnnualTurnover));
        if (minElement.isPresent()) {
            if (minElement.get().getAnnualTurnover() > organization.getAnnualTurnover()) {
                Collection.getInstance().add(organization);
                return "Element was added";
            } else {
                return "Current element not a min";
            }
        } else {
            return "No min element in collection!";
        }

    }

    @Override
    public String getCommandName() {
        return "add_if_min";
    }
}
