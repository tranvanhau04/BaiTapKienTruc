package fit.iuh.eventlistener;

import fit.iuh.event.OrderCancelledEvent;
import fit.iuh.event.OrderCreatedEvent;
import fit.iuh.infrastructure.SimulatedEventBus;
import fit.iuh.readmodel.OrderReadModel;
import fit.iuh.repository.OrderReadModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventListener {

    private final OrderReadModelRepository readModelRepository;
    private final SimulatedEventBus eventBus;

    public OrderEventListener(OrderReadModelRepository readModelRepository, SimulatedEventBus eventBus) {
        this.readModelRepository = readModelRepository;
        this.eventBus = eventBus;

        // Register listeners
        registerListeners();
    }

    /**
     * Register event listeners to the event bus
     */
    private void registerListeners() {
        eventBus.subscribe(OrderCreatedEvent.class, this::onOrderCreated);
        eventBus.subscribe(OrderCancelledEvent.class, this::onOrderCancelled);
        log.info("Order Event Listeners registered");
    }

    /**
     * Handle OrderCreatedEvent
     */
    public void onOrderCreated(OrderCreatedEvent event) {
        log.info("Handling OrderCreatedEvent for Order ID: {}", event.getOrderId());

        OrderReadModel readModel = OrderReadModel.builder()
                .id(event.getOrderId())
                .product(event.getProduct())
                .quantity(event.getQuantity())
                .status(event.getStatus())
                .createdAt(event.getTimestamp())
                .updatedAt(event.getTimestamp())
                .build();

        readModelRepository.save(readModel);
        log.info("OrderReadModel created for Order ID: {}", event.getOrderId());
    }

    /**
     * Handle OrderCancelledEvent
     */
    public void onOrderCancelled(OrderCancelledEvent event) {
        log.info("Handling OrderCancelledEvent for Order ID: {}", event.getOrderId());

        readModelRepository.findById(event.getOrderId())
                .ifPresentOrElse(
                    readModel -> {
                        readModel.setStatus("CANCELLED");
                        readModel.setUpdatedAt(event.getTimestamp());
                        readModelRepository.save(readModel);
                        log.info("OrderReadModel updated to CANCELLED for Order ID: {}", event.getOrderId());
                    },
                    () -> log.warn("OrderReadModel not found for Order ID: {}", event.getOrderId())
                );
    }
}

