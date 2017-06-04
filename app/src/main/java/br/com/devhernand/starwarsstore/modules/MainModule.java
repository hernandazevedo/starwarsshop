package br.com.devhernand.starwarsstore.modules;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.app.main.MainInteractor;
import br.com.devhernand.starwarsstore.app.main.MainInteractorImpl;
import br.com.devhernand.starwarsstore.modules.services.ProductServiceModule;
import br.com.devhernand.starwarsstore.services.ProductService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Nando on 02/06/2017.
 */

@Module(includes = {ProductServiceModule.class,})
public class MainModule {

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public MainInteractor providesMainInteractor(
            ProductService productService) {
        return new MainInteractorImpl(productService);
    }
}
