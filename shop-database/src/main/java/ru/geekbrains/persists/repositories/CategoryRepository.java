package ru.geekbrains.persists.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.entities.Product;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByTitle(String title);
    Integer countById(Long id);

}
