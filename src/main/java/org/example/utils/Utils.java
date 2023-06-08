package org.example.utils;

import org.example.organization.*;
import org.example.storage.User;

import java.sql.Date;
import java.time.Instant;
import java.util.Locale;
import java.util.Scanner;

public class Utils {
    /**
     * метод для создани? объекта организации чтением консоли
     * @return organization
     */
    public static Organization readOrganization() {
        Scanner scanner = new Scanner(System.in);
        Organization organization = new Organization();
        System.out.println("Введите имя организации и нажмите enter:");
        organization.setName(getName(scanner));
        System.out.println("Введите координаты организации(2 координаты float, long через enter):");
        organization.setCoordinates(new Coordinates(getFloatCoordinatesId(scanner), getLongCoordinatesId(scanner)));
        organization.setCreationDate(Date.from(Instant.now()));
        System.out.println("Введите годовой оборот организации long и нажмите enter:");
        organization.setAnnualTurnover(getLongAnnualTurnoverId(scanner));
        System.out.println("Введите тип организации(COMMERCIAL, PUBLIC, TRUST) и нажмите enter:");
        organization.setType(getType(scanner));
        System.out.println("Введите адрес организации(имя и 3 координаты double double int через enter):");
        organization.setOfficialAddress(new Address(getStreetName(scanner), new Location(getDoubleCoordinatesId(scanner), getDoubleCoordinatesId(scanner), getIntCoordinatesId(scanner))));
        organization.setCreator(User.login);
        return organization;
    }

    /**
     * getters для валидации некоторых полей
     * @param scanner
     * @return values
     */
    private static float getFloatCoordinatesId(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
            try {
                float l = Float.parseFloat(s);
                return l;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input!");
            }
        }
    }
    private static int getIntCoordinatesId(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
            try {
                int l = Integer.parseInt(s);
                return l;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input!");
            }
        }
    }
    private static Integer getIntegerId(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
            try {
                Integer l = Integer.valueOf(s);
                if (l > 0) {
                    return l;
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input!");
            }
        }
    }
    private static double getDoubleCoordinatesId(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
            try {
                double l = Double.parseDouble(s);
                return l;

            } catch (NumberFormatException e) {
                System.out.println("Incorrect input!");
            }
        }
    }
    private static long getLongAnnualTurnoverId(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
            try {
                long l = Long.parseLong(s);
                if (l > 0) {
                    return l;
                }
                else{System.out.println("Incorrect input!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input!");
            }

        }

    }
    private static long getLongCoordinatesId(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
            try {
                long l = Long.parseLong(s);
                if (l < 274) {
                    return l;
                }
                else {System.out.println("Incorrect input!");}
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input!");
            }
        }
    }
    private static OrganizationType getType(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
          try{  if (s != null && s.length() > 0) {
                return OrganizationType.valueOf(s.toUpperCase(Locale.ENGLISH).trim());
            }System.out.println("Incorrect input!");}
            catch (IllegalArgumentException e) {
                System.out.println("Incorrect input!"); }
        }
    }
    private static String getName(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
           if (s != null && !s.isEmpty()) {
               String[] x = s.split(" ");
               if(x.length>=1) {
                return s; }
            } System.out.println("Incorrect input!");

        }
    }
    private static String getStreetName(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
            if (s != null && !s.isEmpty() && s.length()<83) {
                String[] x = s.split(" ");
                if(x.length>=1) {
                    return s; }
            } System.out.println("Incorrect input!");

        }
    }
}
