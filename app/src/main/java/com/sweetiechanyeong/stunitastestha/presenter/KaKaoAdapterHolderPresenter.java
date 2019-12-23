package com.sweetiechanyeong.stunitastestha.presenter;


import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidata.Document;

public class KaKaoAdapterHolderPresenter {


    private Document document;

    private int position;

    private View view;

    public void bind() {

        if (view != null && document != null) {

            // 이미지 찾지 못함 숨김
            view.initScreeen();

            // 이미지 설정
            view.setImage(document);

        }

    }/* bind() */

    public interface View {

        void initScreeen();

        void setImage(Document document);

    }// interface


    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}// class