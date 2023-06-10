package org.example.commands;

public class Help implements Command{
    /**
     * @return  справка по доступным командам
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    @Override
    public String execute() {
        return ANSI_CYAN+"help"+ANSI_RESET+" : вывести справку по доступным командам\n"+
                ANSI_CYAN+"info"+ANSI_RESET+": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                ANSI_CYAN+"show"+ANSI_RESET+" : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                ANSI_CYAN+"add {element} "+ANSI_RESET+": добавить новый элемент в коллекцию\n" +
                ANSI_CYAN+"update id {element} "+ANSI_RESET+": обновить значение элемента коллекции, id которого равен заданному\n" +
                ANSI_CYAN+"remove_by_id"+ANSI_RESET+" : удалить элемент из коллекции по его id\n" +
                ANSI_CYAN+"clear"+ANSI_RESET+" : очистить коллекцию\n" +
                ANSI_CYAN+"save"+ANSI_RESET+" : сохранить коллекцию в файл\n" +
                ANSI_CYAN+"execute_script file_name"+ANSI_RESET+" : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                ANSI_CYAN+"exit"+ANSI_RESET+" : завершить программу (без сохранения в файл)\n" +
                ANSI_CYAN+"head"+ANSI_RESET+" : вывести первый элемент коллекции\n" +
                ANSI_CYAN+"add_if_max {element}"+ANSI_RESET+" : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                ANSI_CYAN+ "add_if_min {element}"+ANSI_RESET+" : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                ANSI_CYAN+"max_by_creation_date"+ANSI_RESET+" : вывести любой объект из коллекции, значение поля creationDate которого является максимальным\n" +
                ANSI_CYAN+"group_counting_by_type "+ANSI_RESET+": сгруппировать элементы коллекции по значению поля type, вывести количество элементов в каждой группе\n" +
                ANSI_CYAN+"print_ascending"+ANSI_RESET+" : вывести элементы коллекции в порядке возрастания";
    }

    @Override
    public String getCommandName() {
        return "help";
    }
}
