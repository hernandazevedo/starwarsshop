package br.com.devhernand.starwarsstore.app.transaction;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.devhernand.starwarsstore.model.TransactEntity;
import br.com.devhernand.starwarsstore.model.json.Transact;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nando on 05/06/2017.
 */

public class TransactionPresenterTests {


    @Mock
    private TransactionInteractor interactor;

    @Mock
    private TransactionView view;

    private TransactionPresenterImpl presenter;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = new TransactionPresenterImpl(interactor,view);
    }

    @Test
    public void showTransactions(){
        List<TransactEntity> transactList = getTransactMock();
        when(interactor.listAllTransactions()).thenReturn(transactList);
        presenter.showTransactions();
        verify(view).onShowTransactionsSuccess(anyListOf(Transact.class));
    }

    @Test
    public void showTransactionsEmpty(){
        List<TransactEntity> transactList = new ArrayList<>();
        when(interactor.listAllTransactions()).thenReturn(transactList);
        presenter.showTransactions();
        verify(view).onTransactionsListEmpty();
    }


    @Test
    public void showTransactionsNullList(){
        List<TransactEntity> transactList = null;
        when(interactor.listAllTransactions()).thenReturn(transactList);
        presenter.showTransactions();
        verify(view).onTransactionsListEmpty();
    }


    @NonNull
    private List<TransactEntity> getTransactMock(){
        List<TransactEntity> transactList = new ArrayList<>();
        TransactEntity transact = getTransactEntity();
        transactList.add(transact);
        return transactList;
    }

    @NonNull
    private TransactEntity getTransactEntity() {
        TransactEntity transact = new TransactEntity();
        transact.setName("Name Mock");
        transact.setCardNumber("****-****-****-1452");
        transact.setValue("7000");
        transact.setDateTime("05/06/2017 15:33:00");
        return transact;
    }

}
