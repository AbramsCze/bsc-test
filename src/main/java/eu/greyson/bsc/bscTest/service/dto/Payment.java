package eu.greyson.bsc.bscTest.service.dto;

import eu.greyson.bsc.bscTest.exception.PaymentParseException;
import org.springframework.util.StringUtils;

/** Data transfer object for single payment. */
public class Payment {
    /** Uppercase 3 letter currency code. */
    private String currency;

    /** Payment amount. */
    private Integer amount;

    /** Create payment object from line of file. */
    public Payment(String payment) {
        if(isPaymentValid(payment)) {
            currency = payment.substring(0,2);
            amount = Integer.valueOf(payment.substring(3).trim());
        }
        else {
            throw new PaymentParseException(payment);
        }
    }

    /** @return if payment have valid structure. */
    public static boolean isPaymentValid(String payment) {
        return !StringUtils.isEmpty(payment) && payment.length() > 5;
    }

    /******************************************************************************************************************/

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%s %d", currency, amount);
    }
}
