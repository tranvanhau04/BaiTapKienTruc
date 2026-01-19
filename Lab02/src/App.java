// import OrderState.decoratorpattern.BasicOrder;
// import OrderState.decoratorpattern.DeliveredDecorator;
// import OrderState.decoratorpattern.OrderDecorator;
// import OrderState.decoratorpattern.ProcessingDecorator;
// import OrderState.statepattern.CreatedState;
// import OrderState.statepattern.Order;
// import OrderState.strategypattern.ProcessingStrategy;

// import taxcalculation.decoratorpattern.BasicProduct;
// import taxcalculation.decoratorpattern.ConsumptionTaxDecorator;
// import taxcalculation.decoratorpattern.VATTaxDecorator;
// import taxcalculation.statepattern.ConsumptionTaxState;
// import taxcalculation.strategypattern.VATTaxStrategy;

import paymentsystem.decoratorpattern.BasicPayment;
import paymentsystem.decoratorpattern.DiscountDecorator;
import paymentsystem.decoratorpattern.Payment;
import paymentsystem.decoratorpattern.ProcessingFeeDecorator;
import paymentsystem.statepattern.CreditCardState;
import paymentsystem.statepattern.PaymentContext;
import paymentsystem.strategypattern.PaymentService;
import paymentsystem.strategypattern.PaypalStrategy;

public class App {
    public static void main(String[] args) {

        // DatabaseConnection db1 = DatabaseConnection.getInstance();
        // DatabaseConnection db2 = DatabaseConnection.getInstance();

        // db1.connect();

        // System.out.println(db1 == db2);

        // Database db1 = DatabaseFactory.createDatabase("mysql");
        // db1.connect();

        // Database db2 = DatabaseFactory.createDatabase("postgres");
        // db2.connect();
//==========================================================================================================================
        // ===== STATE PATTERN =====
        // System.out.println("=== STATE PATTERN ===");
        // Order orderState = new Order();
        // orderState.setState(new CreatedState());
        // orderState.process();

        // ===== STRATEGY PATTERN =====
        // System.out.println("\n=== STRATEGY PATTERN ===");
        // OrderState.strategypattern.Order orderStrategy =
        //         new OrderState.strategypattern.Order();
        // orderStrategy.setStrategy(new ProcessingStrategy());
        // orderStrategy.process();

        // ===== DECORATOR PATTERN =====
        // System.out.println("\n=== DECORATOR PATTERN ===");
        // OrderDecorator orderDecorator =
        //         new DeliveredDecorator(
        //                 new ProcessingDecorator(
        //                         new BasicOrder()
        //                 )
        //         );
        // orderDecorator.process();

//===============================================================================================================
        // ===== STATE PATTERN =====
        // System.out.println("=== STATE PATTERN ===");
        // taxcalculation.statepattern.Product p1 =
        //         new taxcalculation.statepattern.Product(100);
        // p1.setTaxState(new ConsumptionTaxState());
        // p1.calculateFinalPrice();

        // ===== STRATEGY PATTERN =====
        // System.out.println("\n=== STRATEGY PATTERN ===");
        // taxcalculation.strategypattern.Product p2 =
        //         new taxcalculation.strategypattern.Product(100);
        // p2.setTaxStrategy(new VATTaxStrategy());
        // p2.calculateFinalPrice();

        // ===== DECORATOR PATTERN =====
        // System.out.println("\n=== DECORATOR PATTERN ===");
        // taxcalculation.decoratorpattern.Product p3 =
        //         new VATTaxDecorator(
        //                 new ConsumptionTaxDecorator(
        //                         new BasicProduct(100)
        //                 )
        //         );
        // System.out.println("Final price: " + p3.getPrice());


        System.out.println("=== STATE PATTERN ===");
        PaymentContext context = new PaymentContext();
        context.setState(new CreditCardState());
        context.process(100);

        System.out.println("\n=== STRATEGY PATTERN ===");
        PaymentService service = new PaymentService();
        service.setStrategy(new PaypalStrategy());
        service.process(100);

        System.out.println("\n=== DECORATOR PATTERN ===");
        Payment payment =
                new DiscountDecorator(
                        new ProcessingFeeDecorator(
                                new BasicPayment(100)
                        )
                );
        System.out.println("Final amount: " + payment.getAmount());
    }
}
