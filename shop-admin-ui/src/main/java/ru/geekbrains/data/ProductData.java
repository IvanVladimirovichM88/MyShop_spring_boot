package ru.geekbrains.data;

import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.entities.Product;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ProductData {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private List<String> categoryTitle;
    private List<PictureData> picturesData;
    private MultipartFile[] newPictures;

    ///////////////////////////////////////////

    public ProductData() {
    }

    public ProductData (Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.categoryTitle = product.getCategories().stream()
                .map(Category::getTitle).collect(Collectors.toList());
        this.picturesData = product.getPictureRefs().stream()
                .map(PictureData::new).collect(Collectors.toList());
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

    public List<String> getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(List<String> categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<PictureData> getPicturesData() {
        return picturesData;
    }

    public void setPicturesData(List<PictureData> picturesData) {
        this.picturesData = picturesData;
    }

    public MultipartFile[] getNewPictures() {
        return newPictures;
    }

    public void setNewPictures(MultipartFile[] newPictures) {
        this.newPictures = newPictures;
    }
}
