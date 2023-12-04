import entity.Tickets;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketsDaoTest {
    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;
    private static Tickets ticket;

    @BeforeAll
    static void setup() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.getTransaction();
    }

    @BeforeEach
    public void createTicket() {
        ticket = Tickets.builder()
                .id(58)
                .passengerName("Anton")
                .passportNumber("344JJ5L")
                .flightId(2)
                .seatNumber("A11")
                .cost(3600)
                .build();
    }

    @AfterAll
    static void cleanup() {
        transaction.rollback();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testSave() {
        transaction.begin();
        session.persist(ticket);
        Tickets saveTicket = session.get(Tickets.class, ticket.getId());
        assertEquals(ticket.getPassengerName(), saveTicket.getPassengerName());
        assertEquals(ticket.getId(), saveTicket.getId());
    }

    @Test
    public void testUpdate() {
        Tickets updateTicket = Tickets.builder()
                .id(ticket.getId())
                .passengerName("Iv")
                .passportNumber(ticket.getPassportNumber())
                .flightId(2)
                .seatNumber("A12")
                .cost(3800)
                .build();

        Tickets testTicket = session.merge(updateTicket);

        assertNotEquals(ticket.getCost(), testTicket.getCost());
        assertEquals(ticket.getId(), updateTicket.getId());
        assertNotEquals(ticket.getCost(), testTicket.getCost());
        assertNotEquals(ticket, testTicket);

    }

    @Test
    public void testDelete() {
         session.remove(ticket);
        Tickets deleteTicket =   session.get(Tickets.class,ticket.getId());
        assertNull(deleteTicket);
    }
}
