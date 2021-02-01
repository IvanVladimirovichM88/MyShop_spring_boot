package ru.geekbrains.persists.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByCategoriesIs(Category category);

}
