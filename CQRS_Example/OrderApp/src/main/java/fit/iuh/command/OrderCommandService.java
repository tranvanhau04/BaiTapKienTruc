package fit.iuh.command;

import fit.iuh.entity.Order;
import fit.iuh.entity.OrderEvent;
import fit.iuh.entity.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void createOrder(String product, Integer quantity) {
        Order order = new Order();
        order.setProductName(product);
        order.setQuantity(quantity);
        order.setStatus("CREATED");
        repository.save(order);

        // Phát Event
        eventPublisher.publishEvent(new OrderEvent(order.getId(), "ORDER_CREATED"));
    }

    public void cancelOrder(Long id) {
        Order order = repository.findById(id).orElseThrow();
        order.setStatus("CANCELLED");
        repository.save(order);

        // Phát Event
        eventPublisher.publishEvent(new OrderEvent(id, "ORDER_CANCELLED"));
    }
}
