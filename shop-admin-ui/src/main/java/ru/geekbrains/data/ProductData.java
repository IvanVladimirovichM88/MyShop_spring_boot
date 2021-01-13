package ru.geekbrains.data;

import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductData {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private List<String> categoryTitle;

    public ProductData() {
    }

    public ProductData (Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.categoryTitle = product.getCategories().stream()
                .map(Category::getTitle).collect(Collectors.toList());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(List<String> categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
