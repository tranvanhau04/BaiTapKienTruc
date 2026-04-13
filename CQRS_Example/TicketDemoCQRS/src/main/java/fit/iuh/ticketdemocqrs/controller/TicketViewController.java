package fit.iuh.ticketdemocqrs.controller;

import fit.iuh.ticketdemocqrs.command.TicketCommandService;
import fit.iuh.ticketdemocqrs.query.TicketQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/tickets")
public class TicketViewController {

    @Autowired
    private TicketQueryService queryService;

    @Autowired
    private TicketCommandService commandService;

    // Trang chủ hiển thị danh sách vé (Query)
    @GetMapping
    public String listTickets(Model model) {
        model.addAttribute("tickets", queryService.getAllTickets());
        return "ticket-index";
    }

    // Xử lý đặt vé (Command) sau đó redirect về trang chủ
    @PostMapping("/book")
    public String bookTicket(@RequestParam String train, @RequestParam String name) {
        commandService.bookTicket(train, name);
        return "redirect:/view/tickets";
    }

    // Xử lý hủy vé (Command)
    @PostMapping("/cancel/{id}")
    public String cancelTicket(@PathVariable String id) {
        commandService.cancelTicket(id);
        return "redirect:/view/tickets";
    }
}