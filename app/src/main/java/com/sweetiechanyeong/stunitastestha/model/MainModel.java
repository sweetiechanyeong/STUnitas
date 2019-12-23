package com.sweetiechanyeong.stunitastestha.model;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidata.Document;
import com.sweetiechanyeong.stunitastestha.constents.MainConstants;
import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidatapaging.KakaoPagingAdapter;
import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidatapaging.KakaoViewModel;

public class MainModel {

    private Activity context;
    private MainConstants.Presenter presenter;
    private KakaoViewModel viewModel;
    private KakaoPagingAdapter kakaoPagingAdapter;

    public MainModel(Activity context, KakaoPagingAdapter kakaoPagingAdapter, MainConstants.Presenter presenter) {

        this.context = context;
        this.presenter = presenter;
        this.kakaoPagingAdapter = kakaoPagingAdapter;

        // 페이지 리스트 생성
        viewModel = ViewModelProviders.of((FragmentActivity) context).get(KakaoViewModel.class);

        // presenter 인터페이스 전달
        viewModel.setPresent(presenter);



    }/* 생성자 */


    public void initSearch(String query) {

        // 검색어 설정
        viewModel.setQuery(query);

        // 변경 감지
        viewModel.load(1).observe((LifecycleOwner) context, new Observer<PagedList<Document>>() {
            @Override
            public void onChanged(@Nullable PagedList<Document> documentList) {
                kakaoPagingAdapter.submitList(documentList);
            }/* onChanged() */
        });


    }/* requestServer() */

}
