package br.com.devhernand.starwarsstore.modules;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.repository.TransactRepository;
import br.com.devhernand.starwarsstore.app.transaction.TransactionInteractor;
import br.com.devhernand.starwarsstore.app.transaction.TransactionInteractorImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Nando on 02/06/2017.
 */

@Module(includes = {RepositoryModule.class,})
public class TransactionModule {

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public TransactionInteractor providesTransactionInteractor(
            TransactRepository transactRepository) {
        return new TransactionInteractorImpl(transactRepository);
    }
}
