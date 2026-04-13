package fit.iuh.controller;

import fit.iuh.dto.OrderDTO;
import fit.iuh.service.OrderIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class OrderUIController {

    private final OrderIntegrationService orderIntegrationService;

    public OrderUIController(OrderIntegrationService orderIntegrationService) {
        this.orderIntegrationService = orderIntegrationService;
    }

    @GetMapping
    public String index(Model model) {
        log.info("Loading order management page");
        List<OrderDTO> orders = orderIntegrationService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("orderCount", orders.size());
        return "order-management";
    }

    @PostMapping("/api/orders/create")
    @ResponseBody
    public OrderDTO createOrder(@RequestParam String product, @RequestParam Integer quantity) {
        log.info("Creating order: product={}, quantity={}", product, quantity);
        return orderIntegrationService.createOrder(product, quantity);
    }

    @PostMapping("/api/orders/{id}/cancel")
    @ResponseBody
    public OrderDTO cancelOrder(@PathVariable Long id, @RequestParam String reason) {
        log.info("Cancelling order: id={}, reason={}", id, reason);
        return orderIntegrationService.cancelOrder(id, reason);
    }

    @GetMapping("/api/orders/list")
    @ResponseBody
    public List<OrderDTO> getOrders() {
        log.info("Fetching all orders");
        return orderIntegrationService.getAllOrders();
    }
}

