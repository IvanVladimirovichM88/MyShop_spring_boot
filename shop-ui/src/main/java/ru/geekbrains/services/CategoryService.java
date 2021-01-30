package ru.geekbrains.services;

import ru.geekbrains.persists.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    Category getOneCategoryById(Long id);
}
