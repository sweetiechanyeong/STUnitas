package com.sweetiechanyeong.stunitastestha.model.kakaoimageapidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("collection")
    @Expose
    private String collection;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("display_sitename")
    @Expose
    private String displaySitename;
    @SerializedName("doc_url")
    @Expose
    private String docUrl;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;
    @SerializedName("width")
    @Expose
    private Integer width;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDisplaySitename() {
        return displaySitename;
    }

    public void setDisplaySitename(String displaySitename) {
        this.displaySitename = displaySitename;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}
