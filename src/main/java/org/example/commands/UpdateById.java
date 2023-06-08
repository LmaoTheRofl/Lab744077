package org.example.commands;

import org.example.organization.Organization;
import org.example.storage.Collection;
import org.example.storage.Database;
import org.example.storage.User;

import java.util.Scanner;

public class UpdateById implements Command{
    /**
     * обновить значение элемента коллекции, id которого равен заданному
     * @return element updated
     */
    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id");
        while(true) {
        long x = scanner.nextLong();
        for(Organization organization : Collection.getInstance().getAll()) {
            if(x == organization.getId() & organization.getCreator().equals(User.login)){
                Collection.getInstance().updateById(x);
                return "Element updated";
            }}
            return  "вы не можете изменить объект по этому id";}
    }

    @Override
    public String getCommandName() {
        return "update";
    }
}
