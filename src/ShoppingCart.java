import java.util.Date;

public class ShoppingCart {
    public static final int ONE_HUNDRED_PERCENT = 100;
    private DynamicArray<ProductInCart> products;
    private double price;
    private Date date;

    public ShoppingCart() {
        this.products = new DynamicArray<>(ProductInCart.class);
        this.date = new Date();
        this.price = 0;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDiscount(double discount) {
        this.price *= (1 - discount / ONE_HUNDRED_PERCENT);
    }

    public double getPrice() {
        return this.price;
    }

    public void addProducts(Product product, int quantity, boolean isVip) {
        ProductInCart pic = new ProductInCart(product, quantity);
        this.products.addItem(pic);
        this.price += pic.getPrice(isVip);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < products.length(); i++) {
            output.append(i + 1).append(". ").append(products.getItem(i)).append("\n");
        }
        output.append(" \ntotal price: ").append(this.price);
        return output.toString();
    }


}
