package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class RefundController {

    private final RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping(value = "/return")
    public ResponseEntity<Object> doRefund(@RequestBody Map<String, String> tokenMap) {
        try {

            UUID token = UUID.fromString(tokenMap.get("token"));

            Ticket ticket = refundService.successfulReturn(token);
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("ticket", ticket);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (WrongTokenException e) {
            Map<String, Object> errorBody = new LinkedHashMap<>();
            errorBody.put("error", "Wrong token!");
            return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            // Catch exception if UUID.fromString fails
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token format.");
        }  catch (Exception e) {
            Map<String, Object> errorBody = new LinkedHashMap<>();
            errorBody.put("error", "An unexpected error occurred.");
            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
