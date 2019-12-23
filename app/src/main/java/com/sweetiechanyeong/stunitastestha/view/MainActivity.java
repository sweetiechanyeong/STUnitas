package com.sweetiechanyeong.stunitastestha.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;
import com.sweetiechanyeong.stunitastestha.R;
import com.sweetiechanyeong.stunitastestha.constents.MainConstants;
import com.sweetiechanyeong.stunitastestha.databinding.ActivityMainBinding;
import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidatapaging.KakaoPagingAdapter;
import com.sweetiechanyeong.stunitastestha.presenter.MainPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity implements MainConstants.View {


    @Inject
    MainConstants.Presenter mainPresenter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);


        // 레이아웃 매니저 설정
        binding.rvSearchImageList.setLayoutManager(new LinearLayoutManager(this));


        // Presenter 생성
        mainPresenter = new MainPresenter(this);


//        MainComponent component = DaggerMainComponent.builder()
//                .mainPresenterModule(new MainPresenterModule())
//                .build();
//
//        component.inject(this);

        // RxJAva2 Java8 람다 적용 X
        RxTextView.textChangeEvents(binding.etUser)
                .debounce(1, TimeUnit.SECONDS)
                .filter(new Predicate<TextViewTextChangeEvent>() {
                    @Override
                    public boolean test(TextViewTextChangeEvent event) throws Exception {
                        return (String.valueOf(event.getText())) != null;
                    }/* test() */
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextViewTextChangeEvent>() {


                    @Override
                    public void onError(Throwable e) {
                        showDialog("검색어 입력 처리중 오류 발생");
                    }/* onError() */

                    @Override
                    public void onComplete() {
                        Log.d("로그", "onComplete()");
                    }/* onComplete() */


                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("로그", "onSubscribe()");
                    }/* onSubscribe() */

                    @Override
                    public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                        mainPresenter.textChanged(textViewTextChangeEvent.getText().toString());
                    }/* onNext() */
                });


    }/* onCreate() */


    @Override
    public void setAdapter(KakaoPagingAdapter kakaoPagingAdapter) {

        binding.rvSearchImageList.setAdapter(kakaoPagingAdapter);

    }/* setAdapter() */


    @Override
    public void hideKeyBoard() {
        View view = getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }/* hideKeyBoard() */


    private ProgressDialog progress;


    @Override
    public void showProgressBar() {


        // UI 작업은 메인 쓰레드로 실행
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (progress == null) {
                    progress = new ProgressDialog(MainActivity.this);
                    progress.setMessage("search Image :) ");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(true);
                }

                if (progress != null) {
                    progress.show();
                }

            }/* run() */
        });


    }/* showProgressBar() */


    @Override
    public void dismissProgress() {


        // UI 작업은 메인 쓰레드로 실행
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (progress != null) {
                    progress.dismiss();


                }

            }/* run() */
        });


    }/* dismissProgress() */

    // 알림창 띄우기
    @Override
    public void showDialog(String text) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("알림");

        alertDialogBuilder
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }/* showDialog() */

}// class
