package tech.ada.products_api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservations")
@SecurityRequirement(name = "Bearer Authentication")
public class ReservationController {

    @DeleteMapping("/{table-number}")
    public ResponseEntity<Void> delete(@PathVariable("table-number") Integer tableNumber,
                                       @RequestHeader("date-time") LocalDateTime dateTime) {
        //TODO - Remove reservations
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
