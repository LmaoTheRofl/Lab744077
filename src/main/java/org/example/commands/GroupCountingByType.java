package org.example.commands;

import org.example.organization.Organization;
import org.example.organization.OrganizationType;
import org.example.storage.Collection;
import org.example.storage.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.organization.OrganizationType.TRUST;
import static org.example.organization.OrganizationType.PUBLIC;

public class GroupCountingByType implements Command{
    /**
     * сгруппировать элементы коллекции по значению поля type, вывести количество элементов в каждой группе
     * @return collection sorted
     */
    @Override
    public String execute() {
        Collection.getInstance().sortByType();
        List<OrganizationType> presentTRUSTTypes = new ArrayList<OrganizationType>();
        List<OrganizationType> presentPUBLICTypes = new ArrayList<OrganizationType>();
        List<OrganizationType> presentCOMMERCIALTypes = new ArrayList<OrganizationType>();
        int counterTRUST = 0;
        int counterPUBLIC = 0;
        int counterCOMMERCIAL = 0;
        for(Organization organization : Collection.getInstance().getAll() ){
            if(organization.getType().equals(TRUST)) {
                presentTRUSTTypes.add(organization.getType());}
            else if(organization.getType().equals(PUBLIC)) {
                presentPUBLICTypes.add(organization.getType());}
            else {
                presentCOMMERCIALTypes.add(organization.getType());}

        }
        System.out.println("TRUST: "+presentTRUSTTypes.toArray().length);
        System.out.println("PUBLIC: "+presentPUBLICTypes.toArray().length);
        System.out.println("COMMERCIAL: "+presentCOMMERCIALTypes.toArray().length);
        return Collection.getInstance()
                .getAll()
                .stream()
                .map(Organization::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String getCommandName() {
        return "group_counting_by_type";
    }
}
