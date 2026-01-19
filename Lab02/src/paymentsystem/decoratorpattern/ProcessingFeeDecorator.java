package paymentsystem.decoratorpattern;

public class ProcessingFeeDecorator extends PaymentDecorator {

    public ProcessingFeeDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public double getAmount() {
        return payment.getAmount() + 5;
    }
}
