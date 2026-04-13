package fit.iuh.event;

import fit.iuh.entity.OrderEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    @EventListener
    public void handleOrderEvent(OrderEvent event) {
        System.out.println(">>> NHẬN ĐƯỢC EVENT: " + event.getType() + " cho đơn hàng ID: " + event.getOrderId());
    }
}
