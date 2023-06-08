package org.example.storage;

import org.example.organization.Organization;
import org.example.utils.Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Collection {
    private ArrayDeque<Organization> collection;
    private static Collection INSTANCE;

    public static Collection getInstance()  {
        if (INSTANCE == null) {
            INSTANCE = new Collection();
        }
        return INSTANCE;
    }

    private Collection() {
       try{ this.collection = Database.getInstance().getAllOrganizations();}
       catch (SQLException e) {e.printStackTrace();}
    }


    /**
     * Валидация? полей организации
     * @param organization организазция для проверки
     * @return результат валидации
     */
    private boolean validate(Organization organization) {
        if(organization.getName().trim().isEmpty()) {
            return false;}
        if(organization.getCoordinates().getY() > 274){
            return false;}
        if(organization.getAnnualTurnover()<=0) {
            return false;}
        if(organization.getCreationDate()==null) {
            return false;}
        if(organization.getType()==null) {
            return false;}
        if(organization.getOfficialAddress().getStreet() == null || organization.getOfficialAddress().getStreet().length()>83 || organization.getOfficialAddress().getTown() == null){
            return false;}
        return true;
    }

    public void print() {
        collection.forEach(System.out::println);
    }

    public void clear() {
        collection.clear();
    }
    public FileTime getFileCreationDate(String fileName) {
        try {
            return (FileTime) Files.getAttribute((new File(fileName)).toPath(), "creationTime");
        } catch (IOException e) {
            return null;
        }
    }

    public ArrayDeque<Organization> getAll() {
        return collection;
    }
    public void removeById(long x) {
        TreeSet<Long> idCounter = new TreeSet<>();
        Long id = Long.valueOf(1);
        collection.removeIf(e -> String.valueOf(e.getId()).equals(String.valueOf(x)));
        for(Organization organization : collection) {
            while (!idCounter.add(id)) {
                id++;
            }
            organization.setId(id);}


    }

    public void updateById(long x) {
        collection.removeIf(e -> String.valueOf(e.getId()).equals(String.valueOf(x)));
        Organization object = Utils.readOrganization();
        object.setId(x);
        collection.add(object);
    }

    public void add(Organization organization) {
        organization.setId((long) RecordsDao.createOrganization(organization));
        collection.add(organization);
    }
    public void sortAscending() {
        ArrayDeque<Organization>  y = new ArrayDeque<Organization>();
        List<Organization> x = new ArrayList<Organization>(collection).stream().sorted(Comparator.comparing(Organization::getAnnualTurnover)).collect(Collectors.toList());
        y.addAll(x);
        this.collection = y;
    }
    public void sortById() {
        try {ArrayDeque<Organization>  y = new ArrayDeque<Organization>();
            List<Organization> x = new ArrayList<Organization>(collection).stream().sorted(Comparator.comparing(Organization::getId)).collect(Collectors.toList());
            y.addAll(x);
            this.collection = y;}
        catch(NullPointerException e) {}
    }
    public void sortByType() {
        ArrayDeque<Organization>  y = new ArrayDeque<Organization>();
        List<Organization> x = new ArrayList<Organization>(collection).stream().sorted(Comparator.comparing(Organization::getType)).collect(Collectors.toList());
        y.addAll(x);
        this.collection = y;
    }



}
