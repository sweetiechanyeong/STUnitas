package com.sweetiechanyeong.stunitastestha.model.kakaoimageapidatapaging;


import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sweetiechanyeong.stunitastestha.constents.MainConstants;
import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidata.Document;
import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidata.Example;
import com.sweetiechanyeong.stunitastestha.api.RetroCallback;
import com.sweetiechanyeong.stunitastestha.api.RetroAPI;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KakaoDataSource extends PageKeyedDataSource<Integer, Document> {

    private String query = "";
    private MainConstants.Presenter presenter;


    // 팩토리 클래스
    public static class Factory extends DataSource.Factory<Integer, Document> {

        private String query = "";
        MainConstants.Presenter presenter;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }


        public MainConstants.Presenter getPresenter() {
            return presenter;
        }

        public void setPresenter(MainConstants.Presenter presenter) {
            this.presenter = presenter;
        }


        @Override
        public android.arch.paging.DataSource<Integer, Document> create() {

            KakaoDataSource kakaoDataSource = new KakaoDataSource();
            kakaoDataSource.setQuery(query);
            kakaoDataSource.setPresenter(presenter);

            return kakaoDataSource;
        }/* create() */
    }// class


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Document> callback) {
        Log.d("로그", "DataSource oadInitial");

        final int curPage = 1;
        final int nextPage = curPage + 1;

        requestServer(query, curPage, new RetroCallback() {
            @Override
            public void onSuccess(List<Document> documentList) {
                Objects.requireNonNull(callback).onResult(documentList, 0, nextPage);
            }/* onSuccess() */
        });


    }/* loadInitial() */

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Document> callback) {

    }/* loadBefore() */

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Document> callback) {

        Log.d("로그", "DataSource loadAfter");


        int curPage = params.key;
        final int nextPage = curPage + 1;

        requestServer(query, params.key, new RetroCallback() {
            @Override
            public void onSuccess(List<Document> documentList) {

                callback.onResult(documentList, nextPage);

            }/* onSuccess() */
        });

    }/* loadAfter() */


    private void requestServer(final String query, final int preKey, final RetroCallback retroCallback) {

        // 프로그래스바 보여주기
        presenter.needShowProgressBar();


        // retrofit 객체 생성 및 BaseURL 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dapi.kakao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 인터페이스 객체 생성
        RetroAPI service = retrofit.create(RetroAPI.class);


        Log.d("로그", "요청 받은 페이지 : " + preKey);
        // get 데이터를 담은 서비스 인터페이스 객체 전달 받기
        Call<Example> call = service.getData(query, preKey);

        // 서버로 요청 보내기
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {


                // 프로그레스 바 사라지게하기
                presenter.needDismissProgressBar();

                Log.d("로그", "API 호출완료" + response.body());

                // 응답 값이 있을경우
                if (response.body() != null) {

                    List<Document> documentList = response.body().getDocuments();
                    Log.d("로그", "응답 데이터 있음 : " + documentList.size());

                    // 검색 결과가 있을떄
                    if (documentList.size() > 0) {


                        retroCallback.onSuccess(documentList);


                    } else {

                        presenter.needShoeDialog("검색 결과가 없습니다.");

                    }// else


                } else {

                    if (!query.equals("")) {
                        presenter.needShoeDialog("서버에서 받아온 데이터가 없습니다.");
                    }


                }// else

            }/* onResponse() */

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                // 프로그레스 바 사라지게하기
                presenter.needDismissProgressBar();

                presenter.needShoeDialog("오류가 발생 하였습니다.\n내용: " + t.toString());


            }/* onFailure() */
        });


    }/* requestServer() */


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }


    public MainConstants.Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MainConstants.Presenter presenter) {
        this.presenter = presenter;
    }


}// class
