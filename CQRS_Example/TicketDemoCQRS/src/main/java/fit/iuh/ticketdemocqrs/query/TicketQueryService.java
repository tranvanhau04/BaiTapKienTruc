package fit.iuh.ticketdemocqrs.query;

import fit.iuh.ticketdemocqrs.common.Ticket;
import fit.iuh.ticketdemocqrs.common.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketQueryService {
    @Autowired
    private TicketRepository ticketRepository;

    // xem danh sách vé
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // tìm chuyến tàu
    public List<Ticket> search (String trainNumber) {
        return ticketRepository.findByTrainNumber(trainNumber);
    }
}
