public class ProductInCart {
    private Product product;
    private int quantity;

    public ProductInCart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getPrice(boolean isVIP) {
        return this.product.getPrice(isVIP) * quantity;
    }

    public String toString() {
        return this.product.getProductName() + " * " + this.quantity;
    }
}
