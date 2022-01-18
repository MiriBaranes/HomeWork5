public class Product {
    private String productName;
    private String description;
    private double price;
    private boolean isInStoke;
    private double vipPrice;
    public static final int ONE_HUNDRED_PERCENT = 100;

    public Product(String productName, String description, double price, boolean isInStoke, double discount) {
        if (price < 0)
            throw new IllegalArgumentException("price can't be negative");

        this.productName = productName;
        this.description = description;
        this.price = price;
        this.isInStoke = isInStoke;
        this.vipPrice = priceAfterDiscount(this.price, discount);
    }

    public double priceAfterDiscount(double price, double discount) {
        return price - ((price * discount) / ONE_HUNDRED_PERCENT);
    }

    public double getPrice(boolean isVip) {
        return isVip ? this.vipPrice : this.price;
    }

    public boolean isInStoke() {
        return isInStoke;
    }

    public void setInStoke(boolean inStoke) {
        isInStoke = inStoke;
    }

    public String getProductName() {
        return productName;
    }

    public String toString() {
        return "Product name: "+this.productName +"\nDescription "+this.description +"\nPrice: " + this.price + "\nPrice for club: " +
                this.vipPrice;
    }
}
