package ru.geekbrains.persists.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.persists.entities.PictureRef;

import java.util.Optional;

public interface PictureRefRepository extends JpaRepository<PictureRef, Long> {

    @Query("SELECT picRef FROM PictureRef picRef WHERE picRef.id = :picId AND picRef.picture.filename IS NOT null")
    Optional<PictureRef> findByIdWithFileName(Long picId);
}
