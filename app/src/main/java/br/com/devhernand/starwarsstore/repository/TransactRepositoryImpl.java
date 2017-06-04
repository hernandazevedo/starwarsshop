package br.com.devhernand.starwarsstore.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.devhernand.starwarsstore.model.TransactEntity;

/**
 * Created by Nando on 04/06/2017.
 */

public class TransactRepositoryImpl implements TransactRepository {
    /**
     * Method that saves the transaction
     */
    public void saveTransaction(String cardNumber,String name,String value){
        TransactEntity transacao = new TransactEntity();
        transacao.setCardNumber("****-****-****-"+cardNumber.substring(cardNumber.length() - 4));
        transacao.setName(name);
        transacao.setDateTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        transacao.setValue(value);
        transacao.save();

    }

    /**
     * Method that list all of the transactions
     * @return
     */
    public List<TransactEntity> listAll(){
        Iterator<TransactEntity> transactIterator =  TransactEntity.findAll(TransactEntity.class);
        List<TransactEntity> transactList = new ArrayList<>();

        while(transactIterator.hasNext()){
            TransactEntity t = transactIterator.next();

            transactList.add(t);
        }
        return transactList;
    }
}
