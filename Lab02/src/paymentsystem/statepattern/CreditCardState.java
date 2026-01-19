package paymentsystem.statepattern;

public class CreditCardState implements PaymentState {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " by Credit Card");
    }
}
