package br.com.devhernand.starwarsstore.modules;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.repository.TransactRepository;
import br.com.devhernand.starwarsstore.repository.TransactRepositoryImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Nando on 04/06/2017.
 */
@Module(includes = {})
public class RepositoryModule {



    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public TransactRepository providesTransactionRepository() {
        return new TransactRepositoryImpl();
    }
}
