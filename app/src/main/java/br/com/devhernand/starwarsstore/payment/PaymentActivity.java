package br.com.devhernand.starwarsstore.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import javax.inject.Inject;

import br.com.devhernand.starwarsstore.BaseActivity;
import br.com.devhernand.starwarsstore.R;

public class PaymentActivity extends BaseActivity implements PaymentView {

    /* Fields */
    @NotEmpty(messageResId = R.string.msg_required_field)
    private EditText etCardNumber,etName,etMonth,etYear,etCvv;
    private TextView totalShopping;
    private AppCompatButton btnPay;

    @Inject
    public PaymentInteractor interactor;
    private PaymentPresenter presenter;

    private Validator validator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        renderView();
        init();

        presenter = new PaymentPresenterImpl(interactor, this);
    }

    public void init(){
        initToolbar(getString(R.string.payment_data));
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        presenter.doPayment(etCardNumber.getText().toString(),
                            etName.getText().toString(),
                            etMonth.getText().toString(),
                            etYear.getText().toString(),
                            etCvv.getText().toString());
    }

    public void renderView() {
        setContentView(R.layout.activity_payment);
        super.initCommonComponents();
        btnPay = (AppCompatButton) findViewById(R.id.btnPay);
        //etCardNumber,etName,etMonth,etYear,etCvv
        etCardNumber = (EditText) findViewById(R.id.etCardNumber);
        etName = (EditText) findViewById(R.id.etName);
        etMonth = (EditText) findViewById(R.id.etMonth);
        etYear = (EditText) findViewById(R.id.etYear);
        etCvv = (EditText) findViewById(R.id.etCvv);
        totalShopping = (TextView) findViewById(R.id.totalShopping);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, PaymentActivity.class);
        ActivityCompat.startActivity(activity,intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void onPaymentSucess() {
        showToast(getString(R.string.msg_payment_sucess));
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
