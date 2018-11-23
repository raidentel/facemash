package com.facemash.cat.feign.model;

import com.facemash.cat.model.CatEntity;

import java.util.List;

public class CatApiModel {

    private List<CatEntity> images;

    public List<CatEntity> getImages() {
        return images;
    }

    public CatApiModel setImages(List<CatEntity> images) {
        this.images = images;
        return this;
    }
}
