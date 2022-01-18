import java.util.Scanner;

public class Shop {
    public static final int EMPLOYEE_ACCOUNT = 0;
    public static final int CLIENT_ACCOUNT = 1;
    public static final int VIP = 1;
    public static final int NOT_VIP = 0;
    public static final int IS_IN_STOCK = 1;
    public static final int NOT_IN_STOCK = 2;
    public static final int NOT_EXIST_INDEX=-1;
    public static final int REGULAR_EMPLOYEE=1;
    public static final int MEMBER_MANAGEMENT=3;
    public static final int EXIT_CHOICE = -999;
    private DynamicArray<Client> users;
    private DynamicArray<Product> products;

    public Shop() {
        this.users = new DynamicArray<>(Client.class);
        this.products = new DynamicArray<>(Product.class);
    }


    public Class<? extends Client> storeUsers(int typeNumber) {
        return typeNumber == 0 ? Employee.class : Client.class;
    }

    public void currentUser() {
        int type = getIntInput("Press 0 for employee 1 for customer", x -> x == EMPLOYEE_ACCOUNT || x == CLIENT_ACCOUNT);
        String firstName = new ParserBaseFullName("Enter a your first name name").getInput();
        Class<? extends Client> userType = storeUsers(type);
        String lastName = new ParserBaseFullName("Enter your last name: ").getInput();
        String username = new ParserBaseUsername("Enter a user name: ", this, userType).getInput();
        String password = new ParserBasePassword("Enter a password: ").getInput();
        int UsersVIP = getIntInput("for vip tap 1, else 0", x -> x == VIP || x == NOT_VIP);
        boolean isVip = (UsersVIP == 1);
        Client user1;
        if (type == CLIENT_ACCOUNT) {
            user1 = new Client(firstName, lastName, username, password, isVip);
        } else {
            int typeOfEmployee = getIntInput("Enter the rank of your employee -\n" +
                    " 1- regular employee\n" +
                    " 2- manager\n" +
                    " 3- member of management", x -> REGULAR_EMPLOYEE <= x && x <= MEMBER_MANAGEMENT);
            user1 = new Employee(firstName, lastName, username, password, isVip, typeOfEmployee);
        }
        this.users.addItem(user1);
    }

    public Client loginToAnExistingAccount() {
        int kindOfBill = getIntInput("tap 0 to employee account\ntap 1 to customer account", x -> x == CLIENT_ACCOUNT || x == EMPLOYEE_ACCOUNT);
        Class<? extends Client> type = storeUsers(kindOfBill);
        String userName = getInput("Enter your username");
        String password = getInput("Enter your password");
        Client user = getUserByCredentials(type, userName, password);
        if (user != null) {
            System.out.println(user.login());
        }
        return user;
    }

    public void printAllClubMembers() {
        DynamicArray<Client> clubUser = new DynamicArray<>(Client.class);
        for (int i = 0; i < users.length(); i++) {
            if (users.getItem(i).isVIP()) {
                clubUser.addItem(users.getItem(i));
            }
        }
        clubUser.printNumericalArray();
    }

    public void printAllClient() {
        this.users.printNumericalArray();
    }

    public void printCustomersWhoMadeAPurchase() {
        DynamicArray<Client> madePurchase = new DynamicArray<>(Client.class);
        for (int i = 0; i < users.length(); i++) {
            if (users.getItem(i).getAmountOfPurchases() > 0) {
                madePurchase.addItem(users.getItem(i));
            }
        }
        madePurchase.printNumericalArray();
    }

    public void printTheCustomerSpentTheMost() {
        double max = 0;
        int index = 0;
        for (int i = 0; i < users.length(); i++) {
            if (users.getItem(i).getSpent() > max) {
                max = users.getItem(i).getSpent();
                index = i;
            }
        }
        System.out.println("The customer who spent the most \n");
        System.out.println(users.getItem(index));
    }

    public void addingANewProductToTheStore() {

        String nameProduct = getInput("Enter the name of the product to exit press "+EXIT_CHOICE);
        if (!nameProduct.equals(EXIT_CHOICE+"")) {
            String descriptionProduct = getInput("Enter the description of the product");
            double priceProduct = getIntInput("Enter price of the product", x -> x >= 0);
            double clubMembersDiscount = getIntInput("Enter club members discount", x -> x >= 0);
            Product product = new Product(nameProduct, descriptionProduct, priceProduct, true, clubMembersDiscount);
            this.products.addItem(product);
            this.products.printNumericalArray();
            addingANewProductToTheStore();
        }
    }

    public void startShopping(Client client) {
        ShoppingCart cart = new ShoppingCart();
        boolean act = true;
        if (this.inStock().length() == 0) {
            System.out.println("There is no product yet.");
            act = false;
        }
        while (act) {
            System.out.println("Product list: ");
            this.inStock().printNumericalArray();
            int output = getIntInput("enter a number of product ,to exit press -1.", x ->
                    (0 < x && x <= this.inStock().length()) || x == NOT_EXIST_INDEX);
            act = output != NOT_EXIST_INDEX;
            if (act) {
                int productIndex = output - 1;
                int quantity = getIntInput("Enter quantity: ", x -> 0 < x);
                cart.addProducts(this.inStock().getItem(productIndex), quantity, client.isVIP());
                interimNotice(cart, client);
            }
        }
        if (cart.getPrice() > 0) {
            client.addShoppingToArr(cart);
            System.out.println("Your list: \n" + cart);
        }
    }

    public void interimNotice(ShoppingCart cart, Client client) {
        System.out.println("Current list: " + cart);
        if (client.getClass().equals(Employee.class)) {
            System.out.println("Excluding employee discount!");
        }
    }


    public DynamicArray<Product> inStock() {
        DynamicArray<Product> productInStock = new DynamicArray<>(Product.class);
        for (int i = 0; i < products.length(); i++) {
            if (this.products.getItem(i).isInStoke()) {
                productInStock.addItem(this.products.getItem(i));
            }
        }
        return productInStock;
    }

    public boolean matchUser(Client storeUsers, String username, String password) {
        return storeUsers.getUsername().equals(username) && storeUsers.getPassword().equals(password);
    }

    private Client getUserByCredentials(Class<? extends Client> type, String username, String password) {
        Client result = null;

        for (int i = 0; i < users.length(); i++) {
            Client client = users.getItem(i);
            if (client.getClass().equals(type)) {
                if (matchUser(client, username, password)) {
                    result = client;
                    break;
                }
            }
        }
        return result;
    }

    private String getInput(String request) {

        return new ParserBase(request).getInput();
    }

    public void changingProductStatus() {
        int choice;
        do {
            this.products.printNumericalArray();
            choice = getIntInput("Enter a number of the product to exit press "+EXIT_CHOICE, x -> (x > 0 && x <= products.length()) || x == EXIT_CHOICE);
            if (choice != EXIT_CHOICE) {
                int index = choice - 1;
                int changeStatus = getIntInput("Press 1 if it is in stock, 2 if it is not in stock to exit press "+EXIT_CHOICE, x -> x == IS_IN_STOCK || x == NOT_IN_STOCK || x == EXIT_CHOICE);
                boolean isInStock = (changeStatus == IS_IN_STOCK);
                this.products.getItem(index).setInStoke(isInStock);
            }
        } while (choice != EXIT_CHOICE);
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

    public DynamicArray<Client> getUsers() {
        return users;
    }

    public DynamicArray<Client> arrayByFilter(Class<? extends Client> userType) {
        DynamicArray<Client> byValue = new DynamicArray<>(Client.class);
        for (int i = 0; i < users.length(); i++) {
            if (users.getItem(i).getClass().equals(userType)) {
                byValue.addItem(users.getItem(i));
            }
        }
        return byValue;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Employee shop:\n ");
        DynamicArray<?> employeeShop = arrayByFilter(Employee.class);
        for (int i = 0; i < employeeShop.length(); i++) {
            output.append(i + 1).append(". ").append(employeeShop.getItem(i));
        }
        output.append("\nClient shop:\n ");
        DynamicArray<?> clientShop = arrayByFilter(Client.class);
        for (int i = 0; i < clientShop.length(); i++) {
            output.append(i + 1).append(". ").append(clientShop.getItem(i));
        }
        output.append("\nProduct shop:");
        for (int i = 0; i < this.products.length(); i++) {
            output.append(i + 1).append(". ").append(this.products.getItem(i));
        }
        return output.toString();
    }
}
