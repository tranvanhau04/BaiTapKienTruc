package paymentsystem.decoratorpattern;

public class BasicPayment implements Payment {
    private double amount;

    public BasicPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
