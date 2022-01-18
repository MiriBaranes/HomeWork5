import java.util.Scanner;

public class Main {
    public static final int CREATE_A_NEW_ACCOUNT = 1;
    public static final int LOG_IN = 2;
    public static final int EXIT = 3;

    public static void main(String[] args) {
        Shop shop = new Shop();
        mainMenuAct(shop);
    }

    public static int mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n1. Create a new account \n2. Log in to a new account" +
                    "\n3.Exit.\n\nEnter your choice: ");
            choice = scanner.nextInt();
        }
        while (choice < CREATE_A_NEW_ACCOUNT || choice > EXIT);
        return choice;
    }

    public static void mainMenuAct(Shop shop) {
        int choice = 0;
        while (choice != EXIT) {
            choice = mainMenu();
            switch (choice) {
                case CREATE_A_NEW_ACCOUNT:
                    shop.currentUser();
                    break;
                case LOG_IN:
                    Client user = shop.loginToAnExistingAccount();
                    if (user != null) {
                        user.justLoggedIn(shop);
                    } else {
                        System.out.println("invalid");
                    }
                    break;
            }
        }
    }

}
