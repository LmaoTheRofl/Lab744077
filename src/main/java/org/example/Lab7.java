package org.example;

import org.example.commands.CommandHandler;
import org.example.storage.Collection;
import org.example.storage.Database;
import org.example.storage.RecordsDao;
import java.util.Scanner;

public class Lab7 {
    public static void main(String[] args) {
        Collection collection = Collection.getInstance();
        System.out.println("Connected to database!");
        CommandHandler commandHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sign in or login");
        String x = RecordsDao.register();
        System.out.println(x);
        System.out.println(RecordsDao.sign_in(x));
      while (scanner.hasNextLine()) {
            System.out.println(commandHandler.execute(scanner.nextLine().toLowerCase()));
            collection.sortById();
        }


    }
}
