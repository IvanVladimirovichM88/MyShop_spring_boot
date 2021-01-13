package ru.geekbrains.persists.entities;

import javax.persistence.*;

@Entity
@Table(name = "picture_ref_tbl")
public class PictureRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_ref_id")
    private Long id;

    @Column(name = "name_fld")
    private String name;

    @Column(name = "content_type_fld")
    private String contentType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "picture_id")
    private Picture picture;

    //////////////////////////////////////////////////

    public PictureRef() {
    }

    public PictureRef(String name, String contentType, Picture picture) {
        this.name = name;
        this.contentType = contentType;
        this.picture = picture;
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

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
