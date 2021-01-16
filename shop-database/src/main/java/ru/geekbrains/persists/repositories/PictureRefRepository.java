package ru.geekbrains.persists.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persists.entities.PictureRef;

public interface PictureRefRepository extends JpaRepository<PictureRef, Long> {
}
