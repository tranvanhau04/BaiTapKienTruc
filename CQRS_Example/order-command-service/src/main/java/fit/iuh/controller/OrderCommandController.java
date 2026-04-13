package fit.iuh.controller;

import fit.iuh.dto.CancelOrderRequest;
import fit.iuh.dto.CreateOrderRequest;
import fit.iuh.dto.OrderResponse;
import fit.iuh.service.OrderCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderCommandController {

    private final OrderCommandService orderCommandService;

    public OrderCommandController(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        log.info("Received POST request to create order: {}", request);
        OrderResponse response = orderCommandService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<OrderResponse> cancelOrder(
            @PathVariable Long id,
            @RequestBody CancelOrderRequest request) {
        log.info("Received PUT request to cancel order ID: {}", id);
        OrderResponse response = orderCommandService.cancelOrder(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Command Service is running on port 8081");
    }
}

