package br.com.devhernand.starwarsstore.services;

import br.com.devhernand.starwarsstore.model.json.Transact;
import br.com.devhernand.starwarsstore.modules.networking.NetworkError;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Nando on 31/05/2017.
 */

public class PaymentService {


    private final PaymentEndpoints endpoints;

    public PaymentService(PaymentEndpoints endpoints) {
        this.endpoints = endpoints;
    }

    public Subscription doPayment(final Transact transactToSend, final DoPaymentCallback callback) {

        return endpoints.doPayment(transactToSend)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Transact>>() {
                    @Override
                    public Observable<? extends Transact> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Transact>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSendPaymentError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(Transact response) {
                        //FIXME here its being mocked by Apiary
                        callback.onSendPaymentSuccess(transactToSend);
                    }
                });
    }


    public interface DoPaymentCallback{
        void onSendPaymentSuccess(Transact transactCompleted);

        void onSendPaymentError(NetworkError networkError);
    }


}
