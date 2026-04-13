package fit.iuh.controller;

import fit.iuh.dto.OrderQueryResponse;
import fit.iuh.service.OrderQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderQueryController {

    private final OrderQueryService orderQueryService;

    public OrderQueryController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @GetMapping
    public ResponseEntity<List<OrderQueryResponse>> getAllOrders() {
        log.info("Received GET request to fetch all orders");
        List<OrderQueryResponse> orders = orderQueryService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderQueryResponse> getOrderById(@PathVariable Long id) {
        log.info("Received GET request to fetch order with ID: {}", id);
        return orderQueryService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Query Service is running on port 8082");
    }
}

