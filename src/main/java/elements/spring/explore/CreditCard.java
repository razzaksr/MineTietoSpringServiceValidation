package elements.spring.explore;

import javax.validation.constraints.*;
import java.util.Date;

public class CreditCard {
    // creditCardNumber = credit_card_number
    @Digits(integer = 12, fraction = 0, message = "Invalid credit card number")
    @NotNull(message = "Credit card should be present")
    private Long creditcardNumber;

    @Digits(integer = 3, fraction = 0, message = "Invalid credit card cvv")
    private Integer creditcardCvv;

    @Future(message = "Invalid expiry date")
    private Date creditcardExpiry;

    @Digits(integer = 4, fraction = 0, message = "Invalid PIN")
    private Integer creditcardPin;
    private Integer creditcardLimit;
    private Integer creditcardUsage;
    private Integer creditcardAvailable;
    private boolean creditcardStatus;
    private String creditcardHolder;
    public CreditCard() {
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditcardNumber=" + creditcardNumber +
                ", creditcardCvv=" + creditcardCvv +
                ", creditcardExpiry=" + creditcardExpiry +
                ", creditcardPin=" + creditcardPin +
                ", creditcardLimit=" + creditcardLimit +
                ", creditcardUsage=" + creditcardUsage +
                ", creditcardAvailable=" + creditcardAvailable +
                ", creditcardStatus=" + creditcardStatus +
                ", creditcardHolder='" + creditcardHolder + '\'' +
                '}';
    }

    public CreditCard(Long creditcardNumber, Integer creditcardCvv, Date creditcardExpiry, Integer creditcardPin, Integer creditcardLimit, Integer creditcardUsage, Integer creditcardAvailable, boolean creditcardStatus, String creditcardHolder) {
        this.creditcardNumber = creditcardNumber;
        this.creditcardCvv = creditcardCvv;
        this.creditcardExpiry = creditcardExpiry;
        this.creditcardPin = creditcardPin;
        this.creditcardLimit = creditcardLimit;
        this.creditcardUsage = creditcardUsage;
        this.creditcardAvailable = creditcardAvailable;
        this.creditcardStatus = creditcardStatus;
        this.creditcardHolder = creditcardHolder;
    }

    public Long getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(Long creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public Integer getCreditcardCvv() {
        return creditcardCvv;
    }

    public void setCreditcardCvv(Integer creditcardCvv) {
        this.creditcardCvv = creditcardCvv;
    }

    public Date getCreditcardExpiry() {
        return creditcardExpiry;
    }

    public void setCreditcardExpiry(Date creditcardExpiry) {
        this.creditcardExpiry = creditcardExpiry;
    }

    public Integer getCreditcardPin() {
        return creditcardPin;
    }

    public void setCreditcardPin(Integer creditcardPin) {
        this.creditcardPin = creditcardPin;
    }

    public Integer getCreditcardLimit() {
        return creditcardLimit;
    }

    public void setCreditcardLimit(Integer creditcardLimit) {
        this.creditcardLimit = creditcardLimit;
    }

    public Integer getCreditcardUsage() {
        return creditcardUsage;
    }

    public void setCreditcardUsage(Integer creditcardUsage) {
        this.creditcardUsage = creditcardUsage;
    }

    public Integer getCreditcardAvailable() {
        return creditcardAvailable;
    }

    public void setCreditcardAvailable(Integer creditcardAvailable) {
        this.creditcardAvailable = creditcardAvailable;
    }

    public boolean isCreditcardStatus() {
        return creditcardStatus;
    }

    public void setCreditcardStatus(boolean creditcardStatus) {
        this.creditcardStatus = creditcardStatus;
    }

    public String getCreditcardHolder() {
        return creditcardHolder;
    }

    public void setCreditcardHolder(String creditcardHolder) {
        this.creditcardHolder = creditcardHolder;
    }


}