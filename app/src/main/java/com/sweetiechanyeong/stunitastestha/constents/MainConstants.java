package com.sweetiechanyeong.stunitastestha.constents;

import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidatapaging.KakaoPagingAdapter;

public interface MainConstants {

    interface View {

        // 어댑터 설정
        void setAdapter(KakaoPagingAdapter kakaoPagingAdapter);

        // 키보드 숨기기
        void hideKeyBoard();

        // 프로그레스 바 보여주기
        void showProgressBar();

        // 프로그래스바 사라지게하기
        void dismissProgress();

        // 다이어로그 보여주기
        void showDialog(String text);
    }

    interface Presenter {

        // 쿼리 변경및 검색 초기화
        void textChanged(String query);

        // 프로그래스바 보여주기 요청
        void needShowProgressBar();

        // 프로그래스바 사라지기 요청
        void needDismissProgressBar();

        // 다이어로그 보여주기 요청
        void needShoeDialog(String text);



    }

}
