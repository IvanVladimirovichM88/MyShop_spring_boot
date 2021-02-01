package ru.geekbrains.persists.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persists.entities.PictureRef;

import java.util.Optional;

@Repository
public interface PictureRefRepository extends JpaRepository<PictureRef, Long> {

    @Query("SELECT picRef FROM PictureRef picRef WHERE picRef.id = :picId AND picRef.picture.filename IS NOT null")
    Optional<PictureRef> findByIdWithFileName(Long picId);

    @Query("SELECT picRef FROM PictureRef picRef WHERE picRef.id = :picId AND picRef.picture.data IS NOT null")
    Optional<PictureRef> findByIdWithBlob(Long picId);

    void deleteById(Long id);

}
