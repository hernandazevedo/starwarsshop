package br.com.devhernand.starwarsstore.transaction;

import java.util.List;

import br.com.devhernand.starwarsstore.model.TransactEntity;
import br.com.devhernand.starwarsstore.repository.TransactRepository;

/**
 * Created by Nando on 04/06/2017.
 */

public class TransactionInteractorImpl implements TransactionInteractor {

    private TransactRepository transactRepository;
    public TransactionInteractorImpl(TransactRepository transactRepository) {
        this.transactRepository = transactRepository;
    }

    @Override
    public List<TransactEntity> listAllTransactions(){
        return  transactRepository.listAll();
    }

}
