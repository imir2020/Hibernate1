package TicketsDao;

import entity.Tickets;
import org.hibernate.cfg.Configuration;

public class TicketsDao {
    private static final TicketsDao INSTANCE = new TicketsDao();
  private final Configuration configuration = new Configuration();

  public static TicketsDao getInstance(){
      return INSTANCE;
  }

    public void saveTicket(Tickets ticket) {
        configuration.configure();
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(ticket);
            session.getTransaction().commit();
        }
    }

    public void updateTicket(Tickets ticket) {
        configuration.configure();
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            Tickets entityFromBase = session.get(Tickets.class,ticket.getId());

            entityFromBase.setPassportNumber(ticket.getPassportNumber());
            entityFromBase.setPassengerName(ticket.getPassengerName());
            entityFromBase.setFlightId(ticket.getFlightId());
            entityFromBase.setSeatNumber(ticket.getSeatNumber());
            entityFromBase.setCost(ticket.getCost());

            session.merge(entityFromBase);
            session.getTransaction().commit();
        }
    }

    public void deleteTicket(Integer id){
        configuration.configure();
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            Tickets entity = session.get(Tickets.class, id);
            if (entity != null) {
                session.remove(entity);
            }
            session.getTransaction().commit();
        }
    }
}
