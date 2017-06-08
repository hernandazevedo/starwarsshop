package br.com.devhernand.starwarsstore.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Nando on 04/06/2017.
 * http://satyan.github.io/sugar/getting-started.html
 */
public class TransactEntity extends SugarRecord<TransactEntity> implements Serializable {


    //id automaticamente gerado pelo sugar
    private String value;
    private String dateTime; //dd/MM/yyyy HH:MM:ss
    private String cardNumber;// ############9999
    private String name;

    public TransactEntity(){
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
