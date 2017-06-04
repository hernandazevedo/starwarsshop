package br.com.devhernand.starwarsstore.app.transaction;

import java.util.List;

import br.com.devhernand.starwarsstore.app.base.BaseViewContract;
import br.com.devhernand.starwarsstore.model.json.Transact;

/**
 * Created by Nando on 04/06/2017.
 */

public interface TransactionView extends BaseViewContract{
    void onShowTransactionsSuccess(List<Transact> transactList);
    void onTransactionsListEmpty();
}
