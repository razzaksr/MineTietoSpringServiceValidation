package elements.spring.explore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

@RestController
@RequestMapping("/credit")
public class MyBankController {
    @Autowired
    MyBankService myBankService;
    @Autowired
    private JavaMailSender emailSender;

//    @GetMapping("/")
//    public void sendSimpleMessage() {
//        String to="razzaksr@gmail.com", subject="Test mail", text="Testing my spring mail";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
//    }

    Logger logger= LoggerFactory.getLogger(MyBankController.class);

    @DeleteMapping("/block/{number}/{pin}")
    public String suspend(@PathVariable("number") Long number,@PathVariable("pin") Integer pin){
        try{
            return myBankService.apiBlock(number,pin);
        }
        catch (CardException cardException){
            logger.warn(cardException.toString());
            return cardException.toString();
        }
    }

    @PutMapping("/swipe/{purchase}")
    public CreditCard swipe(@PathVariable("purchase") Integer purchase, @RequestBody CreditCard creditCard){
        CreditCard creditCard1=new CreditCard();
        try{
            creditCard1=myBankService.apiUpdate(creditCard,purchase);
        }
        catch (CardException cardException){
            logger.warn(cardException.toString());
        }
        return creditCard1;
    }

    @PostMapping("/new")
    public ResponseEntity<Object> saved(@Valid @RequestBody CreditCard creditCard){
        CreditCard creditCard1=null;
        try{
            creditCard1 = myBankService.apiSave(creditCard);
        }
        catch (CardException cardException){
            logger.warn(cardException.toString());
        }
        return ResponseEntity.ok(creditCard1);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/limit/{amount}")
    public List<CreditCard> gettingLimit(@PathVariable("amount") Integer amount){
        return myBankService.apiByLimit(amount);
    }

    @GetMapping("/one/{number}")
    public Optional<CreditCard> gettingOne(@PathVariable("number") Long number){
        return myBankService.apiFindById(number);
    }

    @GetMapping("/view")
    public List<CreditCard> fetchAll(){
        List<CreditCard> myList=new ArrayList<>();
        try {
            myList = myBankService.apiFindAll();
        } catch (SQLSyntaxErrorException e) {
//            return ResponseEntity.internalServerError().body(new CardException(" Due to system failure"));
            logger.error(String.valueOf(new CardException("Due to System failure")));
        }
        catch (CardException cardException){
            logger.error(String.valueOf(new CardException()));
        }
        return myList;
//        try {
//            return ResponseEntity.ok(myBankService.apiFindAll());
//        } catch (SQLSyntaxErrorException e) {
////            return ResponseEntity.internalServerError().body(new CardException(" Due to system failure"));
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CardException(" Due to system failure"));
//        }
//        catch (CardException cardException){
//            return ResponseEntity.badRequest().body(new CardException(" Due to system failure"));
//        }
    }
}
