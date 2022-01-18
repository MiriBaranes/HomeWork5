import java.util.Scanner;

public class Employee extends Client {
    public static final double DISCOUNT_RATE_BY_RANK = 10;
    public static final int BACK_TO_THE_MAIN_MENU = 8;
    public static final int PRINT_ALL_CUSTOMERS = 1;
    public static final int PRINT_ALL_MEMBERS_CLUB = 2;
    public static final int PRINT_CUSTOMERS_THAT_MADE_AT_LIST_ONE_PURCHASE = 3;
    public static final int PRINT_THE_CUSTOMER_WITH_THE_HIGHEST_PRICE_PURCHASES = 4;
    public static final int ADDING_A_NEW_PRODUCT_TO_THE_STORE = 5;
    public static final int CHANGE_INVENTORY_STATUS = 6;
    public static final int MAKING_A_PURCHASE = 7;
    private Rank rank;

    public Employee(String firstName, String lastName, String username, String password, boolean isVIP, Rank rank) {
        super(firstName, lastName, username, password, isVIP);
        this.rank = rank;
    }

    public Employee(String firstName, String lastName, String username, String password, boolean isVIP, int rank) {
        this(firstName, lastName, username, password, isVIP, Rank.fromInt(rank));
    }

    public Rank getRank() {
        return rank;
    }

    private int getIntInput(String request, IntValidator validator) {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.println(request);
            input = scanner.nextInt();
        } while (!validator.isValid(input));
        return input;
    }

    public String login() {
        return (super.login() + "[" + upperFirst(this.getRank().name() + "]"));
    }

    public double getDiscount() {
        return this.rank.getValue() * DISCOUNT_RATE_BY_RANK;
    }

    @Override
    public void addShoppingToArr(ShoppingCart shoppingCart) {
        shoppingCart.setDiscount(this.getDiscount());
        super.addShoppingToArr(shoppingCart);
    }

    public String typeClass() {
        return "Employee, rank- [" + (this).upperFirst(this.getRank().name()) + "]";
    }

    public void onlyForEmployees(Shop shop, int choice) {
        switch (choice) {
            case PRINT_ALL_CUSTOMERS:
                shop.printAllClient();
                break;
            case PRINT_ALL_MEMBERS_CLUB:
                shop.printAllClubMembers();
                break;
            case PRINT_CUSTOMERS_THAT_MADE_AT_LIST_ONE_PURCHASE:
                shop.printCustomersWhoMadeAPurchase();
                break;
            case PRINT_THE_CUSTOMER_WITH_THE_HIGHEST_PRICE_PURCHASES:
                shop.printTheCustomerSpentTheMost();
                break;
            case ADDING_A_NEW_PRODUCT_TO_THE_STORE:
                shop.addingANewProductToTheStore();
                break;
            case CHANGE_INVENTORY_STATUS:
                shop.changingProductStatus();
                break;
            case MAKING_A_PURCHASE:
                shop.startShopping(this);
                break;
        }
    }

    public void worksMenuAct(Shop shop) {
        int choice = -1;
        while (choice != BACK_TO_THE_MAIN_MENU) {
            choice = worksMenu();
            onlyForEmployees(shop, choice);
        }
    }

    private int worksMenu() {
        return getIntInput("\n" +
                "1. Print all customers in the store\n" +
                "2. Print a list of club members.\n" +
                "3. Print customers who have made at least one purchase\n" +
                "4. The customer with the highest amount of purchases\n" +
                "5. Adding a new product to the store\n" +
                "6. Change inventory status\n" +
                "7. Making a purchase.\n" +
                "8. Logout and back to the main menu\n\n" +
                "Enter your choice: ", (x) -> PRINT_ALL_CUSTOMERS <= x && x <= BACK_TO_THE_MAIN_MENU);
    }

    @Override
    public void justLoggedIn(Shop shop) {
        worksMenuAct(shop);
    }

    // should be static
    public String upperFirst(String str) {
        StringBuilder sb = new StringBuilder((str.charAt(0) + "").toUpperCase());
        str = str.toLowerCase();
        for (int i = 1; i < str.length(); i++) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public String toString() {
        return super.toString();
    }


}
