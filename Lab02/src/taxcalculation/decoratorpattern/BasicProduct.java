package taxcalculation.decoratorpattern;

public class BasicProduct implements Product {
    private double price;

    public BasicProduct(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
