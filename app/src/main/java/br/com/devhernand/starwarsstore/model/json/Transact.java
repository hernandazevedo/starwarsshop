package br.com.devhernand.starwarsstore.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

/**
 * Created by Nando on 28/05/2017.
 */
@Generated("org.jsonschema2pojo")
public class Transact implements Serializable {

    @Expose
    @SerializedName("value")
    private Long value;

    @Expose
    @SerializedName("cvv")
    private Long cvv;

    @Expose
    @SerializedName("card_number")
    private String cardNumber;// ############9999

    @Expose
    @SerializedName("card_holder_name")
    private String cardHolderName;

    @Expose
    @SerializedName("exp_date")
    private String expDate;

    private transient String dateTime;

    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getCvv() {
        return cvv;
    }

    public void setCvv(Long cvv) {
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
