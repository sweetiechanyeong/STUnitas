package com.sweetiechanyeong.stunitastestha.model.kakaoimageapidatapaging;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.sweetiechanyeong.stunitastestha.constents.MainConstants;
import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidata.Document;


public class KakaoViewModel extends AndroidViewModel {

    private DataSource.Factory dataSourceFactory ;

    public void setQuery(String query) {
        ((KakaoDataSource.Factory) dataSourceFactory).setQuery(query);

    }/* setQuery() */



    public void setPresent(MainConstants.Presenter presenter) {
        ((KakaoDataSource.Factory)dataSourceFactory).setPresenter(presenter);

    }/* setQuery() */

    private LivePagedListBuilder<Integer, Document> pagedListBuilder;
    private DataSource<Integer, Document> dataSource;

    public KakaoViewModel(@NonNull Application application) {
        super(application);
        // 페이지 리스트 설정
        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(1)
                .setPrefetchDistance(4)
                .setPageSize(1)
                .build();

        // 데이터 소스 팩토리 생성
        dataSourceFactory = new KakaoDataSource.Factory();

        // 페이지 리스트 생성
        pagedListBuilder = new LivePagedListBuilder<>(dataSourceFactory, config);


    }/* 생성자 */

    //     특정 위치(key)로 이동시에 호출
    public LiveData<PagedList<Document>> load(int key) {
        LiveData<PagedList<Document>> kakaoList = pagedListBuilder.setInitialLoadKey(key).build();
        return kakaoList;
    }/* load() */

    // 현재 DataSource가 갱신되었을 때 호출
    public void invalidate() {
        dataSource.invalidate();
    }/* invalidate() */


}