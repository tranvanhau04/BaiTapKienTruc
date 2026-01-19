package OrderState.decoratorpattern;

public class CancelledDecorator extends OrderDecorator {
    public CancelledDecorator(Order order) {
        super(order);
    }

    public void process() {
        order.process();
        System.out.println("Order cancelled and refunded.");
    }
}

