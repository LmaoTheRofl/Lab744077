package org.example.commands;

import org.example.organization.Organization;
import org.example.storage.Collection;
import org.example.storage.Database;
import org.example.storage.User;

import java.util.Scanner;

public class RemoveById implements Command{
    /**
     *  удалить элемент из коллекции по его id
     * @return Element removed
     */
    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id");
        while(true) {
            long x = scanner.nextLong();
            for(Organization organization : Collection.getInstance().getAll()) {
                if(x == organization.getId() & organization.getCreator().equals(User.login)){
                    Collection.getInstance().removeById(x);
                    return "Element removed";
                }}
          return "вы не можете удалить объект по этому id";}

    }

    @Override
    public String getCommandName() {
        return "remove_by_id";
    }
}
