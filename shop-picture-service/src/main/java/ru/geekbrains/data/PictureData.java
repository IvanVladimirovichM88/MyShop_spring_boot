package ru.geekbrains.data;

import ru.geekbrains.persists.entities.Picture;
import ru.geekbrains.persists.entities.PictureRef;

public class PictureData {
    private Long id;
    private String name;
    private String contentType;

    public PictureData (PictureRef pictureRef){
        this.id = pictureRef.getId();
        this.name = pictureRef.getName();
        this.contentType = pictureRef.getContentType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
