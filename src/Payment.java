import java.sql.Date;

public class Payment {
    private int paymentID;
    private String cardName;
    private String cardNumber;
    private String CVV;
    private Date expDate;

    public Payment(int paymentID, String cardName, String cardNumber, String CVV, Date expDate) {
        this.paymentID = paymentID;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.expDate = expDate;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", cardName='" + cardName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", CVV='" + CVV + '\'' +
                ", expDate=" + expDate +
                '}';
    }
}


