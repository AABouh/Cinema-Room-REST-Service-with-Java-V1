package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class StatsController {

    private final PurchaseService purchaseService;
    private static final String SECRET_PASSWORD = "super_secret"; // Secret password, could be moved to configuration


    public StatsController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> doGetStats(@RequestParam(value = "password", required = false) String password) {
        try {
            if (!SECRET_PASSWORD.equals(password)) {
                throw new StatsPasswordException("The password is wrong!");
            }

            Stats stats = purchaseService.getStats();
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("income", stats.getIncome());
            body.put("available", stats.getAvailable());
            body.put("purchased", stats.getPurchased());

            return new ResponseEntity<>(body, HttpStatus.OK);


        } catch (StatsPasswordException ex) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", ex.getMessage());
            return new ResponseEntity<>(body,HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", e.getMessage());
            return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
