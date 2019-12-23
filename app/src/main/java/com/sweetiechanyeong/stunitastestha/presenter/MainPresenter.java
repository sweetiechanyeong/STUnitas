package com.sweetiechanyeong.stunitastestha.presenter;

import android.app.Activity;

import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidatapaging.KakaoPagingAdapter;
import com.sweetiechanyeong.stunitastestha.constents.MainConstants;
import com.sweetiechanyeong.stunitastestha.model.MainModel;

public class MainPresenter implements MainConstants.Presenter {

    private Activity context;

    private MainConstants.View mainView;
    private MainModel mainModel;

    private KakaoPagingAdapter kakaoPagingAdapter;


    public MainPresenter(MainConstants.View view) {


        if (view instanceof Activity) {

            context = (Activity) view;

            // 페이징 어댑터 생성
            kakaoPagingAdapter = new KakaoPagingAdapter();

            // View 연결
            mainView = view;

            // 어댑터 설정
            mainView.setAdapter(kakaoPagingAdapter);

            // Model 연결
            mainModel = new MainModel(context, kakaoPagingAdapter, this);


        }

    }/* 생성자 */


    @Override
    public void textChanged(final String query) {

        // 검색어 설정 및 페이지 관측 시작
        mainModel.initSearch(query);

        // 키보드 감추기
        mainView.hideKeyBoard();

//        RXJava를 이용하지 않을때
//        new Thread() {
//
//            @Override
//            public void run() {
//
//                // 대기
//                try {
//                    Thread.sleep(waitSearchTime);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                // 텍스트 변화가 없을때
//                if (watcherText.equals(query)) {
//
//                    // 검색어 설정 및 페이지 관측 시작
//                    mainModel.initSearch(query);
//
//                    // 키보드 감추기
//                    mainView.hideKeyBoard();
//
//                }
//
//            }/* run() */
//        }.start();

    }/* textChanged() */

    @Override
    public void needShowProgressBar() {

        mainView.showProgressBar();

    }/* needShowProgressBar() */

    @Override
    public void needDismissProgressBar() {

        mainView.dismissProgress();

    }/* needDismissProgressBar() */

    @Override
    public void needShoeDialog(String text) {

        mainView.showDialog(text);

    }/* needShoeDialog() */


}// class
