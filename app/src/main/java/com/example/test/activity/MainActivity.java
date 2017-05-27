package com.example.test.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.test.injector.ActivityModule;
import com.example.test.injector.DaggerActivityComponent;
import com.example.test.models.BaseResponse;
import com.example.test.R;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.text.DecimalFormat;
import java.util.HashMap;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,IMainActivity.IView {

    EditText etTitle,etContent;
    Button releaseBtn;
    LinearLayout llPrice;
    EditText etPrice;
    CheckBox cbFree;

    @Inject
    PresenterMain presenterMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

        etTitle = (EditText) findViewById(R.id.et_news_title);
        etContent = (EditText) findViewById(R.id.et_content);
        etPrice = (EditText) findViewById(R.id.et_price);
        cbFree = (CheckBox) findViewById(R.id.cb_free);
        llPrice = (LinearLayout) findViewById(R.id.ll_price);
        findViewById(R.id.tv_newsList).setOnClickListener(this);
        releaseBtn = (Button) findViewById(R.id.btn_release_news);
        releaseBtn.setOnClickListener(this);
        cbFree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                freeReselea();
            }
        });
        writeNews();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_release_news:

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("title",etTitle.getText().toString());
                hashMap.put("content",etContent.getText().toString());
                String price = doubleUnit();
                if(!TextUtils.isEmpty(price)){
                    hashMap.put("price",price);
                }

                presenterMain.releaseNews(hashMap);
                break;
            case R.id.tv_newsList:
                jumpNewList();
                break;
        }
    }

    @Override
    public HashMap<String,String> getNewsParams(HashMap<String, String> params){
        return params;
    }

    @Override
    public void showError() {

    }

    @Override
    public void showSuccess(BaseResponse data) {

    }

    //跳转
    public void jumpNewList(){
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }


    public void writeNews(){
        Observable.combineLatest(RxTextView.textChanges(etTitle),
                RxTextView.textChanges(etContent),
                new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence username_content, CharSequence passowrd_content) {
                boolean et_name_valid = !TextUtils.isEmpty(username_content);
                boolean et_pass_valid = !TextUtils.isEmpty(passowrd_content);
                return et_name_valid && et_pass_valid;
            }
        }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable throwable) {}

            @Override
            public void onNext(Boolean validContentResult) {
                if(validContentResult){
                    releaseBtn.setEnabled(true);
                }else {
                    releaseBtn.setEnabled(false);
                }
            }
        });
    }


    public void freeReselea(){
        if(cbFree.isChecked()){
            llPrice.setVisibility(View.GONE);
        }else {
            llPrice.setVisibility(View.VISIBLE);
        }
    }

    //两个单位
    public String doubleUnit(){
        String price = etPrice.getText().toString().trim();
        if(price != null && !price.equals("")){
            try {
                Double dP = Double.parseDouble(price);
               price = new DecimalFormat("#0.00").format(dP);
            }catch (Exception e){
                e.printStackTrace();
                price = null;
            }

        }
        return price;
    }


}
