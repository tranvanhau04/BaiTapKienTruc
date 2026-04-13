package fit.iuh.controller;

import fit.iuh.command.OrderCommandService;
import fit.iuh.entity.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderViewController {
    @Autowired
    private OrderRepository repository; // Demo nhanh nên gọi thẳng Repo Query
    @Autowired
    private OrderCommandService commandService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", repository.findAll());
        return "order-view";
    }

    @PostMapping("/create")
    public String create(@RequestParam String product, @RequestParam Integer quantity) {
        commandService.createOrder(product, quantity);
        return "redirect:/orders";
    }

    @PostMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id) {
        commandService.cancelOrder(id);
        return "redirect:/orders";
    }
}