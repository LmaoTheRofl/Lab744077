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
    private long creationTime;

    public long getCreationTime() {
        return creationTime;
    }

    public static Collection getInstance()  {
        if (INSTANCE == null) {
            INSTANCE = new Collection();
        }
        return INSTANCE;
    }

    private Collection() {
       try{ this.collection = Database.getInstance().getAllOrganizations();
           this.creationTime = System.currentTimeMillis();}
       catch (SQLException e) {e.printStackTrace();}
    }



    public void print() {
        collection.forEach(System.out::println);
    }

    public void clear() {
        collection.clear();
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
        organization.setId(generateId());
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
    private Long generateId() {
        Long id =  collection.stream()
                .map(Organization::getId)
                .max(Comparator.comparing(Long::longValue))
                .orElse(0L);
        return ++id;
    }



}
