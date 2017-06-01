package br.com.devhernand.starwarsstore.deps;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.modules.ProductModule;
import br.com.devhernand.starwarsstore.modules.networking.NetworkModule;
import br.com.devhernand.starwarsstore.main.MainActivity;
import dagger.Component;
import dagger.Module;

/**
 * Created by Nando on 31/05/2017.
 */

@Singleton
@Component(modules = {NetworkModule.class, ProductModule.class})
@Module
public interface Deps {
    void inject(MainActivity mainActivity);
}
