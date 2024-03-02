package cinema;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    // Assuming you're autowiring PurchaseService
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping(value = "/purchase")
    public ResponseEntity<Object> doPurchase(@RequestBody Seat seat) {
        try {

            PurchaseSuccess purchaseSuccess = purchaseService.successfulPurchase(seat);

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("token", purchaseSuccess.getToken());
            body.put("ticket", purchaseSuccess.getTicket());

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (OutOfBoundsException e) {
            Map<String, Object> errorBody = new LinkedHashMap<>();
            errorBody.put("error", "The number of a row or a column is out of bounds!");
            return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
        } catch (SeatAlreadyPurchasedException e) {
            Map<String, Object> errorBody = new LinkedHashMap<>();
            errorBody.put("error", e.getMessage());
            return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> errorBody = new LinkedHashMap<>();
            errorBody.put("error", "An unexpected error occurred.");
            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
