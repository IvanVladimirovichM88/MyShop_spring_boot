package ru.geekbrains.service;

import ru.geekbrains.persists.entities.Picture;

import java.util.Optional;

public interface PictureService {
    Optional<String> getPictureContentTypeById(Long id);
    Optional<byte[]> getPictureById(Long id);
    Picture createPicture(byte[] picture);
    void removePictureById(Long id);
}
