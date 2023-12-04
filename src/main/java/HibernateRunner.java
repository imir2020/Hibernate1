import TicketsDao.TicketsDao;
import entity.Tickets;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {
    public static void main(String[] args) {
        TicketsDao ticketsDao = TicketsDao.getInstance();
        Tickets ticket = Tickets.builder()
                .id(57)
                .passportNumber("344JJ5L")
                .passengerName("Anton")
                .flightId(2)
                .seatNumber("A9")
                .cost(3000)
                .build();

        Tickets updateTicket = Tickets.builder()
                .id(56)
                .passportNumber("33WWW")
                .passengerName("Anton")
                .flightId(2)
                .seatNumber("J2")
                .cost(5000)
                .build();

//        ticketsDao.saveTicket(ticket);
//        ticketsDao.updateTicket(updateTicket);
        //       ticketsDao.deleteTicket(ticket.getId());
    }
}
