package fit.iuh.service;

import fit.iuh.dto.CancelOrderRequest;
import fit.iuh.dto.CreateOrderRequest;
import fit.iuh.dto.OrderResponse;
import fit.iuh.entity.Order;
import fit.iuh.event.OrderCancelledEvent;
import fit.iuh.event.OrderCreatedEvent;
import fit.iuh.infrastructure.SimulatedEventBus;
import fit.iuh.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class OrderCommandService {

    private final OrderRepository orderRepository;
    private final SimulatedEventBus eventBus;

    public OrderCommandService(OrderRepository orderRepository, SimulatedEventBus eventBus) {
        this.orderRepository = orderRepository;
        this.eventBus = eventBus;
    }

    /**
     * Create a new order
     */
    public OrderResponse createOrder(CreateOrderRequest request) {
        log.info("Creating order for product: {} with quantity: {}", request.getProduct(), request.getQuantity());

        Order order = Order.builder()
                .product(request.getProduct())
                .quantity(request.getQuantity())
                .status("PENDING")
                .build();

        Order savedOrder = orderRepository.save(order);
        log.info("Order created with ID: {}", savedOrder.getId());

        // Publish event
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getProduct(),
                savedOrder.getQuantity(),
                savedOrder.getStatus()
        );
        eventBus.publish(event);
        log.info("OrderCreatedEvent published for Order ID: {}", savedOrder.getId());

        return convertToResponse(savedOrder);
    }

    /**
     * Cancel an order
     */
    public OrderResponse cancelOrder(Long orderId, CancelOrderRequest request) {
        log.info("Cancelling order with ID: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        order.setStatus("CANCELLED");
        Order updatedOrder = orderRepository.save(order);
        log.info("Order cancelled with ID: {}", orderId);

        // Publish event
        OrderCancelledEvent event = new OrderCancelledEvent(
                updatedOrder.getId(),
                request.getReason()
        );
        eventBus.publish(event);
        log.info("OrderCancelledEvent published for Order ID: {}", orderId);

        return convertToResponse(updatedOrder);
    }

    private OrderResponse convertToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}

