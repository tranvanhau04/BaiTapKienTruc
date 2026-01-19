package paymentsystem.statepattern;

public class PaymentContext {
    private PaymentState state;

    public void setState(PaymentState state) {
        this.state = state;
    }

    public void process(double amount) {
        state.pay(amount);
    }
}
