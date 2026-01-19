package OrderState.decoratorpattern;

public class DeliveredDecorator extends OrderDecorator {
    public DeliveredDecorator(Order order) {
        super(order);
    }

    public void process() {
        order.process();
        System.out.println("Order delivered successfully.");
    }
}

