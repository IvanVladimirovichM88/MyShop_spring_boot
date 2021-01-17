package ru.geekbrains.persists.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "picure_tbl")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long id;


    @Column(name = "data_fld", length = 33554430)
    private byte[] data;

    @Column(name = "filename_fld")
    private String filename;


    ////////////////////////////////////////////////


    public Picture() {
    }

    public Picture (String filename){
        this.filename = filename;
    }

    public Picture(byte[] data){
        this.data = data;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }



}
