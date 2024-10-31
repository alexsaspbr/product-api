package tech.ada.products_api.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class ReservationKey {

    private LocalDate date;
    private LocalDate time;
    private int tableNumber;

}
