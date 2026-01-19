package paymentsystem.statepattern;

public class PaypalState implements PaymentState {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " by PayPal");
    }
}
