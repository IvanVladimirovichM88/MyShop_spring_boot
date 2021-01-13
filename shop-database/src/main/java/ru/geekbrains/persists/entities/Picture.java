package ru.geekbrains.persists.entities;

import javax.persistence.*;

@Entity
@Table(name = "picure_tbl")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long id;

    @Lob
    @Column(name = "data_fld", length = 33554430)
    private byte[] data;


    ////////////////////////////////////////////////


    public Picture() {
    }

    public Picture(byte[] data) {
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
}
