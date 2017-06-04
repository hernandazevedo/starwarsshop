package br.com.devhernand.starwarsstore.transaction;

import java.util.List;

import br.com.devhernand.starwarsstore.model.TransactEntity;

/**
 * Created by Nando on 04/06/2017.
 */

public interface TransactionInteractor {

    List<TransactEntity> listAllTransactions();

}
