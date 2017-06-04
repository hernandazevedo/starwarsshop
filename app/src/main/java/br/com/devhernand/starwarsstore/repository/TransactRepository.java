package br.com.devhernand.starwarsstore.repository;

import java.util.List;

import br.com.devhernand.starwarsstore.model.TransactEntity;

/**
 * Created by Nando on 04/06/2017.
 */

public interface TransactRepository {
    /**
     * Method that saves the transaction
     */
    void saveTransaction(String cardNumber,String name,String value);
    /**
     * Method that list all of the transactions
     * @return
     */
    List<TransactEntity> listAll();
}
