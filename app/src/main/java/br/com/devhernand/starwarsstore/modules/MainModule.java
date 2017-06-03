package br.com.devhernand.starwarsstore.modules;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.main.MainInteractor;
import br.com.devhernand.starwarsstore.main.MainInteractorImpl;
import br.com.devhernand.starwarsstore.modules.networking.NetworkModule;
import br.com.devhernand.starwarsstore.services.ProductService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Nando on 02/06/2017.
 */

@Module(includes = {ProductModule.class,})
public class MainModule {

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public MainInteractor providesMainInteractor(
            ProductService productService) {
        return new MainInteractorImpl(productService);
    }
}
