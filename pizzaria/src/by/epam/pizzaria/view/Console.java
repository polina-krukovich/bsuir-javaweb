package by.epam.pizzaria.view;

import by.epam.pizzaria.bean.Response;
import by.epam.pizzaria.bean.pizza.PizzaRequest;
import by.epam.pizzaria.bean.pizza.PizzaResponse;
import by.epam.pizzaria.bean.user.UserRequest;
import by.epam.pizzaria.bean.user.UserResponse;
import by.epam.pizzaria.controller.Controller;
import by.epam.pizzaria.controller.command.CommandName;
import by.epam.pizzaria.controller.command.CommandStatus;
import by.epam.pizzaria.entity.Pizza;
import by.epam.pizzaria.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * This is a singleton class for accessing View Layer operations
 *
 * @author Polina Krukovich
 */

public final class Console {
    private static final Console instance = new Console();
    public static Console getInstance() {
        return instance;
    }

    private Controller controller = new Controller();
    private Scanner input = new Scanner(System.in);
    private static final Map<Integer, String> commands;
    static {
        commands = new HashMap<>();
        commands.put(1, "sign in");
        commands.put(2, "sign up");
        commands.put(3, "sign out");
        commands.put(4, "pizza create");
        commands.put(5, "pizza read by id");
        commands.put(6, "pizza read by name");
        commands.put(7, "pizza read all");
        commands.put(8, "pizza update name");
        commands.put(9, "pizza update desc");
        commands.put(10, "pizza update price");
        commands.put(11, "pizza delete");
        commands.put(12, "help");
        commands.put(13, "exit");
    }
    private final String ANSI_RESET = "\u001B[0m";

    private final String delimiter = "* * * * * * * * * * * * * * * * * * * * *";
    private final String WELCOME_MESSAGE = "WELCOME TO PIZZERIA ONLINE SERVICE!";
    private final String EXIT_MESSAGE = "THANKS FOR USING PIZZERIA ONLINE SERVICE!";
    private final String HELP_MESSAGE = "Type 'help' to see all the commands";
    private final String COMMAND_ERROR_MESSAGE = "Such command doesn't exist";
    private final String ENTER_REQUEST = "Enter";
    private final String LOGIN_REQUEST = ENTER_REQUEST + " login:";
    private final String PASSWORD_REQUEST = ENTER_REQUEST + " password:";
    private final String ROLE_REQUEST = ENTER_REQUEST + " user role (admin/user):";
    private final String PIZZA_NAME_REQUEST = ENTER_REQUEST + " pizza name:";
    private final String PIZZA_ID_REQUEST = ENTER_REQUEST + " pizza id:";
    private final String PIZZA_PRICE_REQUEST = ENTER_REQUEST + " pizza price:";
    private final String PIZZA_DESCRIPTION_REQUEST = ENTER_REQUEST + " pizza description:";
    private final String ADMIN = "admin";
    private final String USER = "user";

    private Console() { }

    private void println(String message) {
        System.out.println(message);
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void printError(String message) {
        String ANSI_RED = "\u001B[31m";
        println(ANSI_RED + message + ANSI_RESET);
    }

    private void printSuccess(String message) {
        String ANSI_GREEN = "\u001B[32m";
        println(ANSI_GREEN + message + ANSI_RESET);
    }

    private void printAlert(String message) {
        String ANSI_YELLOW = "\u001B[33m";
        println(ANSI_YELLOW + message + ANSI_RESET);
    }

    private void printResultMessage(Response response) {
        if (response.getStatus() == CommandStatus.SUCCESS) {
            printSuccess(response.getSuccessMessage());
        } else {
            printError(response.getErrorMessage());
        }
    }

    private void printPizzaInfo(Pizza pizza) {
        printAlert("Name:          " + pizza.getName());
        printAlert("Description:   " + pizza.getDescription());
        printAlert("Price:         " + pizza.getPrice() + "$");
        printAlert("ID:            " + pizza.getId());
    }

    private int parseCommand(String str) {
        str = str.trim().toLowerCase();
        for (int i = 1; i <= commands.size(); i++) {
            if (str.equals(commands.get(i)) || str.equals(String.valueOf(i))) {
                return i;
            }
        }
        return -1;
    }

    private void help() {
        for (HashMap.Entry<Integer, String> command : commands.entrySet()) {
            println("\t" + command.getKey().toString() + "\t............... " + command.getValue());
        }
    }

    private void signIn() {
        println(LOGIN_REQUEST);
        String login = input.nextLine().trim().toLowerCase();

        println(PASSWORD_REQUEST);
        String password = input.nextLine().trim().toLowerCase();

        UserRequest userRequest = new UserRequest();
        userRequest.setLogin(login);
        userRequest.setPassword(password);
        userRequest.setCommandName(CommandName.SIGN_IN);

        UserResponse userResponse = (UserResponse) controller.executeTask(userRequest);
        printResultMessage(userResponse);
    }

    private void signUp() {
        println(LOGIN_REQUEST);
        String login = input.nextLine().trim().toLowerCase();

        println(PASSWORD_REQUEST);
        String password = input.nextLine().trim().toLowerCase();

        String role;
        do {
            println(ROLE_REQUEST);
            role = input.nextLine().trim().toLowerCase();
        } while (!role.equals(ADMIN) && !role.equals(USER));
        boolean isAdmin = role.equals(ADMIN);

        UserRequest userRequest = new UserRequest();
        userRequest.setLogin(login);
        userRequest.setPassword(password);
        userRequest.setAdmin(isAdmin);
        userRequest.setCommandName(CommandName.SIGN_UP);

        UserResponse userResponse = (UserResponse) controller.executeTask(userRequest);
        printResultMessage(userResponse);
    }

    private void signOut() {
        UserRequest userRequest = new UserRequest();
        userRequest.setCommandName(CommandName.SIGN_OUT);

        UserResponse userResponse = (UserResponse) controller.executeTask(userRequest);
        printResultMessage(userResponse);
    }

    private void createPizza() {
        println(PIZZA_NAME_REQUEST);
        String name = input.nextLine().trim().toLowerCase();

        println(PIZZA_DESCRIPTION_REQUEST);
        String desc = input.nextLine().trim().toLowerCase();

        double price;
        do {
            println(PIZZA_PRICE_REQUEST);
            try {
                price = Double.parseDouble(input.nextLine().trim().toLowerCase());
            } catch (NumberFormatException e) {
                price = -1;
            }
        } while (price < 0);

        PizzaRequest pizzaRequest = new PizzaRequest();
        pizzaRequest.setName(name);
        pizzaRequest.setDescription(desc);
        pizzaRequest.setPrice(price);
        pizzaRequest.setCommandName(CommandName.CREATE_PIZZA);

        PizzaResponse pizzaResponse = (PizzaResponse) controller.executeTask(pizzaRequest);
        printResultMessage(pizzaResponse);
    }

    private void readPizzaById() {
        int id;
        do {
            println(PIZZA_ID_REQUEST);
            try {
                id = Integer.parseInt(input.nextLine().trim().toLowerCase());
            } catch (NumberFormatException e) {
                id = -1;
            }
        } while (id < 0);

        PizzaRequest pizzaRequest = new PizzaRequest();
        pizzaRequest.setId(id);
        pizzaRequest.setCommandName(CommandName.READ_PIZZA_BY_ID);

        PizzaResponse pizzaResponse = (PizzaResponse) controller.executeTask(pizzaRequest);
        printResultMessage(pizzaResponse);

        if (pizzaResponse.getStatus() == CommandStatus.SUCCESS) {
            printPizzaInfo(pizzaResponse.getPizza());
        }
    }

    private void readPizzaByName() {
        println(PIZZA_NAME_REQUEST);
        String name = input.nextLine().trim().toLowerCase();

        PizzaRequest pizzaRequest = new PizzaRequest();
        pizzaRequest.setName(name);
        pizzaRequest.setCommandName(CommandName.READ_PIZZA_BY_NAME);

        PizzaResponse pizzaResponse = (PizzaResponse) controller.executeTask(pizzaRequest);
        printResultMessage(pizzaResponse);

        if (pizzaResponse.getStatus() == CommandStatus.SUCCESS) {
            printPizzaInfo(pizzaResponse.getPizza());
        }
    }

    private void readAllPizzas() {
        PizzaRequest pizzaRequest = new PizzaRequest();
        pizzaRequest.setCommandName(CommandName.READ_PIZZA_ALL);

        PizzaResponse pizzaResponse = (PizzaResponse) controller.executeTask(pizzaRequest);
        printResultMessage(pizzaResponse);

        if (pizzaResponse.getStatus() == CommandStatus.SUCCESS) {
            for(Pizza pizza : pizzaResponse.getPizzas()) {
                printPizzaInfo(pizza);
                println("");
            }
        }
    }

    private void updatePizzaName() {
        int id;
        do {
            println(PIZZA_ID_REQUEST);
            try {
                id = Integer.parseInt(input.nextLine().trim().toLowerCase());
            } catch (NumberFormatException e) {
                id = -1;
            }
        } while (id < 0);

        println(PIZZA_NAME_REQUEST);
        String name = input.nextLine().trim().toLowerCase();

        PizzaRequest pizzaRequest = new PizzaRequest();
        pizzaRequest.setId(id);
        pizzaRequest.setName(name);
        pizzaRequest.setCommandName(CommandName.UPDATE_PIZZA_NAME);

        PizzaResponse pizzaResponse = (PizzaResponse) controller.executeTask(pizzaRequest);
        printResultMessage(pizzaResponse);
    }

    private void updatePizzaDescription() {
        int id;
        do {
            println(PIZZA_ID_REQUEST);
            try {
                id = Integer.parseInt(input.nextLine().trim().toLowerCase());
            } catch (NumberFormatException e) {
                id = -1;
            }
        } while (id < 0);

        println(PIZZA_DESCRIPTION_REQUEST);
        String desc = input.nextLine().trim().toLowerCase();

        PizzaRequest pizzaRequest = new PizzaRequest();
        pizzaRequest.setId(id);
        pizzaRequest.setDescription(desc);
        pizzaRequest.setCommandName(CommandName.UPDATE_PIZZA_DESCRIPTION);

        PizzaResponse pizzaResponse = (PizzaResponse) controller.executeTask(pizzaRequest);
        printResultMessage(pizzaResponse);
    }

    private void updatePizzaPrice() {
        int id;
        do {
            println(PIZZA_ID_REQUEST);
            try {
                id = Integer.parseInt(input.nextLine().trim().toLowerCase());
            } catch (NumberFormatException e) {
                id = -1;
            }
        } while (id < 0);

        double price;
        do {
            println(PIZZA_PRICE_REQUEST);
            try {
                price = Double.parseDouble(input.nextLine().trim().toLowerCase());
            } catch (NumberFormatException e) {
                price = -1;
            }
        } while (price < 0);

        PizzaRequest pizzaRequest = new PizzaRequest();
        pizzaRequest.setId(id);
        pizzaRequest.setPrice(price);
        pizzaRequest.setCommandName(CommandName.UPDATE_PIZZA_PRICE);

        PizzaResponse pizzaResponse = (PizzaResponse) controller.executeTask(pizzaRequest);
        printResultMessage(pizzaResponse);
    }

    private void deletePizza() {
        int id;
        do {
            println(PIZZA_ID_REQUEST);
            try {
                id = Integer.parseInt(input.nextLine().trim().toLowerCase());
            } catch (NumberFormatException e) {
                id = -1;
            }
        } while (id < 0);

        PizzaRequest pizzaRequest = new PizzaRequest();
        pizzaRequest.setId(id);
        pizzaRequest.setCommandName(CommandName.DELETE_PIZZA);

        PizzaResponse pizzaResponse = (PizzaResponse) controller.executeTask(pizzaRequest);
        printResultMessage(pizzaResponse);
    }

    /**
     * Run program cycle, execute incoming commands until the Exit command is entered
     */
    public void start() {
        printAlert(delimiter + "\n" + WELCOME_MESSAGE + "\n" + delimiter);
        println(HELP_MESSAGE);

        boolean isRunning = true;
        while(isRunning) {
            String commandString = input.nextLine();
            int commandNumber = parseCommand(commandString);
            switch (commandNumber) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    signOut();
                    break;
                case 4:
                    createPizza();
                    break;
                case 5:
                    readPizzaById();
                    break;
                case 6:
                    readPizzaByName();
                    break;
                case 7:
                    readAllPizzas();
                    break;
                case 8:
                    updatePizzaName();
                    break;
                case 9:
                    updatePizzaDescription();
                    break;
                case 10:
                    updatePizzaPrice();
                    break;
                case 11:
                    deletePizza();
                    break;
                case 12:
                    help();
                    break;
                case 13:
                    isRunning = false;
                    printAlert(delimiter + "\n" + EXIT_MESSAGE + "\n" + delimiter);
                    break;
                default:
                    printError(COMMAND_ERROR_MESSAGE);
                    break;
            }
        }
    }


}
