package fit.iuh.ticketdemocqrs.command;

import fit.iuh.ticketdemocqrs.common.Ticket;
import fit.iuh.ticketdemocqrs.common.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketCommandService {
    @Autowired
    private TicketRepository ticketRepository;

    // tạo vé
    public String bookTicket (String train, String name) {
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID().toString());
        ticket.setTrainNumber(train);
        ticket.setCustomerName(name);
        ticket.setStatus("BOOKED");
        ticketRepository.save(ticket);
        return ticket.getId();
    }

    // hủy vé
    public void cancelTicket(String id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        ticket.setStatus("CANCELLED");
        ticketRepository.save(ticket);
    }

}
