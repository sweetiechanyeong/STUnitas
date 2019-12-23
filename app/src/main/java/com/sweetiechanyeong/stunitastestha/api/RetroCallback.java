package com.sweetiechanyeong.stunitastestha.api;

import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidata.Document;

import java.util.List;

public interface RetroCallback {

    void onSuccess(List<Document> documentList);

}