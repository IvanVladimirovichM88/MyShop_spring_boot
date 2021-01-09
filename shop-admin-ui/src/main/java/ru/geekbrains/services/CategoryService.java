package ru.geekbrains.services;

import ru.geekbrains.data.CategoryData;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.entities.Product;

import java.util.List;

public interface CategoryService {

    Category getById(Long id);
    CategoryData getByIdCategoryData(Long id);
    List<Category> getAllCategories();
    List<CategoryData> getAllCategoriesData();
    void remove(Long id);
    void saveOreUpdate(Category category);
    Category createOrUpdateCategory(CategoryData categoryData);
}
