package ru.geekbrains.persists.entities;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Entity
@Table(name = "picure_tbl")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long id;

    @Transient
    //@Column(name = "data_fld", length = 33554430)
    private byte[] data;

    @Column(name = "path_fld")
    private String path;

    ////////////////////////////////////////////////


    public Picture() {
    }

    public Picture(byte[] data) {
        this.data = data;
        this.path = generatePicturePath();
        this.save();
    }

    public void save()  {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(this.path);
            fos.write(this.data, 0, this.data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String generatePicturePath(){
        return "c:/temp/"+ UUID.randomUUID().toString().substring(0,8);
    }
}
