package tech.ada.products_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Reservation {

    @EmbeddedId
    private ReservationKey reservationKey;
    private String customerName;

}
