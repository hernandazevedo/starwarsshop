package br.com.devhernand.starwarsstore.app.transaction;

import java.util.ArrayList;
import java.util.List;

import br.com.devhernand.starwarsstore.model.TransactEntity;
import br.com.devhernand.starwarsstore.model.json.Transact;

/**
 * Created by Nando on 04/06/2017.
 */

public class TransactionPresenterImpl implements TransactionPresenter {


    private final TransactionInteractor interactor;
    private final TransactionView view;


    public TransactionPresenterImpl(TransactionInteractor interactor, TransactionView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void showTransactions() {
        List<TransactEntity> transactList = interactor.listAllTransactions();
        if(transactList != null && !transactList.isEmpty()){
            List<Transact> transacts = new ArrayList<>();

            for(TransactEntity e :transactList) {
                Transact t = new Transact();
                t.setCardNumber(e.getCardNumber());

                if(e.getValue() != null)
                    t.setValue(Long.valueOf(e.getValue()));

                t.setDateTime(e.getDateTime());
                t.setCardHolderName(e.getName());
                transacts.add(t);
            }

            view.onShowTransactionsSuccess(transacts);
        }else{
            view.onTransactionsListEmpty();
        }
    }
}
