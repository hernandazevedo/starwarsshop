package br.com.devhernand.starwarsstore.app.transaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.devhernand.starwarsstore.app.main.MainInteractorImpl;
import br.com.devhernand.starwarsstore.model.TransactEntity;
import br.com.devhernand.starwarsstore.model.json.Transact;
import br.com.devhernand.starwarsstore.repository.TransactRepository;
import br.com.devhernand.starwarsstore.services.ProductService;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nando on 05/06/2017.
 */

public class TransactionInteractorTests {


    @Mock
    private TransactRepository transactRepository;

    private TransactionInteractorImpl interactor;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        interactor = new TransactionInteractorImpl(transactRepository);
    }

    @Test
    public void testListAllTransactions(){
        List<TransactEntity> transactList = getTransactMock();

        when(transactRepository.listAll()).thenReturn(transactList);
        List<TransactEntity> listResult =  interactor.listAllTransactions();
        verify(transactRepository).listAll();
        Assert.assertNotNull(listResult);
        Assert.assertFalse(listResult.isEmpty());
    }

    private List<TransactEntity> getTransactMock(){
        List<TransactEntity> transactList = new ArrayList<>();
        TransactEntity transact = new TransactEntity();
        transact.setName("Name Mock");
        transact.setCardNumber("****-****-****-1452");
        transact.setValue("7000");
        transact.setDateTime("05/06/2017 15:33:00");
        transactList.add(transact);
        return transactList;
    }
}
