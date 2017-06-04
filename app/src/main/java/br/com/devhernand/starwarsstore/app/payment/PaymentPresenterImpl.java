package br.com.devhernand.starwarsstore.app.payment;

import android.util.Log;

import java.util.List;

import br.com.devhernand.starwarsstore.BuildConfig;
import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.model.json.Transact;
import br.com.devhernand.starwarsstore.modules.networking.NetworkError;
import br.com.devhernand.starwarsstore.services.PaymentService;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nando on 04/06/2017.
 */

public class PaymentPresenterImpl implements PaymentPresenter ,PaymentService.DoPaymentCallback{

    private final PaymentInteractor interactor;
    private final PaymentView view;

    private CompositeSubscription subscriptions;

    public PaymentPresenterImpl(PaymentInteractor interactor, PaymentView view) {
        this.interactor = interactor;
        this.view = view;
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void doPayment(String cardNumber, String name, String month, String year, String cvv) {
        view.showWait();
        Transact transact = new Transact();
        transact.setCardNumber(cardNumber);
        transact.setCardHolderName(name);
        transact.setCvv(Long.valueOf(cvv));
        transact.setExpDate(getExpirationMonthYear(month,year));
        transact.setValue(getSumValue());

        interactor.doPayment(transact,this);
    }

    @Override
    public void onCreate() {
        Long sumInChart = getSumValue();
        if(sumInChart > 0) {
            view.onCreateSuccess(sumInChart.toString());
        }else{
            view.onCreateError();
        }
    }

    @Override
    public void onStop() {
        subscriptions.unsubscribe();
    }


    @Override
    public void onSendPaymentSuccess(Transact transactCompleted) {
        try {
            interactor.saveTransaction(transactCompleted);
            view.removeWait();
            interactor.clearCart();
            view.onPaymentSucess();
        }catch (Exception e){
            Log.d(BuildConfig.LOG_TAG, e.getMessage());
            view.removeWait();
        }
    }

    @Override
    public void onSendPaymentError(NetworkError networkError) {
        view.removeWait();
        view.showMessage(networkError.getMessage());
    }

    private String getExpirationMonthYear(String month, String year) {

        StringBuilder sb = new StringBuilder();
        sb.append(month).append("/").append(year);
        return  sb.toString();
    }

    private Long getSumValue()
    {
        List<Product> productList = interactor.getProductsInChart();
        Long sumValue = 0L;
        for(Product p:productList){
            sumValue += p.getPrice();
        }
        return sumValue;
    }
}
