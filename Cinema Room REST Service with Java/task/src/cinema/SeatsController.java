package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatsController {
    @GetMapping(value = "seats", produces = "application/json")
    public Seats getSeats(){
        return new Seats(9,9);
    }
}
