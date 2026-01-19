package OrderState.decoratorpattern;

public class BasicOrder implements Order {
    public void process() {
        System.out.println("Basic order created.");
    }
}

