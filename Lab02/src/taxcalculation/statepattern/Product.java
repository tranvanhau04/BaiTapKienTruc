package taxcalculation.statepattern;

public class Product {
    private double price;
    private TaxState taxState;

    public Product(double price) {
        this.price = price;
    }

    public void setTaxState(TaxState taxState) {
        this.taxState = taxState;
    }

    public void calculateFinalPrice() {
        price = taxState.applyTax(price);
        System.out.println("Final price: " + price);
    }
}
