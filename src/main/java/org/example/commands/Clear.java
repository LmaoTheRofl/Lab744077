package org.example.commands;

import org.example.organization.Organization;
import org.example.storage.Collection;
import org.example.storage.User;

public class Clear implements Command {
    /**
     * очистить коллекцию
     * @return collection cleared
     */
    @Override
    public String execute() {
        for(Organization organization : Collection.getInstance().getAll()) {
            if(organization.getCreator().equals(User.login)){
                Collection.getInstance().removeById(organization.getId());
            }
        } return "Collection cleared";}


    @Override
    public String getCommandName() {
        return "clear";
    }
}
