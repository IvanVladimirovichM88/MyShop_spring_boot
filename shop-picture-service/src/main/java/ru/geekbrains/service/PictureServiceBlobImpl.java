package ru.geekbrains.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.geekbrains.persists.entities.Picture;
import ru.geekbrains.persists.entities.PictureRef;
import ru.geekbrains.persists.repositories.PictureRefRepository;

import java.util.Optional;

@Service
@ConditionalOnProperty(value = "picture.storage.type", havingValue = "database")
public class PictureServiceBlobImpl implements PictureService{

    private PictureRefRepository pictureRefRepository;

    public PictureServiceBlobImpl(PictureRefRepository pictureRefRepository) {
        this.pictureRefRepository = pictureRefRepository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(Long id) {
        return pictureRefRepository.findById(id)
                .map(PictureRef::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureById(Long id) {
        return pictureRefRepository.findByIdWithBlob(id)
                .map(pictureRef -> pictureRef.getPicture().getData());
    }

    @Override
    public Picture createPicture(byte[] picture) {
        return new Picture(picture);
    }

    @Override
    public void removePictureById(Long id) {
        pictureRefRepository.deleteById(id);
    }
}
