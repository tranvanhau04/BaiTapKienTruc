package OrderState.decoratorpattern;

public class ProcessingDecorator extends OrderDecorator {
    public ProcessingDecorator(Order order) {
        super(order);
    }

    public void process() {
        order.process();
        System.out.println("Processing order: packing and shipping.");
    }
}

