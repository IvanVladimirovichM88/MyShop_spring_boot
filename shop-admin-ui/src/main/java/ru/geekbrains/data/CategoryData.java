package ru.geekbrains.data;


import ru.geekbrains.persists.entities.Category;

public class CategoryData {
    private Long id;
    private String title;

    public CategoryData() {
    }

    public CategoryData(Category category){
        this.id = category.getId();
        this.title = category.getTitle();
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
}
