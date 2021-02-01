package ru.geekbrains.persists.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_tbl")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "price_fld")
    private Double price;

    @Column(name = "description_fld")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "product_category_tbl",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PictureRef> pictureRefs = new ArrayList<>();


    ////////////////////////////////////////////////////////////////////


    public Product() {
    }

    public Product(String title, Double price, Category category) {
        this.title = title;
        this.price = price;
        this.categories.add(category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PictureRef> getPictureRefs() {
        return pictureRefs;
    }

    public void setPictureRefs(List<PictureRef> pictureRefs) {
        this.pictureRefs = pictureRefs;
    }
}
