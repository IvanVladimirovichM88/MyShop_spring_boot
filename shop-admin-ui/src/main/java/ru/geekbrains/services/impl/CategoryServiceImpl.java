package ru.geekbrains.services.impl;

import org.springframework.stereotype.Service;
import ru.geekbrains.data.CategoryData;
import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.repositories.CategoryRepository;
import ru.geekbrains.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public CategoryData getByIdCategoryData(Long id) {
        return new CategoryData( categoryRepository.getOne(id) );
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryData> getAllCategoriesData() {
        return categoryRepository.findAll().stream().map(CategoryData::new).collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void saveOreUpdate(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category createOrUpdateCategory(CategoryData categoryData) {
        Category category =  categoryData.getId() == null ?
                                        new Category():
                                        categoryRepository.getOne(categoryData.getId());
        category.setTitle(categoryData.getTitle());
        return categoryRepository.save(category);
    }
}
