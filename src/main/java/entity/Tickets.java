package entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ticket",schema = "public")
public class Tickets {
    @Id
    private Integer id;
    @Column(name = "passport_no")
    private String passportNumber;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "flight_id")
    private Integer flightId;
    @Column(name = "seat_no")
    private String seatNumber;
    private Integer cost;
}
